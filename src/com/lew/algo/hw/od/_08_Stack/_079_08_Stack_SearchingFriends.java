// (C卷,100分)- 找朋友（Java & JS & Python & C）
// 题目描述
// 在学校中，N个小朋友站成一队， 第i个小朋友的身高为height[i]，
//
// 第i个小朋友可以看到的第一个比自己身高更高的小朋友j，那么j是i的好朋友(要求j > i)。
//
// 请重新生成一个列表，对应位置的输出是每个小朋友的好朋友位置，如果没有看到好朋友，请在该位置用0代替。
//
// 小朋友人数范围是 [0, 40000]。
//
// 输入描述
// 第一行输入N，N表示有N个小朋友
//
// 第二行输入N个小朋友的身高height[i]，都是整数
//
// 输出描述
// 输出N个小朋友的好朋友的位置
//
// 用例
// 输入 2
// 100 95
// 输出 0 0
// 说明
// 第一个小朋友身高100，站在队尾位置，向队首看，没有比他身高高的小朋友，所以输出第一个值为0。
//
// 第二个小朋友站在队首，前面也没有比他身高高的小朋友，所以输出第二个值为0。
//
// 输入 8
// 123 124 125 121 119 122 126 123
// 输出 1 2 6 5 5 6 0 0
// 说明 123的好朋友是1位置上的124
// 124的好朋友是2位置上的125
// 125的好朋友是6位置上的126
// 以此类推

package com.lew.algo.hw.od._08_Stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class _079_08_Stack_SearchingFriends {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println(getResult(arr));
        System.out.println(getResultMy(arr));
    }

    // 单调递减栈：求下一个比自身大的元素
    public static String getResultMy(int[] arr) {
        int[] result = new int[arr.length];
        // 单调栈：存储元素的索引
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 第i个小朋友的身高h，需要不断地与栈顶元素比较
            // 如果栈顶元素存在并且h > 栈顶元素 stack.peek()
            // 意味着栈顶元素找到了右边最近的比他更高的身高h
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                // 上一个矮个子同学的索引
                Integer topIndex = stack.pop();
                result[topIndex] = i;
            }
            stack.push(i);
        }

        return Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static String getResult(int[] arr) {
        LinkedList<int[]> stack = new LinkedList<>();

        int len = arr.length;
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            int ele = arr[i];

            while (true) {
                if (stack.size() == 0) {
                    stack.add(new int[] {ele, i});
                    break;
                }

                int[] peek = stack.getLast();
                int peekEle = peek[0];
                int peekIndex = peek[1];

                if (ele > peekEle) {
                    res[peekIndex] = i;
                    stack.removeLast();
                } else {
                    stack.add(new int[] {ele, i});
                    break;
                }
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for (int v : res) {
            sj.add(v + "");
        }

        return sj.toString();
    }
}