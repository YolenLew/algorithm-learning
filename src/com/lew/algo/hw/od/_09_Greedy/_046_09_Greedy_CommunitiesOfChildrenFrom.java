// (C卷,100分)- 小朋友来自多少小区（Java & JS & Python & C）
// 在线OJ刷题
// 题目详情 - 小朋友来自多少分区 - Hydro
//
// 题目描述
// 幼儿园组织活动，老师布置了一个任务：
//
// 每个小朋友去了解与自己同一个小区的小朋友还有几个。
//
// 我们将这些数量汇总到数组 garden 中。
//
// 请根据这些小朋友给出的信息，计算班级小朋友至少来自几个小区？
//
// 输入描述
// 输入：garden[] = {2, 2, 3}
//
// 输出描述
// 输出：7
//
// 备注
// garden 数组长度最大为 999
// 每个小区的小朋友数量最多 1000 人，也就是 garden[i] 的范围为 [0, 999]
// 用例
// 输入 2 2 3
// 输出 7
// 说明
// 第一个小朋友反馈有两个小朋友和自己同一小区，即此小区有3个小朋友。
//
// 第二个小朋友反馈有两个小朋友和自己同一小区，即此小区有3个小朋友。
//
// 这两个小朋友，可能是同一小区的，且此小区的小朋友只有3个人。
//
// 第三个小区反馈还有3个小朋友与自己同一小区，则这些小朋友只能是另外一个小区的。这个小区有4个小朋友。
//
// 题目解析
// 本题的输出其实是至少的小朋友数量，而不是班级小朋友至少来自几个小区。

package com.lew.algo.hw.od._09_Greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class _046_09_Greedy_CommunitiesOfChildrenFrom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(getResult(nums));
        } catch (Exception e) {
            System.out.println("0");
        }
    }

    public static int getResult(int[] nums) {
        // 1.如果两个小朋友所反馈的人数不一致，那么他们必然不可能来自同一个小区
        // 2.如果存在n个小朋友均反馈有m个其他小朋友和他来自同一小区，那么小区人数应该为m+1（+1表示包含这个小朋友自己）
        // 2.1 为了使得总的小区数尽可能地少，我们会贪心地将尽可能多的小朋友安排到同一个小区里。一个小区的人数上限为m+1，
        // 因此此时所需要的小区数量为ceil(n / (m+1))， 这些小区所包含的小朋友数量为ceil(n / (m+1)) * (m+1)

        // num是反馈，cnts[num]是给出相同反馈的（小朋友）数量
        // 哈希表计数器cnts，[m] = n表示反馈人数为m的小朋友的数量为n
        HashMap<Integer, Integer> cnts = new HashMap<>();
        for (int num : nums) {
            cnts.put(num, cnts.getOrDefault(num, 0) + 1);
        }

        // ans 记录题解
        int ans = 0;
        for (int key : cnts.keySet()) {
            // key是反馈，假设某小朋友反馈有key个人和自己一个小区，那么该小区总人数为total = key+1
            int total = key + 1;
            // 所需要的小区数量为ceil(n / (m+1))， 这些小区所包含的小朋友数量为ceil(n / (m+1)) * (m+1)
            ans += Math.ceil(cnts.get(key) * 1.0 / total) * total;
        }

        return ans;
    }
}