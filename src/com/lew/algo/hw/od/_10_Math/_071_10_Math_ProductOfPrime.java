// (C卷,100分)- 素数之积（Java & JS & Python & C）
// 题目描述
// RSA加密算法在网络安全世界中无处不在，它利用了极大整数因数分解的困难度，数据越大，安全系数越高，给定一个 32 位正整数，请对其进行因数分解，找出是哪两个素数的乘积。
//
// 输入描述
// 一个正整数 num 0 < num < 2147483647
//
// 输出描述
// 如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1, -1
//
// 用例
// 输入 15
// 输出 3 5
// 输入 27
// 输出 -1 -1
// 题目解析
// 首先，要了解素数概念，及素数判定方法
//
// 判定素数的四种方法（JavaScript）_伏城之外的博客-CSDN博客
//
// 其次再来解析题目
//
// 给定一个 32 位正整数，请对其进行因数分解，找出是哪两个素数的乘积。
//
// 15 可以被分解为 3 5，由于是这两个素数乘积为15，所以判定为分解成功；
//
// 27 可以被分解为 3 3 3，由于不是两个素数的乘积，所以判定为分解失败；
//
// 这里，我理解题目想表达的意思是，
// 给定的正整数，要支持只能分解为两个素数因子，且两个素数乘积要为给定的正整数，那么
//
// 若给定的正整数为素数，则只能分解为1和自身，而1不是素数，所以判定为分解失败
// 这可以帮助我们缩小了给定的正整数的范围只能是非素数。
//
// 另外如果一个正整数为两个素数的乘积，比如 11 * 13 = 143，则必然只能分解为这两个素数，因为这两个素数无法再次分解，所以该正整数没有其他的素数因子了。所以，一旦我们得到一个可以被正整数整除的素数因子，则另一个因子只能为素数。

package com.lew.algo.hw.od._10_Math;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _071_10_Math_ProductOfPrime {
    public static void main(String[] args) {
        // 考察的是一些数学知识，如果数字n不是素数，则它一定可以写成两个数字相乘的形式（除了1*n），这两个数
        // 字称为n的因子，如：16=2*8=4*4，所以在判断n是否为素数时，只要找到一对因子中的一个就能证明n不是素
        // 数，而这一对因子中至少有一个因子小于等于 根号 n
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(getResult(num));
        System.out.println(getResult03(num));
        // System.out.println(getResult01(num));
    }

    // 求解素数之积
    // 给定的正整数，要支持只能分解为两个素数因子，且两个素数乘积要为给定的正整数，那么若给定的正整数为素数，则只能分解为1和自身，而1不是素数，所以判定为分解失败
    // 这可以帮助我们缩小了给定的正整数的范围只能是非素数。
    //
    // 另外如果一个正整数为两个素数的乘积，比如 11 * 13 = 143，则必然只能分解为这两个素数，因为这两个素数无法再次分解，所以该正整数没有其他的素数因子了。
    // 所以，一旦我们得到一个可以被正整数整除的素数因子，则另一个因子只能为素数。
    public static String getResult(int n) {
        // 如果n为素数，则必然不可能是两个素数之积
        if (isPrime(n)) {
            return "-1 -1";
        }

        // 假设i为n的因子
        for (int i = 2; i < n; i++) {
            // 若n不能整除i,则i不是n的因子，继续下次循环，找新的i
            // 若n可以整除i,则i就是n的因子
            if (n % i == 0) {
                // j为n的另一因子
                int j = n / i;

                // 只有i,j因子都为素数时，n才是符合题意的素数之积
                if (isPrime(i) && isPrime(j)) {
                    // 如果n为两个素数之积，则n只能分解为这两个因子，因为素数无法再次分解出其他因子，也就是说n不再有其他因子了（因子不包含1和自身）
                    return i < j ? i + " " + j : j + " " + i;
                } else {
                    // 如果i，j有一个不是素数因子，则说明n存在非素数因子，此时n不可能是素数之积
                    // 如果i，j为相同的素数因子，则n不是满足题意的素数之积
                    // 此时可以判定n不符合要求了，直接退出循环
                    break;
                }
            }
        }

        return "-1 -1";
    }

    // 判断n是否为素数
    public static boolean isPrime(int n) {
        if (n <= 3)
            return n > 1;

        if (n % 6 != 1 && n % 6 != 5)
            return false;

        for (int i = 5; i <= Math.sqrt(n); i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    private static String getResult03(int num) {
        Set<Integer> factors = new HashSet<>();

        int tmp = num;
        int f = 2;
        // 先找到输入整数的所有因子，并存储在一个集合中
        while (tmp != 1) {
            // 判断 f 是否是 tmp 的因子，如果不是则递增 f，直到找到 tmp 的一个因子
            if (tmp % f != 0) {
                f++;
            } else {
                factors.add(f);
                tmp /= f;
            }
        }

        String result = "-1 -1";
        boolean flag = false;
        for (Integer f1 : factors) {
            for (Integer f2 : factors) {
                if (f1 * f2 == num) {
                    int min = Math.min(f1, f2);
                    int max = Math.max(f1, f2);
                    result = min + " " + max;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        return result;
    }
}