package net.kurobako.cef4j.policy;

import static com.google.errorprone.matchers.Description.NO_MATCH;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.BugPattern.SeverityLevel;
import com.google.errorprone.ErrorProneFlags;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.AnnotationTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

/**
 * Bans {@code javax.annotation.Nullable} from public/protected API. The policy is that absence is modelled with
 * {@code Optional<T>}, so a null-able public signature is either a design bug or a member that should be
 * package-private. Private and package-private members may keep null-able signatures.
 *
 * <p>Packages still under migration are exempted via {@code -XepOpt:NullableForbidden:AllowedPackages} (comma-separated
 * exact package names); the list shrinks as each package is cleaned up.
 */
@AutoService(BugChecker.class)
@BugPattern(
        name = "NullableForbidden",
        summary = "@Nullable must not appear on public/protected API; model absence with Optional<T>",
        severity = SeverityLevel.ERROR)
public final class NullableForbiddenCheck extends BugChecker implements AnnotationTreeMatcher {
    private static final String NULLABLE = "javax.annotation.Nullable";
    private static final String NULL_UNMARKED = "com.uber.nullaway.annotations.NullUnmarked";
    private static final String ALLOWED_PACKAGES = "NullableForbidden:AllowedPackages";

    private final Set<String> allowedPackages = new HashSet<>();

    public NullableForbiddenCheck() {
        this(ErrorProneFlags.empty());
    }

    public NullableForbiddenCheck(ErrorProneFlags flags) {
        allowedPackages.addAll(flags.getSetOrEmpty(ALLOWED_PACKAGES));
    }

    @Override
    public Description matchAnnotation(AnnotationTree tree, VisitorState state) {
        if (!isNullable(tree)) {
            return NO_MATCH;
        }
        Tree member = enclosingMember(state.getPath());
        if (member == null || !isPublicOrProtected(member)) {
            return NO_MATCH;
        }
        Element symbol = ASTHelpers.getSymbol(member);
        if (symbol == null || inAnonymousOrLocal(symbol) || isNullUnmarked(symbol)) {
            return NO_MATCH;
        }
        if (allowedPackages.contains(packageOf(symbol))) {
            return NO_MATCH;
        }
        if (isInheritedNullable(tree, member, symbol, state)) {
            return NO_MATCH;
        }
        return buildDescription(tree)
                .setMessage("@Nullable is banned on public/protected API; use Optional<T> for absence")
                .build();
    }

    private static boolean isNullable(AnnotationTree tree) {
        Element symbol = ASTHelpers.getSymbol(tree);
        return symbol instanceof TypeElement && NULLABLE.contentEquals(((TypeElement) symbol).getQualifiedName());
    }

    private static @Nullable Tree enclosingMember(TreePath path) {
        TreePath parent = path.getParentPath();
        while (parent != null) {
            Tree leaf = parent.getLeaf();
            if (leaf instanceof MethodTree || leaf instanceof ClassTree) {
                return leaf;
            }
            if (leaf instanceof VariableTree) {
                TreePath up = parent.getParentPath();
                if (up == null) {
                    return null;
                }
                if (up.getLeaf() instanceof ClassTree) {
                    return leaf;
                }
                if (up.getLeaf() instanceof MethodTree) {
                    return up.getLeaf();
                }
                return null;
            }
            parent = parent.getParentPath();
        }
        return null;
    }

    private static boolean isPublicOrProtected(Tree member) {
        Element symbol = ASTHelpers.getSymbol(member);
        if (symbol == null) {
            return false;
        }
        Set<Modifier> modifiers = symbol.getModifiers();
        return modifiers.contains(Modifier.PUBLIC) || modifiers.contains(Modifier.PROTECTED);
    }

    private static boolean inAnonymousOrLocal(Element symbol) {
        Element enclosing = symbol.getEnclosingElement();
        while (enclosing != null) {
            if (enclosing instanceof TypeElement) {
                NestingKind nesting = ((TypeElement) enclosing).getNestingKind();
                if (nesting == NestingKind.ANONYMOUS || nesting == NestingKind.LOCAL) {
                    return true;
                }
            }
            enclosing = enclosing.getEnclosingElement();
        }
        return false;
    }

