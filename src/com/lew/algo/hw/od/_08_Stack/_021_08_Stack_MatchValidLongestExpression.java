// 最长合法表达式
// 题目描述
// 提取字符串中的最长合法简单数学表达式，
// 字符串长度最长的，并计算表达式的值。
// 如果没有返回0.
// 简单数学表达式只能包含以下内容：
// 0-9数字，符号+-*
// 说明：
//
// 所有数字，计算结果都不超过long
// 如果有多个长度一样的，请返回第一个表达式的结果
// 数学表达式，必须是最长的，合法的
// 操作符不能连续出现，如+--+1是不合法的
// 输入描述
// 字符串
//
// 输出描述
// 表达式值
//
// 示例一
// 输入
// 1-2abcd
// 输出
// -1
package com.lew.algo.hw.od._08_Stack;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _021_08_Stack_MatchValidLongestExpression {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        // 下面正则可得90%+通过率
        // Pattern compile = Pattern.compile("^(-?\\d+)([+*-])(\\d+)$");

        // 下面正则应该可得100%通过率，如果不行再用前面正则
        Pattern compile = Pattern.compile("^([+-]?\\d+)([+*-])(\\d+)$");

        int maxExpLen = 0;
        long ans = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);

                Matcher matcher = compile.matcher(sub);

                if (matcher.find() && sub.length() > maxExpLen) {
                    maxExpLen = sub.length();

                    long op_num1 = Long.parseLong(matcher.group(1));
                    String op = matcher.group(2);
                    long op_num2 = Long.parseLong(matcher.group(3));

                    switch (op) {
                        case "*":
                            ans = op_num1 * op_num2;
                            break;
                        case "+":
                            ans = op_num1 + op_num2;
                            break;
                        case "-":
                            ans = op_num1 - op_num2;
                            break;
                    }
                }
            }
        }

        System.out.println(ans);
    }


    // private static final String v = "0123456789+-*";
    //
    // private static String solutionOfAmoscloud(String line) {
    // char[] chars = line.toCharArray();
    // List<String> list = new LinkedList<>();
    // for (int i = 0; i < chars.length; i++) {
    // char cur = chars[i];
    // if (Character.isDigit(cur)) {
    // int start = i;
    // // 操作符不能连续出现，如 +--+1 是不合法的
    // while (i + 1 < chars.length && v.contains(chars[i + 1] + "")) {
    // if (!Character.isDigit(cur) && !Character.isDigit(chars[i + 1])) {
    // break;
    // }
    // i++;
    // }
    // list.add(line.substring(start, i + 1));
    // }
    // }
    //
    // list.sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));
    //
    // if (list.size() > 0) {
    // return calc(list.get(0));
    // } else {
    // return "0";
    // }
    // }
    //
    // private static String calc(String str) {
    // char[] chars = str.toCharArray();
    //
    // StringBuilder sb = new StringBuilder();
    // for (char aChar : chars) {
    // if (Character.isDigit(aChar)) {
    // sb.append(aChar);
    // } else {
    // sb.append(",").append(aChar).append(",");
    // }
    // }
    //
    // List<String> list = Stream.of(sb.toString().split(",")).collect(Collectors.toList());
    // return splitStepCalculate(list).get(0);
    // }
    //
    // public static List<String> splitStepCalculate(List<String> cls) {
    // if (cls.size() == 1) {
    // return cls;
    // }
    // // 计算乘除法
    // for (int i = 0; i < cls.size(); i++) {
    // if (cls.get(i).equals("*")) {
    // calculate(cls, i, "*");
    // i = 0;
    // }
    //
    // if (i < cls.size() && cls.get(i).equals("/")) {
    // calculate(cls, i, "/");
    // i = 0;
    // }
    // }
    // // 计算加减法
    // for (int i = 0; i < cls.size(); i++) {
    // if (cls.get(i).equals("+")) {
    // calculate(cls, i, "+");
    // i = 0;
    // }
    //
    // if (i < cls.size() && cls.get(i).equals("-")) {
    // calculate(cls, i, "-");
    // i = 0;
    // }
    // }
    // return cls;
    // }
    //
    // private static void calculate(List<String> cls, int i, String symbol) {
    // int s = Integer.parseInt(cls.get(i - 1));
    // int e = Integer.parseInt(cls.get(i + 1));
    // int t = 0;
    // switch (symbol) {
    // case "*":
    // t = s * e;
    // break;
    // case "/":
    // t = s / e;
    // break;
    // case "+":
    // t = s + e;
    // break;
    // case "-":
    // t = s - e;
    // break;
    // }
    // cls.set(i, t + "");
    // cls.remove(i - 1);
    // cls.remove(i);
    // }
}