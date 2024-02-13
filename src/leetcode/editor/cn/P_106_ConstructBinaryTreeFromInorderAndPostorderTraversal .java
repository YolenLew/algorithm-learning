//给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并
//返回这颗 二叉树 。
//
//
//
// 示例 1:
//
//
//输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//输出：[3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//输入：inorder = [-1], postorder = [-1]
//输出：[-1]
//
//
//
//
// 提示:
//
//
// 1 <= inorder.length <= 3000
// postorder.length == inorder.length
// -3000 <= inorder[i], postorder[i] <= 3000
// inorder 和 postorder 都由 不同 的值组成
// postorder 中每一个值都在 inorder 中
// inorder 保证是树的中序遍历
// postorder 保证是树的后序遍历
//
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1171 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

//java:从中序与后序遍历序列构造二叉树
class P_106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P_106_ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
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

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for (int i = 0; i < inorder.length; i++) {
                valToIndexMap.put(inorder[i], i);
            }
            // 二叉树的构造问题一般都是使用「分解问题」的思路：构造整棵树 = 根节点 + 构造左子树 + 构造右子树。
            // 本题：通过两个数组构造二叉树，抽象函数为如下
            return build(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
        }

        private TreeNode build(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd) {
            // 递归退出条件
            if (postStart > postEnd) {
                return null;
            }
            // 根据图解，后序数组最后一个值就是根节点值
            int rootVal = postorder[postEnd];
            // 构造根节点
            TreeNode root = new TreeNode(rootVal);
            if (postStart == postEnd) {
                return root;
            }
            // 根据值获取在中序数组中的根节点索引
            Integer rootIndex = valToIndexMap.get(rootVal);
            // 左子树的节点个数：根据中序数组根索引的左侧全是左子树节点求解
            int leftSize = rootIndex - inStart;
            // 构造左子树
            root.left = build(postorder, postStart, postStart + leftSize - 1, inorder, inStart, rootIndex - 1);
            // 构造右子树
            root.right = build(postorder, postStart + leftSize, postEnd - 1, inorder, rootIndex + 1, inEnd);

            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}