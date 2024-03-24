// (C卷,100分)- 求最多可以派出多少支团队（Java & JS & Python & C）
// 题目描述
// 用数组代表每个人的能力，一个比赛活动要求参赛团队的最低能力值为N，每个团队可以由1人或者2人组成，且1个人只能参加1个团队，计算出最多可以派出多少只符合要求的团队。
//
// 输入描述
// 第一行代表总人数，范围1-500000
// 第二行数组代表每个人的能力
// 数组大小，范围1-500000
// 元素取值，范围1-500000
// 第三行数值为团队要求的最低能力值，范围1-500000
// 输出描述
// 最多可以派出的团队数量
//
// 用例
// 输入 5
// 3 1 5 7 9
// 8
// 输出 3
// 说明 说明 3、5组成一队 1、7一队 9自己一队 输出3
// 输入 7
// 3 1 5 7 9 2 6
// 8
// 输出 4
// 说明 3、5组成一队，1、7一队，9自己一队，2、6一队，输出4
// 输入 3
// 1 1 9
// 8
// 输出 1
// 说明 9自己一队，输出1
// 题目解析
// 本题要求最多的组队，而组队要求是：
//
// 可以1人组队，也可以2人组队
// 团队的能力值之和需要大于等于最低能力minCap要求
// 因此，为了组更多队伍，我们应该尽量让单人组队，即：
//
// 需要将能力值大于等于minCap的筛选出来，单人组队
// 然后剩余的人，按照能力值升序排序，定义L,R指针，初始时L=0，R=k-1，k是剩余人总数
//
// 接着尝试L，R进行组队：
//
// 如果L，R两人的能力之和大于等于minCap，则组队成功，L++，R--
// 否则，说明L无法和任何人组队，因为R已经是当前最高能力的人，L无法和R组队，则也意味着无法和能力值比R低的人组队，因此L++
// 在上面过程中，计算出组队个数作为题解。

package com.lew.algo.hw.od._15_DoubleIndex;

import java.util.Arrays;
import java.util.Scanner;

public class _078_15_DoubleIndex_MaxSendingTeams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] capacities = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int minCap = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(n, capacities, minCap));
        System.out.println(getResultMy(n, capacities, minCap));
    }

    private static int getResult(int n, int[] capacities, int minCap) {
        // 升序
        Arrays.sort(capacities);

        int l = 0;
        int r = n - 1;

        // 记录题解
        int ans = 0;

        // 单人组队
        while (r >= l && capacities[r] >= minCap) {
            r--;
            ans++;
        }

        // 双人组队
        while (l < r) {
            int sum = capacities[l] + capacities[r];

            // 如果两个人的能力值之和>=minCap，则组队
            if (sum >= minCap) {
                ans++;
                l++;
                r--;
            } else {
                // 否则将能力低的人剔除，换下一个能力更高的人
                l++;
            }
        }

        return ans;
    }

    // 贪心+双指针
    private static int getResultMy(int n, int[] capacities, int minCap) {
        Arrays.sort(capacities);

        int left = 0;
        int right = n - 1;

        int windowCap = 0;

        int ans = 0;

        while (left < right) {
            if (capacities[right] >= minCap) {
                ans++;
                right--;
                continue;
            }
            // 单个能力不足以组队，需要两个人
            windowCap = capacities[left] + capacities[right];
            if (windowCap >= minCap) {
                ans++;
                left++;
                right--;
            } else {
                left++;
            }
        }
        // 最终还需要判断left==right时的情形
        if (left == right && capacities[right] >= minCap) {
            ans++;
        }

        return ans;
    }

}