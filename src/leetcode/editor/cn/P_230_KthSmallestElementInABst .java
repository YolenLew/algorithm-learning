//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,1,4,null,2], k = 1
//输出：1
//
//
// 示例 2：
//
//
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
//
//
//
//
//
//
// 提示：
//
//
// 树中的节点数为 n 。
// 1 <= k <= n <= 10⁴
// 0 <= Node.val <= 10⁴
//
//
//
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 816 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

//java:二叉搜索树中第K小的元素
class P_230_KthSmallestElementInABst {
    public static void main(String[] args) {
        Solution solution = new P_230_KthSmallestElementInABst().new Solution();
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
        // 记录结果
        int res;
        // 记录当前元素排名
        int rank;

        public int kthSmallest00(TreeNode root, int k) {
            // 中序遍历：二叉搜索树的中序遍历为递增序列。升序排列，找到第k个即可
            traverse(root, k);
            return res;
        }

        public void traverse(TreeNode root, int k) {
            if (root == null) {
                return;
            }
            // 中序遍历：左树遍历
            traverse(root.left, k);
            // 中序遍历：中遍历，记录排名
            rank++;
            if (rank == k) {
                // 找到第 k 小的元素
                res = root.val;
                return;
            }
            // 中序遍历：右树遍历
            traverse(root.right, k);
        }

        // BST 的中序遍历结果是有序的（升序）
        public int kthSmallest(TreeNode root, int k) {
            reverseMy(root, k);
            return resMy;
        }

        int resMy = 0;
        int rankMy = 0;

        private void reverseMy(TreeNode root, int k) {
            if (root == null) {
                return;
            }
            // 先遍历左树
            reverseMy(root.left, k);
            rankMy++;
            if (rankMy == k) {
                resMy = root.val;
                return;
            }

            reverseMy(root.right, k);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}