package com.lew.algo.hw.od;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author luyonglang
 * @date 2024/3/14
 */
public class AlgoTest {
    public static void main(String[] args) {
        // 字符 ? ASSIC码：63
        System.out.println('?' + 0);

        // 优先级队列
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < 10; i++) {
            pq1.offer(ThreadLocalRandom.current().nextInt(0, 100));
            pq2.offer(ThreadLocalRandom.current().nextInt(0, 100));
        }

        while (!pq1.isEmpty()) {
            System.out.print(pq1.poll() + " ");
        }
        System.out.println();

        while (!pq2.isEmpty()) {
            System.out.print(pq2.poll() + " ");
        }
        System.out.println();

        System.out.println("-------------calculateNumSum--------------");
        System.out.println(calculateNumSum(123) + "," + cal_digit_sum(123));
        System.out.println(calculateNumSum(4659) + "," + cal_digit_sum(4659));
        System.out.println();
    }

    private static int calculateNumSum(int num) {
        return String.valueOf(num).chars().map(Character::getNumericValue).sum();
    }

    public static int cal_digit_sum(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }
}
