//给你一棵二叉树的根节点，返回该树的 直径 。
//
// 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
//
// 两节点之间路径的 长度 由它们之间边数表示。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,4,5]
//输出：3
//解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
//
//
// 示例 2：
//
//
//输入：root = [1,2]
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [1, 10⁴] 内
// -100 <= Node.val <= 100
//
//
// Related Topics 树 深度优先搜索 二叉树 👍 1476 👎 0

package leetcode.editor.cn;

//java:二叉树的直径
class P_543_DiameterOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P_543_DiameterOfBinaryTree().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        int maxDiameterer = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            traverse(root);
            return maxDiameterer;
        }

        // 遍历二叉树
        void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 分析：实质求左右子树最大高度之和(注意：不一定必须经过根节点，所以需要递归遍历整棵树)
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            maxDiameterer = Math.max(maxDiameterer, leftDepth + rightDepth);
            // 递归遍历：即分别以左右节点为根节点，继续遍历
            traverse(root.left);
            traverse(root.right);
        }

        // 计算二叉树的最大深度
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 分别求左右子树高度，取较大值
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);

            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}