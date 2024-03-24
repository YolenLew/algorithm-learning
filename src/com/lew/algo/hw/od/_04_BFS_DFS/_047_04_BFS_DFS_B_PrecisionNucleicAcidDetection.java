// (C卷,100分)- 精准核酸检测（Java & JS & Python & C）
// 在线OJ刷题
// 题目详情 - 精准核酸检测 - Hydro
//
// 题目描述
// 为了达到新冠疫情精准防控的需要，为了避免全员核酸检测带来的浪费，需要精准圈定可能被感染的人群。
//
// 现在根据传染病流调以及大数据分析，得到了每个人之间在时间、空间上是否存在轨迹交叉。
//
// 现在给定一组确诊人员编号（X1,X2,X3,...,Xn），在所有人当中，找出哪些人需要进行核酸检测，输出需要进行核酸检测的人数。（注意：确诊病例自身不需要再做核酸检测）
//
// 需要进行核酸检测的人，是病毒传播链条上的所有人员，即有可能通过确诊病例所能传播到的所有人。
//
// 例如：A是确诊病例，A和B有接触、B和C有接触、C和D有接触、D和E有接触，那么B\C\D\E都是需要进行核酸检测的人。
//
// 输入描述
// 第一行为总人数 N
//
// 第二行为确认病例人员编号（确诊病例人员数量 < N），用逗号分割
//
// 第三行开始，为一个 N * N 的矩阵，表示每个人员之间是否有接触，0表示没有接触，1表示有接触。
//
// 输出描述
// 整数：需要做核酸检测的人数
//
// 备注
// 人员编号从0开始
// 0 < N < 100
// 用例
// 输入 5
// 1,2
// 1,1,0,1,0
// 1,1,0,0,0
// 0,0,1,0,1
// 1,0,0,1,0
// 0,0,1,0,1
// 输出 3
// 说明
// 编号为1、2号的人员，为确诊病例。
//
// 1号和0号有接触，0号和3号有接触。
//
// 2号和4号有接触。
//
// 所以，需要做核酸检测的人是0号、3号、4号，总计3人需要进行核酸检测。

package com.lew.algo.hw.od._04_BFS_DFS;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class _047_04_BFS_DFS_B_PrecisionNucleicAcidDetection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // 确诊人员
        String[] startListStr = scanner.nextLine().split(",");
        int[] startList = new int[startListStr.length];
        for (int i = 0; i < startListStr.length; i++) {
            startList[i] = Integer.parseInt(startListStr[i]);
        }

        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] row = scanner.nextLine().split(",");
            for (int j = 0; j < n; j++) {
                mat[i][j] = Integer.parseInt(row[j]);
            }
        }
        System.out.println(getResult(n, startList, mat));
        System.out.println(getResultMyBfs(n, startList, mat));
    }

    private static int getResultMyBfs(int n, int[] confirmArr, int[][] touchArr) {
        // 确诊+感染人员编号标记
        int[] checkList = new int[n];
        // 接触人员队列（包含确诊）:BFS的队列
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < confirmArr.length; i++) {
            int confirmNo = confirmArr[i];
            checkList[confirmNo] = 1;
            queue.offer(confirmNo);
        }

        int result = 0;
        while (!queue.isEmpty()) {
            int needCheckNo = queue.poll();
            result++;
            // 遍历传递链
            for (int i = 0; i < touchArr.length; i++) {
                // 检查needCheckNo都接触过谁
                if (needCheckNo != i && touchArr[needCheckNo][i] == 1 && checkList[i] == 0) {
                    // 说明i被接触了
                    queue.offer(i);
                    checkList[i] = 1;
                }
            }
        }

        return result - confirmArr.length;
    }

    private static int getResult(int n, int[] startList, int[][] mat) {
        int[] checkList = new int[n];
        for (int i : startList) {
            checkList[i] = 1;
        }

        int ans = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i : startList) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int i = queue.poll();
            ans++;
            for (int j = 0; j < n; j++) {
                if (j != i && mat[i][j] == 1 && checkList[j] == 0) {
                    checkList[j] = 1;
                    queue.offer(j);
                }
            }
        }

        return ans - startList.length;
    }
}