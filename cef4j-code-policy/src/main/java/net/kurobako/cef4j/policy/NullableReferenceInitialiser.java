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
import com.sun.tools.javac.code.Type;
import java.io.Serial;
import java.util.Optional;

/**
 * Reference holders created without an initial value hold null until first set, which NullAway does not model: a
 * {@code new AtomicReference<>()} typed {@code AtomicReference<Foo>} hands out null from {@code get()} while claiming
 * non-null. The value type must be declared {@code @Nullable}.
 */
// an Error Prone checker reads the symbols and trees of javac itself, which jdk.compiler does not export
@SuppressWarnings("baumkuchen:jdk-internal-api")
@AutoService(BugChecker.class)
@BugPattern(
        summary =
                "A reference holder created without an initial value starts as null; declare its value type @Nullable",
        severity = SeverityLevel.ERROR)
public final class NullableReferenceInitialiser extends BugChecker implements NewClassTreeMatcher {
    @Serial
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
        return targetType(tree, state)
                .flatMap(holder -> holder.getTypeArguments().stream().findFirst())
                .filter(value -> value.getAnnotationMirrors().stream()
                        .noneMatch(annotation -> NULLABLE.contentEquals(
                                annotation.getAnnotationType().toString())))
                .map(value -> buildDescription(tree)
                        .setMessage("starts as null; declare the value type as @Nullable " + value)
                        .build())
                .orElse(NO_MATCH);
    }

    // Diamond inference drops type-use annotations, so read the declared type of the receiving variable.
    private static Optional<Type> targetType(NewClassTree tree, VisitorState state) {
        final Tree parent = state.getPath().getParentPath().getLeaf();
        if (parent instanceof VariableTree variable) {
            return Optional.ofNullable(ASTHelpers.getSymbol(variable)).map(symbol -> symbol.type);
        }
        if (parent instanceof AssignmentTree assignment) {
            return Optional.ofNullable(ASTHelpers.getSymbol(assignment.getVariable()))
                    .map(symbol -> symbol.type);
        }
        return Optional.ofNullable(ASTHelpers.getType(tree));
    }
}
