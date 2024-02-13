//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。
//
//
//
// 示例 1:
//
//
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
//
//
//
//
// 提示:
//
//
// 1 <= preorder.length <= 3000
// inorder.length == preorder.length
// -3000 <= preorder[i], inorder[i] <= 3000
// preorder 和 inorder 均 无重复 元素
// inorder 均出现在 preorder
// preorder 保证 为二叉树的前序遍历序列
// inorder 保证 为二叉树的中序遍历序列
//
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 2200 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

//java:从前序与中序遍历序列构造二叉树
class P_105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P_105_ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
        // 中序数组中，数值和索引的映射（题目已约束不会有重复值）
        Map<Integer, Integer> valToIndexMap = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 构造中序数组中，数值和索引的映射
            for (int i = 0; i < inorder.length; i++) {
                valToIndexMap.put(inorder[i], i);
            }
            // 二叉树的构造问题一般都是使用「分解问题」的思路：构造整棵树 = 根节点 + 构造左子树 + 构造右子树。
            // 本题：通过两个数组构造二叉树，抽象函数为如下
            return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        // 递归构造二叉树
        private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            // 递归退出条件
            if (preStart > preEnd) {
                return null;
            }
            // 画图解析可知：前序数组中，第一个索引的值为根节点；中序数组中，根节点索引值左侧为左子树，右侧为右子树
            int rootVal = preorder[preStart];
            // 获取根节点在中序数组中的索引
            Integer rootIndex = valToIndexMap.get(rootVal);
            // 构造根节点
            TreeNode root = new TreeNode(rootVal);
            // 递归构造左子树
            int leftSize = rootIndex - inStart;
            root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndex - 1);
            // 递归构造右子树
            root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndex + 1, inEnd);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}