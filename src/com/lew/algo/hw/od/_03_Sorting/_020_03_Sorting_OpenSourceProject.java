// (C卷,100分)- 开源项目热度榜单（Java & JS & Python & C）
//题目描述
//某个开源社区希望将最近热度比较高的开源项目出一个榜单，推荐给社区里面的开发者。
//
//对于每个开源项目，开发者可以进行关注（watch）、收藏（star）、fork、提issue、提交合并请求（MR）等。
//
//数据库里面统计了每个开源项目关注、收藏、fork、issue、MR的数量，开源项目的热度根据这5个维度的加权求和进行排序。
//
//H = W(watch) x #watch + W(star) x #star + W(fork) x #fork + W(issue) x #issue + W(mr) x #mr
//
//H 表示热度值
//W(watch)、W(star)、W(fork)、W(issue)、W(mr) 分别表示5个统计维度的权重
//#watch、#star、#fork、#issue、#mr 分别表示5个统计维度的统计值
//榜单按照热度值降序排序，对于热度值相等的，按照项目名字转换为全小写字母后的字典序排序（'a','b','c',...,'x','y','z'）。
//
//输入描述
//第一行输入为N，表示开源项目的个数，0 ＜ N ＜100。
//
//第二行输入为权重值列表，一共 5 个整型值，分别对应关注、收藏、fork、issue、MR的权重，权重取值 0 < W ≤ 50。
//
//第三行开始接下来的 N 行为开源项目的统计维度，每一行的格式为：
//
//name nr_watch nr_start nr_fork nr_issue nr_mr
//
//其中 name 为开源项目的名字，由英文字母组成，长度 ≤ 50，其余 5 个整型值分别为该开源项目关注、收藏、fork、issue、MR的数量，数量取值 0 < nr ≤ 1000。
//
//输出描述
//按照热度降序，输出开源项目的名字，对于热度值相等的，按照项目名字转换为全小写后的字典序排序（'a' > 'b' > 'c' > ... > 'x' > 'y' > 'z'）。
//
//用例
//输入	4
//8 6 2 8 6
//camila 66 70 46 158 80
//victoria 94 76 86 189 211
//anthony 29 17 83 21 48
//emily 53 97 1 19 218
//输出	victoria
//camila
//emily
//anthony
//说明
//排序热度值计算：
//
//camila：66*8 + 70*6 + 46*2 + 158*8 + 80*6 = 2784
//
//victoria: 94*8 + 76*6 + 86*2 + 189*8 + 211*6 = 4158
//
//anthony: 29*8 + 17*6 + 83*2 + 21*8 + 48*6 = 956
//
//emily: 53*8 + 97*6 + 1*2 + 19*8 + 218*6 = 2468
//
//输入	5
//5 6 6 1 2
//camila 13 88 46 26 169
//grace 64 38 87 23 103
//lucas 91 79 98 154 79
//leo 29 27 36 43 178
//ava 29 27 36 43 178
//输出	lucas
//grace
//camila
//ava
//leo
//说明
//排序热度值计算：
//
//camila: 13*5 + 88*6 + 46*6 + 26*1 + 169*2 = 1233
//
//grace: 64*5 + 38*6 + 87*6 + 23*1 + 103*2 = 1299
//
//lucas: 91*5 + 79*6 + 98*6 + 154*1 + 79*2 = 1829
//
//leo: 29*5 + 27*6 + 36*6 + 43*1 + 178*2 = 922
//
//ava: 29*5 + 27*6 + 36*6 + 43*1 + 178*2 = 922
//
//题目解析
//简单的多条件排序题。

package com.lew.algo.hw.od._03_Sorting;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class _020_03_Sorting_OpenSourceProject {
    static class Project {
        String name;
        int hot;

        public Project(String name, int hot) {
            this.name = name;
            this.hot = hot;
        }
    }

    public static void main00(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] weights = new int[5];
        for (int i = 0; i < 5; i++) {
            weights[i] = sc.nextInt();
        }

        Project[] projects = new Project[n];

        for (int i = 0; i < n; i++) {
            String name = sc.next();

            int hot = 0;
            for (int j = 0; j < 5; j++) {
                hot += sc.nextInt() * weights[j];
            }

            projects[i] = new Project(name, hot);
        }

        Arrays.sort(projects,
            (a, b) -> a.hot != b.hot ? b.hot - a.hot : a.name.toLowerCase().compareTo(b.name.toLowerCase()));

        for (Project project : projects) {
            System.out.println(project.name);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 开源项目的个数，0 ＜ N ＜100
        int n = sc.nextInt();
        // 权重值列表，一共 5 个整型值
        int w1 = sc.nextInt();
        int w2 = sc.nextInt();
        int w3 = sc.nextInt();
        int w4 = sc.nextInt();
        int w5 = sc.nextInt();
        // 注意换行时需要补充这个
        sc.nextLine();

        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().split(" ");
            String name = input[0];
            int a1 = Integer.parseInt(input[1]);
            int a2 = Integer.parseInt(input[2]);
            int a3 = Integer.parseInt(input[3]);
            int a4 = Integer.parseInt(input[4]);
            int a5 = Integer.parseInt(input[5]);
            int total = w1 * a1 + w2 * a2 + w3 * a3 + w4 * a4 + w5 * a5;
            list.add(new AbstractMap.SimpleEntry<>(name, total));
        }
        // 排序
        list.sort(
            (e1, e2) -> !Objects.equals(e1.getValue(), e2.getValue()) ? Integer.compare(e2.getValue(), e1.getValue()) :
                e1.getKey().toLowerCase().compareTo(e2.getKey().toLowerCase()));
        list.forEach(e -> System.out.println(e.getKey()));
    }
}