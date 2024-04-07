//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
// 测试用例的答案是一个 32-位 整数。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
//
//
// 示例 2:
//
//
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 2 * 10⁴
// -10 <= nums[i] <= 10
// nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
//
//
// Related Topics 数组 动态规划 👍 2194 👎 0

package leetcode.editor.cn;

//java:乘积最大子数组
class P_152_MaximumProductSubarray {
    public static void main(String[] args) {
        Solution solution = new P_152_MaximumProductSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProduct00(int[] nums) {
            // 状态定义：
            //      设动态规划列表dp1 ，dp1[i]代表以元素 nums[i]为结尾的连续子数组最小积
            //      设动态规划列表dp2 ，dp2[i]代表以元素 nums[i]为结尾的连续子数组最大积
            // 选择定义：每一个子数组，必须选择当前元素nums[i]，保证 dp[i] 递推到 dp[i+1]的正确性；如果不包含 nums[i]，递推时则不满足题目的 连续子数组 要求
            // 转移方程：因为必须要选择nums[i]，所以可以进行比较求解
            //      dp[i] = Math.max(nums[i], nums[i] * dp1[i - 1], nums[i] * dp2[i - 1]);
            // 最值积 dp[i]中必须包含元素 nums[i]：保证 dp[i] 递推到 dp[i+1]的正确性；如果不包含 nums[i]，递推时则不满足题目的 连续子数组 要求

            int n = nums.length;
            // 定义：以 nums[i] 结尾的子数组，乘积最小为 dp1[i]
            int[] dp1 = new int[n];
            // 定义：以 nums[i] 结尾的子数组，乘积最大为 dp2[i]
            int[] dp2 = new int[n];

            // base case
            dp1[0] = nums[0];
            dp2[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                dp1[i] = Math.min(nums[i], Math.min(nums[i] * dp1[i - 1], nums[i] * dp2[i - 1]));
                dp2[i] = Math.max(nums[i], Math.max(nums[i] * dp1[i - 1], nums[i] * dp2[i - 1]));
            }

            // 遍历最大值列表
            int res = Integer.MIN_VALUE;
            for (int num : dp2) {
                res = Math.max(res, num);
            }

            return res;
        }

        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        public int maxProduct(int[] nums) {
            int n = nums.length;
            // 存在负负得正情况，声明两个数组，一个最大值，一个最小值
            int[] minDp = new int[n];
            int[] maxDp = new int[n];

            // base case
            minDp[0] = nums[0];
            maxDp[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                minDp[i] = Math.min(nums[i], Math.min(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i]));
                maxDp[i] = Math.max(nums[i], Math.max(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i]));
            }

            int ans = Integer.MIN_VALUE;
            for (int value : maxDp) {
                ans = Math.max(ans, value);
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}