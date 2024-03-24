// (C卷,100分)- 悄悄话（Java & JS & Python & C）
// 题目描述
// 给定一个二叉树，每个节点上站一个人，节点数字表示父节点到该节点传递悄悄话需要花费的时间。
//
// 初始时，根节点所在位置的人有一个悄悄话想要传递给其他人，求二叉树所有节点上的人都接收到悄悄话花费的时间。
//
// 输入描述
// 给定二叉树
//
// 0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
//
// 注：-1表示空节点
//
//
//
// 输出描述
// 返回所有节点都接收到悄悄话花费的时间
//
// 38
//
// 用例
// 输入 0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
// 输出 38
// 说明 无

package com.lew.algo.hw.od._12_Tree;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author luyonglang
 * @date 2024/3/15
 */
public class _036_12_Tree_ConstructTripleTree02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = getResultMy(nums);
        System.out.println(result);
    }

    private static int getResultMy(int[] nums) {
        // 先构造树
        TreeNode root = constructTree(nums, 0);
        // 自顶向下DFS
        dfs(root, 0);
        return ans;
    }

    static int ans = 0;

    private static void dfs(TreeNode root, int pathSum) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            // 到达叶子节点: 更新结果
            ans = Math.max(ans, pathSum + root.val);
        }

        dfs(root.left, pathSum + root.val);
        dfs(root.right, pathSum + root.val);
    }

    private static TreeNode constructTree(int[] arr, int i) {
        // i从0开始，第 i 个节点的左子节点为第 2*i + 1 个节点，右子节点为第 2*i+2个节点，因此用简单的递归就可以构建二叉树
        if (i >= arr.length) {// i >= arr.length 时,表示已经到达了根节点
            return null;
        }
        if (arr[i] == -1) {
            return null;
        }
        TreeNode root = new TreeNode(arr[i]); // 根节点
        root.left = constructTree(arr, 2 * i + 1); // 递归建立左孩子结点
        root.right = constructTree(arr, 2 * i + 2); // 递归建立右孩子结点
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
