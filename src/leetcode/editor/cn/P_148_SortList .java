//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
//
//
// 示例 2：
//
//
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
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
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内
// -10⁵ <= Node.val <= 10⁵
//
//
//
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
//
// Related Topics 链表 双指针 分治 排序 归并排序 👍 2227 👎 0

package leetcode.editor.cn;

//java:排序链表
class P_148_SortList {
    public static void main(String[] args) {
        Solution solution = new P_148_SortList().new Solution();
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
        public ListNode sortList(ListNode head) {
            // 自顶向下归并排序：分割+递归+归并排序
            // 递归结束条件：只有一个节点或者没有节点，不能分割了
            if (head == null || head.next == null) {
                return head;
            }
            // 第一步，递归分割：找中心节点，不断递归分割
            // 找到链表中间节点并断开链表
            ListNode midNode = middleNode(head);
            ListNode halfRightNode = midNode.next;
            midNode.next = null;
            // 接着递归下钻：不断分割节点
            ListNode left = sortList(head);
            ListNode right = sortList(halfRightNode);

            // 第二步，归并排序
            return mergeTwoLists(left, right);
        }

        // 合并两个有序链表（21. 合并两个有序链表）
        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode curr = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                } else {
                    curr.next = l2;
                    l2 = l2.next;
                }
                curr = curr.next;
            }
            // 将较长的链表节点填充到末尾
            curr.next = l1 != null ? l1 : l2;
            return dummy.next;
        }

        public ListNode middleNode(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 快慢指针方法，找中心节点
            // 注意：此处不是一般的找中心点(或中心右侧)，而是找中心点或中心点左侧节点，所以让fast先走一步或两步；
            // 因为fast和slow同一起点的话，对于节点数为偶数的链表，返回的是中间两个节点的第二个，无法正确拆分偶数链表。
            // 例如，一个链表只有1,2两个节点，如果用上述代码`ListNode fast = head;`，返回的中间节点是节点2，无法拆分。为了返回正确，需要让fast先走一步或两步
            ListNode fast = head.next.next;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            return slow;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}