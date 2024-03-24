// (C卷,100分)- 停车场车辆统计（Java & JS & Python & C）
// 题目描述
// 特定大小的停车场，数组cars[]表示，其中1表示有车，0表示没车。
//
// 车辆大小不一，小车占一个车位（长度1），货车占两个车位（长度2），卡车占三个车位（长度3）。
//
// 统计停车场最少可以停多少辆车，返回具体的数目。
//
// 输入描述
// 整型字符串数组cars[]，其中1表示有车，0表示没车，数组长度小于1000。
//
// 输出描述
// 整型数字字符串，表示最少停车数目。
//
// 用例
// 输入 1,0,1
// 输出 2
// 说明
// 1个小车占第1个车位
//
// 第二个车位空
//
// 1个小车占第3个车位
//
// 最少有两辆车
//
// 输入 1,1,0,0,1,1,1,0,1
// 输出 3
// 说明
// 1个货车占第1、2个车位
//
// 第3、4个车位空
//
// 1个卡车占第5、6、7个车位
//
// 第8个车位空
//
// 1个小车占第9个车位
//
// 最少3辆车
//
// 题目解析
// 这道题的意思应该是：给定了车位占用情况，如 1,1,0,0,1,1,1,0,1，这种车位占用情况，可能停了6辆车，即每个1都停了一个小车，这是最多的情况，但是现在要求最少可能停几辆车。
//
// 解题思路也很简单，先把卡车，即111的停车情况先弄出来，再将火车，即11的停车情况弄出来，最后再弄小车1的情况。

package com.lew.algo.hw.od._15_DoubleIndex;

import java.util.Scanner;

public class _076_15_DoubleIndex_StatisticsOfCarPark {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(getResult(input));
        System.out.println(getResult00(input));
        System.out.println(getResult01(input));
        System.out.println(getResult10(input));
    }

    // 思路: 双指针+ 贪心
    // 题目其实可以转换为考虑若干个停满车的区间，问最少可以停多少辆车
    // 比如有 1,1,1,1,1,1这六个可以停车的位置，我们可以停2辆卡车或者3辆货车或者6辆小车，显
    // 然，停2辆卡车最优，那么贪心的考虑就是优先停卡车，然后如果剩余有位置，就填货车，最后
    // 剩余的位置填小车
    // 统计连续1的个数可以使用双指针算法来实现
    private static int getResult10(String input) {
        char[] charArr = input.toCharArray();
        int n = input.length();
        int l = 0, r = 0; // 双指针
        int res = 0; // 最少停车数量
        while (l < n) {
            if (charArr[l] == 0) {
                l++;
                continue;
            }
            r = l;
            while (r < n && charArr[r] != 0) {
                r++;
            }
            int num = r - l; // 连续1的个数(某一段可以停的车的数量)
            for (int i = 3; i >= 1; i--) { // 从大到小枚举停卡车、货车、小车
                res += num / i;
                num = num % i;
            }
            l = r;
        }

        return res;
    }

    // 字符串替换
    private static int getResult(String input) {
        // 先把卡车，即111的停车情况先弄出来，再将火车，即11的停车情况弄出来，最后再弄小车1的情况
        String str = input.replaceAll(",", "") // 替换,
            .replaceAll("111", "x") // 替换111
            .replaceAll("11", "x") // 替换11
            .replaceAll("1", "x"); // 替换1

        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'x') {
                ans++;
            }
        }
        return ans;
    }

    // 字符串替换
    private static int getResult00(String input) {
        String str = input.replaceAll(",", "") // 替换,
            .replaceAll("111", "x") // 替换111
            .replaceAll("11", "x") // 替换11
            .replaceAll("1", "x"); // 替换1

        return (int)str.chars().filter(c -> c == 'x').count();
    }

    // 字符串替换
    private static int getResult01(String input) {
        String str = input.replaceAll(",", "") // 替换,
            .replaceAll("111", "x") // 替换111
            .replaceAll("11", "x") // 替换11
            .replaceAll("1", "x") // 替换1
            .replaceAll("0", ""); // 替换1

        return str.length();
    }
}