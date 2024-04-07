//给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
//
//
//
//
// 示例 1：
//
//
//输入：n = 12
//输出：3
//解释：12 = 4 + 4 + 4
//
// 示例 2：
//
//
//输入：n = 13
//输出：2
//解释：13 = 4 + 9
//
//
//
// 提示：
//
//
// 1 <= n <= 10⁴
//
//
// Related Topics 广度优先搜索 数学 动态规划 👍 1925 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:完全平方数
class P_279_PerfectSquares {
    public static void main(String[] args) {
        Solution solution = new P_279_PerfectSquares().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numSquares00(int n) {
            // 子问题：f(k)， 和为 k 的完全平方数的最少数量
            // 递推关系/状态转移方程：f(k) = min{f(k-1*1) + 1, f(k-2*2) + 1, f(k - 3*3) + 1....}
            // 计算顺序：自底向上的、使用 dp 数组的循环方法
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            // base case 基本情况
            dp[0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j * j <= i; j++) {
                    // 注意：此处加1是因为，j*j是一个完全平方数，需要补充此处的次数
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }

            return dp[n];
        }

        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j * j <= i; j++) {
                    dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
                }
            }

            return dp[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}