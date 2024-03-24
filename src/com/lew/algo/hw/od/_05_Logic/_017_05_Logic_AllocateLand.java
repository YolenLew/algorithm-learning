// (C卷,100分)- 分配土地（Java & JS & Python & C）
// 题目描述
// 从前有个村庄，村民们喜欢在各种田地上插上小旗子，旗子上标识了各种不同的数字。
//
// 某天集体村民决定将覆盖相同数字的最小矩阵形的土地分配给村里做出巨大贡献的村民，请问此次分配土地，做出贡献的村民种最大会分配多大面积?
//
// 输入描述
// 第一行输入 m 和 n，
//
// m 代表村子的土地的长
// n 代表土地的宽
// 第二行开始输入地图上的具体标识
//
// 输出描述
// 此次分配土地，做出贡献的村民种最大会分配多大面积
//
// 备注
// 旗子上的数字为1~500，土地边长不超过500
// 未插旗子的土地用0标识
// 用例
// 输入 3 3
// 1 0 1
// 0 0 0
// 0 1 0
// 输出 9
// 说明 土地上的旗子为1，其坐标分别为(0,0)，(2,1)以及(0,2)，为了覆盖所有旗子，矩阵需要覆盖的横坐标为0和2，纵坐标为0和2，所以面积为9，即（2-0+1）*（2-0+1）= 9
// 输入 3 3
// 1 0 2
// 0 0 0
// 0 3 4
// 输出 1
// 说明 由于不存在成对的小旗子，故而返回1，即一块土地的面积。
// 题目解析
// 根据用例1说明来看，最小矩形需要覆盖相同数字得所有旗子。
//
// 土地上的旗子为1，其坐标分别为(0,0)，(2,1)以及(0,2)，为了覆盖所有旗子，矩阵需要覆盖的横坐标为0和2，纵坐标为0和2，所以面积为9，即（2-0+1）*（2-0+1）= 9
//
// 但是这产生一个问题？比如下图所示：
//
//
//
// 如果我要覆盖住所有的数字1，那么必然会同时覆盖了数字2，3，此时该怎么办呢？
//
// 题目没有说怎么处理，那么我理解就不需要管，只需要保证覆盖所有1的矩形最小就行。
//
// 本题解题思路很简单，只需要统计出相同数字的出现的“所有坐标位置”中：
//
// 最小的横坐标（矩形最上面一行的行号）
// 最大的横坐标（矩形最下面一行的行号）
// 最小的纵坐标（矩形最左边一列的列号）
// 最大的纵坐标（矩形最右边一列的列号）
// 即可。比如：前面图示中数字1的所有坐标位置(1,1)、(1,3)、(2,4)、(3,1)、(3,3)中：
//
// 最小的横坐标：1
// 最大的横坐标：3
// 最小的纵坐标：1
// 最大的纵坐标：4
// 那么包含所有数字1的最小矩形面积为：
//
// (最大的横坐标 - 最小的横坐标 + 1) * (最大的纵坐标 - 最小的纵坐标 + 1)

package com.lew.algo.hw.od._05_Logic;

import java.util.HashMap;
import java.util.Scanner;

public class _017_05_Logic_AllocateLand {
    static class Rect {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        private void setRow(int row) {
            this.minRow = Math.min(this.minRow, row);
            this.maxRow = Math.max(this.maxRow, row);
        }

        private void setCol(int col) {
            this.minCol = Math.min(this.minCol, col);
            this.maxCol = Math.max(this.maxCol, col);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(); // 长（行数）
        int n = sc.nextInt(); // 宽（列数）

        // 注意：需要存在至少两个相同小旗子
        HashMap<Integer, Rect> rects = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = sc.nextInt();

                if (num > 0) {
                    rects.putIfAbsent(num, new Rect());
                    rects.get(num).setRow(i);
                    rects.get(num).setCol(j);
                }
            }
        }

        int maxArea = 0;
        for (int num : rects.keySet()) {
            Rect rect = rects.get(num);

            maxArea = Math.max(maxArea, (rect.maxRow - rect.minRow + 1) * (rect.maxCol - rect.minCol + 1));
        }

        System.out.println(maxArea);
    }
}