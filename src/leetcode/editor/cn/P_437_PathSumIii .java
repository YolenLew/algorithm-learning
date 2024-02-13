//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
//
//
// 示例 2：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [0,1000]
//
// -10⁹ <= Node.val <= 10⁹
// -1000 <= targetSum <= 1000
//
//
// Related Topics 树 深度优先搜索 二叉树 👍 1797 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

//java:路径总和 III
class P_437_PathSumIii {
    public static void main(String[] args) {
        Solution solution = new P_437_PathSumIii().new Solution();
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
        // 前缀和Map：key，（从根节点开始的）前缀和，value，路径个数
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        // 结果
        int result;
        // 目标和
        int specifiedSum;

        public int pathSum(TreeNode root, int targetSum) {
            // 前缀和、回溯、递归
            // 先把0放入，是因为从根节点到当前节点的和如果刚好相等，这时差值就为0
            prefixSumMap.put(0L, 1);
            specifiedSum = targetSum;
            recur(root, 0);
            return result;
        }

        /**
         * 递归遍历求路径的前缀和
         *
         * @param root   节点
         * @param preSum 当前节点的前缀和
         */
        private void recur(TreeNode root, long preSum) {
            if (root == null) {
                return;
            }
            preSum += root.val;
            // 判断前缀和是否包含
            if (prefixSumMap.containsKey(preSum - specifiedSum)) {
                result += prefixSumMap.get(preSum - specifiedSum);
            }
            // 记录前缀和
            prefixSumMap.put(preSum, prefixSumMap.getOrDefault(preSum, 0) + 1);
            // 继续遍历子树
            recur(root.left, preSum);
            recur(root.right, preSum);
            // 当前节点遍历完成，退出后要剔除；因为路径方向必须是向下的（只能从父节点到子节点）
            prefixSumMap.put(preSum, prefixSumMap.get(preSum) - 1);
        }

        // 递归不借助外部变量版本
        private int recurWithValue(TreeNode root, long preSum) {
            if (root == null) {
                return 0;
            }
            int total = 0;
            preSum += root.val;
            // 判断前缀和是否包含
            if (prefixSumMap.containsKey(preSum - specifiedSum)) {
                total += prefixSumMap.get(preSum - specifiedSum);
            }
            // 记录前缀和
            prefixSumMap.put(preSum, prefixSumMap.getOrDefault(preSum, 0) + 1);
            // 继续遍历子树
            total += recurWithValue(root.left, preSum);
            total += recurWithValue(root.right, preSum);
            // 当前节点遍历完成，退出后要剔除；因为路径方向必须是向下的（只能从父节点到子节点）
            prefixSumMap.put(preSum, prefixSumMap.get(preSum) - 1);
            return total;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}