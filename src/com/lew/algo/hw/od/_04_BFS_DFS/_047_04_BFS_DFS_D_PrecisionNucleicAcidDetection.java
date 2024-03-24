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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class _047_04_BFS_DFS_D_PrecisionNucleicAcidDetection {
    static void dfs(int n, int i, int[] checkList, int[][] mat, int[] ans) {
        // 默认累加，因为遍历的是所有确诊编号，最后结果会剔除这部分确诊数据
        ans[0]++;
        // 将编号i标记为已检查过
        checkList[i] = 1;
        // 遍历所有与i关联的编号j
        for (int j = 0; j < n; j++) {
            // 1.i和j不是同一个人
            // 2.i和j接触过
            // 3.j尚未检查过
            if (j != i && mat[i][j] == 1 && checkList[j] == 0) {
                dfs(n, j, checkList, mat, ans);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String startListStr = scanner.nextLine();
        // 构造一个用来解析 str 的 StringTokenizer 对象，并提供一个指定的分隔符
        StringTokenizer tokenizer = new StringTokenizer(startListStr, ",");
        // 确诊人员集合
        List<Integer> startList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            startList.add(Integer.parseInt(tokenizer.nextToken()));
        }

        // 接触关系矩阵
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            String row = scanner.nextLine();
            tokenizer = new StringTokenizer(row, ",");
            for (int j = 0; j < n; j++) {
                mat[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        // 人员编号检查标记
        int[] checkList = new int[n];
        int[] ans = {0};
        // 遍历所有确诊编号i
        for (int i : startList) {
            if (checkList[i] == 0) {
                dfs(n, i, checkList, mat, ans);
            }
        }

        // 最后要减去原本已经确诊的人，才是所有需要检测的人数
        System.out.println(ans[0] - startList.size());
    }
}
