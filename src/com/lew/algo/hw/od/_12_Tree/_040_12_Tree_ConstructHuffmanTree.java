// (C卷,100分)- 生成哈夫曼树（Java & JS & Python & C）
// 题目描述
// 给定长度为 n 的无序的数字数组，每个数字代表二叉树的叶子节点的权值，数字数组的值均大于等于1。
//
// 请完成一个函数，根据输入的数字数组，生成哈夫曼树，并将哈夫曼树按照中序遍历输出。
//
// 为了保证输出的二叉树中序遍历结果统一，增加以下限制：
//
// 二叉树节点中，左节点权值小于右节点权值，根节点权值为左右节点权值之和。当左右节点权值相同时，左子树高度小于等于右子树高度。
//
// 注意：
//
// 所有用例保证有效，并能生成哈夫曼树。
//
// 提醒：
//
// 哈夫曼树又称为最优二叉树，是一种带权路径长度最短的二叉树。
//
// 所谓树的带权路径长度，就是树中所有的叶节点的权值乘上其到根节点的路径长度（若根节点为 0 层，叶节点到根节点的路径长度为叶节点的层数）
//
// 输入描述
// 例如：由叶子节点：5 15 40 30 10，生成的最优二叉树如下图所示，该树的最短带权路径长度为：40 * 1 + 30 * 2 + 5 * 4 + 10 * 4 = 205。
//
//
//
// 输出描述
// 输出一个哈夫曼树的中序遍历数组，数值间以空格分隔
//
//
// 用例
// 输入 5
// 5 15 40 30 10
// 输出 40 100 30 60 15 30 5 15 10
// 说明 根据输入，生成哈夫曼树，按照中序遍历返回。所有节点中，左节点权值小于等于右节点权值之和。当左右节点权值相同时，左子树高度小于右子树。结果如上图所示。
// 题目解析
// 本题主要是考察哈夫曼树的构建。

package com.lew.algo.hw.od._12_Tree;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;

public class _040_12_Tree_ConstructHuffmanTree {
    // 哈夫曼树节点
    static class Node {
        Node lchild; // 左孩子节点
        Node rchild; // 右孩子节点
        int weight; // 当前节点的权重
        int height; // 当前节点代表子树的高度

        public Node(Node lc, Node rc, int weight, int height) {
            this.lchild = lc;
            this.rchild = rc;
            this.weight = weight;
            this.height = height;
        }
    }

    // 了解了哈夫曼树的构造原理后，其实本题就很简单了，只需要不停从给定的权值序列中：
    //
    // 取出两个最小的权值
    // 放回两个最小权值的合并和
    // 直到权值序列中只有一个元素时停止。
    //
    // 而上面取出两个最小、返回合并和后重新排序，这种行为是非常适合使用优先队列（小顶堆）做的。
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 将哈夫曼树节点进行排序，方便后面筛选出权值最小的两个节点
        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) -> a.weight != b.weight ? a.weight - b.weight : a.height - b.height); // 题目说：当左右节点权值相同时，左子树高度小于等于右子树高度。因此当节点权重相同时，再按照节点子树高度升序

        for (int i = 0; i < n; i++) {
            // 创建n个哈夫曼树节点
            int w = sc.nextInt();
            Node node = new Node(null, null, w, 0);
            // 加入优先队列
            pq.offer(node);
        }

        // 初始n个节点经过多轮合并，只剩一个节点时，那么该节点就是哈夫曼树的根节点，因此当优先队列中只剩一个节点时即可停止合并
        while (pq.size() > 1) {
            // 取出优先队列中前两个权值最小的节点，由于优先队列已按照 [节点权重，节点子树高度] 升序优先级，因此先出来的肯定是权重小，或者高度小的节点，即作为新节点的左子树
            Node lc = pq.poll();
            Node rc = pq.poll();

            // 将lc和rc合并，合并后新节点fa的权重，是两个子节点权重之和，fa子树高度 = rc子树高度+1; PS：rc的高度>=lc的高度
            int fa_weight = lc.weight + rc.weight;
            int fa_height = rc.height + 1;

            // 将合并后的新节点加入优先队列
            Node fa = new Node(lc, rc, fa_weight, fa_height);
            pq.offer(fa);
        }

        // 最后优先队列中必然只剩一个节点，即哈夫曼树的根节点，此时对此根节点（哈夫曼树）进行中序遍历
        Node root = pq.poll();
        StringJoiner sj = new StringJoiner(" ");
        midOrder(root, sj);

        System.out.println(sj);
    }

    public static void midOrder(Node root, StringJoiner sj) {
        // 中序遍历，即先遍历二叉树的左子树，再遍历二叉树的根，最后遍历二叉树的右子树
        if (root.lchild != null) {
            midOrder(root.lchild, sj);
        }

        sj.add(root.weight + "");

        if (root.rchild != null) {
            midOrder(root.rchild, sj);
        }
    }
}