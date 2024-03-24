// (C卷,100分)- CPU算力分配（Java & JS & Python & C）
//题目描述
//现有两组服务器A和B，每组有多个算力不同的CPU，其中 A[i] 是 A 组第 i 个CPU的运算能力，B[i] 是 B组 第 i 个CPU的运算能力。
//
//一组服务器的总算力是各CPU的算力之和。
//
//为了让两组服务器的算力相等，允许从每组各选出一个CPU进行一次交换，
//
//求两组服务器中，用于交换的CPU的算力，并且要求从A组服务器中选出的CPU，算力尽可能小。
//
//输入描述
//第一行输入为L1和L2，以空格分隔，L1表示A组服务器中的CPU数量，L2表示B组服务器中的CPU数量。
//
//第二行输入为A组服务器中各个CPU的算力值，以空格分隔。
//
//第三行输入为B组服务器中各个CPU的算力值，以空格分隔。
//
//1 ≤ L1 ≤ 10000
//1 ≤ L2 ≤ 10000
//1 ≤ A[i] ≤ 100000
//1 ≤ B[i] ≤ 100000
//输出描述
//对于每组测试数据，输出两个整数，以空格分隔，依次表示A组选出的CPU算力，B组选出的CPU算力。
//
//要求从A组选出的CPU的算力尽可能小。
//
//备注
//保证两组服务器的初始总算力不同。
//答案肯定存在
//用例
//输入	2 2
//1 1
//2 2
//输出	1 2
//说明	从A组中选出算力为1的CPU，与B组中算力为2的进行交换，使两组服务器的算力都等于3。
//输入	2 2
//1 2
//2 3
//输出	1 2
//说明	无
//输入	1 2
//2
//1 3
//输出	2 3
//说明	无
//输入	3 2
//1 2 5
//2 4
//输出	5 4
//说明	无
//题目解析
//假设A组服务器算力之和为sumA，B组服务器算力之和为sumB，将A组的a和B组的b交换后，A组算力之和等于B组算力之和，则可得公式如下：
//
//sumA - a + b = sumB - b + a
//
//sumA - sumB = 2 * (a - b)
//
//a - b = (sumA - sumB) / 2
//
//其中 sumA, sumB 是已知的，因此，我们可以遍历A组所有元素a，计算出b= a - (sumA - sumB)/2，看B组中是否存在对应b，若存在，则a b就是题解。
//
//2023.12.05
//
//本题可能存在多组符合要求的用于交换的a、b，我们需要保存其中最小的a对应的a、b对

package com.lew.algo.hw.od._05_Logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _004_05_Logic_AllocateCPUCalculation {
    // 假设A组服务器算力之和为sumA，B组服务器算力之和为sumB，将A组的a和B组的b交换后，A组算力之和等于B组算力之和，则可得公式如下：
    //
    //sumA - a + b = sumB - b + a
    //sumA - sumB = 2 * (a - b)
    //a - b = (sumA - sumB) / 2
    //
    //其中 sumA, sumB 是已知的，因此，我们可以遍历A组所有元素a，计算出b= a - (sumA - sumB)/2，看B组中是否存在对应b，若存在，则a b就是题解。
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int L1 = scanner.nextInt(); // A组服务器中的CPU数量
        int L2 = scanner.nextInt(); // B组服务器中的CPU数量
        int[] A = new int[L1]; // A组服务器中各个CPU的算力值
        int[] B = new int[L2]; // B组服务器中各个CPU的算力值

        // 读取A组服务器中各个CPU的算力值
        for (int i = 0; i < L1; i++) {
            A[i] = scanner.nextInt();
        }

        // 读取B组服务器中各个CPU的算力值
        for (int i = 0; i < L2; i++) {
            B[i] = scanner.nextInt();
        }

        // 计算 A 组和 B 组的总算力
        int sumA = Arrays.stream(A).sum(); // 计算A组服务器的总算力
        int sumB = Arrays.stream(B).sum(); // 计算B组服务器的总算力

        // 记录 A 组中每个算力的位置，使用Map来记录每个算力值对应的索引位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < L1; i++) {
            map.put(A[i], i); // 将算力值及其索引位置加入到map中
        }

        // 记录用于交换的最小的a
        int minA = Integer.MAX_VALUE;
        String ans = "";

        // 遍历 B 组的算力，查看是否存在一个算力能够使得两组服务器的算力相等
        for (int i = 0; i < L2; i++) {
            // a = b + (sumA -sumB) / 2;
            int target = (sumA - sumB + 2 * B[i]) / 2; // 计算目标算力值
            if (map.containsKey(target) && (target < minA)) {
                    minA = target;
                    ans = target + " " + B[i];

            }
        }

        System.out.println(ans);
    }
}