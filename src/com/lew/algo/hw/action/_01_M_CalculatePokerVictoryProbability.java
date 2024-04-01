package com.lew.algo.hw.action;

/**
 * @author Yolen
 * @date 2024/3/30
 */
public class _01_M_CalculatePokerVictoryProbability {
    // 牌堆有无限多张点数均匀分布在 1-13 间的牌，你可以抽取任意张。
    // 初始，你的点数是 0，当你手中牌点数之和位于 [21,24)时获胜，否则失败。求你获胜的概率。
    // 面试手撕题目，大家可以试一下
    public static void main(String[] args) {
        int minSum = 21;
        int maxSum = 23;
        // 定义：用 n 个骰子，凑出 point 的点数的概率是 dp[n][point]
        double[][] dp = new double[maxSum + 1][maxSum * 13];
        // base case：一个骰子的时候，每个点数的概率 1/6
        for (int i = 1; i <= 13; i++) {
            dp[1][i] = 1.0 / 13;
        }

        // i：骰子的个数
        for (int i = 2; i < maxSum; i++) {
            // j: 点数的和
            for (int j = i * 1; j <= i * 13; j++) {
                for (int k = 1; k <= 13; k++) {
                    if (j - k <= 0) {
                        break;
                    }

                    // i-1 颗骰子投掷的概率累加
                    // dp(n, point) = sum{dp(n-1, point-k) where 1 <= k <= 13}
                    dp[i][j] += dp[i - 1][j - k] * 1 / 13;
                }
            }
        }

        // 求点数之和位于 [21,24) 的概率
        double ans = 0;
        for (int i = 2; i < dp.length; i++) {
            for (int j = 21; j < 24; j++) {
                ans += dp[i][j];
            }
        }

        System.out.println(ans);
    }
}
