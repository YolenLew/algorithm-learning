// (C卷,100分)- 转盘寿司（Java & JS & Python & C）
//
// 题目描述
// 寿司店周年庆，正在举办优惠活动回馈新老客户。
//
// 寿司转盘上总共有 n 盘寿司，prices[i] 是第 i 盘寿司的价格，
//
// 如果客户选择了第 i 盘寿司，寿司店免费赠送客户距离第 i 盘寿司最近的下一盘寿司 j，前提是 prices[j] < prices[i]，如果没有满足条件的 j，则不赠送寿司。
//
// 每个价格的寿司都可无限供应。
//
//
// 输入描述
// 输入的每一个数字代表每盘寿司的价格，每盘寿司的价格之间使用空格分隔，例如:
//
// 3 15 6 14
//
// 表示：
//
// 第 0 盘寿司价格 prices[0] 为 3
// 第 1 盘寿司价格 prices[1] 为 15
// 第 2 盘寿司价格 prices[2] 为 6
// 第 3 盘寿司价格 prices[3] 为 14
// 寿司的盘数 n 范围为：1 ≤ n ≤ 500
//
// 每盘寿司的价格 price 范围为：1 ≤ price ≤ 1000
//
//
// 输出描述
// 输出享受优惠后的一组数据，每个值表示客户选择第 i 盘寿司时实际得到的寿司的总价格。使用空格进行分隔，例如：
//
// 3 21 9 17
//
// 用例
// 输入 3 15 6 14
// 输出 3 21 9 17
// 说明 无
// 题目解析
// 本题其实就是要我们求解数组中每个元素的下一个更小值元素，另外数组是循环的，即：如果数组某个元素之后没有比其更小的，那么可以循环到数组头部继续找。
//
// 本题其实就是LeetCode - 503 下一个更大元素 II_peekele, peekidx = stack[-1]_伏城之外的博客-CSDN博客
//
// 的变种题。
//
// 解析可以看上面博客。本博客代码解法做了优化，可以结合代码注释加以理解。

package com.lew.algo.hw.od._08_Stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class _019_08_Stack_ConveyorBeltSushi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] prices = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String res = nextGreaterElement(prices);
        String resMy = nextGreaterElementMy(prices);
        System.out.println(res);
        System.out.println(resMy);
    }

    public static String nextGreaterElementMy(int[] prices) {
        // 单调递增栈（从底往上比较）：目标找下一个更小的元素，栈存储的是索引
        Stack<Integer> stack = new Stack<>();
        // 结果数组
        int n = prices.length;
        int[] res = Arrays.copyOf(prices, n);
        // 注意：一般是通过 % 运算符求模（余数），来模拟环形特效，arr[index % n]
        for (int i = 0; i < n * 2; i++) {
            int currIndex = i % n;
            // 当前价格比栈顶的小，那么就是符合要求的下一个价格小的元素
            while (!stack.isEmpty() && prices[stack.peek()] > prices[currIndex]) {
                Integer topIndex = stack.pop();
                res[topIndex] = prices[topIndex] + prices[currIndex];
            }
            stack.push(currIndex);
        }

        return Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static String nextGreaterElement(int[] prices) {
        int n = prices.length;

        // 记录题解
        int[] res = Arrays.copyOf(prices, n);

        // 单调栈，栈底到栈顶单调递增，压栈元素是栈顶元素在nums顺序后面的值
        // 每当压栈时，比较栈顶元素 > 压栈元素？若是，则说明找到了栈顶元素的下一个更小值，此时弹栈，压栈元素继续和新栈顶元素比较大小，直到栈顶元素 <= 压栈元素，则停止比较，执行压栈
        LinkedList<Integer> stack = new LinkedList<>(); // 栈中记录是prices元素的索引

        // 这里循环两轮，因为一轮循环可能无法确保所有值都能找到下一个更小值
        for (int j = 0; j < n * 2; j++) {
            // prices_j 是压栈(索引对应的)元素
            int prices_j = prices[j % n]; // 索引 j % n 是为了让第二轮遍历时，继续从prices的0索引开始

            while (stack.size() > 0) {
                // prices[i] 是栈顶(索引对应的)元素
                int i = stack.getLast();

                if (prices[i] > prices_j) {
                    // 如果栈顶元素 > 压栈元素，则说明找到了栈顶元素的下一个更小值，此时栈顶元素弹出,压栈元素继续和新的栈顶元素比较
                    stack.removeLast();
                    // 题目要统计当前元素和其下一个更小值元素之和
                    res[i] += prices_j;
                } else {
                    break;
                }
            }

            // 只有第一轮遍历时，才允许压栈，第二轮遍历时，只进行比较
            if (j < n) {
                stack.add(j);
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for (int num : res) {
            sj.add(num + "");
        }
        return sj.toString();
    }

}