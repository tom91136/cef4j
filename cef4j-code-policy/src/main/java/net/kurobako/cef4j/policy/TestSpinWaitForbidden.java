package net.kurobako.cef4j.policy;

import static com.google.errorprone.matchers.Description.NO_MATCH;
import static com.google.errorprone.matchers.Matchers.staticMethod;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.BugPattern.SeverityLevel;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.MethodInvocationTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;

@AutoService(BugChecker.class)
@BugPattern(
        summary = "Tests must use bounded synchronization instead of Thread.onSpinWait()",
        severity = SeverityLevel.ERROR)
public final class TestSpinWaitForbidden extends BugChecker implements MethodInvocationTreeMatcher {
    private static final long serialVersionUID = 1L;
    private static final Matcher<ExpressionTree> SPIN_WAIT =
            staticMethod().onClass("java.lang.Thread").named("onSpinWait");

    @Override
    public Description matchMethodInvocation(MethodInvocationTree tree, VisitorState state) {
        ClassTree enclosingClass = ASTHelpers.findEnclosingNode(state.getPath(), ClassTree.class);
        if (enclosingClass == null || !enclosingClass.getSimpleName().toString().endsWith("Test")) return NO_MATCH;
        if (!SPIN_WAIT.matches(tree, state)) return NO_MATCH;
        return buildDescription(tree)
                .setMessage("use TestDeadline, a bounded future, or a latch with an explicit timeout")
                .build();
    }
}
