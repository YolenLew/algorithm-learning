// (C卷,100分)- 表达式括号匹配（Java & JS & Python & C）
// 题目描述
// (1+(2+3)*(3+(8+0))+1-2)这是一个简单的数学表达式,今天不是计算它的值,而是比较它的括号匹配是否正确。
//
// 前面这个式子可以简化为(()(()))这样的括号我们认为它是匹配正确的,
//
// 而((())这样的我们就说他是错误的。注意括号里面的表达式可能是错的,也可能有多个空格，对于这些我们是不用去管的，
//
// 我们只关心括号是否使用正确。
//
// 输入描述
// 给出一行表达式(长度不超过 100)。
//
// 输出描述
// 如果匹配正确输出括号的对数，否则输出-1。
//
// 用例
// 输入 (1+(2+3)*(3+(8+0))+1-2)
// 输出 4
// 说明 无
// 题目解析
// 本题就是括号匹配的变种题，只是加入了一些干扰字符，我们可以用正则去掉非()的字符，然后利用栈结构校验括号是否成对

package com.lew.algo.hw.od._08_Stack;

import java.util.Scanner;
import java.util.Stack;

public class _010_08_Stack_MatchExpressionParenthesis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int result = getResult(input);
        System.out.println(result);
        System.out.println(getResultMy(input));
    }

    private static int getResultMy(String input) {
        // 栈：先进后出，遇到左符号就入栈，遇到右符号就去栈里栈顶匹配
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.pop() == '(') {
                    ans++;
                } else {
                    return -1;
                }
            }
        }

        // YOLEN注意：最终还需要判断stack是否为空！！！
        if (!stack.isEmpty()) {
            return -1;
        }

        return ans;
    }

    private static int getResult(String input) {
        // 栈：先进后出，遇到左符号就入栈，遇到右符号就去栈里栈顶匹配
        Stack<Character> stack = new Stack<>();
        // 合法括号的对数
        int result = 0;

        for (char ch : input.toCharArray()) {
            if (ch != '(' && ch != ')') {
                continue;
            }

            if (ch == '(') {
                // 左括号入栈
                stack.push(ch);
            } else {
                // 右括号：去栈里栈顶匹配
                if (!stack.isEmpty() && stack.peek() == '(') {
                    result++;
                    stack.pop();
                } else {
                    return -1;
                }
            }
        }
        // 注意：最终还需要判断stack是否为空！！！
        if (!stack.isEmpty()) {
            return -1;
        }
        return result;
    }

}