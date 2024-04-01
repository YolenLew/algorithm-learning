package leetcode.editor.cn.model;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int x, ListNode nextNode) {
        val = x;
        next = nextNode;
    }

    public static ListNode createNode(int... nums) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        for (int num : nums) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next;
    }

    public int getVal() {
        return this.val;
    }
}