// (C卷,100分)- 执行时长（Java & JS & Python）
// 题目描述
// 为了充分发挥GPU算力，需要尽可能多的将任务交给GPU执行，现在有一个任务数组，数组元素表示在这1秒内新增的任务个数且每秒都有新增任务。
//
// 假设GPU最多一次执行n个任务，一次执行耗时1秒，在保证GPU不空闲情况下，最少需要多长时间执行完成。
//
// 输入描述
// 第一个参数为GPU一次最多执行的任务个数，取值范围[1, 10000]
// 第二个参数为任务数组长度，取值范围[1, 10000]
// 第三个参数为任务数组，数字范围[1, 10000]
// 输出描述
// 执行完所有任务最少需要多少秒。
// 用例
// 输入
// 3
// 5
// 1 2 3 4 5
//
// 输出 6
// 说明 一次最多执行3个任务，最少耗时6s
// 输入
// 4
// 5
// 5 4 1 1 1
//
// 输出 5
// 说明 一次最多执行4个任务，最少耗时5s
// 题目解析
// 题干中有一个段话很关键："数组元素表示在这1秒内新增的任务个数且每秒都有新增任务"，也就是说，数组中的任务个数不是一次性push完的，而是每过1s才push一次，而这刚好和GPU单次执行时间1s相温吻合。

package com.lew.algo.hw.od._05_Logic;

import java.util.Scanner;

public class _066_05_Logic_RequestHeapMemory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maxCount = sc.nextInt();
        int n = sc.nextInt();
        int[] tasks = new int[n];
        for (int i = 0; i < n; i++)
            tasks[i] = sc.nextInt();

        System.out.println(getResult(maxCount, tasks));
        System.out.println(getResultMy(maxCount, tasks));
    }

    public static int getResultMy(int maxCount, int[] tasks) {
        int time = 0;
        int remain = 0;

        // 注意：此处采取一秒一秒执行的策略
        for (int task : tasks) {
            if (maxCount >= task + remain) {
                remain = 0;
            } else {
                remain = task + remain - maxCount;
            }
            time++;
        }
        while (remain > 0) {
            remain = remain - maxCount;
            time++;
        }

        return time;
    }

    public static int getResult(int maxCount, int[] tasks) {
        int time = 0;
        int remain = 0;

        // 注意：数组中的任务个数不是一次性push完的，而是每过1s才push一次，而这刚好和GPU单次执行时间1s相温吻合。
        for (int task : tasks) {
            if (task + remain > maxCount) {
                remain = task + remain - maxCount;
            } else {
                remain = 0;
            }
            time++;
        }

        while (remain > 0) {
            remain -= maxCount;
            time++;
        }

        return time;
    }

}