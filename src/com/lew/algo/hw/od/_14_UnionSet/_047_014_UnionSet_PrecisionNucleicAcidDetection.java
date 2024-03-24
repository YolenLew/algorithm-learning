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

package com.lew.algo.hw.od._14_UnionSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class _047_014_UnionSet_PrecisionNucleicAcidDetection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int[] confirmed = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(getResult(n, confirmed, matrix));
    }

    public static int getResult(int n, int[] confirmed, int[][] matrix) {
        UnionFindSet ufs = new UnionFindSet(n);

        for (int i = 0; i < n; i++) {
            // 接触关系矩阵matrix是沿对角线对称的，因此只需要遍历对角线一边即可
            for (int j = i; j < n; j++) {
                if (matrix[i][j] == 1) {
                    // 有过接触的人进行合并
                    ufs.union(i, j);
                }
            }
        }

        // 统计每个接触群体（连通分量）中的人数
        int[] cnts = new int[n];
        for (int i = 0; i < n; i++) {
            int fa = ufs.find(i);
            cnts[fa]++;
        }

        // 记录已统计过的感染群体
        HashSet<Integer> confirmed_fa = new HashSet<>();

        // 将有感染者的接触群体的人数统计出来
        int ans = 0;
        for (int i : confirmed) {
            int fa = ufs.find(i);
            // 有可能多个确诊病人在同一个连通分量重，此时需要注意避免重复统计
            // 如果该感染群体已统计过，则不再统计
            if (confirmed_fa.contains(fa))
                continue;
            confirmed_fa.add(fa);

            ans += cnts[fa];
        }

        // 最终需要做核酸的人数，不包括已感染的人
        return ans - confirmed.length;
    }
}

// 并查集实现
class UnionFindSet {
    int[] fa;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        for (int i = 0; i < n; i++)
            fa[i] = i;
    }

    // 寻找x的根节点
    public int find00(int x) {
        if (x != this.fa[x]) {
            this.fa[x] = this.find00(this.fa[x]);
            return this.fa[x];
        }
        return x;
    }

    // 寻找x的根节点
    public int find(int x) {
        if (fa[x] == x) {
            return fa[x];
        }
        while (x != fa[x]) {
            x = fa[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if (x_fa != y_fa) {
            this.fa[y_fa] = x_fa;
        }
    }
}