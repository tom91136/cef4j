package net.kurobako.cef4j.policy;

import static com.google.errorprone.matchers.Description.NO_MATCH;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.BugPattern.SeverityLevel;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.BinaryTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.tree.JCTree;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import org.jspecify.annotations.Nullable;

/**
 * A volatile field can change between reads, so a null check proves nothing about the next read. NullAway trusts the
 * check anyway. Read the field once into a local and check and use the local.
 */
@AutoService(BugChecker.class)
@BugPattern(
        summary = "Null-checked volatile field is read again; copy it to a local and use that instead",
        severity = SeverityLevel.ERROR)
public final class VolatileNullCheckReread extends BugChecker implements BinaryTreeMatcher {
    private static final long serialVersionUID = 1L;

    @Override
    public Description matchBinary(BinaryTree tree, VisitorState state) {
        if (tree.getKind() != Tree.Kind.EQUAL_TO && tree.getKind() != Tree.Kind.NOT_EQUAL_TO) return NO_MATCH;
        VarSymbol field = volatileField(tree.getLeftOperand(), tree.getRightOperand());
        if (field == null) field = volatileField(tree.getRightOperand(), tree.getLeftOperand());
        if (field == null) return NO_MATCH;
        Tree body = enclosingBody(state.getPath());
        if (body == null) return NO_MATCH;
        int checkEnd = state.getEndPosition(tree);
        VarSymbol checked = field;
        Boolean reread = new TreeScanner<Boolean, Void>() {
            @Override
            public Boolean visitAssignment(AssignmentTree node, Void unused) {
                return scan(node.getExpression(), null);
            }

            @Override
            public Boolean visitIdentifier(IdentifierTree node, Void unused) {
                return readsAfterCheck(node);
            }

            @Override
            public Boolean visitMemberSelect(MemberSelectTree node, Void unused) {
                return readsAfterCheck(node) || Boolean.TRUE.equals(super.visitMemberSelect(node, null));
            }

            @Override
            public Boolean reduce(@Nullable Boolean left, @Nullable Boolean right) {
                return Boolean.TRUE.equals(left) || Boolean.TRUE.equals(right);
            }

            private boolean readsAfterCheck(ExpressionTree node) {
                return checked.equals(ASTHelpers.getSymbol(node)) && ((JCTree) node).getStartPosition() > checkEnd;
            }
        }.scan(body, null);
        if (!Boolean.TRUE.equals(reread)) return NO_MATCH;
        return buildDescription(tree)
                .setMessage("volatile field '" + field.getSimpleName()
                        + "' is read again after this null check; copy it to a local first")
                .build();
    }

    private static @Nullable VarSymbol volatileField(ExpressionTree candidate, ExpressionTree other) {
        if (other.getKind() != Tree.Kind.NULL_LITERAL) return null;
        Symbol symbol = ASTHelpers.getSymbol(candidate);
        if (!(symbol instanceof VarSymbol variable) || variable.getKind() != ElementKind.FIELD) return null;
        return variable.getModifiers().contains(Modifier.VOLATILE) ? variable : null;
    }

    private static @Nullable Tree enclosingBody(TreePath path) {
        for (TreePath up = path; up != null; up = up.getParentPath()) {
            Tree leaf = up.getLeaf();
            if (leaf instanceof LambdaExpressionTree lambda) return lambda.getBody();
            if (leaf instanceof MethodTree method) return method.getBody();
        }
        return null;
    }
}
