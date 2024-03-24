// (C卷,100分)- 密码输入检测（Java & JS & Python & C）
// 题目描述
// 给定用户密码输入流 input，输入流中字符 '<' 表示退格，可以清除前一个输入的字符，请你编写程序，输出最终得到的密码字符，并判断密码是否满足如下的密码安全要求。
//
// 密码安全要求如下：
//
// 密码长度 ≥ 8；
// 密码至少需要包含 1 个大写字母；
// 密码至少需要包含 1 个小写字母；
// 密码至少需要包含 1 个数字；
// 密码至少需要包含 1 个字母和数字以外的非空白特殊字符；
// 注意空串退格后仍然为空串，且用户输入的字符串不包含 '<' 字符和空白字符。
//
// 输入描述
// 用一行字符串表示输入的用户数据，输入的字符串中 '<' 字符标识退格，用户输入的字符串不包含空白字符，例如：
//
// ABC<c89%000<
//
// 输出描述
// 输出经过程序处理后，输出的实际密码字符串，并输出改密码字符串是否满足密码安全要求。两者间由 ',' 分隔， 例如：
//
// ABc89%00,true
//
// 用例
// 输入 ABC<c89%000<
// 输出 ABc89%00,true
// 说明 多余的C和0由于退格被去除,最终用户输入的密码为ABc89%00，且满足密码安全要求，输出true
// 题目解析
// 本题可以利用栈的压栈来模拟用户输入字符，弹栈来模拟'<'退格操作。
//
// 最后统计栈中：所有字符数量>=8 && 小写字母数量 >= 1 && 大写字母数量 >= 1 && 数字数量 >= 1 && 非字母数字空白字符数量 >= 1
//
// 若满足，则将栈中字符拼接为字符串后再追加“true”，否则追加“false”

package com.lew.algo.hw.od._08_Stack;

import java.util.LinkedList;
import java.util.Scanner;

public class _016_08_Stack_TestPasswordInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    private static String getResult(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '<') {
                if (stack.isEmpty())
                    continue;
                stack.removeLast();
            } else {
                stack.addLast(c);
            }
        }

        int upper = 0;
        int lower = 0;
        int number = 0;
        int non_letter_number = 0;

        StringBuilder password = new StringBuilder();
        for (Character c : stack) {
            password.append(c);

            if (c >= 'a' && c <= 'z') {
                lower++;
            } else if (c >= 'A' && c <= 'Z') {
                upper++;
            } else if (c >= '0' && c <= '9') {
                number++;
            } else {
                non_letter_number++;
            }
        }

        if (password.length() >= 8 && lower >= 1 && upper >= 1 && number >= 1 && non_letter_number >= 1) {
            password.append(",true");
        } else {
            password.append(",false");
        }

        return password.toString();
    }
}