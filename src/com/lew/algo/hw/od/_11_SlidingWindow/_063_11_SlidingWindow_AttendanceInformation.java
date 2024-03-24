// (C卷,100分)- 考勤信息（Java & JS & Python & C）
// 题目描述
// 公司用一个字符串来表示员工的出勤信息
//
// absent：缺勤
// late：迟到
// leaveearly：早退
// present：正常上班
// 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下：
//
// 缺勤不超过一次；
// 没有连续的迟到/早退；
// 任意连续7次考勤，缺勤/迟到/早退不超过3次。
// 输入描述
// 用户的考勤数据字符串
//
// 记录条数 >= 1；
// 输入字符串长度 < 10000；
// 不存在非法输入；
// 如：
//
// 2
// present
// present absent present present leaveearly present absent
//
// 输出描述
// 根据考勤数据字符串，如果能得到考勤奖，输出”true”；否则输出”false”，
// 对于输入示例的结果应为：
//
// true false
//
// 用例
// 输入 2
// present
// present present
// 输出 true
// true
// 说明 无
// 输入 2
// present
// present absent present present leaveearly present absent
// 输出 true
// false
// 说明 无
// 题目解析
// 本题需要注意下
//
// 没有连续的迟到/早退；
//
// 根据考友反馈，连续的迟到/早退，应该指：本次是迟到或早退，上次也是迟到或早退。
//
// 比如，本次是迟到，上次是早退，则也算是：连续的迟到/早退
//
// 本题主要难点在于如果统计”任意连续7次考勤“中的缺勤/迟到/早退次数。
//
// 这里我们可以使用滑动窗口，且滑窗窗口的长度最大为7
//
// 当滑窗右边界索引R < 7 时，则滑窗长度 = R + 1
// 当滑窗右边界索引R >= 7时，则滑窗长度 = 7，同时能推导出滑窗左边界索引L为 = R - 6

package com.lew.algo.hw.od._11_SlidingWindow;

import java.util.Scanner;

public class _063_11_SlidingWindow_AttendanceInformation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String[][] records = new String[n][];
        for (int i = 0; i < n; i++) {
            records[i] = sc.nextLine().split(" ");
        }

        getResult(n, records);
    }

    public static void getResult(int n, String[][] records) {
        for (int i = 0; i < n; i++) {
            System.out.println(isAward(records[i]));
        }
    }

    public static boolean isAward(String[] record) {
        // 总缺勤次数
        int absent = 0;

        // 滑窗内正常上班的次数
        int present = 0;

        // 记录前一次的考勤记录
        String preRecord = "";

        for (int i = 0; i < record.length; i++) {
            if (i >= 7) {
                // 滑窗长度最大为7，如果超过7，则滑窗的左边界需要右移, 滑窗失去的部分record[i - 7]
                // 如果失去部分是present，则更新滑窗内present次数
                if ("present".equals(record[i - 7]))
                    present--;
            }

            // 当前的考勤记录
            String curRecord = record[i];

            switch (curRecord) {
                case "absent":
                    // 缺勤不超过一次
                    if (++absent > 1)
                        return false;
                    break;
                case "late":
                case "leaveearly":
                    // 没有连续的迟到/早退
                    if ("late".equals(preRecord) || "leaveearly".equals(preRecord))
                        return false;
                    break;
                case "present":
                    present++;
                    break;
            }

            preRecord = curRecord;

            // 任意连续7次考勤，缺勤/迟到/早退不超过3次, 相当于判断： 滑窗长度 - present次数 <= 3
            int window_len = Math.min(i + 1, 7); // 滑窗长度
            if (window_len - present > 3)
                return false;
        }

        return true;
    }
}