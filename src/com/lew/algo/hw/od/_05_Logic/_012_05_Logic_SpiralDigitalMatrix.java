//(C卷,100分)- 螺旋数字矩阵（Java & JS & Python & C）
//题目描述
//疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法：
//
//给出数字个数 n （0 < n ≤ 999）和行数 m（0 < m ≤ 999），从左上角的 1 开始，按照顺时针螺旋向内写方式，依次写出2,3,....,n，最终形成一个 m 行矩阵。
//
//小明对这个矩阵有些要求：
//
//每行数字的个数一样多
//列的数量尽可能少
//填充数字时优先填充外部
//数字不够时，使用单个 * 号占位
//输入描述
//两个整数，空格隔开，依次表示 n、m
//
//输出描述
//符合要求的唯一矩阵
//
//用例
//输入	9 4
//输出	1 2 3
//* * 4
//9 * 5
//8 7 6
//说明	9个数字写出4行，最少需要3列
//输入	3 5
//输出
//1
//
//2
//
//3
//
//*
//
//*
//
//说明	3个数字写5行，只有一列，数字不够用*号填充
//输入	120 7
//输出	1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
//46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 19
//45 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 63 20
//44 83 114 115 116 117 118 119 120 * * * * * * 99 64 21
//43 82 113 112 111 110 109 108 107 106 105 104 103 102 101 100 65 22
//42 81 80 79 78 77 76 75 74 73 72 71 70 69 68 67 66 23
//41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 25 24
//说明	无
//题目解析
//本题需要我们将1~n数字按照螺旋顺序填入矩阵。
//
//本题只给出了矩阵的行数m，没有给列数，需要我们求解一个最少的列数来满足矩阵能够填入n个数字，因此列数 k = ceil(n / m)，这里的除法不是整除，并且要对除法的结果向上取整。
//
//将数字1~n按照螺旋顺序，从矩阵matrix左上角开始填入，比较考察思维能力，具体实现如下：
//
//定义变量step，初始step=1，表示当前要填入的数字，因此step ≤ n
//定义变量x，y，初始x=0, y=0，表示要填值得矩阵位置，即初始时从矩阵左上角开始填入
//然后按照顺序循环进行下面四个填值操作：
//
//正序填入第X行
//正序填入第Y列
//倒序填入第X行
//倒序填入第Y列

package com.lew.algo.hw.od._05_Logic;

import java.util.Scanner;
import java.util.StringJoiner;

public class _012_05_Logic_SpiralDigitalMatrix {

