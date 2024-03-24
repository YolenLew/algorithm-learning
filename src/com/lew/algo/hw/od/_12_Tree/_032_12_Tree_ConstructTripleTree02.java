// (C卷,100分)- 计算三叉搜索树的高度（Java & JS & Python & C）
// 题目描述
// 定义构造三叉搜索树规则如下：
//
// 每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：
//
// 如果数小于节点的数减去500，则将数插入节点的左子树
// 如果数大于节点的数加上500，则将数插入节点的右子树
// 否则，将数插入节点的中子树
// 给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度。
//
// 输入描述
// 第一行为一个数 N，表示有 N 个数，1 ≤ N ≤ 10000
//
// 第二行为 N 个空格分隔的整数，每个数的范围为[1,10000]
//
// 输出描述
// 输出树的高度（根节点的高度为1）
//
// 用例
// 输入 5
// 5000 2000 5000 8000 1800
// 输出 3
// 说明
// 最终构造出的树如下，高度为3：
//
// 输入 3
// 5000 4000 3000
// 输出 3
// 说明
// 最终构造出的树如下，高度为3：
// 输入 9
// 5000 2000 5000 8000 1800 7500 4500 1400 8100
// 输出 4
// 说明
// 最终构造出的树如下，高度为4：

package com.lew.algo.hw.od._12_Tree;

import java.util.Scanner;

/**
 * @author luyonglang
 * @date 2024/3/15
 */
public class _032_12_Tree_ConstructTripleTree02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        System.out.println(getResult(values));
    }

    static int ans = 0;

    private static int getResult(int[] values) {
        Node root = new Node(values[0]);
        // 遍历元素构建树：注意：根节点的高度为1
        for (int i = 1; i < values.length; i++) {
            dfs(root, values[i], 1);
        }

        return ans;
    }

    private static void dfs(Node currNode, int value, int currDepth) {
        if (value < currNode.value - 500) {
            // 将数插入节点的左子树
            if (currNode.left == null) {
                currNode.left = new Node(value);
                ans = Math.max(ans, currDepth + 1);
                return;
            }
            dfs(currNode.left, value, currDepth + 1);
        } else if (value > currNode.value + 500) {
            // 将数插入节点的右子树
            if (currNode.right == null) {
                currNode.right = new Node(value);
                ans = Math.max(ans, currDepth + 1);
                return;
            }
            dfs(currNode.right, value, currDepth + 1);
        } else {
            // 否则，将数插入节点的中子树
            if (currNode.mid == null) {
                currNode.mid = new Node(value);
                ans = Math.max(ans, currDepth + 1);
                return;
            }
            dfs(currNode.mid, value, currDepth + 1);
        }
    }

    static class Node {
        int value;
        Node left;
        Node mid;
        Node right;

        Node(int v) {
            this.value = v;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
