//给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵
//树的后序遍历，重构并返回二叉树。
//
// 如果存在多个答案，您可以返回其中 任何 一个。
//
//
//
// 示例 1：
//
//
//
//
//输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
//输出：[1,2,3,4,5,6,7]
//
//
// 示例 2:
//
//
//输入: preorder = [1], postorder = [1]
//输出: [1]
//
//
//
//
// 提示：
//
//
// 1 <= preorder.length <= 30
// 1 <= preorder[i] <= preorder.length
// preorder 中所有值都 不同
// postorder.length == preorder.length
// 1 <= postorder[i] <= postorder.length
// postorder 中所有值都 不同
// 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历
//
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 337 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

//java:根据前序和后序遍历构造二叉树
class P_889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P_889_ConstructBinaryTreeFromPreorderAndPostorderTraversal().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        // 后序数组中，数值和索引的映射（题目已约束不会有重复值）
        Map<Integer, Integer> valToIndexMap = new HashMap<>();

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            for (int i = 0; i < postorder.length; i++) {
                valToIndexMap.put(postorder[i], i);
            }
            // 二叉树的构造问题一般都是使用「分解问题」的思路：构造整棵树 = 根节点 + 构造左子树 + 构造右子树。
            // 本题：通过两个数组构造二叉树，抽象函数为如下
            return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
        }

        // 递归构造关键：找出左右子树的分界
        private TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
            // 递归退出条件
            if (preStart > preEnd) {
                return null;
            }
            if (preStart == preEnd) {
                return new TreeNode(preorder[preStart]);
            }
            // 根据图解，前序数组第一个值就是根节点值
            int rootVal = preorder[preStart];
            // 左子树的节点个数：这里假定前序数组中第二个索引为左子树根节点
            int leftRootVal = preorder[preStart + 1];
            Integer leftRootIndex = valToIndexMap.get(leftRootVal);
            int leftSize = leftRootIndex - postStart + 1;
            // 构造根节点
            TreeNode root = new TreeNode(rootVal);
            // 构造左子树节点
            root.left = build(preorder, preStart + 1, preStart + leftSize, postorder, postStart, leftRootIndex);
            // 构造右子树节点
            root.right = build(preorder, preStart + leftSize + 1, preEnd, postorder, leftRootIndex + 1, postEnd);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}