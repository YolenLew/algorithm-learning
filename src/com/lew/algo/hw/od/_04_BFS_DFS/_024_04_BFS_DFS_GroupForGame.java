// (C卷,100分)- 游戏分组（Java & JS & Python & C）
// 题目描述
// 部门准备举办一场王者荣耀表演赛，有 10 名游戏爱好者参与，分为两队，每队 5 人。
//
// 每位参与者都有一个评分，代表着他的游戏水平。为了表演赛尽可能精彩，我们需要把 10 名参赛者分为示例尽量相近的两队。
//
// 一队的实力可以表示为这一队 5 名队员的评分总和。
//
// 现在给你 10 名参与者的游戏水平评分，请你根据上述要求分队，最后输出这两组的实力差绝对值。
//
// 例：10 名参赛者的评分分别为：5 1 8 3 4 6 7 10 9 2，分组为（1 3 5 8 10）和（2 4 6 7 9），两组实力差最小，差值为1。有多种分法，但是实力差的绝对值最小为1。
//
// 输入描述
// 10个整数，表示10名参与者的游戏水平评分。范围在 [1, 10000] 之间。
//
// 输出描述
// 1个整数，表示分组后两组实力差绝对值的最小值。
//
// 用例
// 输入 1 2 3 4 5 6 7 8 9 10
// 输出 1
// 说明 10名队员分为两组，两组实力差绝对值最小为1

package com.lew.algo.hw.od._04_BFS_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class _024_04_BFS_DFS_GroupForGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int[] powers = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(powers));
        System.out.println(getResultMy(powers));
        System.out.println(getResultMy02(powers));
    }

    static int result02 = Integer.MAX_VALUE;
    static int totalSum02;

    public static int getResultMy02(int[] arr) {
        Arrays.sort(arr);

        totalSum02 = Arrays.stream(arr).sum();

        dfsMy02(arr, 0, 0, 0);

        return result02;
    }

    private static void dfsMy02(int[] arr, int level, int pathSum, int start) {
        // 一共十名选手，所以每组就是5名，level从0开始
        if (level == 5) {
            result02 = Math.min(result02, Math.abs(totalSum02 - pathSum * 2));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            // 剪枝逻辑：同一层级，相等的分支可剪枝
            if (i > start && arr[i] == arr[i -1]) {
                continue;
            }

            dfsMy02(arr, level + 1, pathSum + arr[i], i + 1);
        }
    }

    public static int getResult(int[] arr) {
        Arrays.sort(arr);

        ArrayList<Integer> res = new ArrayList<>();
        // dfs求10选5的去重组合，并将组合之和记录进res中，即res中记录的是所有可能性的5人小队实力值之和
        dfs(arr, 0, 0, 0, res);

        int sum = Arrays.stream(arr).reduce(Integer::sum).orElse(0);
        // 某队实力为subSum，则另一队实力为sum - subSum，则两队实力差为 abs((sum - subSum) - subSum)，先求最小实力差
        return res.stream().map(subSum -> Math.abs(sum - 2 * subSum)).min((a, b) -> a - b).orElse(0);
    }

    // 求解去重组合
    public static void dfs(int[] arr, int index, int level, int sum, ArrayList<Integer> res) {
        if (level == 5) {
            res.add(sum);
            return;
        }

        for (int i = index; i < 10; i++) {
            if (i > index && arr[i] == arr[i - 1])
                continue; // arr已经升序，这里进行树层去重
            dfs(arr, i + 1, level + 1, sum + arr[i], res);
        }
    }

    private static int ans = Integer.MAX_VALUE;
    private static int totalSum = 0;

    public static int getResultMy(int[] arr) {
        Arrays.sort(arr);
        totalSum = Arrays.stream(arr).sum();

        // DFS遍历：限定长度的排列组合
        dfsMy(arr, 0, 0, 0);

        return ans;
    }

    private static void dfsMy(int[] nums, int pathSum, int level, int start) {
        if (level == 5) {
            ans = Math.min(ans, Math.abs(totalSum - 2 * pathSum));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 元素可重复不可复选的排列组合问题：同一层级的相邻树枝如果相等，可以进行剪枝
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            dfsMy(nums, pathSum + nums[i], level + 1, i + 1);
        }
    }

}