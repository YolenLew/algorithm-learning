//给定一个二叉树 root ，返回其最大深度。
//
// 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
//
//
//
// 示例 1：
//
//
//
//
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：3
//
//
// 示例 2：
//
//
//输入：root = [1,null,2]
//输出：2
//
//
//
//
// 提示：
//
//
// 树中节点的数量在 [0, 10⁴] 区间内。
// -100 <= Node.val <= 100
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1806 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

//java:二叉树的最大深度
class P_104_MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P_104_MaximumDepthOfBinaryTree().new Solution();
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

        // 动态规划思路
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);

            return 1 + Math.max(leftDepth, rightDepth);
        }

        // 回溯算法思路
        public int maxDepth90(TreeNode root) {
            calDepth(root);
            return ans;
        }

        int ans = 0;
        int depth = 0;

        public void calDepth(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序遍历位置
            depth++;

            // 遍历的过程中记录最大深度
            ans = Math.max(ans, depth);
            calDepth(root.left);
            calDepth(root.right);

            // 后序遍历位置
            depth--;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}