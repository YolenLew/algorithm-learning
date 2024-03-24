// (C卷,100分)- 围棋的气（Java & JS & Python & C）
//题目描述
//围棋棋盘由纵横各19条线垂直相交组成，棋盘上一共19 x 19 = 361 个交点，对弈双方一方执白棋，一方执黑棋，落子时只能将棋子置于交点上。
//
//“气”是围棋中很重要的一个概念，某个棋子有几口气，是指其上下左右方向四个相邻的交叉点中，有几个交叉点没有棋子，由此可知：
//
//在棋盘的边缘上的棋子最多有 3 口气（黑1），在棋盘角点的棋子最多有2口气（黑2），其他情况最多有4口气（白1）
//所有同色棋子的气之和叫做该色棋子的气，需要注意的是，同色棋子重合的气点，对于该颜色棋子来说，只能计算一次气，比如下图中，黑棋一共4口气，而不是5口气，因为黑1和黑2中间红色三角标出来的气是两个黑棋共有的，对于黑棋整体来说只能算一个气。
//本题目只计算气，对于眼也按气计算，如果您不清楚“眼”的概念，可忽略，按照前面描述的规则计算即可。
//
//
//现在，请根据输入的黑棋和白棋得到坐标位置，计算黑棋和白棋一共各有多少气？
//
//输入描述
//输入包含两行数据，如：
//
//0 5 8 9 9 10
//5 0 9 9 9 8
//
//每行数据以空格分隔，数据个数是2的整数倍，每两个数是一组，代表棋子在棋盘上的坐标；
//坐标的原点在棋盘左上角点，第一个值是行号，范围从0到18；第二个值是列号，范围从0到18。
//举例说明：第一行数据表示三个坐标（0, 5）、(8, 9)、(9, 10)
//第一行表示黑棋的坐标，第二行表示白棋的坐标。
//题目保证输入两行数据，无空行且每行按前文要求是偶数个，每个坐标不会超出棋盘范围。
//输出描述
//8 7
//
//两个数字以空格分隔，第一个数代表黑棋的气数，第二个数代表白棋的气数。
//
//用例
//输入	0 5 8 9 9 10
//5 0 9 9 9 8
//输出	8 7
//说明
//如果将输入数据放到棋盘上
//
//
//
//数数黑棋一共8口气，数数白棋一共7口气。
//
//题目解析
//本题棋盘中“气”的位置有如下特点：
//
//该位置没有棋子
//该位置上下左右存在至少一个棋子（若为黑棋，则当前位置就是黑棋的气，若为白棋，则当前位置就是白棋的气，若既有黑棋，也有白棋，则同时为两个颜色棋的气）

