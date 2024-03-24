// (C卷,100分)- 查找众数及中位数（Java & JS & Python & C）
// 题目描述
// 众数是指一组数据中出现次数量多的那个数，众数可以是多个。
//
// 中位数是指把一组数据从小到大排列，最中间的那个数，如果这组数据的个数是奇数，那最中间那个就是中位数，如果这组数据的个数为偶数，那就把中间的两个数之和除以2，所得的结果就是中位数。
//
// 查找整型数组中元素的众数并组成一个新的数组，求新数组的中位数。
//
// 输入描述
// 输入一个一维整型数组，数组大小取值范围 0<N<1000，数组中每个元素取值范围 0<E<1000
//
// 输出描述
// 输出众数组成的新数组的中位数
//
// 用例
// 输入 10 11 21 19 21 17 21 16 21 18 15
// 输出 21
// 输入 2 1 5 4 3 3 9 2 7 4 6 2 15 4 2 4
// 输出 3
// 输入 5 1 5 3 5 2 5 5 7 6 7 3 7 11 7 55 7 9 98 9 17 9 15 9 9 1 39
// 输出 7

package com.lew.algo.hw.od._10_Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _068_10_Math_CalculateModeAndMedium {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        System.out.println(getResult(input));
        System.out.println(getResultHash(input));
    }

    public static int getResult(String str) {
        Integer[] nums = Arrays.stream(str.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        HashMap<Integer, Integer> count = new HashMap<>();

        // 统计各数字出现次数
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // 获取最大出现次数
        int max = count.values().stream().max((a, b) -> a - b).orElse(0);

        // 将众数挑选出来
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer k : count.keySet()) {
            if (count.get(k) == max)
                ans.add(k);
        }
        // List<Integer> ans1 = count.entrySet().stream()
        // .filter(entry -> entry.getValue() == max)
        // .map(Map.Entry::getKey)
        // .collect(Collectors.toList());

        // 众数升序
        ans.sort((a, b) -> a - b);

        // 中位数取值
        int mid = ans.size() / 2;
        if (ans.size() % 2 == 0) {
            // 偶数个数时，取中间两个位置的平均值
            return (ans.get(mid) + ans.get(mid - 1)) / 2;
        } else {
            // 奇数个数时，取中间位置的值
            return ans.get(mid);
        }
    }

    // 思路二：哈希表模拟+排序
    // 按照题目的规则，使用哈希表去统计原数组中每个元素出现的次数，然后再把出现次数最大的一些数字存到一个新数组中，最后再对新数组进行排序，并根据新数组长度的奇偶性来确定最终输出的中位数的值。具体可以参考下面代码
    private static int getResultHash(String str) {
        String[] input = str.split(" ");
        int[] w = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            w[i] = Integer.parseInt(input[i]);
        }

        Map<Integer, Integer> mp = new HashMap<>();
        for (int x : w) {
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }

        int maxCnt = Collections.max(mp.values());
        List<Integer> num = new ArrayList<>();
        for (int k : mp.keySet()) {
            if (mp.get(k) == maxCnt) {
                num.add(k);
            }
        }

        Collections.sort(num);
        int n = num.size();
        if (n % 2 == 1) {
            return num.get(n / 2);
        } else {
            return (num.get(n / 2) + num.get(n / 2 - 1)) / 2;
        }
    }

}