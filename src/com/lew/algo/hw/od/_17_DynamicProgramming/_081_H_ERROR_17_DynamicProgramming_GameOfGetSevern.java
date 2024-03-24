// (C卷,200分)- 抢7游戏（Java & JS & Python & C）
// 题目描述
// A、B两个人玩抢7游戏，游戏规则为：
//
// A先报一个起始数字 X（10 ≤ 起始数字 ≤ 10000），B报下一个数字 Y （X - Y < 3），A再报一个数字 Z（Y - Z < 3），以此类推，直到其中一个抢到7，抢到7即为胜者；
//
// 在B赢得比赛的情况下，一共有多少种组合？
//
// 输入描述
// 起始数字 M
//
// 10 ≤ M ≤ 10000
// 如：
//
// 100
//
// 输出描述
// B能赢得比赛的组合次数
//
// 用例
// 输入 10
// 输出 1
// 说明 无

package com.lew.algo.hw.od._17_DynamicProgramming;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class _081_H_ERROR_17_DynamicProgramming_GameOfGetSevern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入A的初始报数
        int M = scanner.nextInt();
        System.out.println(getResult00(M));
        System.out.println(getResult11(M));
    }

    // 注意：有问题,long存在整型溢出问题
    private static long getResult10Error(long M) {
        // 初始化dpA和dpB两个初始数组
        long[] dpA = new long[(int)(M + 2)];
        long[] dpB = new long[(int)(M + 2)];
        dpA[(int)M] = 1;

        // dp过程，从M-1开始遍历，到7结束
        for (int i = (int)(M - 1); i >= 6; i--) {
            dpB[i] = dpA[i + 1] + dpA[i + 2];
            dpA[i] = dpB[i + 1] + dpB[i + 2];
        }

        // 输出dpB[7]即为最终答案
        return dpB[7];
    }
    // -------------------------------------------------------------------
    // ------------------------动态规划---------------------------------
    // -------------------------------------------------------------------

    // 解题思路
    // A和B每次选择的数字，只能比上一个数字小1或2，两个人所选择的数字越来越小直到降为7。这种计算组合数的问题很容易想到使用动态规划来解题。
    //
    // 我们考虑动态规划三部曲：
    //
    // dp数组的含义是什么？
    // 由于A和B两个人是交替地进行数字选择的，我们可以构建两个dp数组，dpA和dpB。
    //
    // dpA[i]表示A选择了第i个数字时的方法数，dpB[i]表示B选择了第i个数字时的方法数。
    //
    // 动态转移方程是什么？
    // 由于两个人的选择是交替进行的，因此A的状态由先前的B转移得到，B的状态由先前的A转移得到。
    //
    // 当B选择了数字i时，上一轮A的选择必然是i+1或i+2。因此B选择数字i的组合数为A选择了数字i+1和i+2的组合数的总和，即存在动态转移方程dpB[i] = dpA[i+1] + dpA[i+2]。
    //
    // 对于A选择了数字i的情况，同理存在dpA[i] = dpB[i+1] + dpB[i+2]
    //
    // 由于选择是从大到小进行的，因此必须从M-1开始进行逆序遍历，直到选择了7。即
    // ————————————————
    //
    // 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
    //
    // 原文链接：https://blog.csdn.net/weixin_48157259/article/details/135577956
    private static String getResult11(int M) {
        // 初始化dpA和dpB两个初始数组
        // dpA[i]表示A选择了第i个数字时的方法数，dpB[i]表示B选择了第i个数字时的方法数
        BigInteger[] dpA = new BigInteger[(M + 2)];
        BigInteger[] dpB = new BigInteger[(M + 2)];
        Arrays.fill(dpA, new BigInteger("0"));
        Arrays.fill(dpB, new BigInteger("0"));

        // base case：报数M时，是从A开始的，所以必有dpB[M] = 1、dpA[M] = 0；
        // 因此，后续的遍历可以从M-1开始
        dpA[M] = new BigInteger("1");

        // 选择是从大到小进行的，因此可从M-1开始进行逆序遍历，直到选择了7
        // dp过程，从M-1开始遍历，到7结束
        for (int i = (M - 1); i >= 6; i--) {
            // 当B选择了数字i时，上一轮A的选择必然是i+1或i+2。因此B选择数字i的组合数为A选择了数字i+1和i+2的组合数的总和
            dpB[i] = dpA[i + 1].add(dpA[i + 2]);
            // 对于A选择了数字i的情况，同理存在dpA[i] = dpB[i+1] + dpB[i+2]
            dpA[i] = dpB[i + 1].add(dpB[i + 2]);
        }

        // 输出dpB[7]即为最终答案
        return dpB[7].toString();
    }

    // -------------------------------------------------------------------
    // -------------------------------------------------------------------
    // -------------------------------------------------------------------

    private static String getResult00(int m) {
        initFactor(m - 7);

        int oneCount = m - 7;
        int twoCount = 0;

        // 记录B赢的情况数
        BigInteger ans = new BigInteger("0");

        while (oneCount >= 0) {
            // 叫的次数为奇数时，才能B赢
            if ((oneCount + twoCount) % 2 != 0) {
                ans = ans.add(getPermutationCount(oneCount, twoCount));
            }

            // 合并两个1为一个2
            oneCount -= 2;
            twoCount += 1;
        }

        return ans.toString();
    }

    static BigInteger[] factor;

    // 求解不重复的全排列数
    public static BigInteger getPermutationCount(int oneCount, int twoCount) {
        if (oneCount == 0 || twoCount == 0) { // 即 1 1 1 1 1 或 2 2 2 这种情况，此时只有一种排列
            return new BigInteger("1");
        } else {
            // 排列数去重，比如 1 1 1 2 2 的不重复排列数为 5! / 3! / 2! = 10
            return factor[oneCount + twoCount].divide(factor[oneCount].multiply(factor[twoCount]));
        }
    }

    // 阶乘
    public static void initFactor(int n) {
        factor = new BigInteger[n + 1];
        factor[0] = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            factor[i] = factor[i - 1].multiply(new BigInteger(i + ""));
        }
    }

}