package com.lew.algo.hw.od._06_Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _025_06_Array_QiOfWeiqi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String blackInput = sc.nextLine();
        String whiteInput = sc.nextLine();
        String[] blackChess = convertToCoordinate(blackInput);
        String[] whiteChess = convertToCoordinate(whiteInput);
        String result = countingMy(blackChess, whiteChess) + " " + countingMy(whiteChess, blackChess);
        System.out.println(result);
    }

    static int maxSideMy = 18;

    static int countingMy(String[] alias, String[] ememy) {
        Set<String> totalQiSet = new HashSet<>();
        for (String alia : alias) {
            totalQiSet.add(alia);
            String[] coorArr = alia.split("_");
            int x = Integer.parseInt(coorArr[0]);
            int y = Integer.parseInt(coorArr[1]);

            if (x > 0) {
                totalQiSet.add((x - 1) + "_" + y);
            }
            if (x < maxSideMy) {
                totalQiSet.add((x + 1) + "_" + y);
            }
            if (y > 0) {
                totalQiSet.add(x + "_" + (y - 1));
            }
            if (y < maxSideMy) {
                totalQiSet.add(x + "_" + (y + 1));
            }
        }
        int res = totalQiSet.size() - alias.length;

        for (String str : ememy) {
            if (totalQiSet.contains(str)) {
                res--;
            }
        }

        return res;
    }

    static String[] convertToCoordinate(String input) {
        String[] inputArr = input.split(" ");
        String[] chess = new String[inputArr.length / 2];
        for (int i = 0; i < chess.length; i++) {
            chess[i] = inputArr[i * 2] + "_" + inputArr[i * 2 + 1];
        }
        return chess;
    }

    public static void main00(String[] args) {
        Scanner sc = new Scanner(System.in);

        String blackInput = sc.nextLine();
        String whiteInput = sc.nextLine();
        int[] black = Arrays.stream(blackInput.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] white = Arrays.stream(whiteInput.split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(black, white));
        // ------------------------------------
        // ------------------------------------
        // ------------------------------------
        String[] blacks = transform(blackInput.split(" "));
        String[] whites = transform(whiteInput.split(" "));
        System.out.println(counting(blacks, whites) + " " + counting(whites, blacks));

        // ------------------------------------
        // ------------------------------------
        // ------------------------------------
        // set记录所有黑白棋子的坐标，格式：x,y
        Set<String> set = new HashSet<>();
        // 分别初始化棋子坐标
        int[][] thirdBlacks = collect(blackInput.split(" "), set);
        int[][] thirdWhites = collect(whiteInput.split(" "), set);
        boolean[][] visitedBlack = new boolean[19][19];
        boolean[][] visitedWhite = new boolean[19][19];
        int ansBlack = check(thirdBlacks, visitedBlack, set);
        int ansWhite = check(thirdWhites, visitedWhite, set);
        System.out.println(ansBlack + " " + ansWhite);
    }

    private static int[][] collect(String[] input, Set<String> set) {
        int[][] chessArray = new int[input.length / 2][2];
        for (int i = 0; i < input.length; i += 2) {
            int x = Integer.parseInt(input[i]);
            int y = Integer.parseInt(input[i + 1]);
            chessArray[i / 2][0] = x;
            chessArray[i / 2][1] = y;
            set.add(x + "," + y);
        }
        return chessArray;
    }

    static int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private static int check(int[][] locations, boolean[][] visited, Set<String> set) {
        int ans = 0;
        for (int[] location : locations) {
            for (int[] dir : dirs) { // 查看上下左右四个邻居
                int x = location[0] + dir[0], y = location[1] + dir[1];
                // 邻居未越界 && 邻居未访问 && 邻居不是棋子
                if (x >= 0 && x < 19 && y >= 0 && y < 19 && !visited[x][y] && !set.contains(x + "," + y)) {
                    ans++;
                    visited[x][y] = true;
                }
            }
        }
        return ans;
    }

    private static String getResult(int[] black, int[] white) {

        // 定义棋盘，没有棋子用0表示
        int[][] board = new int[19][19];

        for (int i = 0; i < black.length; i += 2) {
            int x = black[i];
            int y = black[i + 1];
            board[x][y] = 1; // 棋盘中黑棋用1表示
        }

        for (int i = 0; i < white.length; i += 2) {
            int x = white[i];
            int y = white[i + 1];
            board[x][y] = 2; // 棋盘中白棋用2表示
        }

        // 黑棋的气数
        int black_liberty_count = 0;
        // 白棋的气数
        int white_liberty_count = 0;

        // 上下左右四个方向的偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                // 如果当前位置没有棋子，则可能是黑棋或白棋的气
                if (board[i][j] == 0) {
                    // 当前位置是否为黑棋的气
                    boolean isBlackLiberty = false;
                    // 当前位置是否白棋的气
                    boolean isWhiteLiberty = false;

                    // 若为黑棋或者白棋的气，则当前位置的上下左右的位置上必有黑棋或白棋
                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];

                        // 若当前位置的上下左右的位置越界，则不考虑
                        if (newI < 0 || newI >= 19 || newJ < 0 || newJ >= 19)
                            continue;

                        // 若当前位置的上下左右的位置存在黑棋，则当前位置为黑棋的气
                        isBlackLiberty = isBlackLiberty || (board[newI][newJ] == 1);
                        // 若当前位置的上下左右的位置存在白棋，则当前位置为白棋的气
                        isWhiteLiberty = isWhiteLiberty || (board[newI][newJ] == 2);
                    }

                    if (isBlackLiberty)
                        black_liberty_count++;
                    if (isWhiteLiberty)
                        white_liberty_count++;
                }
            }
        }

        return black_liberty_count + " " + white_liberty_count;
    }

    static int maxSide = 18;

    static int counting(String[] alias, String[] ememy) {
        Set<String> count = new HashSet<>();
        for (String a : alias) {
            count.add(a);
            String[] loc = a.split("_");
            int x = Integer.parseInt(loc[0]);
            int y = Integer.parseInt(loc[1]);
            if (x > 0) {
                count.add(Integer.toString(x - 1) + "_" + loc[1]);
            }
            if (x < maxSide) {
                count.add(Integer.toString(x + 1) + "_" + loc[1]);
            }
            if (y > 0) {
                count.add(loc[0] + "_" + Integer.toString(y - 1));
            }
            if (y < maxSide) {
                count.add(loc[0] + "_" + Integer.toString(y + 1));
            }
        }
        int res = count.size() - alias.length;
        for (String e : ememy) {
            if (count.contains(e)) {
                res--;
            }
        }
        return res;
    }

    static String[] transform(String[] locs) {
        String[] chess = new String[locs.length / 2];
        for (int i = 0; i < locs.length; ) {
            chess[i / 2] = locs[i] + "_" + locs[i + 1];
            i += 2;
        }
        return chess;
    }
}