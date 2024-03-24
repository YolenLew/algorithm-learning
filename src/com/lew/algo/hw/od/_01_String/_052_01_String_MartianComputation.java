// (C卷,100分)- 火星文计算（Java & JS & Python）
// 题目描述
// 已知火星人使用的运算符为#、$，其与地球人的等价公式如下：
//
// x#y = 2*x+3*y+4
//
// x$y = 3*x+y+2
//
// 其中x、y是无符号整数
// 地球人公式按C语言规则计算
// 火星人公式中，$的优先级高于#，相同的运算符，按从左到右的顺序计算
// 现有一段火星人的字符串报文，请你来翻译并计算结果。
//
// 输入描述
// 火星人字符串表达式（结尾不带回车换行）
//
// 输入的字符串说明：字符串为仅由无符号整数和操作符（#、$）组成的计算表达式。
//
// 例如：123#4$5#67$78。
//
// 用例保证字符串中，操作数与操作符之间没有任何分隔符。
// 用例保证操作数取值范围为32位无符号整数。
// 保证输入以及计算结果不会出现整型溢出。
// 保证输入的字符串为合法的求值报文，例如：123#4$5#67$78
// 保证不会出现非法的求值报文，例如类似这样字符串：
// #4$5 //缺少操作数
//
// 4$5# //缺少操作数
//
// 4#$5 //缺少操作数
//
// 4 $5 //有空格
//
// 3+4-5*6/7 //有其它操作符
//
// 12345678987654321$54321 //32位整数计算溢出
//
// 输出描述
// 根据输入的火星人字符串输出计算结果（结尾不带回车换行）。
//
// 用例
// 输入 7#6$5#12
// 输出 226
// 说明
// 7#6$5#12
//
// =7#(3*6+5+2)#12
//
// =7#25#12
//
// =(2*7+3*25+4)#12
//
// =93#12
//
// =2*93+3*12+4
//
// =226

package com.lew.algo.hw.od._01_String;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _052_01_String_MartianComputation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(getResult(str));
        System.out.println(getResultMy(str));
    }

    public static long getResultMy(String str) {
        Pattern dollarPattern = Pattern.compile("(\\d+)\\$(\\d+)");
        while (true) {
            Matcher dollarMatch = dollarPattern.matcher(str);
            if (dollarMatch.find()) {
                String subStr = dollarMatch.group(0);
                long x = Long.parseLong(dollarMatch.group(1));
                long y = Long.parseLong(dollarMatch.group(2));
                str = str.replaceFirst(subStr.replace("$", "\\$"), 3 * x + y + 2 + "");
            } else {
                break;
            }
        }
        // 接下来计算#
        long[] nums = Arrays.stream(str.split("#")).mapToLong(Long::parseLong).toArray();
        long x1 = nums[0];
        for (int i = 1; i < nums.length; i++) {
            x1 = 2 * x1 + 3 * nums[i] + 4;
        }
        return x1;
    }

    public static long getResult(String str) {
        Pattern p = Pattern.compile("(\\d+)\\$(\\d+)");

        while (true) {
            Matcher m = p.matcher(str);
            if (!m.find())
                break;

            String subStr = m.group(0);
            long x = Long.parseLong(m.group(1));
            long y = Long.parseLong(m.group(2));
            str = str.replaceFirst(subStr.replace("$", "\\$"), 3 * x + y + 2 + "");
        }

        long[] array = Arrays.stream(str.split("#")).mapToLong(Long::parseLong).toArray();
        long x1 = array[0];
        for (int i = 1; i < array.length; i++) {
            x1 = 2 * x1 + 3 * array[i] + 4;
        }
        System.out.println(x1);

        return Arrays.stream(str.split("#")).map(Long::parseLong).reduce((x, y) -> 2 * x + 3 * y + 4).orElse(0L);
    }
}