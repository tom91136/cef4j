package net.kurobako.cef4j.policy;

import static com.google.errorprone.matchers.Description.NO_MATCH;
import static com.google.errorprone.matchers.method.MethodMatchers.instanceMethod;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.BugPattern.SeverityLevel;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.MethodInvocationTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;

/** {@link java.io.File} mutators report failure only through their boolean result, so ignoring it hides the error. */
@AutoService(BugChecker.class)
@BugPattern(
        summary = "java.io.File operations report failure through their result; check it or use java.nio.file.Files",
        severity = SeverityLevel.ERROR)
public final class IgnoredFileOperationResult extends BugChecker implements MethodInvocationTreeMatcher {
    private static final long serialVersionUID = 1L;

    private static final Matcher<ExpressionTree> FILE_OPERATION = instanceMethod()
            .onDescendantOf("java.io.File")
            .namedAnyOf(
                    "createNewFile",
                    "delete",
                    "mkdir",
                    "mkdirs",
                    "renameTo",
                    "setExecutable",
                    "setLastModified",
                    "setReadOnly",
                    "setReadable",
                    "setWritable");

    @Override
    public Description matchMethodInvocation(MethodInvocationTree tree, VisitorState state) {
        if (!(state.getPath().getParentPath().getLeaf() instanceof ExpressionStatementTree)) return NO_MATCH;
        if (!FILE_OPERATION.matches(tree, state)) return NO_MATCH;
        return describeMatch(tree);
    }
}
