// (C卷,100分)- 最富裕的小家庭（Java & JS & Python & C）
// 目录
//
// 题目描述
//
// 输入描述
//
// 输出描述
//
// 用例
//
// 题目解析
//
// JS算法源码
//
// Java算法源码
//
// Python算法源码
//
// C算法源码
//
// 题目描述
// 在一颗树中，每个节点代表一个家庭成员，节点的数字表示其个人的财富值，一个节点及其直接相连的子节点被定义为一个小家庭。
//
// 现给你一颗树，请计算出最富裕的小家庭的财富和。
//
// 输入描述
// 第一行为一个数 N，表示成员总数，成员编号 1~N。1 ≤ N ≤ 1000
//
// 第二行为 N 个空格分隔的数，表示编号 1~N 的成员的财富值。0 ≤ 财富值 ≤ 1000000
//
// 接下来 N -1 行，每行两个空格分隔的整数（N1, N2），表示 N1 是 N2 的父节点。
//
// 输出描述
// 最富裕的小家庭的财富和
//
// 用例
// 输入 4
// 100 200 300 500
// 1 2
// 1 3
// 2 4
// 输出 700
// 说明
//
//
// 成员1，2，3 组成的小家庭财富值为600
//
// 成员2，4 组成的小家庭财富值为700
//
// 题目解析
// 简单的逻辑分析题。
//
// 由于题目输入会给出 树形结构 中，父节点->子节点 的信息，比如下面红色部分
//
// 4
// 100 200 300 500
// 1 2
// 1 3
// 2 4
//
// 因此我们遍历这些信息时，就可以直接将子节点的财富，汇总到其父节点上。
//
// 具体实现是，由第二行输入解析得到每个节点的财富值数组 wealth。
//
// 然后根据第三行~最后一行的输入：fa ch，执行 wealth[fa] += wealth[ch]，即将子节点的财富值汇总到父节点上。
//
// 这样最终wealth数组的最大值就是题解。
//
// 需要注意的是，题目规定成员编号 1~N，因此定义wealth数组时，我们应该将其长度定义为N+1，且从索引1开始操作，来匹配成员编号1~N。
//
// 2023.11.26 上面逻辑中：
//
// wealth[fa] += wealth[ch]
//
// 存在问题。
//
// 比如下面用例：
//
// 5
// 100 200 300 400 500
// 3 4
// 3 5
// 1 3
// 1 2
//
// 3是1的子节点，但是3的家庭关系先被定义出来了，因此如果将家庭的财富都汇总到父节点身上，上面逻辑会将节点3的财富值变为1200，从而影响后面父节点为1的家庭财富计算。
//
// 本题需要额外定义一个family数组，用于记录每个父节点对应家庭的财富值，这样就可以避免上面问题。
package com.lew.algo.hw.od._05_Logic;

import java.util.Arrays;
import java.util.Scanner;

public class _013_05_Logic_WealthFamily {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] member = new long[n + 1];
        long[] family = new long[n + 1];
        // 录入成员财富
        for (int i = 1; i <= n; i++) {
            member[i] = sc.nextInt();
            family[i] = member[i];
        }

        // 根据家庭关系，录入家庭财富
        long ans = 0;
        for (int i = 1; i <= n - 1; i++) {
            int fa = sc.nextInt();
            int son = sc.nextInt();
            family[fa] += family[son];

        }

        for (long wealth : family) {
            ans = Math.max(ans, wealth);
        }
        System.out.println(ans);
    }

    public static void main111(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 这里wealth长度定义为n+1，是为了让wealth数组索引对应成员编号 1~N
        long[] wealth = new long[n + 1];
        long[] family = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            wealth[i] = sc.nextInt();
            family[i] = wealth[i];
        }

        for (int i = 0; i < n - 1; i++) {
            int fa = sc.nextInt();
            int ch = sc.nextInt();
            family[fa] += wealth[ch];
        }

        System.out.println(Arrays.stream(family).max().orElse(0));
    }
}