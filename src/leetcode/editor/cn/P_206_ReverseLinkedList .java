//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
//
//
// 示例 2：
//
//
//输入：head = [1,2]
//输出：[2,1]
//
//
// 示例 3：
//
//
//输入：head = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围是 [0, 5000]
// -5000 <= Node.val <= 5000
//
//
//
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
//
// Related Topics 递归 链表 👍 3545 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.ListNode;

//java:反转链表
class P_206_ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new P_206_ReverseLinkedList().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseList00(ListNode head) {
            // 双指针解法：当前指针遍历链表; 上一指针记录历史前一个节点
            ListNode pre = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            return pre;
        }

        // 明确递归函数的定义来解决递归问题, 而不要跳进递归，这个思想很有帮助 ! 一旦陷入递归, 思维就很会一团乱麻
        // 递归解法：https://labuladong.online/algo/data-structure/reverse-linked-list-recursion/#%E4%B8%80%E3%80%81%E9%80%92%E5%BD%92%E5%8F%8D%E8%BD%AC%E6%95%B4%E4%B8%AA%E9%93%BE%E8%A1%A8
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 先翻转head后的链表（不要试图堆栈去冥想，画图理解整体的思路）
            ListNode last = reverseList(head.next);

            // 最后处理首节点的指向问题
            head.next.next = head;
            head.next = null;

            return last;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}