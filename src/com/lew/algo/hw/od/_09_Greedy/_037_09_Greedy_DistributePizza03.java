package com.lew.algo.hw.od._09_Greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author code5bug
 */
public class _037_09_Greedy_DistributePizza03 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++)
            p[i] = in.nextInt();

        Solution solution = new Solution();
        System.out.println(solution.solve(p, n));
    }
}

// 解题思路： 记忆化搜索算法，计算“吃货”在每一轮中的最佳选择。使用二维缓存数组 cache 来存储中间结果，避免重复计算。
//
// 代码描述：
//
// 定义 get_max_sum 函数，表示在给定可选的左右边界索引和剩余披萨块数，吃货能分到的最大披萨总和。
// 在 get_max_sum 函数中，首先对左右边界进行调整（避免数组越界），“馋嘴”选择最大的一块。
// 使用递归计算（ “吃货”）两种情况下的最大总和：选择左边界块和选择右边界块。
// 返回最优解并将结果缓存到 cache 中，避免重复计算。
// 在主函数中，尝试每种选择，然后取结果最大的值。

class Solution {
    int n;
    int[] p;
    long[][] cache;

    /**
     * @param l, r: 可以选择的两端索引下标
     * @param t: 剩余的披萨块数
     * @return: 返回 “吃货” 最优选择时可以分到的披萨总和
     */
    private long getMaxSum(int l, int r, int t) {
        if (t <= 1)
            return 0L;
        l = (l + n) % n;
        r = r % n;

        // “馋嘴” 选择最大的一块
        if (p[l] >= p[r]) {
            l = (l - 1 + n) % n;
        } else {
            r = (r + 1) % n;
        }

        if (cache[l][r] != -1)
            return cache[l][r];

        long s1 = p[l] + getMaxSum(l - 1, r, t - 2); // “吃货” 选择 p[l]
        long s2 = p[r] + getMaxSum(l, r + 1, t - 2); // “吃货” 选择 p[r]

        // “吃货” 选择最有利的，并返回结果
        return cache[l][r] = Math.max(s1, s2);
    }

    public long solve(int[] p, int n) {
        this.p = p;
        this.n = n;
        this.cache = new long[n][n];
        Arrays.stream(cache).forEach(row -> Arrays.fill(row, -1));

        long maxsum = 0;
        for (int i = 0; i < n; i++) {
            maxsum = Math.max(maxsum, p[i] + getMaxSum(i - 1, i + 1, n - 1));
        }

        return maxsum;
    }
}