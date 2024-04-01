//你选择掷出 num 个色子，请返回所有点数总和的概率。
//
// 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 num 个骰子所能掷出的点数集合中第 i 小的那个的概率。
//
//
//
// 示例 1：
//
//
//输入：num = 3
//输出：[0.00463,0.01389,0.02778,0.04630,0.06944,0.09722,0.11574,0.12500,0.12500,0.
//11574,0.09722,0.06944,0.04630,0.02778,0.01389,0.00463]
//
//
// 示例 2：
//
//
//输入：num = 5
//输出:[0.00013,0.00064,0.00193,0.00450,0.00900,0.01620,0.02636,0.03922,0.05401,0.
//06944,0.08372,0.09452,0.10031,0.10031,0.09452,0.08372,0.06944,0.05401,0.03922,0.
//02636,0.01620,0.00900,0.00450,0.00193,0.00064,0.00013]
//
//
//
//
// 提示：
//
//
// 1 <= num <= 11
//
//
//
//
// Related Topics 数学 动态规划 概率与统计 👍 587 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:统计结果概率
class P_LCR_185_NgeTouZiDeDianShuLcof {
    public static void main(String[] args) {
        Solution solution = new P_LCR_185_NgeTouZiDeDianShuLcof().new Solution();
        System.out.println(Arrays.toString(solution.statisticsProbability(3)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义状态转移方程：dp(n, point) 函数表示用 n 个骰子抛出 point 点数的概率，那么这个状态转移关系如下：
        //dp(n, point) = sum{dp(n-1, point-k) where 1 <= k <= 6}
        public double[] statisticsProbability(int num) {
            // n 个骰子可能扔出的结果的最大值和最小值
            int min = num, max = num * 6;
            // 定义：用 n 个骰子，凑出 point 的点数的概率是 dp[n][point]
            double[][] dp = new double[num + 1][max + 1];

            // base case：一个骰子的时候，每个点数的概率 1/6
            for (int i = 1; i <= 6; i++) {
                dp[1][i] = 1.0 / 6;
            }

            // 自底向上的迭代解法：求解概率
            // 2个骰子
            for (int i = 2; i <= num; i++) {
                // dp[i][k] = dp
                for (int j = i * 1; j <= i * 6; j++) {
                    for (int k = 1; k <= 6; k++) {
                        if (j <= k) {
                            break;
                        }
                        // 用 i 个骰子抛出 j 点数的概率
                        dp[i][j] += dp[i - 1][j - k] * 1 / 6;
                    }
                }
            }

            double[] result = new double[max - min + 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = dp[num][min  + i];
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}