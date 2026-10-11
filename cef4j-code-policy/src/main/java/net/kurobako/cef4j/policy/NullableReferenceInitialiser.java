package net.kurobako.cef4j.policy;

import static com.google.errorprone.matchers.Description.NO_MATCH;
import static com.google.errorprone.matchers.Matchers.anyOf;
import static com.google.errorprone.matchers.method.MethodMatchers.constructor;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.BugPattern.SeverityLevel;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.NewClassTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.NewClassTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import javax.lang.model.element.AnnotationMirror;
import org.jspecify.annotations.Nullable;

/**
 * Reference holders created without an initial value hold null until first set, which NullAway does not model: a
 * {@code new AtomicReference<>()} typed {@code AtomicReference<Foo>} hands out null from {@code get()} while claiming
 * non-null. The value type must be declared {@code @Nullable}.
 */
@AutoService(BugChecker.class)
@BugPattern(
        summary =
                "A reference holder created without an initial value starts as null; declare its value type @Nullable",
        severity = SeverityLevel.ERROR)
public final class NullableReferenceInitialiser extends BugChecker implements NewClassTreeMatcher {
    private static final long serialVersionUID = 1L;

    private static final String NULLABLE = "org.jspecify.annotations.Nullable";
    private static final Matcher<ExpressionTree> EMPTY_HOLDER = anyOf(
            constructor()
                    .forClass("java.util.concurrent.atomic.AtomicReference")
                    .withNoParameters(),
            constructor()
                    .forClass("java.util.concurrent.atomic.AtomicReferenceArray")
                    .withParameters("int"),
            constructor().forClass("java.lang.ThreadLocal").withNoParameters());

    @Override
    public Description matchNewClass(NewClassTree tree, VisitorState state) {
        if (tree.getClassBody() != null || !EMPTY_HOLDER.matches(tree, state)) return NO_MATCH;
        Type holder = targetType(tree, state);
        if (holder == null || holder.getTypeArguments().isEmpty()) return NO_MATCH;
        Type value = holder.getTypeArguments().get(0);
        for (AnnotationMirror annotation : value.getAnnotationMirrors()) {
            if (NULLABLE.contentEquals(annotation.getAnnotationType().toString())) return NO_MATCH;
        }
        return buildDescription(tree)
                .setMessage("starts as null; declare the value type as @Nullable " + value)
                .build();
    }

    // Diamond inference drops type-use annotations, so read the declared type of the receiving variable.
    private static @Nullable Type targetType(NewClassTree tree, VisitorState state) {
        Tree parent = state.getPath().getParentPath().getLeaf();
        if (parent instanceof VariableTree variable) {
            Symbol symbol = ASTHelpers.getSymbol(variable);
            return symbol == null ? null : symbol.type;
        }
        if (parent instanceof AssignmentTree assignment) {
            Symbol symbol = ASTHelpers.getSymbol(assignment.getVariable());
            return symbol == null ? null : symbol.type;
        }
        return ASTHelpers.getType(tree);
    }
}
