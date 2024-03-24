// (C卷,100分)- 会议室占用时间（Java & JS & Python & C）
//题目描述
//现有若干个会议，所有会议共享一个会议室，用数组表示各个会议的开始时间和结束时间，格式为：
//
//[[会议1开始时间, 会议1结束时间], [会议2开始时间, 会议2结束时间]]
//
//请计算会议室占用时间段。
//
//输入描述
//第一行输入一个整数 n，表示会议数量
//
//之后输入n行，每行两个整数，以空格分隔，分别表示会议开始时间，会议结束时间
//
//输出描述
//输出多行，每个两个整数，以空格分隔，分别表示会议室占用时间段开始和结束
//
//备注
//会议室个数范围：[1, 100]
//会议室时间段：[1, 24]
//用例
//输入	4
//1 4
//2 5
//7 9
//14 18
//输出	1 5
//7 9
//14 18
//说明
//输入：[[1,4],[2,5],[7,9],[14,18]]
//
//输出：[[1,5],[7,9],[14,18]]
//
//说明：时间段[1,4]和[2,5]重叠，合并为[1,5]
//
//输入	2
//1 4
//4 5
//输出	1 5
//说明
//输入：[[1,4],[4,5]]
//
//输出：[[1,5]]
//
//说明：时间段[1,4]和[4,5]连续
//
//题目解析
//本题实际考试时为核心代码模式，非ACM模式，即无需处理输入输出。
//
//本博客代码实现仍然以ACM模式处理，但是会将 "输入输出处理" 与 "核心代码" 分开，大家只看核心代码即可。
//
//本题是区间合并问题。
//
//我们可以将所有区间开始起始位置升序，然后取出第一个区间作为基准值pre，从第二个区间cur开始遍历：
//
//如果 cur.start <= pre.end，则说明两个区间有重叠，此时我们应该将两个区间合并，合并策略是将pre.end = max(pre.end, cur.end)，比如：
//
//pre = [1, 4]，cur = [2, 5]，那么按此策略合并后，pre = [1, 5]
//
//pre = [1, 100]，cur = [7, 9]，那么按此策略合并后，pre = [1, 100]
//
//如果 cur.start > pre.end，则说明两个区间无交集，此时pre无法和后面任何区间合并（因为已经按照开始时间升序了，后面区间的开始时间肯定也大于pre
// .end），此时pre时间段就是一个独立的会议室占用时间，我们将它缓存记录下来，并将更新pre = cur，即将cur作为新的基准值和后面的区间比较
//按此逻辑，即可完成所有区间的合并。

package com.lew.algo.hw.od._06_Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class _044_06_Array_CombinedMeetingOccupancyTime {
    // 输入输出处理
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] roomTimes = new int[n][2];
        for (int i = 0; i < n; i++) {
            roomTimes[i][0] = sc.nextInt();
            roomTimes[i][1] = sc.nextInt();
        }

        int[][] res = new _044_06_Array_CombinedMeetingOccupancyTime().merge(roomTimes);
        for (int[] time : res) {
            System.out.println(time[0] + " " + time[1]);
        }

        int[][] resLeet = new _044_06_Array_CombinedMeetingOccupancyTime().mergeLeet(roomTimes);
        for (int[] time : resLeet) {
            System.out.println(time[0] + " " + time[1]);
        }
    }

    // 本题实际考试时会核心代码模式，无需处理输入输出，只需要写出merge方法实现即可
    public int[][] merge(int[][] roomTimes) {
        // 将各个会议按照开始时间升序
        Arrays.sort(roomTimes, (a, b) -> a[0] - b[0]);

        // 记录合并后的会议室占用时间段
        ArrayList<int[]> list = new ArrayList<>();

        // 上一个会议占用时间段
        int[] pre = roomTimes[0];

        for (int i = 1; i < roomTimes.length; i++) {
            // 当前会议占用时间段
            int[] cur = roomTimes[i];

            if (cur[0] <= pre[1]) {
                // 当前会议开始时间 <= 上一个会议结束时间，则两个会议时间重叠，可以合并
                // 注意合并时，结束时间取两个时间段中较大的结束时间
                pre[1] = Math.max(pre[1], cur[1]);
            } else {
                // 否则不可以合并
                list.add(pre);
                pre = cur;
            }
        }

        list.add(pre);

        return list.toArray(new int[0][]);
    }

    public int[][] mergeLeet(int[][] roomTimes) {
        // 在按第一个元素递增排序的情况下，后一个区间的最小值2 <= 前一个区间的最大值3，说明两区间有重复的部分，此时需要合并。
        //
        // 怎么处理合并的结果
        // 每次合并后的结果放到list中
        // 判断是否需要合并：后一个区间的最小值 <= 前一个区间的最大值？
        // 合并：把list的最后一个数组【前面是已经合并了的，最后一个是待合并的】的第一个元素换成最小值【已经是最小的了，因为是按第一个元素升序排列的】，二个元素换成最大值
        //
        // 作者：低调的M先生
        // 链接：https://leetcode.cn/problems/merge-intervals/solutions/2675176/li-kou-56ti-he-bing-qu-jian-mei-yi-bu-xi-djsw/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        //

        // 数组元素小于1就不用合并了
        if (roomTimes.length <= 1) {
            return roomTimes;
        }

        ArrayList<int[]> res = new ArrayList<>();
        // 按第一个元素排序
        Arrays.sort(roomTimes, Comparator.comparingInt(o -> o[0]));
        for (int[] interval : roomTimes) {
            // 不能合并，就直接添加到list
            if (res.isEmpty() || interval[0] > res.get(res.size() - 1)[1]) {
                res.add(interval);
            } else {
                // 如果最后一个区间和当前区间重叠，就合并
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], interval[1]);
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}