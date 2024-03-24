package com.lew.algo.hw.od._09_Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _053_09_Greedy_MinAdjustmentsForChildrenGroups02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean[] f = new boolean[1000]; // 标记是否已经分好组
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();

        // 读取输入的字符串，分别处理成数字列表
        String stra = scanner.nextLine();
        String strb = scanner.nextLine();
        stra += " ";
        strb += " ";
        int current = 0;

        // 处理输入的字符串并将数字添加到aList
        for (int i = 0; i < stra.length(); i++) {
            if (Character.isDigit(stra.charAt(i))) {
                current = current * 10 + stra.charAt(i) - '0';
            } else {
                aList.add(current);
                current = 0;
            }
        }

        current = 0;
        // 处理输入的字符串并将数字添加到bList
        for (int i = 0; i < strb.length(); i++) {
            if (Character.isDigit(strb.charAt(i))) {
                current = current * 10 + strb.charAt(i) - '0';
            } else {
                bList.add(current);
                current = 0;
            }
        }

        int n = bList.size();

        // 标记每个学生的组数
        // for (int i = 0; i < n; i += 3) {
        // for (int j = 0; j < 3; j++) {
        // numToGroupMap.put(bList.get(i + j), i / 3 + 1);
        // }
        // }

        // 标记每个学生的组数
        Map<Integer, Integer> numToGroupMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            numToGroupMap.put(bList.get(i), i / 3 + 1);
        }

        int ans = 0;

        // 不断重新排布，直到所有学生都在3连中
        while (true) {
            boolean ff = false;

            // 遍历aList，考虑连续的学生是否在同一组
            for (int i = 0; i < n; i++) {
                int currentNum = aList.get(i);

                if (f[numToGroupMap.get(currentNum)]) {
                    continue; // 如果已经凑成了，就不用继续凑了
                }

                int num = 1;

                // 判断是否存在连续的学生在同一组
                if (i + 1 < n && numToGroupMap.get(aList.get(i + 1)).equals(numToGroupMap.get(currentNum))) {
                    num++;
                }

                if (i + 2 < n && num == 2
                    && numToGroupMap.get(aList.get(i + 2)).equals(numToGroupMap.get(currentNum))) {
                    num++;
                }

                if (num == 2) {
                    f[numToGroupMap.get(currentNum)] = true;
                    ans += 1;

                    // 将2连变为3连
                    for (int j = 0; j < n; j++) {
                        if (numToGroupMap.get(aList.get(j)).equals(numToGroupMap.get(currentNum)) && j != i
                            && j != i + 1) {
                            int tmp = aList.get(j);
                            aList.remove(j);
                            aList.add(i, tmp);
                        }
                    }

                    ff = true;
                }

                if (num == 3) {
                    f[numToGroupMap.get(currentNum)] = true;
                }
            }

            // 如果没有2连，需要将1连变为3连
            if (!ff) {
                for (int i = 0; i < n; i++) {
                    int currentNum = aList.get(i);

                    if (f[numToGroupMap.get(currentNum)]) {
                        continue;
                    }

                    ans += 2;

                    // 将1连变为3连
                    for (int j = 0; j < n; j++) {
                        if (numToGroupMap.get(aList.get(j)).equals(numToGroupMap.get(currentNum))
                            && !aList.get(j).equals(currentNum)) {
                            int tmp = aList.get(j);
                            aList.remove(j);
                            aList.add(i, tmp);
                        }
                    }
                }
            }

            // 判断是否所有学生都在3连中
            boolean isout = true;
            for (int i = 0; i < n; i += 3) {
                if (numToGroupMap.get(aList.get(i)).equals(numToGroupMap.get(aList.get(i + 1)))
                    && numToGroupMap.get(aList.get(i)).equals(numToGroupMap.get(aList.get(i + 2)))) {
                    continue;
                } else {
                    isout = false;
                }
            }

            if (isout) {
                break;
            }
        }

        // 输出答案
        System.out.println(ans);
    }
}
