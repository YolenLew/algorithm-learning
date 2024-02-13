//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
// 有效 二叉搜索树定义如下：
//
//
// 节点的左子树只包含 小于 当前节点的数。
// 节点的右子树只包含 大于 当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
//
//
//
//
// 示例 1：
//
//
//输入：root = [2,1,3]
//输出：true
//
//
// 示例 2：
//
//
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
//
//
//
//
// 提示：
//
//
// 树中节点数目范围在[1, 10⁴] 内
// -2³¹ <= Node.val <= 2³¹ - 1
//
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 2265 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

//java:验证二叉搜索树
class P_98_ValidateBinarySearchTree {
    public static void main(String[] args){
        Solution solution = new P_98_ValidateBinarySearchTree().new Solution();
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
    // 最小值初始化为中序序列的第一个节点值
    Integer pre;
    public boolean isValidBST(TreeNode root) {
        // 中序遍历解法：左根右，所以中序遍历一定是得到一个升序的序列。那么就可以通过一个pre值进行判断比较
        if (root == null) {
            return true;
        }
        // 中序遍历：先判断左子树
        boolean validBSTLeft = isValidBST(root.left);
        if (!validBSTLeft) {
            return false;
        }
        // 然后比对根值：如果当前节点的值小于等于中序序列中上一个值，证明不是二叉搜索树（中序遍历是升序）
        if (pre != null && root.val <= pre) {
            return false;
        }
        // 如果当前节点小于中序序列上一个值，证明符合条件，更新pre为当前值，并继续下一个值的判断
        pre = root.val;

        // 中序遍历：最后判断右子树
        return isValidBST(root.right);
    }


    public boolean isValidBSTFirst(TreeNode root) {
        // 分解：传递参数解法
        return isValidBST(root, null, null);
    }

    // 需要传递根节点的值，便于后续子树的大小判断也符合二叉搜索树
    public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max){
        // 分解：每一颗子树都是二叉搜索树
        // 注意：根据 BST 的定义，root 的整个左子树都要小于 root.val，整个右子树都要大于 root.val。
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min.val ) {
            return false;
        }

        if (max != null && root.val >= max.val ) {
            return false;
        }

        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}