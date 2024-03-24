// (C卷,100分)- 最多购买宝石数目（Java & JS & Python & C）
// 题目描述
// 橱窗里有一排宝石，不同的宝石对应不同的价格，宝石的价格标记为 gems[i]
//
// 0 ≤ i < n
// n = gems.length
// 宝石可同时出售0个或多个，如果同时出售多个，则要求出售的宝石编号连续；
//
// 例如客户最大购买宝石个数为m，购买的宝石编号必须为：gems[i]，gems[i+1]，...，gems[i+m-1]
//
// 0 ≤ i < n
// m ≤ n
// 假设你当前拥有总面值为 value 的钱，请问最多能购买到多少个宝石，如无法购买宝石，则返回0。
//
// 输入描述
// 第一行输入n，参数类型为int，取值范围：[0,10^6]，表示橱窗中宝石的总数量。
//
// 之后 n 行分别表示从第0个到第n-1个宝石的价格，即 gems[0] 到 gems[n-1] 的价格，类型为int，取值范围：(0,1000]。
//
// 之后一行输入v，类型为int，取值范围：[0,10^9]，表示你拥有的钱。
//
// 输出描述
// 输出int类型的返回值，表示最大可购买的宝石数量。
//
// 用例
// 输入 7
// 8
// 4
// 6
// 3
// 1
// 6
// 7
// 10
// 输出 3
// 说明
// gems = [8,4,6,3,1,6,7], value = 10
//
// 最多购买的宝石为gems[2]至gems[4]或者gems[3]至gems[5]
//
// 输入 0
// 1
// 输出 0
// 说明 gems = [], value = 1
// 因为没有宝石，所以返回0
// 输入 9
// 6
// 1
// 3
// 1
// 8
// 9
// 3
// 2
// 4
// 15
// 输出 4
// 说明 gems = [6, 1, 3, 1, 8, 9, 3, 2, 4], value = 15
// 最多购买的宝石为gems[0]至gems[3]
// 输入 9
// 1
// 1
// 1
// 1
// 1
// 1
// 1
// 1
// 1
// 10
// 输出 9
// 说明 gems = [1, 1, 1, 1, 1, 1, 1, 1, 1], value = 10
// 最多购买的宝石为gems[0]至gems[8]，即全部购买

package com.lew.algo.hw.od._11_SlidingWindow;

import java.util.Scanner;

public class _034_11_SlidingWindow_MaxNumberOfGemsPurchased {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        int[] gems = new int[n];
        for (int i = 0; i < n; i++) {
            gems[i] = sc.nextInt();
        }
        int money = sc.nextInt();
        System.out.println(getResultMy(gems, n, money));
        System.out.println();
        // --------------------------------
        // --------------------------------
        // --------------------------------

    }

    private static int getResultMy(int[] gems, int n, int money) {
        int ans = 0;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < n) {
            windowSum += gems[right];

            if (windowSum <= money) {
                ans = Math.max(ans, right - left + 1);
            }

            // 缩小左指针
            while (windowSum > money) {
                windowSum -= gems[left];
                left++;
            }

            right++;
        }

        return ans;

//        while (left < n) {
//            if (cost < money) {
//                ans = Math.max(ans, right - left + 1);
//                // 继续移动右指针
//                right++;
//                if (right >= n) {
//                    break;
//                }
//                cost += gems[right];
//            } else if (cost == money) {
//                ans = Math.max(ans, right - left + 1);
//                // 继续移动指针
//                cost -= gems[left];
//                left++;
//                right++;
//                if (right >= n) {
//                    break;
//                }
//                cost += gems[right];
//            } else {
//                cost -= gems[left];
//                left++;
//                if (left >= n) {
//                    break;
//                }
//                cost += gems[left];
//            }
//        }
//        return ans;
    }

}