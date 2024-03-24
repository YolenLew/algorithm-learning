// (C卷,100分)- 解密犯罪时间（Java & JS & Python）
// 题目描述
// 警察在侦破一个案件时，得到了线人给出的可能犯罪时间，形如 “HH:MM” 表示的时刻。
//
// 根据警察和线人的约定，为了隐蔽，该时间是修改过的，
//
// 解密规则为：利用当前出现过的数字，构造下一个距离当前时间最近的时刻，则该时间为可能的犯罪时间。
//
// 每个出现数字都可以被无限次使用。
//
// 输入描述
// 形如HH:SS字符串，表示原始输入。
//
// 输出描述
// 形如HH:SS的字符串，表示推理处理的犯罪时间。
//
// 备注
// 1.可以保证现任给定的字符串一定是合法的。
//
// 例如，“01:35”和“11:08”是合法的，“1:35”和“11:8”是不合法的。
//
// 2.最近的时刻可能在第二天。
//
// 用例
// 输入 输出
// 20:12 20:20
// 23:59 22:22
// 12:58 15:11
// 18:52 18:55
// 23:52 23:53
// 09:17 09:19
// 07:08 08:00
// 题目解析
// 解密规则为：利用当前出现过的数字，构造下一个距离当前时间最近的时刻，则该时间为可能的犯罪时间。
//
// 本题重在理解“下一个”最近时间，这里是“下一个”，而不是“上一个”，因此要求的时间在当前时间之后。但是备注中又说：“最近的时刻可能在第二天。”
//
// 也就是说，要求的时间 并不是严格 比当前时间 小的。
//
// 这题其实用dfs求全排列的话，就很简单。
//
// 我们基于dfs求得输入“20:12”的全部排列，
//
// 即由0，1，2构成的四层排列，题目说“每个出现数字都可以被无限次使用”，因此每层多可以使用相同的数字。

package com.lew.algo.hw.od._04_BFS_DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class _074_04_BFS_DFS_D_DecryptTimeOfCrime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tmp = sc.nextLine().split(":");
        String hour = tmp[0];
        String minute = tmp[1];

        System.out.println(getResult(hour, minute));
        System.out.println(getResultMy(hour, minute));
    }

    public static String getResultMy(String hour, String minute) {
        HashSet<Character> charSet = new HashSet<>();
        for (char ch : hour.toCharArray()) {
            charSet.add(ch);
        }
        for (char ch : minute.toCharArray()) {
            charSet.add(ch);
        }

        List<String> result = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();
        // 求所有数字的全排列时间：比如0、1、2的排列有00:00 00:01等
        Character[] numsArr = new Character[charSet.size()];
        charSet.toArray(numsArr);
        dfsMy(numsArr, path, result);

        int index = result.indexOf(hour + minute);
        StringBuilder  target = new StringBuilder();
        if (index == result.size() - 1) {
            target.append(result.get(0));
        } else {
            target.append(result.get(index + 1));
        }

        target.insert(2, ":");
        return target.toString();
    }

    static Pattern legalTime = Pattern.compile("(([0-1][0-9])|([2][0-3]))[0-5][0-9]");

    public static void dfsMy(Character[] arr, LinkedList<Character> path, List<String> res) {
        if (path.size() == 4) {
            String concatTime = path.stream().map(String::valueOf).collect(Collectors.joining());
            if (legalTime.matcher(concatTime).matches()) {
                res.add(concatTime);
            }
            return;
        }

        // 遍历选择列表
        for (int i = 0; i < arr.length; i++) {
            // 进行选择
            path.addLast(arr[i]);
            dfsMy(arr, path, res);
            path.removeLast();
        }

    }

    // 基于dfs求得输入“20:12”的全部排列，即由0，1，2构成的四层排列，题目说“每个出现数字都可以被无限次使用”，因此每层多可以使用相同的数字。
    // 然后对全排列结果进行字典排序，获取当前时间的下一个时间即可
    public static String getResult(String hour, String minute) {
        HashSet<Character> set = new HashSet<>(); // 去重提取所有时间的数字：比如20:12能提取0、1、2
        for (int i = 0; i < hour.length(); i++)
            set.add(hour.charAt(i));
        for (int i = 0; i < minute.length(); i++)
            set.add(minute.charAt(i));
        Character[] arr = set.toArray(new Character[0]);

        ArrayList<String> res = new ArrayList<>();
        dfs(arr, new LinkedList<>(), res); // 求所有数字的全排列时间：比如0、1、2的排列有00:00 00:01等

        res.sort(String::compareTo);
        int index = res.indexOf(hour + minute);

        String recentTime;
        if (index == res.size() - 1) {
            recentTime = res.get(0);
        } else {
            recentTime = res.get(index + 1);
        }

        String[] split = recentTime.split("");
        split[1] += ":";
        return String.join("", split);
    }

    // 全排列中必然含有不合法的时间，可利用正则来过滤掉非法的时间：[H1][H2][M1][M2]
    // [H1][H2]：([01][0-9])|([2][0-3])，H1取值可能有0、1、2三种数值
    // [M1][M2]：00-59，智能在这个范围取值
    static Pattern p = Pattern.compile("(([01][0-9])|([2][0-3]))[0-5][0-9]");

    public static void dfs(Character[] arr, LinkedList<Character> path, ArrayList<String> res) {
        if (path.size() == 4) { // 满足条件判断
            StringBuilder sb = new StringBuilder();
            for (Character c : path)
                sb.append(c);
            String timeStr = sb.toString();

            if (p.matcher(timeStr).find()) {
                res.add(timeStr);
            }

            return;
        }

        for (Character c : arr) {// 遍历选择列表
            path.add(c);// 判断进行选择
            dfs(arr, path, res); // 递归下钻选择
            path.removeLast(); // 撤销选择
        }
    }
}