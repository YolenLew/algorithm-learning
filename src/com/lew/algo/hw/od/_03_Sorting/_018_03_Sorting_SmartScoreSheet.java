// (C卷,100分)- 智能成绩表（Java & JS & Python & C）
// 题目描述
// 小明来到某学校当老师，需要将学生按考试总分或单科分数进行排名，你能帮帮他吗？
//
// 输入描述
// 第 1 行输入两个整数，学生人数 n 和科目数量 m。
//
// 0 < n < 100
// 0 < m < 10
// 第 2 行输入 m 个科目名称，彼此之间用空格隔开。
//
// 科目名称只由英文字母构成，单个长度不超过10个字符。
// 科目的出现顺序和后续输入的学生成绩一一对应。
// 不会出现重复的科目名称。
// 第 3 行开始的 n 行，每行包含一个学生的姓名和该生 m 个科目的成绩（空格隔开）
//
// 学生不会重名。
// 学生姓名只由英文字母构成，长度不超过10个字符。
// 成绩是0~100的整数，依次对应第2行种输入的科目。
// 第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
//
// 输出描述
// 输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
//
// 用例
// 输入 3 2
// yuwen shuxue
// fangfang 95 90
// xiaohua 88 95
// minmin 100 82
// shuxue
// 输出 xiaohua fangfang minmin
// 说明 按shuxue成绩排名，依次是xiaohua、fangfang、minmin
// 输入 3 2
// yuwen shuxue
// fangfang 95 90
// xiaohua 88 95
// minmin 90 95
// zongfen
// 输出 fangfang minmin xiaohua
// 说明 排序科目不存在，按总分排序，fangfang和minmin总分相同，按姓名的字典顺序，fangfang排在前面
//
//
// 题目解析
// 本题是一道排序题。难度在于排序规则是动态的，不是固定的。
//
// 本题要求按照最后一行输入的科目的分数进行排序，如果对应科目不存在，则按照总分进行排序。
//
// 我的解题思路是：
//
// 首先，定义一个排名要素数组rank，分别记录各科成绩以及总分，即该排名要素数组rank的长度为 m + 1。
//
// 第rank[0]~rank[m-1]上，记录的是第二行输入科目顺序对应科目分数。
// 第rank[m]上，记录的是所有科目的总分。
// 然后，定义一个有效要素索引（即最终用于指定规则的排序要素的索引），比如：
//
// 最后一行输入了shuxue，那么我就去第二行输入：yuwen shuxue，中去找对应出现序号为 1，那么有效要素的索引就是1，最终用于制定排序规则的值就是 rank[1]。
// 如果最后一行输入的科目，在第二行中不存在，那么就是按照总分制定排序规则，此时排序要素取 rank[m]。
// 如果排序要素值相同（可能是某科成绩，可能是总分），那么就再按照学生姓名的字典序排序。
//
// 更多实现细节请看代码，已添加详细注释。

package com.lew.algo.hw.od._03_Sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class _018_03_Sorting_SmartScoreSheet {
    static class Student {
        String name; // 学生姓名
        int[] rank; // 排名要素

        public Student(String name, int[] rank) {
            this.name = name;
            this.rank = rank;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 学生人数
        int n = sc.nextInt();

        // 科目数量
        int m = sc.nextInt();

        // key是科目名称，val是科目出现顺序的序号
        HashMap<String, Integer> subject_rankIdx = new HashMap<>();
        for (int i = 0; i < m; i++) {
            subject_rankIdx.put(sc.next(), i);
        }

        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 学生姓名
            String name = sc.next();

            // rank记录学生排名的要素（0~m-1索引上记录的是各科成绩，m索引上记录的是总分）
            int[] rank = new int[m + 1];
            // 学生的总分
            int score_sum = 0;

            // m 个科目成绩
            for (int j = 0; j < m; j++) {
                rank[j] = sc.nextInt();
                score_sum += rank[j];
            }

            rank[m] = score_sum;

            students.add(new Student(name, rank));
        }

        // 输入用作排名的科目名称
        // 根据用作排名的科目名称获取对应排名要素序号，如果不存在，则按总分排序，排名要素序号取m
        int rankIdx = subject_rankIdx.getOrDefault(sc.next(), m);

        students.sort((a, b) -> a.rank[rankIdx] != b.rank[rankIdx] ? b.rank[rankIdx] - a.rank[rankIdx]
            : a.name.compareTo(b.name));

        StringJoiner sj = new StringJoiner(" ");
        for (Student student : students) {
            sj.add(student.name);
        }

        System.out.println(sj);
    }
}