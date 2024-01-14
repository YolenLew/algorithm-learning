//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 子数组 是数组中的一个连续部分。
//
//
//
// 示例 1：
//
//
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
//
//
// 示例 2：
//
//
//输入：nums = [1]
//输出：1
//
//
// 示例 3：
//
//
//输入：nums = [5,4,-1,7,8]
//输出：23
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
//
//
//
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
//
// Related Topics 数组 分治 动态规划 👍 6519 👎 0

package leetcode.editor.cn;

//java:最大子数组和
class P_53_MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new P_53_MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            // 初始化左右指针
            int left = 0, right = 0;
            // 初始化子集和、最大和（注意：maxSum一定要设置为最小负数，这样遇到全负数组的时候才能不出错）
            int windowSum = 0, maxSum = Integer.MIN_VALUE;
            while (right < nums.length) {
                // 扩大窗口：移动右指针求和
                windowSum += nums[right];
                right++;
                maxSum = Math.max(maxSum, windowSum);
                // 移动左指针：当和为负数时，说明内部有负数，便可进行缩小窗口
                // 注意：无需额外的循环结束条件（left <= rihgt等），因为窗口在不断移除元素，如果都是小于0的值窗口最终会变为0
                while (windowSum < 0) {
                    // 缩小窗口并更新窗口内的元素和
                    windowSum -= nums[left];
                    left++;
                }
            }
            return maxSum;
        }

        public int maxSubArrayDp(int[] nums) {
            // 状态定义： 设动态规划列表dp ，dp[i]代表以元素 nums[i]为结尾的连续子数组最大和
            // 转移方程：动态规划解法，dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            // 最大和 dp[i]中必须包含元素 nums[i]：保证 dp[i] 递推到 dp[i+1]的正确性；如果不包含 nums[i]，递推时则不满足题目的 连续子数组 要求

            // 存储结果集数组
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int maxSum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = dp[i - 1] <= 0 ? nums[i] : dp[i -1] + nums[i];
                maxSum = Math.max(dp[i], maxSum);
            }
            return maxSum;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}