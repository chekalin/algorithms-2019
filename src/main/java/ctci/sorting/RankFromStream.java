package ctci.sorting;

/**
 * 10.10 Rank from Stream:
 * Imagine you are reading in a stream of integers. Periodically, you wish to be able to look up the rank of a number x
 * (the number of values less than or equal to x). Implement the data structures and algorithms to support these
 * operations. That is, implement the method track(int x), which is called when each number is generated, and the
 * method getRankOfNumber(int x), which returns the number of values less than or equal to x (not including x itself).
 */
public class RankFromStream {

    TreeNode root;

    void track(Integer i) {
        if (root == null) {
            root = new TreeNode(i);
        } else {
            root.insert(i);
        }
    }

    int getRankOfNumber(int i) {
        return root.getRank(i);
    }

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        private int size = 1;

        private TreeNode(int val) {
            this.val = val;
        }

        private void insert(int x) {
            if (x <= val) {
                if (left == null) {
                    left = new TreeNode(x);
                } else {
                    left.insert(x);
                }
            } else {
                if (right == null) {
                    right = new TreeNode(x);
                } else {
                    right.insert(x);
                }
            }
            this.size++;
        }

        private int getRank(int x) {
            if (x == val) {
                return left == null ? 0 : this.left.size;
            } else if (x < val) {
                return left == null ? -1 : left.getRank(x);
            } else {
                int rightRank = right == null ? -1 : right.getRank(x);
                if (rightRank == -1) {
                    return -1;
                } else {
                    return (left == null ? 0 : left.size) + 1 + right.getRank(x);
                }
            }
        }

    }

}
