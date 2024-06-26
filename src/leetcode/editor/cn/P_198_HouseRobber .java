//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//
//
//
// 示例 1：
//
//
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 2：
//
//
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 400
//
//
// Related Topics 数组 动态规划 👍 2716 👎 0

package leetcode.editor.cn;

//java:打家劫舍
class P_198_HouseRobber {
    public static void main(String[] args) {
        Solution solution = new P_198_HouseRobber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob00(int[] nums) {
            int pre = 0;
            int curr = 0;

            for (int num : nums) {
                int tmp = Math.max(pre + num, curr);
                pre = curr;
                curr = tmp;
            }

            return curr;
        }

        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            // 子问题：f(k)，从 k 个房子中能偷到的最大金额
            // 递推关系/状态转移方程：f(k) = Math.max(f(k-1), f(k - 2) + value(k-1));
            // 计算顺序：自底向上的、使用 dp 数组的循环方法
            int length = nums.length;
            int[] dp = new int[length + 1];
            dp[1] = nums[0];

            for (int i = 2; i <= nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            }

            return dp[length];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}