// 输入一个n （1 <= n <= 10000)
//求能整除1-n的最小正整数，即最小公倍数
//
//数学要学好，互质的两个数的最小公倍数就是两个相乘，之后在考虑合数。
//
//我们先使用筛法筛出质数，合数中又可以分为两类，一类是合数等于一个质数的k次方，也就是说他和其中一个质数的最小公倍数等于他本身，另一类则可以用质数和质数相乘表示，化简可以变成1。
//
//举个例子，1-10的最小公倍数，lcm(1 2 3 4 5 6 7 8 9 10) 可以化为 lcm(1 1 1 1 5 1 7 8 9 1)相乘。
//
//首先6是2和3的最小公倍数，所以2和3同时存在的时候，6就可以去掉，保留2和3即可。
//
//10也同理，2和5就可以i表示10。
//
//然后再来某个数字是某个素数的k次方的，当该数字存在时，他的那个素数因子就可以被去掉，例如4，8。因为8是2的3次方，4是2的2次方，当4存在的时候可以直接把2删去，当8存在的时候可以把4删去。
//
//故而我们得到lcm[1,10]的最小公倍数为5*7*8*9 = 2520。
//
//因此我们只需要用n以内所有质数的小于n的k次方那个数来替换质数即可。
//————————————————
//版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
//原文链接：https://blog.csdn.net/misayaaaaa/article/details/130418913

package com.lew.algo.scene._04_math;

import java.math.BigInteger;
import java.util.Scanner;

public class _04_math_01_gcd {

    // 输入一个n （1 <= n <= 10000)
    //求能整除1-n的最小正整数，即最小公倍数
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        BigInteger res = new BigInteger(String.valueOf(nums[0]));
        for (int i = 1; i < n; i++) {
            BigInteger t = new BigInteger(String.valueOf(nums[i]));
            // java.math.BigInteger.gcd()：计算两个 BigInteger 的最大公约数
            res = res.multiply(t).divide(res.gcd(t));
        }
        System.out.println(res);
    }

    // 最大公约数 —— Greatest Common Divisor(GCD)
    // 求两个数的最大公约数（辗转相除法，欧几里得算法）
    // 欧几里得算法的基本思想是
    //  1.对于两个整数 a 和 b，如果 a 可以被 b 整除，则 b 就是它们的最大公约数。
    //  2.否则，将 a 对 b 取模得到余数 r，再将 b 和 r 计算最大公约数，直到 r 等于 0。此时，b就是最大公约数
    private static int gcd(int a, int b) {
        while (b > 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    // 两个数 a 和 b 的最小公倍数 (Leatest Common Multiple) 是指同时被 a 和 b 整除的最小倍数，记为 lcm (a, b) 。
    // 特殊的，当 a 和 b 互素时， lcm (a, b) = ab
    // 求两个数的最小公倍数（A和B的最小公倍数 = A和B的乘积 除以 A和B的最大公约数）
    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // 求多个数的最小公倍数：注意，存在整型溢出风险，最好用BigInteger类型
    private static int lcm(int[] ints) {
        int result = ints[0];
        for (int i = 1; i < ints.length; i++) {
            result = lcm(result, ints[i]);
        }
        return result;
    }
}