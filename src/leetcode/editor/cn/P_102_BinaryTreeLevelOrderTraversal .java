//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
//
//
// 示例 2：
//
//
//输入：root = [1]
//输出：[[1]]
//
//
// 示例 3：
//
//
//输入：root = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [0, 2000] 内
// -1000 <= Node.val <= 1000
//
//
// Related Topics 树 广度优先搜索 二叉树 👍 1880 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//java:二叉树的层序遍历
class P_102_BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new P_102_BinaryTreeLevelOrderTraversal().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            // 层序遍历：借助栈，从左往右不断遍历
            LinkedList<TreeNode> nodeList = new LinkedList<>();
            nodeList.offer(root);
            while (!nodeList.isEmpty()) {
                List<Integer> levelResult = new ArrayList<>();
                // 获取当前层级的节点个数
                int size = nodeList.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = nodeList.poll();
                    levelResult.add(node.val);
                    if (node.left != null) {
                        nodeList.offer(node.left);
                    }
                    if (node.right != null) {
                        nodeList.offer(node.right);
                    }
                }
                // 添加到结果列表
                result.add(levelResult);
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}