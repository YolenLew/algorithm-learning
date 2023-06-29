package com.lew.algo.demo;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 任意长度的两个数相加
 * @author Yolen
 */
public class NumUtil {
    public static String addLargeIntegers(String num1, String num2) {
        BigInteger bigNum1 = new BigInteger(num1);
        BigInteger bigNum2 = new BigInteger(num2);
        BigInteger sum = bigNum1.add(bigNum2);
        return sum.toString();
    }

    public static String add(String num1, String num2) {
        BigDecimal bigNum1 = new BigDecimal(num1);
        BigDecimal bigNum2 = new BigDecimal(num2);
        BigDecimal sum = bigNum1.add(bigNum2);
        return sum.toString();
    }

    public static void main(String[] args) {
        String num1 = "1234567890.12345678901234567890";
        String num2 = "9876543210.98765432109876543210";

        String result = add(num1, num2);
        System.out.println("Result: " + result);
    }
}
