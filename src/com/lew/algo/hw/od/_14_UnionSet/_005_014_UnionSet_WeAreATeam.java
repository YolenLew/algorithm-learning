package com.lew.algo.hw.od._14_UnionSet;

import java.util.Scanner;

public class _005_014_UnionSet_WeAreATeam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 总人数
        int n = scanner.nextInt();
        // 消息数: 代表后续还有m行消息
        int m = scanner.nextInt();
        // 数组存储输入: m行 3列
        int[][] msgs = new int[m][3];
        for (int i = 0; i < m; i++) {
            msgs[i][0] = scanner.nextInt();
            msgs[i][1] = scanner.nextInt();
            msgs[i][2] = scanner.nextInt();
        }

        getResult(msgs, n, m);
    }

    private static void getResult(int[][] msgs, int n, int m) {
        // 如果第一行 n 和 m 的值超出约定的范围时，输出字符串”Null“。
        // 1<=n,m<100000
        if (n < 1 || n >= 100000 || m < 1 || m >= 100000) {
            System.out.println("NULL");
            return;
        }

        // 人员标号从1开始，并查集数组长度为n+1，便于后续计算
        UnionFindSet ufs = new UnionFindSet(n + 1);

        for (int[] msg : msgs) {
            int a = msg[0], b = msg[1], c = msg[2];

            if (a < 1 || a > n || b < 1 || b > n) {
                // 当前行 a 或 b 的标号小于 1 或者大于 n 时，输出字符串‘da pian zi‘
                System.out.println("da pian zi");
                continue;
            }

            if (c == 0) {
                // c == 0 代表 a 和 b 在一个团队内
                ufs.union(a, b);
            } else if (c == 1) {
                // c == 1 代表需要判定 a 和 b 的关系，如果 a 和 b 是一个团队，输出一行’we are a team’,如果不是，输出一行’we are not a team’
                System.out.println(ufs.find(a) == ufs.find(b) ? "we are a team" : "we are not a team");
            } else {
                // c 为其他值，输出字符串‘da pian zi‘
                System.out.println("da pian zi");
            }
        }
    }

    // 并查集实现
    static class UnionFindSet {
        int[] fa;

        public UnionFindSet(int n) {
            this.fa = new int[n];
            for (int i = 0; i < n; i++)
                // 先假设每个人都没有好友，每个人都是孤立的，也就是每个人的父亲节点都指向自己。注意：其实没有代号为0的人，可从1开始遍历
                fa[i] = i;
        }

        // 有点难理解
        public int find01(int x) {
            if (this.fa[x] != x) {
                return this.fa[x] = this.find01(this.fa[x]);
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

        // 寻找x的根节点
        public int findxxxx(int x) {
            // 判断是否是根节点，也就是等于自身的节点
            if (x == fa[x]) {
                return x;
            }

            int a = x;
            // 递归寻找根节点
            while (x != fa[x]) {
                x = fa[x];
            }
            // 此时x节点存放的是根节点
            // 在第一次查找时，将要查找的节点之前所有节点都挂在根节点上
            // 步骤：1、保存节点a，防止往前遍历时，变量a被其父亲覆盖
            // 2、节点a往根节点进行遍历，保证a之前的节点都能挂在根节点上
            // 3、将节点a挂在根节点上
            while (a != fa[a]) {
                int z = a;
                a = fa[a];// 从节点a处往根节点方向遍历
                fa[z] = x;// 将z的父亲节点设为x，也就是设为根节点
            }
            return x;
        }

        // 查找节点a和节点b的根节点，如果两个根节点不相同，说明他们不在同一集合，将a的根节点的父亲节点设为b的根节点，合并两个集合，如果相同，说明两个节点在一个集合不用操作任何事情。
        public void union(int x, int y) {
            // 合并：什么是合并，合并就是将有关系的所有人放在一个集合中。也就是将人员分组，每一组都是好朋友。
            int x_fa = this.find(x);
            int y_fa = this.find(y);
            // 如果x的根和y的根不相等，那么需要进行联合，将y的根y_fa的根fa[y_fa]指向x的根 x_fa
            // x -> 4, y -> 5 ------> 5 --> 4
            if (x_fa != y_fa) {
                this.fa[y_fa] = x_fa;
            }
        }
    }
}