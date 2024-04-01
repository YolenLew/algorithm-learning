//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1], n = 1
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1,2], n = 1
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中结点的数目为 sz
// 1 <= sz <= 30
// 0 <= Node.val <= 100
// 1 <= n <= sz
//
//
//
//
// 进阶：你能尝试使用一趟扫描实现吗？
//
// Related Topics 链表 双指针 👍 2837 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.ListNode;

import java.util.Deque;
import java.util.LinkedList;

//java:删除链表的倒数第 N 个结点
class P_19_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new P_19_RemoveNthNodeFromEndOfList().new Solution();
        ListNode node = ListNode.createNode(1, 2, 3, 4, 5);
        ListNode listNode = solution.removeNthFromEnd(node, 2);
        System.out.println(listNode.val);
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

        public ListNode removeNthFromEnd(ListNode head, int n) {
            // 链表技巧：设置一个哑链表，应对空指针问题
            ListNode dummy = new ListNode(0, head);
            // 遍历链表依次放到栈中
            Deque<ListNode> deque = new LinkedList<>();
            ListNode cur = dummy;
            while (cur != null) {
                deque.push(cur);
                cur = cur.next;
            }
            // 然后出栈，倒数第个为需要删除的节点，且此时栈顶元素就是删除节点的前驱节点
            while (n > 0) {
                deque.pop();
                n--;
            }
            ListNode peek = deque.peek();
            peek.next = peek.next.next;
            return dummy.next;
        }

        // 注意：poll是队列数据结构实现类的方法，从队首获取元素，同时获取的这个元素将从原队列删除；
        //pop是栈结构的实现类的方法，表示返回栈顶的元素，同时该元素从栈中删除，当栈中没有元素时，调用该方法会发生异常
        public ListNode removeNthFromEnd90(ListNode head, int n) {
            Deque<ListNode> queue = new LinkedList<>();
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode curr = dummy.next;
            while (curr != null) {
                queue.addFirst(curr);
                curr = curr.next;
            }
            ListNode redNode = null;
            for (int i = n; i > 0; i--) {
                redNode = queue.poll();
            }

            if (queue.isEmpty()) {
                return redNode == null ? null : redNode.next;
            }
            ListNode next = redNode.next;
            ListNode pre = queue.peek();

            redNode.next = null;
            pre.next = next;
            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}