// (C卷,100分)- 分割均衡字符串（Java & JS & Python & C）
// 题目描述
// 均衡串定义：字符串中只包含两种字符，且这两种字符的个数相同。
//
// 给定一个均衡字符串，请给出可分割成新的均衡子串的最大个数。
//
// 约定：字符串中只包含大写的 X 和 Y 两种字符。
//
// 输入描述
// 输入一个均衡串。
//
// 字符串的长度：[2， 10000]。
// 给定的字符串均为均衡字符串
// 输出描述
// 输出可分割成新的均衡子串的最大个数。
//
// 备注
// 分割后的子串，是原字符串的连续子串
//
// 用例
// 输入 XXYYXY
// 输出 2
// 说明 XXYYXY可分割为2个均衡子串，分别为：XXYY、XY
// 题目解析
// 本题要求分割出最多的均衡子串，含义其实是分割出来的均衡子串无法再分解。
//
// 比如用例 "XXYYXY" 分解出来的两个子串 "XXYY" 和 "XY" 都是无法再次分解出均衡子串的。
//
// 如果我们从一个均衡串中取走一个的均衡子串，则均衡串剩余部分依旧是一个均衡串，因为取走的均衡子串中X和Y的数量是相等，因此均衡串剩余部分的X和Y数量也一定是相等的，满足均衡串要求。
//
// 因此，本题我们只需要从左往右扫描输入的均衡串每个字符即可，统计扫描过程中，X字符和Y字符的数量，每当两种字符数量相等时，则说明遇到了一个不可分解的均衡子串。

package com.lew.algo.hw.od._09_Greedy;

import java.util.Scanner;

public class _028_09_Greedy_SplitBalanceString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(getResult(input));
        System.out.println(getResultMy(input));
    }

    private static int getResultMy(String input) {
        int countX = 0;
        int countY = 0;
        int ans = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {
                countX++;
            } else {
                countY++;
            }

            if (countX == countY) {
                ans++;
            }
        }

        return ans;

    }

    private static int getResult(String input) {
        // 对于均衡字符串，以下结论非常重要：若将某个已知的均衡字符串s分割为A和B两个子串，且A为均衡字符串，则B一定也为均衡字符串。
        // 因此，对于任意的均衡字符串s，如果它能够被切割成若干个均衡字符串，为了使得最终的均衡子串尽可能多，我们一定会贪心地对它进行切割直到无法再切割为止。
        // 因此只需要一次遍历原字符串s，并同时记录遍历过程中X的个数X_num和Y的个数Y_num，一旦发现X_num == Y_num，则找到了一个子串，立马进行切割。
        // 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
        // 原文链接：https://blog.csdn.net/weixin_48157259/article/details/135406435

        int countX = 0;
        int countY = 0;

        int ans = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {
                countX++;
            } else {
                countY++;
            }

            if (countX == countY) {
                ans++;
            }
        }

        return ans;
    }
}