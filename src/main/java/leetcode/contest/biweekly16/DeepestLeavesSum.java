package leetcode.contest.biweekly16;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;


class DeepestLeavesSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        LinkedList<Integer> deepestLevel = new LinkedList<>();
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            deepestLevel = new LinkedList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                deepestLevel.add(Objects.requireNonNull(node).val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return deepestLevel.stream().reduce(Integer::sum).orElseThrow();
    }
}
