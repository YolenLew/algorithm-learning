//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。
//
// 示例 1：
//
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//
//
// 示例 3：
//
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2500
// -10⁴ <= nums[i] <= 10⁴
//
//
//
//
// 进阶：
//
//
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
//
// Related Topics 数组 二分查找 动态规划 👍 3582 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:最长递增子序列
class P_300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new P_300_LongestIncreasingSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
            // 子问题：f(k)， 数组nums中0~k个元素组成的最长递增子序列的长度
            // dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。
            // 运用数学归纳法的思想，假设 `dp[0...i-1]` 都已知，想办法求出 `dp[i]`
            // 递推关系/状态转移方程：dp[i] = dp[j] + 1 (j<i, 且 nums[j] < nums[i])
            // 计算顺序：自底向上的、使用 dp 数组的循环方法。
            int[] dp = new int[nums.length];
            // base case：dp[i] 初始值为 1，因为以 nums[i] 结尾的最长递增子序列起码要包含它自己
            Arrays.fill(dp, 1);

            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    // 寻找前述子问题中，最后一个元素比当前元素小的，那么当前子问题的解就是那个子问题的基础上加一
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            // 最后遍历dp数组，寻找最大值
            int max = 1;
            for (int length : dp) {
                max = Math.max(max, length);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}