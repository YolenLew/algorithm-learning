// (C卷,200分)- 小朋友分组最少调整次数（Java & JS & Python & C）
// 题目描述
// n 个学生排成一排，学生编号分别是 1 到 n，n 为 3 的整倍数。
//
// 老师随机抽签决定将所有学生分成 m 个 3 人的小组（n == 3 * m） ，
//
// 为了便于同组学生交流，老师决定将小组成员安排到一起，也就是同组成员彼此相连，同组任意两个成员之间无其它组的成员。
//
// 因此老师决定调整队伍，老师每次可以调整任何一名学生到队伍的任意位置，计为调整了一次， 请计算最少调整多少次可以达到目标。
//
// 注意：对于小组之间没有顺序要求，同组学生之间没有顺序要求。
//
// 输入描述
// 第一行输入初始排队顺序序列
//
// 第二行输入分组排队顺序序列
//
// 输出描述
// 最少调整多少次数
//
// 用例
// 输入 4 2 8 5 3 6 1 9 7
// 6 3 1 2 4 8 7 9 5
// 输出 1
// 说明
// 分组分别为：6,3,1一组，2,4,8一组，7,9,5一组
//
// 初始排队顺序中，只要将5移动到1后面，变为：
//
// 4 2 8 3 6 1 5 9 7
//
// 即可满足分组排队顺序要求。
//
// 因此至少需要调整1次站位。
//
// 输入 8 9 7 5 6 3 2 1 4
// 7 8 9 4 2 1 3 5 6
// 输出 0
// 说明 无
// 输入 7 9 8 5 6 4 2 1 3
// 7 8 9 4 2 1 3 5 6
// 输出 1
// 说明 无
// 题目解析
// 本题有两个难点：
//
// 如何快速判断两个小朋友是否为一组
// 如何调整站队，才能花费次数最少

package com.lew.algo.hw.od._09_Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class _053_09_Greedy_MinAdjustmentsForChildrenGroups {
    // 分块（即连续的相同组的小朋友）
    static class NumCount {
        int num;
        int count;

        public NumCount(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始小朋友（序号）排队顺序
        int[] initialNums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = initialNums.length;

        // 序号->组号 映射关系
        int[] numsToGroupMap = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            numsToGroupMap[num] = i / 3;
        }

        // 初始小朋友（组号）排队顺序
        initialNums = Arrays.stream(initialNums).map(num -> numsToGroupMap[num]).toArray();

        // key是组号，val是对应组号的小朋友分块
        HashMap<Integer, ArrayList<NumCount>> blocks = new HashMap<>();

        // 相邻相同组号合并为块
        LinkedList<NumCount> queue = new LinkedList<>();
        for (int num : initialNums) {
            if (queue.isEmpty() || queue.getLast().num != num) {
                queue.addLast(new NumCount(num, 1));
                // 记录相同组号的各个分块
                blocks.putIfAbsent(num, new ArrayList<>());
                blocks.get(num).add(queue.getLast());
            } else {
                queue.getLast().count++;
            }
        }

        // 记录调整位置次数
        int moved_count = 0;

        while (queue.size() > 0) {
            NumCount first = queue.removeFirst();
            // 如果开头块是空的，或者开头块已经包含3个小朋友，那么不需要调整位置
            if (first.count == 0 || first.count == 3)
                continue;

            if (queue.size() == 0)
                break;

            // 第二块
            NumCount second = queue.getFirst();
            while (second.count == 0) {
                queue.removeFirst();
                second = queue.getFirst();
            }

            // 如果开头块和第二块组号相同，则合并（前面并入后面）
            if (first.num == second.num) {
                second.count += first.count;
                continue;
            }

            /* 如果开头块和第二块组号不同，则进入具体情况分析 */

            if (first.count == 2) {
                // 开头块有2个小朋友，则情况如下组号1例子，此时需要将后面的单独1，并入开头两个1中，即调整一次
                // 1 1 x 1
                moved_count += 1;
                // 后面单独1所在块的小朋友数量清空
                blocks.get(first.num).forEach(block -> block.count = 0);
                continue;
            }

            if (first.count == 1) {
                // 开头块只有1个小朋友，则有两种情况
                if (blocks.get(first.num).size() == 3) {
                    // 对于组号的分块有三个，即如下组号1例子
                    // 1 x 1 y 1 z
                    // 此时需要将后面两个单独1，并入到开头1中，即调整两次
                    moved_count += 2;
                    // 后面两个单独1所在块的小朋友数量清空
                    blocks.get(first.num).forEach(block -> block.count = 0);
                } else {
                    // 对于组号的分块有两个，则如下组号1例子
                    // 1 x 1 1
                    // 此时需要将开头单独1并入到后面两个1中，即调整一次
                    moved_count += 1;
                    // 后面两个1所在块的小朋友数量变为3个
                    blocks.get(first.num).forEach(block -> block.count = 3);
                }
            }
        }

        System.out.println(moved_count);
    }
}