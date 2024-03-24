// 题目描述
// 给出一个二叉树如下图所示：
//
//
//
// 请由该二叉树生成一个新的二叉树，它满足其树中的每个节点将包含原始树中的左子树和右子树的和。
//
//
//
// 左子树表示该节点左侧叶子节点为根节点的一颗新树；右子树表示该节点右侧叶子节点为根节点的一颗新树。
//
// 输入描述
// 2行整数，第1行表示二叉树的中序遍历，第2行表示二叉树的前序遍历，以空格分割
//
// 例如：
//
// 7 -2 6 6 9
// 6 7 -2 9 6
//
// 输出描述
// 1行整数，表示求和树的中序遍历，以空格分割
//
// 例如：
//
// -2 0 20 0 6
//
// 用例
// 输入 -3 12 6 8 9 -10 -7
// 8 12 -3 6 -10 9 -7
// 输出 0 3 0 7 0 2 0
// 说明 无
// 题目解析
// 本题主要是考察二叉树的中序遍历，前序遍历，以及根据中序遍历和前序遍历还原二叉树结构。
//
// 二叉树的中序遍历即：左根右，即先遍历左子树，再遍历根，最后遍历右子树
//
// 二叉树的前序遍历即：根左右，即先遍历根，再遍历左子树，最后遍历右子树
//
// 二叉树的前序遍历序列中首元素就是根节点，比如题目描述中的前序遍历序列：
//
// 6 7 -2 9 6
//
// 其中首元素6就是根节点。
//
// 知道根节点后，我们就可以去中序遍历序列中找打根值对应的节点，比如题目描述中的中序遍历序列：
//
// 7 -2 6 6 9
//
// 上面中序遍历序列中有两个值为6的元素，那么他们都有可能为根，我们需要一一判断：
//
// 如果红色6（第一个6）是根，那么根据中序：“左根右” 的遍历特点，7 -2 就是左子树的中序遍历，6 9 就是右子树的中序遍历
// 如果绿色6（第二个6）是根，那么根据中序：“左根右” 的遍历特点，7 -2 6 就是左子树的中序遍历，9 就是右子树的中序遍历
// 上面两个情况中，我们根据中序遍历特点，得到了左子树的长度、右子树长度。
//
// 而一颗二叉树（子树）的序列长度是固定的，即一颗二叉树（子树）的中序遍历序列和前序遍历序列长度是相同的。
//
// 因此，我们通过中序遍历得到左子树、右子树长度，那么就可以在前序遍历中划分出左子树、右子树范围：
//
// 比如按照中序遍历序列中红色6（第一个6）作为根的话，那么左子树（7 -2）长度为2，右子树（6 9） 长度2，则前序遍历序列可进行如下划分：
//
//
//
// 此时，对比前序的左子树和中序的左子树是否节点相同，对比前序的右子树和中序的右子树是否相同
//
//
//
// 如果左右子树都一致，则当前根是正确根。
//
// 如果我们选错根，比如选中序遍历序列中第二个6作为根，则
//
//
//
// 可以发现中序、前序的左右子树是不一致的。
//
// 综上所述，即我们通过前序序列找到二叉树的根节点值（前序序列首元素），然后使用此根值，去中序序列中找根值位置，并划分出左子树长度，右子树长度，然后分别在前序、中序序列中，找出左子树序列、右子树序列，对比是否一致，如果一致，则中序序列中对应根值位置正确，否则错误。

