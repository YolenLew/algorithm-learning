// (C卷,100分)- 机器人搬砖（Java & JS & Python & C）
// 题目描述
// 机器人搬砖，一共有 N 堆砖存放在 N 个不同的仓库中，第 i 堆砖中有 bricks[i] 块砖头，要求在 8 小时内搬完。
//
// 机器人每小时能搬砖的数量取决于有多少能量格，机器人一个小时中只能在一个仓库中搬砖，机器人的能量格只在这一个小时有效，为使得机器人损耗最小化，应尽量减小每次补充的能量格数。
//
// 为了保障在 8 小时内能完成搬砖任务，请计算每小时给机器人充能的最小能量格数。
//
// 无需考虑机器人补充能力格的耗时；
// 无需考虑机器人搬砖的耗时；
// 机器人每小时补充能量格只在这一个小时中有效；
// 输入描述
// 第一行为一行数字，空格分隔
//
// 输出描述
// 机器人每小时最少需要充的能量格，若无法完成任务，输出 -1
//
// 用例
// 输入 30 12 25 8 19
// 输出 15
// 说明 无
// 输入 10 12 25 8 19 8 6 4 17 19 20 30
// 输出 -1
// 说明 砖的堆数为12堆存放在12个仓库中，机器人一个小时内只能在一个仓库搬砖，不可能完成任务。
// 题目解析
// 本题有个关键说明：
//
// 机器人一个小时中只能在一个仓库中搬砖
//
// 另外：
//
// 机器人搬砖，一共有 N 堆砖存放在 N 个不同的仓库中，第 i 堆砖中有 bricks[i] 块砖头，要求在 8 小时内搬完

package com.lew.algo.hw.od._02_BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class _021_02_BinarySearch_MovingBricksRobot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(getResultMy(input, 8));
        int[] bricks = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(bricks));
    }

    private static int getResultMy(String input, int limit) {
        int[] bricks = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        int totalHeap = bricks.length;
        if (totalHeap > limit) {
            return -1;
        }
        Arrays.sort(bricks);
        if (totalHeap == limit) {
            return bricks[totalHeap - 1];
        }
        // 首先确定最大最小值
        // 然后二分取中值，不断尝试可能解
        int left = 1, right = bricks[totalHeap - 1];
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (validSpeed(mid, limit, bricks)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private static boolean validSpeed(int speed, int limit, int[] bricks) {
        int count = 0;
        for (int brick : bricks) {
            count += (brick + speed - 1) / speed;
            if (count > limit) {
                return false;
            }
        }
        return true;
    }


    public static int getResult(int[] bricks) {
        // 机器人每小时只能在一个仓库干活，因此给定8小时，最多只能搬完8个仓库，如果仓库数量超过8，那么肯定干不完
        if (bricks.length > 8) {
            return -1;
        }

        // 每小时最多需要的能量块
        int max = Arrays.stream(bricks).max().orElse(0);

        // 如果有8个仓库，那么只能1个小时干1个仓库，且机器人每小时需要能量至少是max(bricks)，这样才能保证1个小时内把最多砖块的那个仓库搬完
        if (bricks.length == 8) {
            return max;
        }

        // 如果仓库数少于8个，那么此时每小时能量max(bricks)必然能在8小时内搬完所有仓库，但不是最优解
        int ans = max;

        // 每小时最少需要的能量块
        int min = 1;

        // 二分法
        while (min <= max) {
            // 取中间值
            int mid = (min + max) >> 1;

            if (check(mid, 8, bricks)) {
                // 如果每小时充mid格能量，就能在8小时内，搬完所有砖头，则mid就是一个可能解
                ans = mid;
                // 但mid不一定是最优解，因此继续尝试更小的能量块
                max = mid - 1;
            } else {
                // 如果每小时充mid能量块，无法在8小时能完成工作，则说明每天能量块充少了，下次应该尝试充更多能量块
                min = mid + 1;
            }
        }

        return ans;
    }

    /**
     * @param energy 每小时可以使用的能量块数量
     * @param limit 限制几小时内干完
     * @param bricks 要搬几堆砖头
     * @return 是否可以在limit小时内已指定energy能量办完所有bricks
     */
    public static boolean check(int energy, int limit, int[] bricks) {
        // 已花费的小时数
        int cost = 0;

        for (int brick : bricks) {
            cost += brick / energy + (brick % energy > 0 ? 1 : 0);

            // 如果搬砖过程中发现，花费时间已经超过限制，则直接返回false
            if (cost > limit) {
                return false;
            }
        }

        return true;
    }
}