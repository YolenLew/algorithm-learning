// (C卷,100分)- 找座位（Java & JS & Python & C）
//
// 题目描述
// 在一个大型体育场内举办了一场大型活动，由于疫情防控的需要，要求每位观众的必须间隔至少一个空位才允许落座。
//
// 现在给出一排观众座位分布图，座位中存在已落座的观众，请计算出，在不移动现有观众座位的情况下，最多还能坐下多少名观众。
//
// 输入描述
// 一个数组，用来标识某一排座位中，每个座位是否已经坐人。0表示该座位没有坐人，1表示该座位已经坐人。
//
// 1 ≤ 数组长度 ≤ 10000
// 输出描述
// 整数，在不移动现有观众座位的情况下，最多还能坐下多少名观众。
//
// 用例
// 输入 10001
// 输出 1
// 说明 无
// 输入 0101
// 输出 0
// 说明 无
// 题目解析

package com.lew.algo.hw.od._05_Logic;

import java.util.Scanner;

public class _015_05_Logic_FindSeat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getResult(sc.nextLine().toCharArray()));
    }

    public static int getResult(char[] desk) {
        int ans = 0;

        for (int i = 0; i < desk.length; i++) {
            if (desk[i] == '0') {
                boolean isLeftEmpty = i == 0 || desk[i - 1] == '0';
                boolean isRightEmpty = i == desk.length - 1 || desk[i + 1] == '0';
                if (isLeftEmpty && isRightEmpty) {
                    ans++;
                    desk[i] = '1';
                    i++;
                }
            }
        }

        return ans;
    }
}