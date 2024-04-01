//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//
//
// 示例 2：
//
//
//输入：head = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 100] 内
// 0 <= Node.val <= 100
//
//
// Related Topics 递归 链表 👍 2184 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.ListNode;

//java:两两交换链表中的节点
class P_24_SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new P_24_SwapNodesInPairs().new Solution();
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
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 链表技巧：设置一个哑节点，应对空指针问题
            ListNode dummy = new ListNode(0, head);
            ListNode pre = dummy;
            ListNode fast = dummy.next;
            ListNode slow = dummy.next;

            while (fast != null && fast.next != null) {
                fast = fast.next;
                ListNode temp = fast.next;
                // 交换节点
                fast.next = slow;
                slow.next = temp;
                pre.next = fast;
                // 移动各节点
                pre = slow;
                slow = temp;
                fast = temp;
            }

            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}