// (C卷,100分)- 部门人力分配（Java & JS & Python & C）
// 题目描述
// 部门在进行需求开发时需要进行人力安排。
//
// 当前部门需要完成 N 个需求，需求用 requirements 表述，requirements[i] 表示第 i 个需求的工作量大小，单位：人月。
//
// 这部分需求需要在 M 个月内完成开发，进行人力安排后每个月人力时固定的。
//
// 目前要求每个月最多有2个需求开发，并且每个月需要完成的需求不能超过部门人力。
//
// 请帮助部门评估在满足需求开发进度的情况下，每个月需要的最小人力是多少？
//
// 输入描述
// 输入为 M 和 requirements，M 表示需求开发时间要求，requirements 表示每个需求工作量大小，N 为 requirements长度，
//
// 1 ≤ N/2 ≤ M ≤ N ≤ 10000
// 1 ≤ requirements[i] ≤ 10^9
// 输出描述
// 对于每一组测试数据，输出部门需要人力需求，行末无多余的空格
//
// 用例
// 输入 3
// 3 5 3 4
// 输出 6
// 说明
// 输入数据两行，
//
// 第一行输入数据3表示开发时间要求，
//
// 第二行输入数据表示需求工作量大小，
//
// 输出数据一行，表示部门人力需求。
//
// 当选择人力为6时，2个需求量为3的工作可以在1个月里完成，其他2个工作各需要1个月完成。可以在3个月内完成所有需求。
//
// 当选择人力为5时，4个工作各需要1个月完成，一共需要4个月才能完成所有需求。
//
// 因此6是部门最小的人力需求。
//
// 题目解析
// 本题是将二分和双指针考点结合在了一起。
//
// 本题我们可以换个说法：
//
// 目前有 N 个人（N个需求），
//
// 每个人的体重为requirements[i]，（每个需求开发需要的人力为requirements[i]）
//
// 以及 M 辆自行车（M个月开发），
//
// 每辆自行车至多坐两人（每个月至多开发两个需求），
//
// 现在想要用 M 辆自行车带走 N 个人，问每辆自行车的限重至少是多少？（M个月开发完N个需求，每个月至少需要多少人力）

package com.lew.algo.hw.od._02_BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class _011_02_BinarySearch_DeptStaffAllocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.nextLine());
        int[] requirements = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(requirements, m));
        System.out.println(getResultMy(requirements, m));
    }

    public static long getResultMy(int[] requirements, int m) {
        Arrays.sort(requirements);

        int n = requirements.length;
        int maxWeight = requirements[n - 1];
        int anotherWeight = requirements[n - 2];
        long left = maxWeight;
        long right = maxWeight + anotherWeight;

        long ans = right;

        // 二分查找方法逼近最小值
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (checkSpeedMy(mid, m, requirements)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private static boolean checkSpeedMy(long limit, int m, int[] requirements) {
        int left = 0;
        int right = requirements.length - 1;

        int count = 0;
        while (left <= right) {
            if (requirements[left] + requirements[right] <= limit) {
                left++;
            }

            right--;
            count++;
        }


        return count <= m;
    }

    public static long getResult(int[] requirements, int m) {
        Arrays.sort(requirements);

        int n = requirements.length;

        // 每辆自行车的限重 至少是 最重的那个人的体重: 保证最重的人可以单独骑一辆自行车:最重的两个人可以骑在一辆自行车
        long min = requirements[n - 1];
        // 每辆自行车的限重 至多是 最重的和次重的那两个的体重
        long max = requirements[n - 2] + requirements[n - 1];

        long ans = max;

        // 二分取中间值
        while (min <= max) {
            long mid = (min + max) >> 1; // 需要注意的是，min，max单独看都不超过int，但是二者相加会超过int，因此需要用long类型

            if (check(mid, m, requirements)) {
                // 如果mid限重，可以满足m辆车带走n个人，则mid就是一个可能解，但不一定是最优解
                ans = mid;
                // 继续尝试更小的限重，即缩小右边界
                max = mid - 1;
            } else {
                // 如果mid限重，不能满足m辆车带走n个人，则mid限重小了，我们应该尝试更大的限重，即扩大左边界
                min = mid + 1;
            }
        }

        return ans;
    }

    /**
     * @param limit        每辆自行车的限重
     * @param m            m辆自行车
     * @param requirements n个人的体重数组
     * @return m辆自行车，每辆限重limit的情况下，能否带走n个人
     */
    public static boolean check(long limit, int m, int[] requirements) {
        int l = 0; // 指向体重最轻的人
        int r = requirements.length - 1; // 指向体重最重的人

        // 需要的自行车数量
        int need = 0;

        while (l <= r) {
            // 如果最轻的人和最重的人可以共享一辆车，则l++,r--，
            // 否则最重的人只能单独坐一辆车，即仅r--
            if (requirements[l] + requirements[r] <= limit) {
                l++;
            }
            r--;
            // 用掉一辆车
            need++;
        }

        // 如果m >= need，当前有的自行车数量足够
        return m >= need;
    }

    /**
     * @param limit        每辆自行车的限重
     * @param m            m辆自行车
     * @param requirements n个人的体重数组
     * @return m辆自行车，每辆限重limit的情况下，能否带走n个人
     */
    public static boolean checkSpeed(long limit, int m, int[] requirements) {
        int left = 0;
        int right = requirements.length - 1;

        // 自行车个数
        int count = 0;

        // 以下逻辑：一辆车能带走几个人
        // 首先，肯定先带走最重的人，即右指针的人，所以每次right--
        // 然后，可以判断 左边界和右边界 的和是否小于等于limit，如果满足，那么额外还有带走左指针的人，所以left++
        while (left <= right) {
            if (requirements[left] + requirements[right] <= limit) {
                // 如果最轻的人和最重的人可以共享一辆车，则l++,r--：说明一辆车就能带走两个人，所以左指针也要递增
                // 否则最重的人只能单独坐一辆车，即仅r--
                left++;
            }

            // 右指针每次必须递减，
            right--;
            // 自行车数增加
            count++;
        }

        return count <= m;
    }
}