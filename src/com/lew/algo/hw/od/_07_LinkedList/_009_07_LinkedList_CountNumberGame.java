// (C卷,100分)- 报数游戏（Java & JS & Python）
// 题目描述
// 100个人围成一圈，每个人有一个编码，编号从1开始到100。
//
// 他们从1开始依次报数，报到为M的人自动退出圈圈，然后下一个人接着从1开始报数，直到剩余的人数小于M。
//
// 请问最后剩余的人在原先的编号为多少？
//
// 输入描述
// 输入一个整数参数 M
//
// 输出描述
// 如果输入参数M小于等于1或者大于等于100，输出“ERROR!”；
//
// 否则按照原先的编号从小到大的顺序，以英文逗号分割输出编号字符串
//
// 用例
// 输入 3
// 输出 58,91
// 说明 输入M为3，最后剩下两个人。
// 输入 4
// 输出 34,45,97
// 说明 输入M为4，最后剩下三个人。
// 题目解析
// 本题是经典的约瑟夫环问题，可以利用循环链表解决。

package com.lew.algo.hw.od._07_LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class _009_07_LinkedList_CountNumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 集合大小
        int n = Integer.parseInt(sc.nextLine());
        System.out.println(josephus(100, n));
        System.out.println(cycleList(100, n));
    }

    // 经典的约瑟夫环问题，最佳解题策略是利用循环链表
    public static String josephus(int n, int m) {
        if (m <= 1 || m>= 100) {
            return "ERROR";
        }
        // 构建链表
        LinkedList<String> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(String.valueOf(i));
        }
        // 移除元素
        int removeIndex = 0;
        while (list.size() >= m) {
            removeIndex = (removeIndex + m - 1) % list.size();
            list.remove(removeIndex);
        }
        return String.join(" ", list);
    }

    public static String cycleList(int n, int m) {
        if (m <= 1 || m>= 100) {
            return "ERROR";
        }
        // 初始化
        CycleLinkedList list = new CycleLinkedList();
        for (int i = 1; i <= n; i++) {
            list.append(i);
        }

        // 删除链表
        int count = 1;
        Node cur = list.head;
        while (list.size >= m) {
            if (count == m) {
                cur = list.remove(cur);
                count = 1;
            } else {
                count++;
                cur = cur.next;
            }
        }
        return list.toString();
    }

    // 循环双向节点（方便节点删除）
    static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    // 简易的循环链表实现：
    //
    // 循环链表节点定义
    // 节点双向性prev、next（方便节点删除）
    // 节点值val

    // 循环链表的属性：
    // 链表长度size
    // 链表头节点head
    // 链表尾节点tail

    // 循环链表的操作
    // 尾增节点（append操作）
    // 删除任意节点（remove操作）

    // 循环链表尾增节点，需要注意：
    // 如果size>0，则相当于只需要更新循环链表的尾节点tail
    // 如果size == 0，则相当于更新循环链表的head和tail

    // 循环链表删除任意节点，需要注意：
    // 如果删除的不是head，tail节点，则只需要将被删除节点的prev和next节点关联
    // 如果删除的是head或tail，则还需要更新head、tail指向
    static class CycleLinkedList {
        // 属性
        Node head;
        Node tail;
        int size;

        public CycleLinkedList() {
            Node head = null;
            Node tail = null;
            int size = 0;
        }

        // 循环链表的操作：尾增节点（append操作）
        public void append(int val) {
            Node node = new Node(val);

            if (this.size > 0) {
                this.tail.next = node;
                node.prev = this.tail;
            } else {
                this.head = node;
            }

            // 继续处理首位节点引用(因为是循环引用的)
            this.tail = node;
            // 循环引用重置
            this.head.prev = this.tail;
            this.tail.next = this.head;

            this.size++;
        }

        // 循环链表的操作：删除任意节点（remove操作），同时返回删除节点的下一节点
        public Node remove(Node cur) {
            // 循环链表删除任意节点，需要注意：
            // 如果删除的不是head，tail节点，则只需要将被删除节点的prev和next节点关联
            // 如果删除的是head或tail，则还需要更新head、tail指向

            Node prev = cur.prev;
            Node next = cur.next;
            // 将被删除节点的prev和next节点关联
            prev.next = next;
            next.prev = prev;

            // 删除节点
            cur.next = cur.prev = null;

            if (cur == this.head) {
                this.head = next;
            }

            if (cur == this.tail) {
                this.tail = prev;
            }

            this.size--;
            return next;
        }

        @Override
        public String toString() {
            List<String> list = new ArrayList<>();
            Node cur = this.head;
            for (int i = 0; i < this.size; i++) {
                list.add(String.valueOf(cur.val));
                cur = cur.next;
            }
            return String.join(" ", list);
        }
    }
}