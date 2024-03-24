// (C卷,100分)- 拼接URL（Java & JS & Python）
// 题目描述
// 给定一个url前缀和url后缀,通过,分割 需要将其连接为一个完整的url
//
// 如果前缀结尾和后缀开头都没有/，需要自动补上/连接符
// 如果前缀结尾和后缀开头都为/，需要自动去重
// 约束：不用考虑前后缀URL不合法情况
//
// 输入描述
// url前缀(一个长度小于100的字符串)，url后缀(一个长度小于100的字符串)
//
//
// 输出描述
// 拼接后的url
//
// 用例
// 输入 /acm,/bb
// 输出 /acm/bb
// 说明 无
// 输入 /abc/,/bcd
// 输出 /abc/bcd
// 说明 无
// 输入 /acd,bef
// 输出 /acd/bef
// 说明 无
// 输入 ,
// 输出 /
// 说明 无
// 题目解析
// 逻辑题，可以直接看代码实现了解逻辑。

package com.lew.algo.hw.od._01_String;

import java.util.Scanner;

public class _060_01_String_ConcatUrl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        System.out.println(getResult(s));
        System.out.println(getResultMy(s));
        System.out.println(getResultMy2(s));
    }

    public static String getResultMy2(String s) {
        String[] split = s.split(",", 2);
        String prefix = split[0];
        String suffix = split[1];
        String newPre = prefix.replaceAll("/+$", "");
        String newSuf = suffix.replaceAll("^/+", "");

        return newPre + "/" + newSuf;
    }

    public static String getResultMy(String s) {
        // split(",")函数比较难搞，对于下面情况按照","分割出来的字符串数组分别是：
        // "," => []
        // "1," => ["1"]
        // ",1" => ["", "1"]
        // 改进：使用重载方法split(String regex, int limit) ，limit写2就行
        String[] arr = s.split(",", 2);
        String prefix = arr[0];
        String suffix = arr[1];
        return concatResult(prefix, suffix);
    }

    public static String getResult(String s) {
        // split(",")函数比较难搞，对于下面情况按照","分割出来的字符串数组分别是：
        // "," => []
        // "1," => ["1"]
        // ",1" => ["", "1"]
        // 改进：使用重载方法split(String regex, int limit) ，limit写2就行
        String[] arr = s.split(",");
        String prefix = arr.length > 0 && arr[0].length() > 0 ? arr[0] : "/";
        String suffix = arr.length > 1 && arr[1].length() > 0 ? arr[1] : "/";
        return concatResult(prefix, suffix);
    }

    public static String concatResult(String prefix, String suffix) {
        // prefix前缀，需要将其结尾的多个/去除，使用正则 /+$ 来匹配结尾的多个/
        // suffix后缀，需要将其开头的多个/去除，使用正则 ^/+ 来匹配开头的多个/
        // $的用法：表示从字符串末尾进行匹配
        prefix = prefix.replaceAll("/+$", "");
        // ^的用法：
        //1、限定开头：匹配后面紧跟的字符为开头的字符（[ ]之外或数字、元字符之前），如^[0-9]，含义为以0-9中的数字为第一个数字开头
        // 2、（否）取反:当这个字符出现在一个字符集合模式（[ ]之内）的第一个字符时，表示为取反。如[^0-9]，表示为匹配除了数字以外的字符。
        suffix = suffix.replaceAll("^/+", "");
        return prefix + "/" + suffix;
    }
}