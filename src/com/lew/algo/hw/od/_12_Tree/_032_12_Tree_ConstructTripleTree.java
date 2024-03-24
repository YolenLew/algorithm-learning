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
public class _032_12_Tree_ConstructTripleTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Tree tree = new Tree();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            tree.add(num);
        }

        System.out.println(tree.height);
    }

    static class TreeNode {
        int val; // 节点值
        int height; // 节点所在高度
        TreeNode left; // 左子树
        TreeNode mid; // 中子树
        TreeNode right; // 右子树

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static class Tree {
        TreeNode root; // 树的根节点
        int height; // 树的高度

        public void add(int val) {
            TreeNode node = new TreeNode(val);

            if (this.root == null) {
                // 如果树是空的，则当前创建的节点将作为根节点
                node.height = 1; // 根节点的高度为1
                this.root = node;
                this.height = 1;
            } else {
                // 如果树不是空的，则从根节点开始比较
                TreeNode cur = this.root;

                while (true) {
                    // 假设创建的节点node是当前节点cur的子节点，则node节点高度值=cur节点高度值+1
                    node.height = cur.height + 1;
                    // 如果创建的node进入新层，则更新树的高度
                    this.height = Math.max(node.height, this.height);

                    if (val < cur.val - 500) {
                        // 如果数小于节点的数减去500，则将数插入cur节点的左子树
                        if (cur.left == null) {
                            // 如果cur节点没有左子树，则node作为cur节点的左子树
                            cur.left = node;
                            // 停止探索
                            break;
                        } else {
                            // 否则继续探索
                            cur = cur.left;
                        }
                    } else if (val > cur.val + 500) {
                        // 如果数大于节点的数加上500，则将数插入节点的右子树
                        if (cur.right == null) {
                            cur.right = node;
                            break;
                        } else {
                            cur = cur.right;
                        }
                    } else {
                        // 如果数大于节点的数加上500，则将数插入节点的右子树
                        if (cur.mid == null) {
                            cur.mid = node;
                            break;
                        } else {
                            cur = cur.mid;
                        }
                    }
                }
            }
        }
    }
}