package com.lew.algo.hw.od._12_Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class _083_H_12_Tree_ConstructHuffmanTree {
    static class TreeNode {
        int num; // 当前节点的值
        int childSum; // 当前节点的左子树+右子树的和
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int num) {
            this.num = num;
            this.childSum = 0;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    // 中序遍历序列
    static int[] midOrder;

    // 前序遍历序列
    static int[] preOrder;

    // 记录中序遍历序列中，序列元素值所在位置，本题中可能存在重复元素，因此某个序列元素值可能有多个位置
    static HashMap<Integer, ArrayList<Integer>> midIndexMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        midOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        preOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = midOrder.length;
        for (int i = 0; i < n; i++) {
            int num = midOrder[i];
            midIndexMap.putIfAbsent(num, new ArrayList<>());
            midIndexMap.get(num).add(i);
        }

        // 根据中序序列和前序序列还原树结构
        TreeNode root = buildTree(0, n - 1, 0, n - 1);

        // 记录新的二叉树的的中序遍历序列
        StringJoiner sj = new StringJoiner(" ");
        getMidOrder(root, sj);
        System.out.println(sj);
    }

    // 二叉树中序遍历
    public static void getMidOrder(TreeNode root, StringJoiner sj) {
        if (root == null) {
            return;
        }

        // 先遍历左子树
        TreeNode leftChild = root.leftChild;
        if (leftChild != null) {
            getMidOrder(leftChild, sj);
        }

        // 再遍历根
        sj.add(root.childSum + "");

        // 最后遍历右子树
        TreeNode rightChild = root.rightChild;
        if (rightChild != null) {
            getMidOrder(rightChild, sj);
        }
    }

    /**
     * 根据中序遍历序列、前序遍历序列还原树结构
     *
     * @param midL 中序遍历子序列的左边界
     * @param midR 中序遍历子序列的右边界
     * @param preL 前序遍历子序列的左边界
     * @param preR 前序遍历子序列的右边界
     * @return 树结构的根节点
     */
    public static TreeNode buildTree(int midL, int midR, int preL, int preR) {
        // 某个节点（子树）对应一段子序列，如果对应子序列范围不存在，则子树也不存在
        if (preL > preR)
            return null;

        // 先根据前序遍历序列得到根节点，前序序列的首元素就是根节点
        int rootNum = preOrder[preL];
        TreeNode root = new TreeNode(rootNum);

        // 在中序遍历序列中，找到对应根值的位置，这个位置可能有多个，但是只有一个是正确的
        for (int idx : midIndexMap.get(rootNum)) {
            // 如果对应根值位置越界，则不是正确的
            if (idx < midL || idx > midR)
                continue;

            // 如果中序的左子树，和前序的左子树不同，则对应根值位置不正确
            int leftLen = idx - midL;
            if (notEquals(midL, preL + 1, leftLen))
                continue;

            // 如果中序的右子树，和前序的右子树不同，则对应根值位置不正确
            int rightLen = midR - idx;
            if (notEquals(idx + 1, preR - rightLen + 1, rightLen))
                continue;

            // 找到正确根值位置后，开始分治递归处理左子树和右子树
            root.leftChild = buildTree(midL, idx - 1, preL + 1, preL + leftLen);
            root.rightChild = buildTree(idx + 1, midR, preR - rightLen + 1, preR);

            // 记录该节点：左子树+右子树的和（本题新二叉树节点的值）
            root.childSum = (root.leftChild == null ? 0 : (root.leftChild.num + root.leftChild.childSum))
                + (root.rightChild == null ? 0 : (root.rightChild.num + root.rightChild.childSum));

            break;
        }

        return root;
    }

    /**
     * 判断两个子数组是否相同（元素相同，顺序可以不同）
     *
     * @param midL 子数组1的左边界
     * @param preL 子数组2的左边界
     * @param size 子数组的长度
     * @return 子数组1和子数组2是否相同
     */
    public static boolean notEquals(int midL, int preL, int size) {
        int[] arr1 = Arrays.stream(Arrays.copyOfRange(midOrder, midL, midL + size)).sorted().toArray();
        int[] arr2 = Arrays.stream(Arrays.copyOfRange(preOrder, preL, preL + size)).sorted().toArray();

        for (int i = 0; i < size; i++) {
            if (arr1[i] != arr2[i]) {
                return true;
            }
        }

        return false;
    }
}