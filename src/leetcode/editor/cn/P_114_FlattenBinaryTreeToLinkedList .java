//给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
//
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。
//
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 2000] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
//
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 1623 👎 0

package leetcode.editor.cn;

//java:二叉树展开为链表
class P_114_FlattenBinaryTreeToLinkedList {
    public static void main(String[] args){
        Solution solution = new P_114_FlattenBinaryTreeToLinkedList().new Solution();
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // 「分解」的思维模式：通过前序遍历方式，分解为子树的展开问题
        flatten(root.left);
        flatten(root.right);

        // 左右子树展开后，将左子树转接到Root右子树，然后再把原来的右子树追加到末尾即可
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 转接左树到右侧，并将左树置null！！！！！
        root.right = left;
        root.left = null;

        // 寻找新右树末尾节点
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        // 把原来的右子树追加到末尾即可
        p.right = right;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}