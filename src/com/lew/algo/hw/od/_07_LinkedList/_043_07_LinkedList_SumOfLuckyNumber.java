// (C卷,100分)- 求幸存数之和（Java & JS & Python & C）
// 题目描述
// 给一个正整数数列 nums，一个跳数 jump，及幸存数量 left。
//
// 运算过程为：从索引0的位置开始向后跳，中间跳过 J 个数字，命中索引为 J+1 的数字，该数被敲出，并从该点起跳，以此类推，直到幸存 left 个数为止，然后返回幸存数之和。
//
// 约束：
//
// 0是第一个起跳点
// 起跳点和命中点之间间隔 jump 个数字，已被敲出的数字不计入在内。
// 跳到末尾时无缝从头开始（循环查找），并可以多次循环。
// 若起始时 left > len(nums) 则无需跳数处理过程。
// 方法设计：
//
/// **
// * @param nums 正整数数列，长度范围 [1, 10000]
// * @param jump 跳数，范围 [1, 10000]
// * @param left 幸存数量，范围 [0, 10000]
// * @return 幸存数之和
// */
// int sumOfLeft(int[] nums, int jump, int left)
// 输入描述
// 第一行输入正整数数列
//
// 第二行输入跳数
//
// 第三行输入幸存数量
//
// 输出描述
// 输出幸存数之和
//
// 用例
// 输入 1,2,3,4,5,6,7,8,9
// 4
// 3
// 输出 13
// 说明 从1（索引为0）开始起跳，中间跳过 4 个数字，因此依次删除 6,2,8,5,4,7。剩余1,3,9，返回和为13

package com.lew.algo.hw.od._07_LinkedList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _043_07_LinkedList_SumOfLuckyNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int jump = Integer.parseInt(sc.nextLine());
        int left = Integer.parseInt(sc.nextLine());

        System.out.println(new _043_07_LinkedList_SumOfLuckyNumber().sumOfLeft01(nums, jump, left));
        System.out.println(new _043_07_LinkedList_SumOfLuckyNumber().sumOfLeft02(nums, jump, left));
        System.out.println(getResultMy(nums, jump, left));
    }

    private static int getResultMy(int[] nums, int jump, int left) {
        List<Integer> luckList = Arrays.stream(nums).boxed().collect(Collectors.toList());

        int index = 1;
        while (luckList.size() > left) {
            index = index + jump;
            // 为了避免越界，新起跳点索引位置对剩余节点数取余
            index = index % luckList.size();
            luckList.remove(index);
        }

        return luckList.stream().reduce(Integer::sum).orElse(0);
    }

    // 动态数组解法
    public int sumOfLeft01(int[] nums, int jump, int left) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        // 从起跳点开始的话，需要跳jump+1次，到达需要删除的节点
        // 从起跳点下一个节点开始的话，需要跳jump次，到达需要删除的节点
        // 这里我们从起跳点的下一个节点开始,初始时起跳点为索引0，因此下一个节点为索引1
        int start = 1;

        // 如果剩余节点数 > 幸存数量，则还需要继续删除节点
        while (list.size() > left) {
            // 跳 jump 次
            start += jump;
            // 为了避免越界，新起跳点索引位置对剩余节点数取余
            start %= list.size();
            list.remove(start);
        }

        return list.stream().reduce(Integer::sum).orElse(0);
    }

    // 循环链表的节点定义
    static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // 循环链表定义
    static class CycleLink {
        private Node head; // 私有属性，仅用于链接tail，完成循环
        private Node tail; // 私有属性，仅用于链接head，完成循环
        public Node cur; // 循环链表遍历指针
        public int size; // 循环链表的节点数
        public int sum; // 循环链表中所有节点值之和

        // 初始化循环链表
        public CycleLink(int[] nums) {
            // 向循环链表中添加节点
            for (int num : nums) {
                this.add(num);
            }

            // 将普通链表头尾相连，形成循环链表
            if (this.head != null) {
                this.head.prev = this.tail;
                this.tail.next = this.head;
                // 初始时循环链表的遍历指针指向头位值
                this.cur = this.head;
            }
        }

        private void add(int val) {
            Node node = new Node(val);

            if (this.size == 0) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
                this.tail = node;
            }

            this.sum += val;
            this.size++;
        }

        // 删除循环链表cur指针指向的节点
        public void remove() {
            // 被删除节点的值从 循环链表和 中去除
            this.sum -= this.cur.val;
            // 循环链表节点数量-1
            this.size--;

            // 完成删除节点动作
            Node prev = this.cur.prev;
            Node next = this.cur.next;

            prev.next = next;
            next.prev = prev;

            this.cur.prev = null;
            this.cur.next = null;

            // 遍历指针指向被删除节点的下一个节点
            this.cur = next;
        }

        // 遍历下一个循环链表节点
        public void next() {
            this.cur = this.cur.next;
        }
    }

    public int sumOfLeft02(int[] nums, int jump, int left) {
        CycleLink link = new CycleLink(nums);

        // 从起跳点开始的话，需要跳jump+1次，到达需要删除的节点
        // 从起跳点下一个节点开始的话，需要跳jump次，到达需要删除的节点

        // 这里我们从起跳点的下一个节点开始
        link.next();
        // 如果链表中剩余节点数 > 幸存数量，则还需要继续删除节点
        while (link.size > left) {
            // 跳 jump 次，为了避免冗余转圈， 其实只需要跳 jump % link.size
            int zip_jump = jump % link.size;
            for (int i = 0; i < zip_jump; i++) {
                link.next();
            }
            // 删除当前接节点（被删除的节点其实是下一次的起跳点），这里link.remove方法删除节点后会自动跳到被删除节点的下一个节点，即：起跳点的下一个节点
            link.remove();
        }

        return link.sum;
    }
}