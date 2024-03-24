// (C卷,100分)- 执行任务赚积分（Java & JS & Python & C）
// 题目描述
// 现有N个任务需要处理，同一时间只能处理一个任务，处理每个任务所需要的时间固定为1。
//
// 每个任务都有最晚处理时间限制和积分值，在最晚处理时间点之前处理完成任务才可获得对应的积分奖励。
//
// 可用于处理任务的时间有限，请问在有限的时间内，可获得的最多积分。
//
// 输入描述
// 第一行为一个数 N，表示有 N 个任务
//
// 1 ≤ N ≤ 100
// 第二行为一个数 T，表示可用于处理任务的时间
//
// 1 ≤ T ≤ 100
// 接下来 N 行，每行两个空格分隔的整数（SLA 和 V），SLA 表示任务的最晚处理时间，V 表示任务对应的积分。
//
// 1 ≤ SLA ≤ 100
// 0 ≤ V ≤ 100000
// 输出描述
// 可获得的最多积分
//
// 用例
// 输入 4
// 3
// 1 2
// 1 3
// 1 4
// 1 5
// 输出 5
// 说明 虽然有3个单位的时间用于处理任务，可是所有任务在时刻1之后都无效。
// 所以在第1个时间单位内，选择处理有5个积分的任务。1-3时无任务处理。
// 输入 4
// 3
// 1 2
// 1 3
// 1 4
// 3 5
// 输出 9
// 说明
// 第1个时间单位内，处理任务3，获得4个积分
//
// 第2个时间单位内，处理任务4，获得5个积分
//
// 第3个时间单位内，无任务可处理
//
// 共获得9个积分
//
// 题目解析
// 本题类似于华为校招机试 - 工单调度策略（20220413）_伏城之外的博客-CSDN博客
//
// 本题解析可以参考上面博客。
//
// 另外，本题增加了一个时限T，因为每个任务固定消耗1单位时间，因此时限T单位时间，最多可以完成T个任务。
//
// 我们只需要检查最后优先队列中记录的任务数量是否大于T，如果大于T，则将删除超出部分的任务，而被删除的任务都是积分小的。
//
// 本题使用到了优先队列，对于Java和Python而言，有内置的优先队列实现类。
//
// 而JS和C语言没有，因此我们需要手动实现一个优先队列，关于优先队列的实现可以参考：
//
// LeetCode - 1705 吃苹果的最大数目-CSDN博客 中对于优先队列的解释说明，了解优先队列的底层数据结构，以及上浮、下沉等行为函数

package com.lew.algo.hw.od._09_Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class _031_09_Greedy_ExecutingTask {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int[][] tasks = new int[N][2];
        for (int i = 0; i < N; i++) {
            tasks[i][0] = sc.nextInt();
            tasks[i][1] = sc.nextInt();
        }
        System.out.println(getResult(tasks, T));
        System.out.println(getResultMy(tasks, T));
    }

    public static int getResultMy(int[][] wos, int t) {
        // 按照任务截止时间升序
        Arrays.sort(wos, Comparator.comparingInt(a -> a[0]));

        // pq用于按照积分对任务进行优先级排序，降序排列，积分越大，优先级越高，目的是为了取每一时刻能完成工单的最高积分
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        int ans = 0;

        int currentIdx = wos.length - 1;

        // 时间点倒叙排列，获取当前时间点最高积分的工单
        for (int i = t; i > 0; i--) {
//            for (; 0 <= currentIdx; currentIdx--) {
//                // 判断任务能否在t时刻完成
//                int[] wo = wos[currentIdx];
//                if (wo[0] < i) {
//                    break;
//                }
//                queue.add(wo[1]);
//            }

            while (currentIdx >= 0) {
                int[] wo = wos[currentIdx];

                if (wo[0] >= i) {
                    queue.add(wo[1]);
                    currentIdx--;
                } else {
                    break;
                }
            }

            // 遍历队列，获取该时刻的最大积分
            Integer maxScore = queue.poll();
            ans += maxScore == null ? 0 : maxScore;
        }

        return ans;
    }

    public static int getResult(int[][] wos, int t) {
        // 按照任务截止时间升序
        Arrays.sort(wos, Comparator.comparingInt(a -> a[0]));

        // pq用于按照积分对任务进行优先级排序，积分越小，优先级越高，目的是为了每次替换掉最少积分的工单
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a));

        // 当前时间
        int curTime = 0;
        // 已获得的积分
        int ans = 0;

        // 遍历任务
        for (int[] wo : wos) {
            int endTime = wo[0]; // 任务截止时间
            int score = wo[1]; // 任务积分

            if (curTime < endTime) {
                // 如果 curTime < 当前任务的截止时间，则curTime时刻可以指向当前任务
                pq.offer(score);
                ans += score;
                curTime++;
            } else {
                // 如果 curTime >= 当前任务的截止时间，则当前任务只能在curTime时刻之前找一个时间点执行
                // pq中记录的就是curTime之前时刻执行的任务
                if (pq.size() == 0) {
                    continue;
                }

                // 此时取出pq记录的可执行的任务中最小积分的那个
                int min_score = pq.peek();

                // 如果当前任务的积分 > 前面时间内可执行的任务中最小积分
                if (score > min_score) {
                    // 则我们应该将执行pq中最小积分任务的时间，用于执行当前任务，因为这样可以获得更大积分
                    pq.poll();
                    pq.offer(score);
                    ans += score - min_score;
                }
            }
        }

        // 由于时间限制为t单位，而每个任务花费1单位时间，因此最多完成t个任务，对于多出任务应该去除，且优先去除积分少的
        while (pq.size() > t && t > 0) {
            ans -= pq.poll();
        }

        return ans;
    }
}