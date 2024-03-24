// 题目描述
// 有n个人围成一圈，顺序排号为1-n。
//
// 从第一个人开始报数(从1到3报数)，凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
//
// 输入描述
// 输入人数n（n < 1000）
//
// 输出描述
// 输出最后留下的是原来第几号
//
// 用例
// 输入 2
// 输出 2
// 说明 报数序号为1的人最终报3，因此序号1的人退出圈子，最后剩下序号为2的那位
// 题目解析
// 本题是经典的约瑟夫环问题，最佳解题策略是利用循环链表。
//
// 因此，本题的关键是实现循环链表。

package com.lew.algo.hw.od._07_LinkedList;

import java.util.LinkedList;
import java.util.Scanner;

public class _008_07_LinkedList_CountNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 集合大小
        int n = Integer.parseInt(sc.nextLine());
        System.out.println(josephus(n, 3));
        System.out.println(cycleList(n, 3));
    }

    // 经典的约瑟夫环问题，最佳解题策略是利用循环链表
    public static int josephus(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        // 构建链表
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        // 移除元素
        int removeIndex = 0;
        while (list.size() != 1) {
            removeIndex = (removeIndex + m - 1) % list.size();
            list.remove(removeIndex);
        }
        return list.get(0);
    }

    public static int cycleList(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        // 初始化
        CycleLinkedList list = new CycleLinkedList();
        for (int i = 1; i <= n; i++) {
            list.append(i);
        }

        // 删除链表
        int count = 1;
        Node cur = list.head;
        while (list.size > 1) {
            if (count == m) {
                cur = list.remove(cur);
                count = 1;
            } else {
                count++;
                cur = cur.next;
            }
        }
        return cur.val;
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
    }
}