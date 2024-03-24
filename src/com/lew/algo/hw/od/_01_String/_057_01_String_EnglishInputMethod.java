//

package com.lew.algo.hw.od._01_String;

import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class _057_01_String_EnglishInputMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String pre = sc.nextLine();
        System.out.println(getResult(str, pre));
        System.out.println(getResultMy(str, pre));
    }

    public static String getResultMy(String str, String pre) {
        String[] words = str.split("[^a-z]");
        TreeSet<String> cache = new TreeSet<>();
        Collections.addAll(cache, words);

        String result = cache.stream().filter(s -> s.startsWith(pre)).collect(Collectors.joining(" "));
        if (result.length() > 0) {
            return result;
        } else {
            return pre;
        }
    }

    public static String getResult(String str, String pre) {
        // 方法一：正则匹配；方法二：一个一个字符匹配然后拼接一个单词
        String[] tmp = str.split("[^a-zA-Z]");
        TreeSet<String> cache = new TreeSet<>();
        Collections.addAll(cache, tmp);

        StringJoiner sj = new StringJoiner(" ");
        cache.stream().filter(s -> s.startsWith(pre)).forEach(sj::add);

        String ans = sj.toString();
        if (ans.length() > 0) {
            return ans;
        } else {
            return pre;
        }
    }
}