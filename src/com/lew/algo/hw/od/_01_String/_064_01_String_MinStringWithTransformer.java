// (C卷,100分)- 字符串变换最小字符串（Java & JS & Python & C）
// 题目描述
// 给定一个字符串s，最多只能进行一次变换，返回变换后能得到的最小字符串（按照字典序进行比较）。
//
// 变换规则：交换字符串中任意两个不同位置的字符。
//
// 输入描述
// 一串小写字母组成的字符串s
//
// 输出描述
// 按照要求进行变换得到的最小字符串。
//
// 备注
// s是都是小写字符组成
// 1 ≤ s.length ≤ 1000
// 用例
// 输入 abcdef
// 输出 abcdef
// 说明 abcdef已经是最小字符串，不需要交换。
// 输入 bcdefa
// 输出 acdefb
// 说明 a和b进行位置交换，可以得到最小字符串
// 题目解析
// 我的思路如下，先将输入的字符串str进行字典排序，得到一个新串minStr，如果str和minStr相同，则说明str已经是最小字符串，不需要任何变换，直接返回str。
//
// 如果str和minStr不同，则说明str不是最小字符串，我们需要遍历出str的每一个字符，对比str[i] 和 minStr[i] ，具体处理逻辑如下图

package com.lew.algo.hw.od._01_String;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class _064_01_String_MinStringWithTransformer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(getResult(input));
        System.out.println(getResult01(input));
    }

    public static String getResultMy(String s) {
        char[] minCharArr = s.toCharArray();
        Arrays.sort(minCharArr);

        String minStr = new String(minCharArr);
        if (s.equals(minStr)) {
            return s;
        }

        // 寻找第一个不同
        char[] strCharArr = s.toCharArray();
        for (int i = 0; i < minCharArr.length; i++) {
            if (strCharArr[i] != minCharArr[i]) {
                char orgChar = strCharArr[i];
                char minChar = minCharArr[i];
                strCharArr[i] = minChar;
                strCharArr[s.lastIndexOf(minChar)] = orgChar;
                break;
            }
        }
        return new String(strCharArr);
    }

    public static String getResult(String s) {
        char[] minSArr = s.toCharArray();
        Arrays.sort(minSArr);

        String minS = new String(minSArr);
        if (minS.equals(s))
            return s;

        char[] sArr = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            // 对比str[i]和minStrli], 若发现第一个不同，则将str[i]替换为minStrli]
            // 替换前的原值（比min大的字符），也需要在自身找一个min字符替换为原值，为了最小的原则，选择最后一个匹配的地方替换
            if (sArr[i] != minSArr[i]) {
                char tmp = sArr[i];
                sArr[i] = minSArr[i];
                int swapIndex = s.lastIndexOf(minSArr[i]);
                sArr[swapIndex] = tmp;
                break;
            }
        }
        return new String(sArr);
    }

    // 贪心解法
    // 贪心的策略是：
    // 首选一个字典序尽可能小的字符（比如示例中选择字符"a"）
    // 如果有多个字典序最小的字符，则优先选择位置尽可能靠后的那个（比如示例中选择第三个字符"a"）
    // 将该字符交换到尽可能前的位置，即交换到第一个字典序大于该字符的位置（比如示例中索引1的位置）。
    // 所以考虑逆序遍历原字符串，并且使用一个栈（类似一个单调栈），储存原字符串从右往左看遇到的字典序更小的字符的下标。
    // ————————————————
    // 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
    // 原文链接：https://blog.csdn.net/weixin_48157259/article/details/135760018
    public static String getResult01(String s) {
        char[] ans = s.toCharArray();
        int n = s.length();
        char[] lst = s.toCharArray();

        Stack<Integer> stack = new Stack<>();

        // 逆序遍历字符串s
        for (int i = n - 1; i >= 0; i--) {
            // 如果栈是空栈，或者当前下标i对应的字符lst[i]小于栈顶下标对应的字符lst[stack.peek()]
            // 则将坐标i加入stack
            if (stack.isEmpty() || lst[i] < lst[stack.peek()]) {
                stack.push(i);
            }
        }

        // 正序遍历字符串s
        for (int i = 0; i < n; i++) {
            // 若出现空栈情况，则退出循环
            if (stack.isEmpty()) {
                break;
            }
            // 如果当前下标i位于栈顶元素stack.peek()的左边
            // 则可以进行后续判断
            if (i < stack.peek()) {
                // 若当前字符大于栈顶元素对应的字符，则可以进行交换
                if (lst[i] > lst[stack.peek()]) {
                    char temp = lst[i];
                    lst[i] = lst[stack.peek()];
                    lst[stack.peek()] = temp;
                    ans = new String(lst).toCharArray();
                    break;
                }
                // 否则，考虑下一个i
            }
            // 如果当前下标i不位于栈顶元素stack.peek()的左边
            // 则弹出栈顶元素，考虑下一个字典序较大但是位于较右位置的字符
            else {
                stack.pop();
            }
        }

        return new String(ans);
    }

}