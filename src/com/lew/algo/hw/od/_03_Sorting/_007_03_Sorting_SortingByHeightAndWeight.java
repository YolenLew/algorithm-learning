// (C卷,100分)- 按身高和体重排队（Java & JS & Python & C）
// 题目描述
// 某学校举行运动会，学生们按编号(1、2、3…n)进行标识，现需要按照身高由低到高排列，对身高相同的人，按体重由轻到重排列；对于身高体重都相同的人，维持原有的编号顺序关系。请输出排列后的学生编号。
//
// 输入描述
// 两个序列，每个序列由n个正整数组成（0 < n <= 100）。第一个序列中的数值代表身高，第二个序列中的数值代表体重。
//
// 输出描述
// 排列结果，每个数值都是原始序列中的学生编号，编号从1开始
//
// 用例
// 输入
// 4
// 100 100 120 130
// 40 30 60 50
//
// 输出 2 1 3 4
// 说明
// 输出的第一个数字2表示此人原始编号为2，即身高为100，体重为30的这个人。
//
// 由于他和编号为1的人身高一样，但体重更轻，因此要排在1前面。
//
// 输入
// 3
// 90 110 90
// 45 60 45
//
// 输出 1 3 2
// 说明 1和3的身高体重都相同，需要按照原有位置关系让1排在3前面，而不是3 1 2。
// 题目解析
// 考察多条件排序。

package com.lew.algo.hw.od._03_Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class _007_03_Sorting_SortingByHeightAndWeight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 人数
        int n = Integer.parseInt(sc.nextLine());
        int[] heights = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] weights = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 初始化学生身高体重及编号
        int[][] students = new int[n][3];
        for (int i = 0; i < students.length; i++) {
            students[i] = new int[] {heights[i], weights[i], i + 1};
        }
        // 按指定条件排列：升序排列
        Arrays.sort(students, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (a[1] != b[1] ? (a[1] - b[1]) : (a[2] - b[2])));

        StringJoiner sj = new StringJoiner(" ");
        for (int[] student : students) {
            sj.add(String.valueOf(student[2]));
        }

        System.out.println(sj);
    }

    public static void main1111(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 人数
        int n = Integer.parseInt(sc.nextLine());
        int[] heights = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] weights = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 初始化学生身高体重及编号
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            students.add(new Student(heights[i], weights[i], i + 1));
        }
        // 按指定条件排列：升序排列
        students.sort(Comparator.naturalOrder());
        String result = students.stream().map(Student::getNo).map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(result);
    }

    static class Student implements Comparable<Student> {
        int height;
        int weight;
        int no;

        public Student(int height, int weight, int no) {
            this.height = height;
            this.weight = weight;
            this.no = no;
        }

        @Override
        public int compareTo(Student stu) {
            if (this.height != stu.height) {
                return this.height - stu.height;
            } else if (this.weight != stu.weight) {
                return this.weight - stu.weight;
            } else {
                return this.no - stu.no;
            }
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }
}