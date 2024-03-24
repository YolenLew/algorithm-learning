// (C卷,100分)- 多段线数据压缩（Java & JS & Python & C）
// 在线OJ刷题
// 题目详情 - 多段线数据压缩 - Hydro
//
// 题目描述
// 下图中，每个方块代表一个像素，每个像素用其行号和列号表示。
//
//
//
// 为简化处理，多线段的走向只能是水平、竖直、斜向45度。
//
// 上图中的多线段可以用下面的坐标串表示：(2,8),(3,7),(3,6),(3,5),(4,4),(5,3),(6,2),(7,3),(8,4),(7,5)。
//
// 但可以发现，这种表示不是最简的，其实只需要存储6个蓝色的关键点即可，它们是线段的起点、拐点、终点，而剩下4个点是冗余的。
//
// 现在，请根据输入的包含有冗余数据的多线段坐标列表，输出其最简化的结果。
//
// 输入描述
// 2 8 3 7 3 6 3 5 4 4 5 3 6 2 7 3 8 4 7 5
//
// 所有数字以空格分隔，每两个数字一组，第一个数字是行号，第二个数字是列号；
// 行号和列号范围 为 [0, 64)，用例输入保证不会越界，考生不必检查；
// 输入数据至少包含两个坐标点
// 输出描述
// 2 8 3 7 3 5 6 2 8 4 7 5
//
// 压缩后的最简化坐标列表，和输入数据的格式相同。
//
// 备注
// 输出的坐标相对顺序不能变化
//
// 用例
// 输入 2 8 3 7 3 6 3 5 4 4 5 3 6 2 7 3 8 4 7 5
// 输出 2 8 3 7 3 5 6 2 8 4 7 5
// 说明
// 如上图所示，6个蓝色像素的坐标依次是：
//
// (2, 8)、(3, 7)、(3, 5)、(6, 2)、(8, 4)、(7, 5)
//
// 将他们按顺序输出即可。

package com.lew.algo.hw.od._10_Math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _048_10_Math_GuestsFromForeignCountries {
    // 检查是否三点共线的函数
    public static boolean checkSameLine(int[] p1, int[] p2, int[] p3) {
        // 本题的核心在于如何判定三点共线。
        // 已知三个点A、B、C坐标分别为(x1, y1)、(x2, y2)、(x3, y3)，那么向量AB和AC分别可以用(x2-x1, y2-y1)以及(x3-x1, y3-y1)来表示。
        // 三点共线等价于这两个向量平行，即等价于(x2-x1)*(y3-y1) == (x3-x1)*(y2-y1)成立。
        // 因此我们可以固定一个起始端点start_point来表示某一段线段的起始端点。start_point初始化为所有点中的第一个坐标。起始端点需要加入到答案列表ans中。
        // 然后遍历所有点points，考虑临近的两个点points[i]和poins[i-1]。若start_point、points[i]和poins[i-1]
        // 位于同一条直线上，那么说明中间的点poins[i-1]是可以忽略的
        // 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
        //
        // 原文链接：https://blog.csdn.net/weixin_48157259/article/details/136403626
        int x1 = p1[0], y1 = p1[1];
        int x2 = p2[0], y2 = p2[1];
        int x3 = p3[0], y3 = p3[1];
        return (x2 - x1) * (y3 - y1) == (x3 - x1) * (y2 - y1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int n = input.length / 2; // 获取点的个数n
        int[][] points = new int[n][2]; // 初始化存储点的二维数组
        // 将输入转化为二维数组
        for (int i = 0; i < n; i++) {
            points[i][0] = Integer.parseInt(input[2 * i]);
            points[i][1] = Integer.parseInt(input[2 * i + 1]);
        }

        // 初始化起始端点
        int[] startPoint = points[0];
        List<int[]> ans = new ArrayList<>();

        // 加入第一个点坐标，为起点坐标
        ans.add(startPoint);

        for (int i = 1; i < n; i++) {
            // 检查startPoint、points[i-1]和points[i]是否三点共线
            // 若是，说明points[i-1]被忽略，则可以直接跳过
            if (checkSameLine(startPoint, points[i - 1], points[i])) {
                continue;
            }
            // 否则，说明points[i-1]是一个拐点，加入ans中
            // 同时需要修改points[i-1]为新的起始端点startPoint
            else {
                ans.add(points[i - 1]);
                startPoint = points[i - 1];
            }
        }

        // 加入最后一个点的坐标，为终止点
        ans.add(points[n - 1]);

        // 将ans中的所有二元组输出为1行
        for (int[] point : ans) {
            System.out.print(point[0] + " " + point[1] + " ");
        }
    }
}
