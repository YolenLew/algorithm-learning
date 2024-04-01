//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
//
//
// 示例 2：
//
//
//
//
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
//
//
//
//提示：
//
//
// 链表中的节点数目为 n
// 1 <= k <= n <= 5000
// 0 <= Node.val <= 1000
//
//
//
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
//
//
//
//
// Related Topics 递归 链表 👍 2301 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.ListNode;

//java:K 个一组翻转链表
class P_25_ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new P_25_ReverseNodesInKGroup().new Solution();
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
        // 递归+反转链表：K个一组反转链表，实际就是将区间的链表进行反转[first, Node(k+1))（左闭右开区间），然后递归将所有的k个一组的链表反转即可
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            // 找出反转的区间节点[a,b)
            ListNode first = head;
            ListNode exclude = head;
            for (int i = 0; i < k; i++) {
                if (exclude == null) {
                    // 不足k个，直接return
                    return head;
                }
                exclude = exclude.next;
            }

            // 反转区间链表
            ListNode preNewFirst = reverse(first, exclude);
            // 继续递归反转区间链表
            ListNode nextHead = reverseKGroup(exclude, k);

            // 拼接两段区间
            first.next = nextHead;

            return preNewFirst;
        }

        /**
         * 反转区间 [a, b) 的元素，注意是左闭右开
         */
        public ListNode reverse(ListNode a, ListNode b) {
            // 双指针解法：当前指针遍历链表，并记录上一指针
            ListNode pre = null;
            ListNode curr = a;
            ListNode next = a;
            while (curr != b) {
                // 记录下一节点
                next = curr.next;
                // 反转后，当前节点next指向上一节点
                curr.next = pre;

                // 移动节点
                pre = curr;
                curr = next;
            }

            // 返回反转后的头节点
            return pre;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}