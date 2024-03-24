// (C卷,100分)- 求字符串中所有整数的最小和（Java & JS & Python & C）
// 题目描述
// 输入字符串s，输出s中包含所有整数的最小和。
//
// 说明：
//
// 字符串s，只包含 a-z A-Z ± ；
// 合法的整数包括
//
// 1） 正整数 一个或者多个0-9组成，如 0 2 3 002 102
// 2）负整数 负号 – 开头，数字部分由一个或者多个0-9组成，如 -0 -012 -23 -00023
//
// 输入描述
// 包含数字的字符串
//
// 输出描述
// 所有整数的最小和
//
// 用例
// 输入 bb1234aa
// 输出 10
// 说明 无
// 输入 bb12-34aa
// 输出 -31
// 说明 1+2+（-34） = -31

package com.lew.algo.hw.od._09_Greedy;

import java.math.BigInteger;
import java.util.Scanner;

public class _054_09_Greedy_MinSumOfAllIntegersInString {
    // 贪心地思考问题：为了使得和尽可能地小，当
    //
    // 遇到正数，我们逐个数字相加，譬如遇到"123"，不应该当作数字123来使用，而是应该当作三个数字1、2、3来相加。
    // 遇到负数，我们对整个负数进行整体地考虑。因此需要维护一个变量num，用来表示字符串中的负数。

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(getResult(s));
        System.out.println(getResult01(s));
    }

    private static int getResult(String s) {
        // 在所有负号"-"前面加上一个空格，便于进行分割操作
        s = s.replace("-", " -");
        // 以空格" "为分割依据，对修改后的s进行分割
        // 分割后的每一个子字符串，除了第一个子字符串lst[0]以外
        // 其他子字符串第一个字符一定负号"-"
        String[] lst = s.split(" ");

        // 由于第一个子字符串lst[0]的首个字符不一定是负号"-"
        // 如果不是，我们可在其最前面插上"-a"
        // 使得其可以与其他子字符进行相同的操作
        if (lst[0].charAt(0) != '-') {
            lst[0] = "-a" + lst[0];
        }

        int ans = 0;
        for (String sub_s : lst) {
            // 用于计算位于每一个子字符串最前的负数
            int num = 0;
            // 用于判断负数是否计算完毕的标志
            boolean flag = true;
            for (char ch : sub_s.substring(1).toCharArray()) {
                // 如果遇到数字
                if (Character.isDigit(ch)) {
                    // 如果负数尚未计算完毕
                    if (flag) {
                        num = num * 10 - Character.getNumericValue(ch);
                    }
                    // 如果负数已经计算完毕
                    else {
                        ans += Character.getNumericValue(ch);
                    }
                }
                // 如果遇到其他字符，说明负数计算完毕，将flag修改为false
                else {
                    flag = false;
                }
            }
            // 退出关于子字符串的循环后，ans必须加上位于子字符串开头的负数
            ans += num;
        }

        return ans;
    }

    private static int getResult01(String s) {
        boolean isNegative = false;
        StringBuilder negative = new StringBuilder();

        // int ans = 0;
        BigInteger ans = new BigInteger("0");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                if (isNegative) {
                    negative.append(c);
                } else {
                    // ans += Integer.parseInt(c + "");
                    ans = ans.add(new BigInteger(c + ""));
                }
            } else {
                if (isNegative && negative.length() > 0) {
                    // ans -= Integer.parseInt(negative.toString());
                    ans = ans.subtract(new BigInteger(negative.toString()));
                    negative = new StringBuilder();
                }

                isNegative = c == '-';
            }
        }

        if (negative.length() > 0) {
            // ans -= Integer.parseInt(negative.toString());
            ans = ans.subtract(new BigInteger(negative.toString()));
        }

        return ans.intValue();
    }
}