    private static boolean isNullUnmarked(Element symbol) {
        Element current = symbol;
        while (current != null) {
            for (AnnotationMirror annotation : current.getAnnotationMirrors()) {
                if (NULL_UNMARKED.contentEquals(annotation.getAnnotationType().toString())) {
                    return true;
                }
            }
            current = current.getEnclosingElement();
        }
        return false;
    }

    private static String packageOf(Element symbol) {
        Element current = symbol;
        while (current != null && current.getKind() != ElementKind.PACKAGE) {
            current = current.getEnclosingElement();
        }
        return current == null ? "" : current.toString();
    }

    // @Nullable on an override is required when the overridden declaration is itself @Nullable
    private static boolean isInheritedNullable(AnnotationTree tree, Tree member, Element symbol, VisitorState state) {
        if (symbol.getKind() != ElementKind.METHOD) {
            return false;
        }
        Tree annotated = null;
        TreePath up = state.getPath().getParentPath();
        while (up != null) {
            if (up.getLeaf() instanceof VariableTree
                    || up.getLeaf() instanceof MethodTree
                    || up.getLeaf() instanceof ClassTree) {
                annotated = up.getLeaf();
                break;
            }
            up = up.getParentPath();
        }
        if (annotated instanceof VariableTree) {
            Element param = ASTHelpers.getSymbol(annotated);
            if (param == null || param.getKind() != ElementKind.PARAMETER) {
                return false;
            }
            ExecutableElement method = Objects.requireNonNull((ExecutableElement) param.getEnclosingElement());
            int index = method.getParameters().indexOf((VariableElement) param);
            return overridesNullable(
                    method, Objects.requireNonNull((TypeElement) method.getEnclosingElement()), index, state);
        }
        if (member instanceof MethodTree) {
            return overridesNullable(
                    (ExecutableElement) symbol,
                    Objects.requireNonNull((TypeElement) symbol.getEnclosingElement()),
                    -1,
                    state);
        }
        return false;
    }

    private static boolean overridesNullable(
            ExecutableElement method, TypeElement enclosing, int paramIndex, VisitorState state) {
        Deque<TypeMirror> worklist = new ArrayDeque<>();
        pushSupertypes(enclosing, worklist);
        Set<TypeElement> visited = new HashSet<>();
        while (!worklist.isEmpty()) {
            TypeMirror type = worklist.poll();
            TypeElement superType = typeSymbol(type);
            if (superType == null || !visited.add(superType)) {
                continue;
            }
            for (Element candidate : superType.getEnclosedElements()) {
                if (candidate.getKind() != ElementKind.METHOD
                        || !candidate.getSimpleName().contentEquals(method.getSimpleName())
                        || ((ExecutableElement) candidate).getParameters().size()
                                != method.getParameters().size()) {
                    continue;
                }
                if (paramIndex < 0) {
                    if (isNullableElement(candidate)) return true;
                } else {
                    List<? extends VariableElement> params = ((ExecutableElement) candidate).getParameters();
                    if (paramIndex < params.size() && isNullableElement(params.get(paramIndex))) return true;
                }
            }
            pushSupertypes(superType, worklist);
        }
        return false;
    }

    private static @Nullable TypeElement typeSymbol(TypeMirror type) {
        if (!(type instanceof com.sun.tools.javac.code.Type)) {
            return null;
        }
        return (TypeElement) ((com.sun.tools.javac.code.Type) type).tsym;
    }

    private static void pushSupertypes(TypeElement type, Deque<TypeMirror> worklist) {
        if (type.getSuperclass() != null && type.getSuperclass().getKind() != TypeKind.NONE) {
            worklist.push(type.getSuperclass());
        }
        worklist.addAll(type.getInterfaces());
    }

    private static boolean isNullableElement(Element element) {
        for (AnnotationMirror annotation : element.getAnnotationMirrors()) {
            if (NULLABLE.contentEquals(annotation.getAnnotationType().toString())) {
                return true;
            }
        }
        return false;
    }
}