    public static void main00(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 需要在螺旋矩阵中填入 1 ~ n 数字
        int n = sc.nextInt();

        // 螺旋矩阵行数
        int m = sc.nextInt();

        // 螺旋矩阵列数
        int k = (int)Math.ceil(n * 1.0 / m);

        // 螺旋矩阵
        int[][] matrix = new int[m][k]; // 由于需要填入1~n数字，因此这里未填值的位置值默认初始化为0

        // 当前要填入的值
        int step = 1;

        // 当前要填入的值的位置
        int x = 0;
        int y = 0;

        // 如果填入的值 > n，则停止填值，否则继续填
        while (step <= n) {
            // 正序填入第x行，即：行号不变，列号增加，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (y < k && matrix[x][y] == 0 && step <= n)
                matrix[x][y++] = step++;
            // 正序填完第x行后，y处于末尾越界位置，因此y需要退一步
            y -= 1;
            // 正序填完第x行来到第x行的末尾，即第y列，按照螺旋矩阵顺序，应该从第x+1行开始正序填值第y列
            x += 1;

            // 正序填入第y列，即：列号不变，行号增加，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (x < m && matrix[x][y] == 0 && step <= n)
                matrix[x++][y] = step++;
            x -= 1;
            y -= 1;

            // 倒序填入第x行，即：行号不变，列号减少，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (y >= 0 && matrix[x][y] == 0 && step <= n)
                matrix[x][y--] = step++;
            y += 1;
            x -= 1;

            // 倒序填入第y列，即：列号不变，行号减少，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (x >= 0 && matrix[x][y] == 0 && step <= n)
                matrix[x--][y] = step++;
            x += 1;
            y += 1;
        }

        // 打印螺旋矩阵字符串
        for (int i = 0; i < m; i++) {
            StringJoiner row = new StringJoiner(" ");
            for (int j = 0; j < k; j++) {
                if (matrix[i][j] == 0) {
                    row.add("*");
                } else {
                    row.add(matrix[i][j] + "");
                }
            }
            System.out.println(row);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 需要在螺旋矩阵中填入 1 ~ n 数字
        int n = sc.nextInt();

        // 螺旋矩阵行数
        int m = sc.nextInt();

        // 螺旋矩阵列数
        int k = (int)Math.ceil(n * 1.0 / m);

        getResult(n, m, k);
        getResultMy(n, m, k);

    }

    private static void getResultMy(int n, int m, int k) {
        // 螺旋矩阵
        int[][] matrix = new int[m][k];

        int step = 1;
        int x = 0;
        int y = 0;
        while (step <= n) {
            // 正序x行
            while (validIndex(x, y, step, n, matrix)) {
                matrix[x][y] = step;
                step++;
                y++;
            }

            // 正序y列
            y = y - 1;
            x = x + 1;
            while (validIndex(x, y, step, n, matrix)) {
                matrix[x][y] = step;
                step++;
                x++;
            }

            // 逆序x行
            x = x - 1;
            y = y - 1;
            while (validIndex(x, y, step, n, matrix)) {
                matrix[x][y] = step;
                step++;
                y--;
            }

            // 逆序y列
            x = x - 1;
            y = y + 1;
            while (validIndex(x, y, step, n, matrix)) {
                matrix[x][y] = step;
                step++;
                x--;
            }

            // 准备下一次正序x行
            x = x+1;
            y = y+1;
        }

        // 打印螺旋矩阵字符串
        for (int i = 0; i < m; i++) {
            StringJoiner row = new StringJoiner(" ");
            for (int j = 0; j < k; j++) {
                if (matrix[i][j] == 0) {
                    row.add("*");
                } else {
                    row.add(matrix[i][j] + "");
                }
            }
            System.out.println(row);
        }
    }

    private static void getResult(int n, int m, int k) {

        // 螺旋矩阵
        int[][] matrix = new int[m][k]; // 由于需要填入1~n数字，因此这里未填值的位置值默认初始化为0

        // 当前要填入的值
        int step = 1;

        // 当前要填入的值的位置
        int x = 0;
        int y = 0;

        // 如果填入的值 > n，则停止填值，否则继续填
        while (step <= n) {
            // 正序填入第x行，即：行号不变，列号增加，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (validIndex(x, y, step, n, matrix))
                matrix[x][y++] = step++;
            // 正序填完第x行后，y处于末尾越界位置，因此y需要退一步
            y -= 1;
            // 正序填完第x行来到第x行的末尾，即第y列，按照螺旋矩阵顺序，应该从第x+1行开始正序填值第y列
            x += 1;

            // 正序填入第y列，即：列号不变，行号增加，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (validIndex(x, y, step, n, matrix))
                matrix[x++][y] = step++;
            x -= 1;
            y -= 1;

            // 倒序填入第x行，即：行号不变，列号减少，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (validIndex(x, y, step, n, matrix))
                matrix[x][y--] = step++;
            y += 1;
            x -= 1;

            // 倒序填入第y列，即：列号不变，行号减少，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (validIndex(x, y, step, n, matrix))
                matrix[x--][y] = step++;
            x += 1;
            y += 1;
        }

        // 打印螺旋矩阵字符串
        for (int i = 0; i < m; i++) {
            StringJoiner row = new StringJoiner(" ");
            for (int j = 0; j < k; j++) {
                if (matrix[i][j] == 0) {
                    row.add("*");
                } else {
                    row.add(matrix[i][j] + "");
                }
            }
            System.out.println(row);
        }
    }

    public static boolean validIndex(int x, int y, int currentStep, int maxStep, int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;

        return x >= 0 && x < row && y >= 0 && y < column && matrix[x][y] == 0 && currentStep <= maxStep;
    }
}