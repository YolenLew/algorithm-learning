// (C卷,100分)- 分披萨（Java & JS & Python & C）
// 题目描述
// "吃货"和"馋嘴"两人到披萨店点了一份铁盘（圆形）披萨，并嘱咐店员将披萨按放射状切成大小相同的偶数个小块。但是粗心的服务员将披萨切成了每块大小都完全不同奇数块，且肉眼能分辨出大小。
//
// 由于两人都想吃到最多的披萨，他们商量了一个他们认为公平的分法：从"吃货"开始，轮流取披萨。除了第一块披萨可以任意选取外，其他都必须从缺口开始选。
//
// 他俩选披萨的思路不同。"馋嘴"每次都会选最大块的披萨，而且"吃货"知道"馋嘴"的想法。
//
// 已知披萨小块的数量以及每块的大小，求"吃货"能分得的最大的披萨大小的总和。
//
// 输入描述
// 第 1 行为一个正整数奇数 N，表示披萨小块数量。
//
// 3 ≤ N < 500
// 接下来的第 2 行到第 N + 1 行（共 N 行），每行为一个正整数，表示第 i 块披萨的大小
//
// 1 ≤ i ≤ N
// 披萨小块从某一块开始，按照一个方向次序顺序编号为 1 ~ N
//
// 每块披萨的大小范围为 [1, 2147483647]
// 输出描述
// "吃货"能分得到的最大的披萨大小的总和。
//
// 用例
// 输入 5
// 8
// 2
// 10
// 5
// 7
// 输出 19
// 说明
// 此例子中，有 5 块披萨。每块大小依次为 8、2、10、5、7。
//
// 按照如下顺序拿披萨，可以使"吃货"拿到最多披萨：
//
// "吃货" 拿大小为 10 的披萨
//
// "馋嘴" 拿大小为 5 的披萨
//
// "吃货" 拿大小为 7 的披萨
//
// "馋嘴" 拿大小为 8 的披萨
//
// "吃货" 拿大小为 2 的披萨
//
// 至此，披萨瓜分完毕，"吃货"拿到的披萨总大小为 10 + 7 + 2 = 19
//
// 可能存在多种拿法，以上只是其中一种。

package com.lew.algo.hw.od._09_Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class _037_09_Greedy_DistributePizza04 {
    static int[] pizza;
    // 缓存: 对应缺口cache[left][right]状态下，吃货可得的最大披萨大小
    static long[][] cache;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        pizza = new int[n];
        for (int i = 0; i < n; i++) {
            pizza[i] = sc.nextInt();
        }

        // 缓存: 对应缺口cache[left][right]状态下，吃货可得的最大披萨大小
        cache = new long[n][n];
        Arrays.stream(cache).forEach(arr -> Arrays.fill(arr, -1));

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getMaxPizza(i - 1, i + 1, n) + pizza[i]);
        }

        System.out.println(ans);
    }

    private static long getMaxPizza(int left, int right, int n) {
        // 进入前，吃货已选择，现在轮到馋嘴选择了，馋嘴优先选最大的
        // 首先对索引处理避免越界
        left = (left + n) % n;
        right = right % n;
        if (pizza[left] > pizza[right]) {
            // 馋嘴优先选最大的left，剩余为left - 1, right
            left = (left - 1 + n) % n;
        } else {
            // 馋嘴优先选最大的right，剩余为left, right + 1
            right = (right + 1) % n;
        }

        // 检查面临left, right区间时是否有缓存
        if (cache[left][right] != -1) {
            return cache[left][right];
        }

        //
        if (left == right) {
            cache[left][right] = pizza[left];
        } else {

            cache[left][right] =
                Math.max(getMaxPizza(left - 1, right, n) + pizza[left], getMaxPizza(left, right + 1, n) + pizza[right]);
        }

        return cache[left][right];
    }

}