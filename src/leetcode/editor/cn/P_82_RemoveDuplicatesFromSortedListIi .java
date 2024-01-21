//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
//
//
// 示例 2：
//
//
//输入：head = [1,1,1,2,3]
//输出：[2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围 [0, 300] 内
// -100 <= Node.val <= 100
// 题目数据保证链表已经按升序 排列
//
//
// Related Topics 链表 双指针 👍 1255 👎 0

package leetcode.editor.cn;

//java:删除排序链表中的重复元素 II
class P_82_RemoveDuplicatesFromSortedListIi {
    public static void main(String[] args) {
        Solution solution = new P_82_RemoveDuplicatesFromSortedListIi().new Solution();
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

        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 常用的技巧：dummy 节点，也叫做 哑节点。它在链表的迭代写法中非常常见，因为对于本题而言，我们可能会删除头结点 head，为了维护一个不变的头节点，所以我们添加了 dummy，让dummy.next = head，这样即使 head 被删了，那么会操作 dummy.next 指向新的链表头部，所以最终返回的也是 dummy.next。
            ListNode dummy = new ListNode();
            dummy.next = head;
            // 双指针解法：pre指向当前不含有重复元素的最后一个节点，cur则为不断移动遍历链表的节点
            ListNode pre = dummy, cur = head;
            while (cur != null) {
                // 遍历链表，寻找不重复节点
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                // 判断快指针是否是慢指针的下一个节点
                if (pre.next == cur) {
                    // 如果快指针是慢指针的下一个节点：说明两者之间没有重复节点，且经过上一个while循环，cur与cur.next不重复
                    // 此时，便可移动慢指针pre
                    pre = pre.next;
                } else {
                    // 如果快指针不是慢指针的下一个节点：说明两者之间包含了重复的节点，且经过上一个while循环，cur是最后一个重复的节点
                    // 此时，需记录cur.next这个不重复的节点到结果pre.next中去
                    pre.next = cur.next;
                }
                // 快指针每次跳一步
                cur = cur.next;
            }
            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}