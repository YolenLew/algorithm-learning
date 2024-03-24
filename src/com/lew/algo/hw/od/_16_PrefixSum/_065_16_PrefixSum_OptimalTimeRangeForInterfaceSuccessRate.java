// (C卷,100分)- 查找接口成功率最优时间段（Java & JS & Python & C）
// 题目描述
// 服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，
//
// 数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，
//
// 给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，即平均失败率小于等于minAverageLost，
//
// 找出数组中最长时间段，如果未找到则直接返回NULL。
//
// 输入描述
// 输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(” “)分隔，
//
// minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
//
// 输出描述
// 找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，
//
// 如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(” “)拼接，多个下标对按下标从小到大排序。
//
// 用例
// 输入 1
// 0 1 2 3 4
// 输出 0-2
// 说明
// 输入解释：minAverageLost=1，数组[0, 1, 2, 3, 4]
//
// 前3个元素的平均值为1，因此数组第一个至第三个数组下标，即0-2
//
// 输入 2
// 0 0 100 2 2 99 0 2
// 输出 0-1 3-4 6-7
// 说明
// 输入解释：minAverageLost=2，数组[0, 0, 100, 2, 2, 99, 0, 2]
//
// 通过计算小于等于2的最长时间段为：
//
// 数组下标为0-1即[0, 0]，数组下标为3-4即[2, 2]，数组下标为6-7即[0, 2]，这三个部分都满足平均值小于等于2的要求，
//
// 因此输出0-1 3-4 6-7
//
// 题目解析
// 本题需要求出第二行输入的所有区间，比如0 1 2 3 4区间有
//
// 0
// 0~1, 1
// 0~2, 1~2, 2
// 0~3, 1~3, 2~3, 3
// 0~4, 1~4, 2~4, 3~4, 4
// 我们需要计算出这些区间的平均失败率容忍值averageLost，和第一行输入的minAverageLost比较，
//
// 如果averageLost > minAverageLost，则不考虑
//
// 如果averageLost <= minAverageLost，则考虑
//
// 最后将考虑中所有区间进行比较，保留最长的，注意可能有多个相同时间长度的。
//
// 这里模拟出上面区间，很明显需要使用双重for
//
// for(let i=0; i<arr.length; i++) { // 区间右边界，包含
// for(let j=0; j<i; j++) {// 区间左边界，不包含
//
// }
// }
// 这里，我们为了避免陷入遍历i到j，来计算区间[i, j]的总和，我们可以事先定义一个dp数组，dp[i]表示以0~i区间的和（即前缀和）。
//
// 因此[j, i]区间总和的计算就是dp[j] - dp[i-1]。
//
// 更多前缀和求解任意区间子序列和的知识请看：
//
// 算法设计 - 前缀和 & 差分数列_伏城之外的博客-CSDN博客
//
// 同时为了避免计算平均失败率，我们可以计算总最低失败率，即(j - i + 1) * minAverageLost

package com.lew.algo.hw.od._16_PrefixSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class _065_16_PrefixSum_OptimalTimeRangeForInterfaceSuccessRate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int minAverageLost = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(nums, minAverageLost));
    }

    public static String getResult(int[] nums, int minAverageLost) {
        int n = nums.length;

        // 为了避免陷入遍历i到j，来计算区间[i, j]的总和，可以事先定义一个dp数组，dp[i]表示以0~i-1时间段的和（即前缀和）。
        // 因此[i, j)区间总和的计算就是dp[j] - dp[i]。主要是需要考虑单个时间段比如[i, i)这种情况
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // 符合要求的下标索引组
        ArrayList<int[]> ans = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // sum 是 区间 [i, j-1] 的和，也就是 [i, j) 区间的和
                int sum = preSum[j] - preSum[i];
                // 为了避免计算平均失败率，可以计算总最低失败率，即(j - i + 1) * minAverageLost
                int len = j - i;
                int lost = len * minAverageLost;

                if (sum <= lost) {
                    if (len >= maxLen) {
                        if (len > maxLen) {
                            ans = new ArrayList<>();
                        }
                        ans.add(new int[] {i, j - 1});
                        maxLen = len;
                    }
                }
            }
        }

        if (ans.size() == 0)
            return "NULL";

        ans.sort((a, b) -> a[0] - b[0]);

        StringJoiner sj = new StringJoiner(" ");
        for (int[] an : ans)
            sj.add(an[0] + "-" + an[1]);

        // return ans.stream().map(arr -> arr[0] + "-" + arr[1]).collect(Collectors.joining(" "));
        return sj.toString();
    }
}