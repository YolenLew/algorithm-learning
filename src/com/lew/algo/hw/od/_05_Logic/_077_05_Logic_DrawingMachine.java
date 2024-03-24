// (C卷,100分)- 绘图机器（Java & JS & Python & C）
// 题目描述
// 绘图机器的绘图笔初始位置在原点(0,0)机器启动后按照以下规则来进行绘制直线。
//
// 1. 尝试沿着横线坐标正向绘制直线直到给定的终点E
//
// 2. 期间可以通过指令在纵坐标轴方向进行偏移，offsetY为正数表示正向偏移,为负数表示负向偏移
//
// 给定的横坐标终点值E 以及若干条绘制指令，
//
// 请计算绘制的直线和横坐标轴以及x=E的直线组成的图形面积。
//
// 输入描述
// 首行为两个整数 N 和 E
// 表示有N条指令,机器运行的横坐标终点值E
// 接下来N行 每行两个整数表示一条绘制指令x offsetY
// 用例保证横坐标x以递增排序的方式出现
// 且不会出现相同横坐标x
// 取值范围
//
// 0<N<=10000
// 0<=x<=E<=20000
// -10000<=offsetY<=10000
// 输出描述
// 一个整数表示计算得到的面积 用例保证结果范围在0到4294967295之内。
// 用例
// 输入
// 4 10
// 1 1
// 2 1
// 3 1
// 4 -2
//
// 输出 12
// 说明 无
// 输入
// 2 4
// 0 1
// 2 -2
//
// 输出 4
// 说明 无

package com.lew.algo.hw.od._05_Logic;

import java.util.Scanner;

public class _077_05_Logic_DrawingMachine {
    // 以一边扫描，一边计算每一条指令的增量面积（因为对于X轴来说，一直是往一个方向移动）
    // 设执行到前 条指令之后，当前点的纵坐标为 ，那么在执行第 条指令时，对应的增量面积为
    // 分别为执行完第 条指令前后的横坐标的值。
    // 按照上述步骤一边计算，一边更新各个坐标的值即可。
    public static void main00(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int end_x = sc.nextInt();

        // 记录题解
        long ans = 0;

        long last_x = 0; // 上一个点的横坐标
        long last_y = 0; // 上一个点的纵坐标

        // 获取n行输入
        for (int i = 0; i < n; i++) {
            int cur_x = sc.nextInt(); // 当前点的横坐标
            int offset_y = sc.nextInt(); // 当前点纵坐标相较于上一个点纵坐标的偏移量

            // 算出每个柱子的高度，计算面积然后累加
            // cur_x - last_x 结果是上一个点到当前点的横向距离, 这个距离过程中，高度保持为abs(last_y)
            ans += (cur_x - last_x) * Math.abs(last_y);

            // 更新last_x, last_y
            last_x = cur_x;
            last_y += offset_y;
        }

        // 注意结束位置的处理
        if (end_x > last_x) {
            ans += (end_x - last_x) * Math.abs(last_y);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int end_x = sc.nextInt();

        // 面积
        int ans = 0;

        // 记录坐标
        int last_x = 0;
        int last_y = 0;
        for (int i = 0; i < n; i++) {
            int curr_x = sc.nextInt();
            int offset_y = sc.nextInt();

            ans += (curr_x - last_x) * Math.abs(last_y);

            last_x = curr_x;
            last_y  = last_y + offset_y;
        }

        // 注意结束位置的处理
        if (end_x > last_x) {
            ans += (end_x - last_x) * Math.abs(last_y);
        }

        System.out.println(ans);
    }
}