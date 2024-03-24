// (C卷,100分)- 掌握的单词个数（Java & JS & Python & C）
// 题目描述
// 有一个字符串数组 words 和一个字符串 chars。
//
// 假如可以用 chars 中的字母拼写出 words 中的某个“单词”（字符串），那么我们就认为你掌握了这个单词。
//
// words 的字符仅由 a-z 英文小写字母组成，例如 "abc"
//
// chars 由 a-z 英文小写字母和 "?" 组成。其中英文 "?" 表示万能字符，能够在拼写时当作任意一个英文字母。例如："?" 可以当作 "a" 等字母。
//
// 注意：每次拼写时，chars 中的每个字母和万能字符都只能使用一次。
//
// 输出词汇表 words 中你掌握的所有单词的个数。没有掌握任何单词，则输出0。
//
// 输入描述
// 第一行：输入数组 words 的个数，记作N。
//
// 第二行 ~ 第N+1行：依次输入数组words的每个字符串元素
//
// 第N+2行：输入字符串chars
//
// 输出描述
// 输出一个整数，表示词汇表 words 中你掌握的单词个数
//
// 备注
// 1 ≤ words.length ≤ 100
// 1 ≤ words[i].length, chars.length ≤ 100
// 所有字符串中都仅包含小写英文字母、英文问号
// 用例
// 输入 4
// cat
// bt
// hat
// tree
// atach??
// 输出 3
// 说明 可以拼写字符串"cat"、"bt"和"hat"
// 输入 3
// hello
// world
// cloud
// welldonehohneyr
// 输出 2
// 说明 可以拼写字符串"hello"和"world"
// 输入 3
// apple
// car
// window
// welldoneapplec?
// 输出 2
// 说明 可以拼写字符串"apple"和“car”

package com.lew.algo.hw.od._01_String;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _026_01_String_MasteredWords {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }
        String chars = sc.next();
        System.out.println(getResult(words, n, chars));
        System.out.println(getResultMy(words, n, chars));
    }

    public static int getResultMy(String[] words, int n, String chars) {
        int result = 0;
        // 统计chars字符个数
        Map<Character, Integer> charsCountMap = new HashMap<>();
        for (char ch : chars.toCharArray()) {
            charsCountMap.put(ch, charsCountMap.getOrDefault(ch, 0) + 1);
        }
        // 万能符个数
        int questionNum = charsCountMap.getOrDefault('?', 0);

        for (String word : words) {
            // 统计单词个数
            Map<Character, Integer> wordCountMap = new HashMap<>();
            for (char ch : word.toCharArray()) {
                wordCountMap.put(ch, wordCountMap.getOrDefault(ch, 0) + 1);
            }

            // 判断是否学会
            result += checkLearnedWord(charsCountMap, wordCountMap, questionNum) ? 1 : 0;
        }
        return result;
    }

    private static boolean checkLearnedWord(Map<Character, Integer> charsCountMap, Map<Character, Integer> wordCountMap,
        int questionNum) {
        for (Character character : wordCountMap.keySet()) {
            if (charsCountMap.getOrDefault(character, 0) >= wordCountMap.getOrDefault(character, 0)) {
                continue;
            } else {
                questionNum -= (wordCountMap.getOrDefault(character, 0) - charsCountMap.getOrDefault(character, 0));
                if (questionNum < 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int getResult(String[] words, int n, String chars) {
        int ans = 0;

        // 统计chars字符串中各字符的数量
        int[] cnt_chars = charStatistic(chars);

        for (int i = 0; i < n; i++) {
            int diff = 0;

            // 统计word字符串中各字符的数量
            int[] cnt_word = charStatistic(words[i]);

            for (int j = 0; j < 128; j++) {
                // 记录word的某字符超过chars的对应字符出现的数量
                diff += Math.max(cnt_word[j] - cnt_chars[j], 0);
            }

            if (diff <= cnt_chars['?']) {
                ans++;
                // System.out.println(words[i]);
            }
        }

        return ans;
    }

    public static int[] charStatistic(String s) {
        int[] cnts = new int[128];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnts[c] += 1;
        }

        return cnts;
    }
}