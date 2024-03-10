//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
//
//输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
//
//输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
//
//输入：coins = [1], amount = 0
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 2³¹ - 1
// 0 <= amount <= 10⁴
//
//
// Related Topics 广度优先搜索 数组 动态规划 👍 2726 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:零钱兑换
class P_322_CoinChange {
    public static void main(String[] args) {
        Solution solution = new P_322_CoinChange().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int coinChange(int[] coins, int amount) {
            // 子问题：f(k)，总金额为 k 的硬币组合最少数量
            // 递推关系/状态转移方程：f(k) = min{f(k - coin) + 1 | coin in coins}
            // 计算顺序：自底向上的、使用 dp 数组的循环方法
            int[] dp = new int[amount + 1];
            // 注意，此处不能初始化为Integer.MAX，否则后续加法运算会溢出
            Arrays.fill(dp, amount + 1);
            // base case：金额为0时，无需硬币即可组成总金额0，所以结果为0
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                // 做选择
                for (int coin : coins) {
                    // 剔除无效选择
                    if (i - coin < 0) {
                        continue;
                    }
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }

            return dp[amount] == amount + 1 ? -1 : dp[amount];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}