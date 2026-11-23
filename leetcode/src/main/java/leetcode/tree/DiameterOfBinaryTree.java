package leetcode.tree;

public class DiameterOfBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int maximumDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {

        calculateHeight(root);

        return maximumDiameter;
    }

    private int calculateHeight(TreeNode node) {

        if (node == null) {
            return 0;
        }

        // Calculate left and right subtree heights
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Diameter passing through current node
        int currentDiameter = leftHeight + rightHeight;

        // Update maximum diameter
        maximumDiameter = Math.max(
            maximumDiameter,
            currentDiameter
        );

        // Return height to parent
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {

        DiameterOfBinaryTree solution =
            new DiameterOfBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(
            "Diameter: " + solution.diameterOfBinaryTree(root)
        );
    }
}
