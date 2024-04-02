//二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定
//经过根节点。
//
// 路径和 是路径中各节点值的总和。
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
//
// 示例 2：
//
//
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
//
//
//
//
// 提示：
//
//
// 树中节点数目范围是 [1, 3 * 10⁴]
// -1000 <= Node.val <= 1000
//
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 2161 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

//java:二叉树中的最大路径和
class P_124_BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        Solution solution = new P_124_BinaryTreeMaximumPathSum().new Solution();
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
        // 最大路径和
        int maxPathSum = Integer.MIN_VALUE;

        public int maxPathSum00(TreeNode root) {
            dfs(root);
            return maxPathSum;
        }

        // 问题拆解：1.如果不绕过父节点，那么就求解以当前节点为根的子树内部的路径和，即 innerMaxSum= left + root.val + right;
        // 2.如果绕过父节点：那么需要求解以当前节点为根的子树，对外能提供的最大路径和，即 outputMaxSum = root.val + Math.max(0, left, right);
        public int dfs(TreeNode root) {
            // 空节点，直接返回0
            if (root == null) {
                return 0;
            }
            // 左子树节点路径和
            int leftSum = dfs(root.left);
            // 右子树节点路径和
            int rightSum = dfs(root.right);
            // 不绕过root的父节点，只看root这颗子树的内部最大路径
            int innerMaxSum = root.val + leftSum + rightSum;
            // 更新最大路径和
            maxPathSum = Math.max(maxPathSum, innerMaxSum);

            // 绕过root的父节点：root这颗子树只能选择左右的一侧，这时就需要看子树对外能提供的最大收益
            int outputMaxSum = root.val + Math.max(0, Math.max(leftSum, rightSum));

            // 如果子树对外提供的收益为负，那么可以直接剔除不要
            return outputMaxSum > 0 ? outputMaxSum : 0;
        }


        int myMathSum = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root){
            traverse(root);
            return myMathSum;
        }

        private int traverse(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftSum = traverse(root.left);
            int rightSum = traverse(root.right);

            // 包含root，但不包含root的父节点，只看root这颗子树的内部最大路径
            int innerSum = root.val + leftSum + rightSum;
            myMathSum = Math.max(maxPathSum, innerSum);

            // 包含root的父节点，那么只能选左子树或右子树其中的一侧
            int outerSum = root.val + Math.max(0, Math.max(leftSum, rightSum));
            // 如果子树对外提供的收益为负，那么可以直接剔除不要
            return outerSum > 0 ? outerSum : 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}