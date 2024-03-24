package com.lew.algo.hw.od._13_Hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _041_13_Hash_PasswordDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // 利用字符串替换操作
        System.out.println(getResult(input));
        System.out.println(getResult02(input));
    }

    // 涉及元素一一映射的关系，因此本题是非常简单直接的哈希表题，属于送分题。
    //
    // 为了构建数字和字母之间的映射，可以使用ASCII码值来完成。使用ord("a")可以计算得到字符"a"的ASCII码值，选定数字i的范围为[1,
    // 26]，则ord("a")+i-1可以得到所有小写字母的ASCII码值，再使用chr()内置函数讲ASCII码值转换回小写字母，即chr(ord("a")+i-1)。故构建映射关系哈希表的代码为
    //
    // dic = {i: chr(ord("a") + i - 1) for i in range(1, 27)}
    // 1
    // 剩余部分就相当简单了。
    //
    // 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
    // 原文链接：https://blog.csdn.net/weixin_48157259/article/details/135737827
    private static String getResult02(String input) {
        // 构建数字和字母之间的映射关系
        Map<Integer, Character> dic = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            dic.put(i, (char) ('a' + i - 1));
        }

        // 根据"*"进行分割
        String[] lst = input.split("\\*");

        StringBuilder ans = new StringBuilder();
        // 遍历lst中的所有字符串类型数字num_s
        // java在分割字符串后不会出现空串
        for (int i = 0; i < lst.length; i++) {
            int num = Integer.parseInt(lst[i]);
            ans.append(dic.get(num));
        }

        return ans.toString();
    }

    // 字符串替换操作：将如下密文和明文进行按照顺序依次映射替换
    //
    // "26*" → "z"
    // "25*" → "y"
    // ...
    // "10*" → "j"
    // "9" → "i"
    // ...
    // "1" → "a"
    private static String getResult(String s) {
        for (int i = 26; i >= 1; i--) {
            String key = i + (i > 9 ? "\\*" : "");
            String val = String.valueOf((char)('a' + i - 1));
            s = s.replaceAll(key, val);
        }
        return s;
    }
}