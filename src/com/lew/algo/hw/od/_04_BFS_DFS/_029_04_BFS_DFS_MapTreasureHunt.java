// (C卷,100分)- 小华地图寻宝（Java & JS & Python & C）
// 题目描述
// 小华按照地图去寻宝，地图上被划分成 m 行和 n 列的方格，横纵坐标范围分别是 [0, n-1] 和 [0, m-1]。
//
// 在横坐标和纵坐标的数位之和不大于 k 的方格中存在黄金（每个方格中仅存在一克黄金），但横坐标和纵坐标之和大于 k 的方格存在危险不可进入。小华从入口 (0,0) 进入，任何时候只能向左，右，上，下四个方向移动一格。
//
// 请问小华最多能获得多少克黄金？
//
// 输入描述
// 坐标取值范围如下：
//
// 0 ≤ m ≤ 50
// 0 ≤ n ≤ 50
// k 的取值范围如下：
//
// 0 ≤ k ≤ 100
// 输入中包含3个字数，分别是m, n, k
//
// 输出描述
// 输出小华最多能获得多少克黄金
//
// 用例
// 输入 40 40 18
// 输出 1484
// 说明 无
// 输入 5 4 7
// 输出 20
// 说明 无
// 上面“横坐标和纵坐标之和”其实也是数位之和，即
//
// 横坐标和纵坐标数位之和大于 k 的方格存在危险不可进入
//
// 因此，本题其实就是图的遍历问题，可以使用深度优先搜索DFS，或者广度优先搜索BFS处理。
//
// 从（0，0）点开始，然后进行深搜或者广搜，其上下左右四个新位置，新位置是否可以进入的条件是：
//
// 新位置不越界
// 新位置未被访问过
// 新位置的横坐标、纵坐标的数位之和 <= k
// 每进入一个位置，则获得黄金1克。

package com.lew.algo.hw.od._04_BFS_DFS;

import java.util.Scanner;

public class _029_04_BFS_DFS_MapTreasureHunt {

    // 所谓数位和，指的是一个正整数的各个位的和。比如11的数位和是1+1 = 2
    // 本题其实就是图的遍历问题，可以使用深度优先搜索DFS，或者广度优先搜索BFS处理。
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();

        getResultMy(m, n, k);
        System.out.println(ans);
        getResultMy02(m, n, k);
        System.out.println(ans02);
    }

    // 上下左右
    static int[][] offsets02 = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int ans02 = 0;

    public static int getResultMy02(int m, int n, int k) {
        // 初始化地图
        int[][] map = new int[m][n];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = calculateNumSum(i) + calculateNumSum(j);
            }
        }
        // 访问数组
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                dfsMy02(map, visited, i, j, m, n, k);
            }
        }

        return ans02;
    }

    private static void dfsMy02(int[][] map, boolean[][] visited, int x, int y, int m, int n, int k) {
        // 越界判断
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || map[x][y] > k) {
            return;
        }

        ans02++;
        visited[x][y] = true;
        // 上下左右遍历
        for (int[] ints : offsets02) {
            x += ints[0];
            y += ints[1];
            dfsMy02(map, visited, x, y, m, n, k);
        }
    }

    private static int calculateNumSum(int num) {
        return String.valueOf(num).chars().map(Character::getNumericValue).sum();
    }

    // 上下左右偏移量
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int ans = 0;

    public static int getResultMy(int m, int n, int k) {
        // 坐标的数位和数组
        int[][] grid = new int[m][n];
        // 访问数组
        int[][] visited = new int[m][n];
        // 数组初始化
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = cal_digit_sum(i) + cal_digit_sum(j);
            }
        }
        dfsMy(grid, visited, 0, 0, m, n, k);

        return ans;
    }

    private static void dfsMy(int[][] grid, int[][] visited, int x, int y, int m, int n, int k) {
        // 校验合法性
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] != 0 || grid[x][y] > k) {
            return;
        }

        ans++;
        visited[x][y] = 1;
        // 继续遍历上、下、左、右方向上的新位置继续深搜
        for (int[] offset : offsets) {
            int newX = offset[0] + x;
            int newY = offset[1] + y;
            dfsMy(grid, visited, newX, newY, m, n, k);
        }
    }

    // 求解数位之和
    public static int cal_digit_sum(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }
}