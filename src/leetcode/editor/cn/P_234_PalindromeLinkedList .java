//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,2,1]
//输出：true
//
//
// 示例 2：
//
//
//输入：head = [1,2]
//输出：false
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围[1, 10⁵] 内
// 0 <= Node.val <= 9
//
//
//
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
//
// Related Topics 栈 递归 链表 双指针 👍 1853 👎 0

package leetcode.editor.cn;

//java:回文链表
class P_234_PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new P_234_PalindromeLinkedList().new Solution();
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
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
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }

            // 快慢指针：找到前半部分链表的尾节点
            ListNode firstHalfEnd = getEndOfFirstHalf(head);
            // 反转后半部分链表：便于后续回文判断
            ListNode reverseSecondHalfStart = reverseList(firstHalfEnd.next);
            boolean result = true;
            // 判断是否回文：遍历前半段和反转的后半段节点值
            ListNode p1 = head;
            ListNode p2 = reverseSecondHalfStart;

            while (result && p1 != null && p2 != null) {
                if (p1.val != p2.val) {
                    result = false;
                }
                p1 = p1.next;
                p2 = p2.next;
            }

            // 复原原链表节点顺序
            firstHalfEnd.next = reverseList(reverseSecondHalfStart);

            // 结果返回
            return result;
        }

        private ListNode getEndOfFirstHalf(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                // 慢指针移动一步，快指针移动两步
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        /**
         * 反转链表
         *
         * @param head head
         * @return ListNode
         */
        public ListNode reverseList(ListNode head) {
            // 双指针解法：当前指针遍历链表，并记录上一指针
            ListNode curr = head, pre = null;
            while (curr != null) {
                ListNode temp = curr.next;
                // 修改当前节点的引用：指向上一节点
                curr.next = pre;
                // 记录当前节点：本次标记完成，本次节点记录到pre指针
                pre = curr;
                // 移动到下一节点
                curr = temp;
            }
            return pre;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}