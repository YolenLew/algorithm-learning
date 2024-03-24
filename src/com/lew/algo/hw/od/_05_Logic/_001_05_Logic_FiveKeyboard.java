// (C卷,100分)- 5键键盘（Java & JS & Python）
//题目描述
//有一个特殊的5键键盘，上面有a，ctrl-c，ctrl-x，ctrl-v，ctrl-a五个键。
//
//a键在屏幕上输出一个字母a；
//
//ctrl-c将当前选择的字母复制到剪贴板；
//
//ctrl-x将当前选择的字母复制到剪贴板，并清空选择的字母；
//
//ctrl-v将当前剪贴板里的字母输出到屏幕；
//
//ctrl-a选择当前屏幕上的所有字母。
//
//注意：
//
//剪贴板初始为空，新的内容被复制到剪贴板时会覆盖原来的内容
//当屏幕上没有字母时，ctrl-a无效
//当没有选择字母时，ctrl-c和ctrl-x无效
//当有字母被选择时，a和ctrl-v这两个有输出功能的键会先清空选择的字母，再进行输出
//给定一系列键盘输入，输出最终屏幕上字母的数量。
//
//输入描述
//输入为一行，为简化解析，用数字1 2 3 4 5代表a，ctrl-c，ctrl-x，ctrl-v，ctrl-a五个键的输入，数字用空格分隔。
//输出描述
//输出一个数字，为最终屏幕上字母的数量。
//用例
//输入	1 1 1
//输出	3
//说明	连续键入3个a，故屏幕上字母的长度为3。
//输入	1 1 5 1 5 2 4 4
//输出	2
//说明
//输入两个a后ctrl-a选择这两个a，再输入a时选择的两个a先被清空，所以此时屏幕只有一个a，
//
//后续的ctrl-a，ctrl-c选择并复制了这一个a，最后两个ctrl-v在屏幕上输出两个a，
//
//故屏幕上字母的长度为2（第一个ctrl-v清空了屏幕上的那个a）。
//
//题目解析
//逻辑题，主要考察多情况的处理。
//
//题目中没有准确说明  选择状态 何时被解除，比如我ctrl-a全选所有字母时，然后ctrl-c将选择的字母复制到剪贴板，那么此时屏幕中字母的选中状态是保留还是清除呢？
//
//我理解ctrl-x剪切走屏幕内容，没有字母了，自然就没有选中状态了。另外，a、ctrl-v输入时，如果有字母选中状态，则输入时会覆盖选中内容，那么选中状态就没了。

package com.lew.algo.hw.od._05_Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author luyonglang
 * @date 2024/3/11
 */
public class _001_05_Logic_FiveKeyboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = countChars(nums);
        System.out.println(result);
        System.out.println(getResultMy(nums));
    }

    private static int getResultMy(int[] nums) {
        // 当前屏幕的所有字母
        List<String> screen = new ArrayList<>();
        List<String> clipBoard = new ArrayList<>();
        boolean isSelected = false;

        for (int num : nums) {
            switch (num) {
                // a
                case 1:
                    if (isSelected) {
                        screen.clear();
                    }
                    screen.add("a");
                    isSelected = false;
                    // ctrl-c 复制
                case 2:
                    if (isSelected) {
                        clipBoard.clear();
                        clipBoard.addAll(screen);
                    }
                    break;
                // ctrl-x 剪切
                case 3:
                    // 相当于复制然后清空
                    if (isSelected) {
                        clipBoard.clear();
                        clipBoard.addAll(screen);
                        screen.clear();
                        isSelected = false;
                    }
                    break;

                // ctrl-v 粘贴
                case 4:
                    if (isSelected) {
                        screen.clear();
                        isSelected = false;
                    }
                    screen.addAll(clipBoard);
                    break;

                // ctrl-a 全选:  注意！！！
                case 5:
                    if (!screen.isEmpty()) {
                        isSelected = true;
                    }
                    break;

            }
        }
        return screen.size();
    }

    private static int countChars(int[] nums) {
        // 当前屏幕的所有字母
        List<String> screen = new ArrayList<>();
        List<String> clipboard = new ArrayList<>();
        boolean isSelected = false;

        for (int num : nums) {
            switch (num) {
                // a
                case 1:
                    if (isSelected) {
                        // 有字母被选中: 清空内容
                        screen.clear();
                    }
                    screen.add("a");
                    isSelected = false;
                    break;
                // ctrl-c 复制
                case 2:
                    if (isSelected) {
                        clipboard.clear();
                        clipboard.addAll(screen);
                    }
                    break;
                // ctrl-x 剪切
                case 3:
                    // 相当于复制然后清空
                    if (isSelected) {
                        clipboard.clear();
                        clipboard.addAll(screen);
                        screen.clear();
                        isSelected = false;
                    }
                    break;
                // ctrl-v 粘贴
                case 4:
                    if (isSelected) {
                        screen.clear();
                        isSelected = false;
                    }
                    screen.addAll(clipboard);
                    break;
                // ctrl-a 全选
                case 5:
                    if (!screen.isEmpty()) {
                        isSelected = true;
                    }
                    break;
            }
        }

        return screen.size();
    }
}
