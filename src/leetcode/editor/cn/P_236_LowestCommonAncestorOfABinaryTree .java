//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。”
//
//
//
// 示例 1：
//
//
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
//
//
// 示例 2：
//
//
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
//
//
// 示例 3：
//
//
//输入：root = [1,2], p = 1, q = 2
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [2, 10⁵] 内。
// -10⁹ <= Node.val <= 10⁹
// 所有 Node.val 互不相同 。
// p != q
// p 和 q 均存在于给定的二叉树中。
//
//
// Related Topics 树 深度优先搜索 二叉树 👍 2604 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

//java:二叉树的最近公共祖先
class P_236_LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new P_236_LowestCommonAncestorOfABinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode lowestCommonAncestor00(TreeNode root, TreeNode p, TreeNode q) {
            // 遍历解法：遍历左右子树，如果：
            // 1. p q都能找到 返回最近公共祖先；2. p q 找到一个，返回p q；3. 都没找到 返回null
            if (root == null || root == p || root == q) {
                return root;
            }
            //根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
            // 遍历左子树：
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            // 遍历右子树
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            // p和q都没找到，那就没有
            if (left == null && right == null) {
                return null;
            }
            // 左子树没有p也没有q，就返回右子树的结果
            if (left == null) {
                return right;
            }
            // 右子树没有p也没有q就返回左子树的结果
            if (right == null) {
                return left;
            }
            // 左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
            return root;
        }

        // ---------------------------------------------------------------
        // -----------------------遍历；逻辑分析-----------------------
        // ---------------------------------------------------------------
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }

            // 寻找左子树的结果
            TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
            TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

            if (leftResult == null && rightResult == null) {
                return null;
            }

            if (leftResult == null) {
                return rightResult;
            }

            if (rightResult == null) {
                return leftResult;
            }

            // 左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}