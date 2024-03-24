package com.lew.algo.hw.od._02_BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class _006_02_BinarySearch_WuKongLovePantao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 获取桃子个数
        int[] peaches = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 限时
        int limit = Integer.parseInt(sc.nextLine());

        int result = getResult(peaches, limit);
        System.out.println(result);
    }

    private static int getResult(int[] peaches, int limit) {
        int n = peaches.length;

        // 特例: 树木的个数大于限时
        if (n > limit) {
            return 0;
        }
        Arrays.sort(peaches);
        // 二分查找方法逼近最小值
        int left = 1, right = peaches[n - 1];
        int ans = 0;
        while (left <= right) {
            int speed = left + (right - left) / 2;
            if (checkSpeed(peaches, speed, limit)) {
                ans = speed;
                // 能吃完，继续减小速度
                right = speed - 1;
            } else {
                left = speed + 1;
            }
        }

        return ans;
    }

    private static boolean checkSpeed(int[] peaches, int speed, int limit) {
        int count = 0;
        for (int peach : peaches) {
            // peach个桃子，每小时吃speed个，需要耗时
            count += (peach + speed - 1) / speed;
        }
        return count <= limit;
    }

}