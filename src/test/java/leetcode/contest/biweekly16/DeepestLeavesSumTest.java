package leetcode.contest.biweekly16;

import leetcode.contest.biweekly16.DeepestLeavesSum.TreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeepestLeavesSumTest {

    private DeepestLeavesSum problem = new DeepestLeavesSum();

    @Test
    void sumIsRootIfOnlyOneNode() {
        TreeNode root = tree(1);
        int result = problem.deepestLeavesSum(root);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void sumWithTwoChildren() {
        TreeNode root = tree(1,
                tree(2),
                tree(3)
        );
        int result = problem.deepestLeavesSum(root);
        assertThat(result).isEqualTo(5);
    }

    @Test
    void missingChild() {
        TreeNode root = tree(1,
                tree(2),
                null
        );
        int result = problem.deepestLeavesSum(root);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void testCase() {
        TreeNode root = tree(1,
                tree(2,
                        tree(4,
                                tree(7),
                                null
                        ),
                        tree(5)
                ),
                tree(3,
                        null,
                        tree(6,
                                null,
                                tree(8)))
        );
        int result = problem.deepestLeavesSum(root);
        assertThat(result).isEqualTo(15);
    }

    private TreeNode tree(int value, TreeNode left, TreeNode right) {
        TreeNode treeNode = new TreeNode(value);
        treeNode.left = left;
        treeNode.right = right;
        return treeNode;
    }

    private TreeNode tree(int value) {
        return tree(value, null, null);
    }
}