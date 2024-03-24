// # (C卷,100分)- ABR 车路协同场景（Java & JS & Python）
//
//#### 题目描述
//
//数轴×有两个点的序列 A={A1， A2, …, Am}和 B={B1, B2, ..., Bn}， Ai 和 Bj 均为正整数， A、 B 已经从小到大排好序， A、 B 均肯定不为空，
//
//给定一个距离 R（正整数），列出同时满足如下条件的所有（Ai， Bj）数对
//
//**条件：**
//
//1. Ai <= Bj
//2. Ai,Bj 距离小于等于 R，但如果 Ai 找不到 R 范围内的 Bj，则列出距它最近的 1 个 Bj，当然此种情况仍然要满足 1，
//
//但如果仍然找不到，就丢弃 Ai。
//
//**原型：**
//
//车路协同场景，一条路上发生了有很多事件（ A），要通过很多路测设备（ B）广播给路上的车，需要给每个事件找到一个合适的路测设备去发送广播消息。
//
//
//
//#### 输入描述
//
//按照**人易读**的格式输入一行数据，参见输入样例，其中“ ABR={， }”中的每个字符都是关键分割符，输入中无空格，其他均为任意正整数，
//
//输入 A 和 B 已经排好序， A 和 B 的大小不超过 50，正整数范围不会超过 65535。
//
//
//
//#### 输出描述z
//
//（ Ai,Bj）数对序列，排列顺序满足序列中前面的 Ax<=后面的 Ay，前面的 Bx<=后面的 By，
//
//因为输入 A 和 B 已经排好序，所以实际上输出结果不用特意排序，排序不是考察点。
//
//
//
//#### 用例
//
//| 输入 | A={1,3,5},B={2,4,6},R=1 |
//| ---- | ----------------------- |
//| 输出 | (1,2)(3,4)(5,6)         |
//| 说明 | 无                      |
//
//
//
//#### 题目解析
//
//首先，输入中有效数据的获取，我使用了正则
//
//const regExp = /A\=\{(.+)\}\,B\=\{(.+)\}\,R\=(.+)/;
//
//其中有三个捕获组，分别捕获出1,3,5和2,4,6以及1
//
//![img](https://img-blog.csdnimg.cn/b72a4dd84b9d4d6dae5d4ef1d7a057ce.png)
//
//然后我们就可以通过一些简单的字符串操作得到A,B,R数据了。
//
//
//
//得到A、B、R后，我们只要双重for，外层遍历A，内层遍历B，然后找满足A[i] + R = B[j]的数据，当然在找的过程中，需要记录第一个比A[i]大的B[j1]，因为要防止找不到满足A[i] + R =
// B[j]的数据时，可以输出一个和A[i]最近的B[j1]
//
//> Ai,Bj 距离小于等于 R，但如果 Ai 找不到 R 范围内的 Bj，则列出距它最近的 1 个 Bj，当然此种情况仍然要满足 1，
//
//------
//
//2023.06.22
//
//根据考友提示，本题要返回的 (Ai,Bj) 数对需要满足下面条件
//
//1. Ai <= Bj
//2. Ai,Bj 距离小于等于 R，但如果 Ai 找不到 R 范围内的 Bj，则列出距它最近的 1 个 Bj，当然此种情况仍然要满足 1
//
//关于其中第2个条件
//
//- 如果对于Ai，存在多个Bj，使其满足Ai <= Bj 且 Bj - Ai <= R，则应该全部输出。
//- 如果对于Ai，不存在Bj 满足Ai <= Bj 且 Bj - Ai <= R，则应该进一步检查是否存在Bj > Ai，如果存在，则输出这些Bj中最小的那个和Ai组成的数对。

package com.lew.algo.hw.od._05_Logic;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luyonglang
 * @date 2024/3/11
 */
public class _002_05_Logic_ABRCarRoad {
    // 输入： A={1,3,5},B={2,4,6},R=1
    // 使用正则解析输入： A=\{(.+)},B=\{(.+)},R=(.+)
    // . 匹配除换行符 \n 之外的任何单字符
    // + 匹配前面的子表达式一次或多次。要匹配 + 字符，请使用 \+
    // * 匹配前面的子表达式零次或多次。要匹配 * 字符，请使用 \*
    // ? 匹配前面的子表达式零次或一次，或指明一个非贪婪限定符。要匹配 ? 字符，请使用 \?
    private static final Pattern PATTERN = Pattern.compile("A=\\{(.+)},B=\\{(.+)},R=(.+)");
    private static final Pattern PATTERN11 = Pattern.compile("A=\\{(.+)},B=\\{(.+)},R=(.+)}");

    public static void main(String[] args) {
        // 输入： A={1,3,5},B={2,4,6},R=1
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // 使用正则解析输入： A=\{(.+)},B=\{(.+)},R=(.+)
        Matcher matcher = PATTERN.matcher(input);
        if (matcher.matches()) {
            String[] groupA = matcher.group(1).split(",");
            String[] groupB = matcher.group(2).split(",");
            int R = Integer.parseInt(matcher.group(3));
            int[] arrayA = Arrays.stream(groupA).mapToInt(Integer::parseInt).toArray();
            int[] arrayB = Arrays.stream(groupB).mapToInt(Integer::parseInt).toArray();
            String result = calculateResult(arrayA, arrayB, R);
            System.out.println(result);
        }
    }

    // 暴力双循环遍历
    private static String calculateResult(int[] arrayA, int[] arrayB, int R) {
        StringBuilder sb = new StringBuilder();
        for (int a : arrayA) {
            int count = 0;
            for (int b : arrayB) {
                // 题目要求 Ai <= Bj
                if (b < a) {
                    continue;
                }
                // 1.第一种：只要满足Ai,Bj 距离小于等于 R，则收集所有（Ai,Bj）
                // 2.第二种：Ai,Bj 距离大于 R，则列出距它最近的 1 个 Bj
                if (b - a <= R || count == 0) {
                    sb.append("(").append(a).append(",").append(b).append(")");
                    count++;
                } else {
                    // 跳出本次循环：因为b是升序排序的，以上条件均不符合则无需继续遍历了
                    break;
                }
            }
        }

        return sb.toString();
    }

}
