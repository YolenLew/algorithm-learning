// (C卷,100分)- 剩余银饰的重量（Java & JS & Python & C）
// 题目描述
// 有 N 块二手市场收集的银饰，每块银饰的重量都是正整数，收集到的银饰会被熔化用于打造新的饰品。
//
// 每一回合，从中选出三块最重的银饰，然后一起熔掉。
//
// 假设银饰的重量分别为 x 、y和z，且 x ≤ y ≤ z。那么熔掉的可能结果如下：
//
// 如果 x == y == z，那么三块银饰都会被完全熔掉；
// 如果 x == y 且 y != z，会剩余重量为 z - y 的银块无法被熔掉；
// 如果 x != y 且 y == z，会剩余重量为 y - x 的银块无法被熔掉；
// 如果 x != y 且 y != z，会剩余重量为 z - y 与 y - x 差值 的银块无法被熔掉。
// 最后，
//
// 如果剩余两块，返回较大的重量（若两块重量相同，返回任意一块皆可）
// 如果只剩下一块，返回该块的重量
// 如果没有剩下，就返回 0
// 输入描述
// 输入数据为两行：
//
// 第一行为银饰数组长度 n，1 ≤ n ≤ 40，
// 第二行为n块银饰的重量，重量的取值范围为[1，2000]，重量之间使用空格隔开
// 输出描述
// 如果剩余两块，返回较大的重量（若两块重量相同，返回任意一块皆可）；
//
// 如果只剩下一块，返回该块的重量；
//
// 如果没有剩下，就返回 0。
//
// 用例
// 输入 3
// 1 1 1
// 输出 0
// 说明 选出1 1 1，得到 0，最终数组转换为 []，最后没有剩下银块，返回0
// 输入 3
// 3 7 10
// 输出 1
// 说明 选出 3 7 10，需要计算 (7-3) 和 (10-7) 的差值，即(7-3)-(10-7)=1，所以数组转换为 [1]，剩余一块，返回该块重量，返回1
// 题目解析
// 本题应该只是一道逻辑模拟题。
//
// 我们需要每次取出所有银饰中的最重的三个x,y,z，然后按照题目要求的规则：
//
// 如果 x == y == z，那么三块银饰都会被完全熔掉；
// 如果 x == y 且 y != z，会剩余重量为 z - y 的银块无法被熔掉；
// 如果 x != y 且 y == z，会剩余重量为 y - x 的银块无法被熔掉；
// 如果 x != y 且 y != z，会剩余重量为 z - y 与 y - x 差值 的银块无法被熔掉。
// 这里的规则其实可以总结为一个公式：
//
// Math.abs((z - y) - (y - x))
//
// 带入上面x,y,z关系，即可推导出对应结果式。
//
// 这里需要注意的是，当 x != y 且 y != z，此时剩余重量为 z - y 与 y - x 差值 的银块，可能是0，即完全融掉的情况。
//
// 如果每次还有未熔完的银块，则重新加入到银饰中，然后再取出最重的三个银饰按照上面逻辑处理，直到所有银饰的数量不足三个，结束上面逻辑。
//
// 本题数量级不大，因此每次将未熔完的银块重新加入到银饰中后，可以对所有银饰进行重新排序。但是更优的做法是：
//
// 我们只对初始所有银饰进行一次升序，之后取出尾部三个最重的银饰，如果有未熔完的银块remain，那么就在剩余银饰（有序的）进行二分查找remain的有序插入位置，进行插入，这样可以提高效率。
//
// 关于二分查找有序插入位置可以看：
//
// 算法设计 - 二分法和三分法，洛谷P3382_二分法与三分法_伏城之外的博客-CSDN博客

package com.lew.algo.hw.od._02_BinarySearch;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class _033_02_BinarySearch_WeightOfRemainingSliver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 银饰数组长度
        int n = sc.nextInt();
        sc.nextLine();
        String weightsInput = sc.nextLine();
        int[] arr = Arrays.stream(weightsInput.split(" ")).mapToInt(Integer::parseInt).toArray();

        getResult(n, arr);
        System.out.println("----------------------------");
        System.out.println(getResultMy(n, arr));
    }

    private static int getResultMy(int n, int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int num : arr) {
            queue.offer(num);
        }

        while (queue.size() >= 3) {
            // 每一回合，从中选出三块最重的银饰
            Integer z = queue.poll();
            Integer y = queue.poll();
            Integer x = queue.poll();
            // 一起熔掉
            int remaining = Math.abs((z - y) - (y - x));
            if (remaining == 0) {
                // 三块银饰被完全熔掉
                continue;
            }

            // 否则加入队列，继续溶解
            queue.offer(remaining);
        }

        if (queue.size() == 1) {
            return queue.poll();
        } else if (queue.size() == 2) {
            return queue.poll();
        } else {
            return 0;
        }
    }

    private static void getResult(int n, int[] arr) {
        // n块银饰的重量
        LinkedList<Integer> weights = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            weights.add(arr[i]);
        }

        // 升序
        weights.sort(Comparator.comparingInt(a -> a));

        while (weights.size() >= 3) {
            // 尾删三个最大值
            int z = weights.removeLast();
            int y = weights.removeLast();
            int x = weights.removeLast();

            // 如果 x == y == z，那么下面公式结果：remain=0, 表示三块银饰完全融掉
            // 如果 x == y && y != z，那么下面公式结果：remain = z - y
            // 如果 x != y && y == z，那么下面公式结果：remain = y - x
            // 如果 x != y && y != z，那么下面公式结果：remain = Math.abs((z - y) - (y - x))
            int remain = Math.abs((z - y) - (y - x));

            // 如果还有剩余银块
            if (remain != 0) {
                // 那么就二分查找其在剩余升序weights中的有序插入位置
                int idx = Collections.binarySearch(weights, remain);

                if (idx < 0) {
                    idx = -idx - 1;
                }

                // 在有序插入位置插入
                weights.add(idx, remain);
            }
        }

        if (weights.size() == 2) {
            // 如果剩余两块，返回较大的重量（若两块重量相同，返回任意一块皆可）
            System.out.println(Math.max(weights.get(0), weights.get(1)));
        } else if (weights.size() == 1) {
            // 如果只剩下一块，返回该块的重量
            System.out.println(weights.get(0));
        } else {
            // 如果没有剩下，就返回 0
            System.out.println(0);
        }
    }
}