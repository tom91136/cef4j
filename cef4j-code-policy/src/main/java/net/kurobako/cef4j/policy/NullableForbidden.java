package net.kurobako.cef4j.policy;

import static com.google.errorprone.matchers.Description.NO_MATCH;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.BugPattern;
import com.google.errorprone.BugPattern.SeverityLevel;
import com.google.errorprone.ErrorProneFlags;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.AnnotationTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.NewArrayTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import com.sun.tools.javac.code.Symbol.MethodSymbol;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import org.jspecify.annotations.Nullable;

/**
 * Bans JSpecify's {@code @Nullable} from public/protected API. The policy is that absence is modelled with
 * {@code Optional<T>}, so a null-able public signature is either a design bug or a member that should be
 * package-private. Private and package-private members may keep null-able signatures.
 * {@code @SuppressWarnings("NullAway")} on public/protected API is treated the same way, since it hides a nullable
 * contract behind a non-null signature.
 *
 * <p>Packages still under migration are exempted via {@code -XepOpt:NullableForbidden:AllowedPackages} (comma-separated
 * exact package names); the list shrinks as each package is cleaned up.
 */
@AutoService(BugChecker.class)
@BugPattern(
        summary = "@Nullable must not appear on public/protected API; model absence with Optional<T>",
        severity = SeverityLevel.ERROR)
public final class NullableForbidden extends BugChecker implements AnnotationTreeMatcher {
    private static final long serialVersionUID = 1L;

    private static final String NULLABLE = "org.jspecify.annotations.Nullable";
    private static final String NULL_UNMARKED = "com.uber.nullaway.annotations.NullUnmarked";
    private static final String NULLABLE_BOUNDARY = "net.kurobako.cef4j.policy.NullableBoundary";
    private static final String SUPPRESS_WARNINGS = "java.lang.SuppressWarnings";
    private static final String NULL_AWAY = "NullAway";
    private static final String ALLOWED_PACKAGES = "NullableForbidden:AllowedPackages";

    private final ImmutableSet<String> allowedPackages;

    public NullableForbidden() {
        this(ErrorProneFlags.empty());
    }

    @Inject
    public NullableForbidden(ErrorProneFlags flags) {
        this.allowedPackages = ImmutableSet.copyOf(flags.getSetOrEmpty(ALLOWED_PACKAGES));
    }

    @Override
    public Description matchAnnotation(AnnotationTree tree, VisitorState state) {
        boolean suppression = isNullAwaySuppression(tree);
        if (!suppression && !isNullable(tree)) {
            return NO_MATCH;
        }
        Tree member = enclosingMember(state.getPath());
        if (member == null || !isPublicOrProtected(member)) {
            return NO_MATCH;
        }
        Element symbol = ASTHelpers.getSymbol(member);
        if (symbol == null || inAnonymousOrLocal(symbol) || isNullUnmarked(symbol) || isNullableBoundary(symbol)) {
            return NO_MATCH;
        }
        if (allowedPackages.contains(packageOf(symbol))) {
            return NO_MATCH;
        }
        if (suppression) {
            return buildDescription(tree)
                    .setMessage("@SuppressWarnings(\"NullAway\") on public/protected API hides a nullable contract; "
                            + "declare it with @NullableBoundary or use Optional<T>")
                    .build();
        }
        if (isInheritedNullable(member, symbol, state) || isEqualsParameter(member, state)) {
            return NO_MATCH;
        }
        return buildDescription(tree)
                .setMessage("@Nullable is banned on public/protected API; use Optional<T> for absence")
                .build();
    }

    private static boolean isNullAwaySuppression(AnnotationTree tree) {
        Element symbol = ASTHelpers.getSymbol(tree);
        if (!(symbol instanceof TypeElement typeElement)
                || !SUPPRESS_WARNINGS.contentEquals(typeElement.getQualifiedName())) {
            return false;
        }
        for (ExpressionTree argument : tree.getArguments()) {
            ExpressionTree value =
                    argument instanceof AssignmentTree assignment ? assignment.getExpression() : argument;
            List<? extends ExpressionTree> values =
                    value instanceof NewArrayTree array ? array.getInitializers() : List.of(value);
            for (ExpressionTree item : values) {
                if (item instanceof LiteralTree literal && NULL_AWAY.equals(literal.getValue())) return true;
            }
        }
        return false;
    }

    // equals(Object) must accept null by contract, whatever the overridden declaration says
    private static boolean isEqualsParameter(Tree member, VisitorState state) {
        if (!(member instanceof MethodTree method)) return false;
        TreePath up = state.getPath().getParentPath();
        while (up != null && !(up.getLeaf() instanceof VariableTree) && up.getLeaf() != member) {
            up = up.getParentPath();
        }
        if (up == null || up.getLeaf() == member) return false;
        return method.getName().contentEquals("equals")
                && method.getParameters().size() == 1
                && method.getParameters().get(0) == up.getLeaf()
                && ASTHelpers.isSameType(ASTHelpers.getType(up.getLeaf()), state.getSymtab().objectType, state);
    }

    private static boolean isNullable(AnnotationTree tree) {
        Element symbol = ASTHelpers.getSymbol(tree);
        return symbol instanceof TypeElement typeElement && NULLABLE.contentEquals(typeElement.getQualifiedName());
    }

    private static @Nullable Tree enclosingMember(TreePath path) {
        TreePath parent = path.getParentPath();
        while (parent != null) {
            Tree leaf = parent.getLeaf();
            if (leaf instanceof BlockTree || leaf instanceof LambdaExpressionTree) {
                return null;
            }
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

    private static boolean isNullableBoundary(Element symbol) {
        Element current = symbol;
        while (current != null) {
            for (AnnotationMirror annotation : current.getAnnotationMirrors()) {
                if (NULLABLE_BOUNDARY.contentEquals(
                        annotation.getAnnotationType().toString())) {
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
    private static boolean isInheritedNullable(Tree member, Element symbol, VisitorState state) {
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
            return overridesNullable(method, index, state);
        }
        if (member instanceof MethodTree) {
            return overridesNullable((ExecutableElement) symbol, -1, state);
        }
        return false;
    }

    private static boolean overridesNullable(ExecutableElement method, int paramIndex, VisitorState state) {
        if (!(method instanceof MethodSymbol methodSymbol)) return false;
        for (MethodSymbol candidate : ASTHelpers.findSuperMethods(methodSymbol, state.getTypes())) {
            if (paramIndex < 0) {
                if (isNullableElement(candidate)) return true;
            } else {
                List<? extends VariableElement> params = candidate.getParameters();
                if (paramIndex < params.size() && isNullableElement(params.get(paramIndex))) return true;
            }
        }
        return false;
    }

    // Type-use @Nullable attaches to the return or parameter type rather than the declaration.
    private static boolean isNullableElement(Element element) {
        TypeMirror type =
                element instanceof ExecutableElement executable ? executable.getReturnType() : element.asType();
        return hasNullable(element.getAnnotationMirrors()) || hasNullable(type.getAnnotationMirrors());
    }

    private static boolean hasNullable(List<? extends AnnotationMirror> annotations) {
        for (AnnotationMirror annotation : annotations) {
            if (NULLABLE.contentEquals(annotation.getAnnotationType().toString())) {
                return true;
            }
        }
        return false;
    }
}
