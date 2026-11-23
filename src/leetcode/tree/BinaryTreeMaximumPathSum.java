package leetcode.tree;

public class BinaryTreeMaximumPathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int maximumPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        findMaximumGain(root);

        return maximumPathSum;
    }

    private int findMaximumGain(TreeNode node) {

        if (node == null) {
            return 0;
        }

        // Calculate maximum gain from left and right subtrees
        int leftGain = Math.max(0, findMaximumGain(node.left));
        int rightGain = Math.max(0, findMaximumGain(node.right));

        // Maximum path passing through the current node
        int currentPathSum = node.val + leftGain + rightGain;

        // Update global maximum
        maximumPathSum = Math.max(maximumPathSum, currentPathSum);

        // Return maximum one-sided gain to parent
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {

        BinaryTreeMaximumPathSum solution =
            new BinaryTreeMaximumPathSum();

        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(
            "Maximum Path Sum: " + solution.maxPathSum(root)
        );
    }
}
