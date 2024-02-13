//给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
//
//
//
// 示例 1:
//
//
//
//
//输入: [1,2,3,null,5,null,4]
//输出: [1,3,4]
//
//
// 示例 2:
//
//
//输入: [1,null,3]
//输出: [1,3]
//
//
// 示例 3:
//
//
//输入: []
//输出: []
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [0,100]
//
// -100 <= Node.val <= 100
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1028 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//java:二叉树的右视图
class P_199_BinaryTreeRightSideView {
    public static void main(String[] args) {
        Solution solution = new P_199_BinaryTreeRightSideView().new Solution();
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
        List<Integer> result = new ArrayList<>();

        public List<Integer> rightSideView(TreeNode root) {
            // DFS深度优先搜索算法：只保存该层中第一次访问到的右节点，如果不是第一次访问则跳过
            // 根节点深度为0
            dfs(root, 0);
            return result;
        }

        private void dfs(TreeNode root, int depth) {
            if (root == null) {
                return;
            }
            // 先访问 当前节点，再递归地访问 右子树 和 左子树。
            // 判断深度：如果是第一次访问该层，因为是根、右、左的遍历顺序，所以可以直接添加值
            if (result.size() == depth) {
                // 此处巧妙借助结果集的大小来当作深度判断
                result.add(root.val);
            }
            depth++;
            dfs(root.right, depth);
            dfs(root.left, depth);
        }

        public List<Integer> rightSideViewBFS(TreeNode root) {
            // BFS广度优先搜索算法：结合栈列表的层序遍历
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> result = new ArrayList<>();
            // 层序遍历：借助栈，从左往右不断遍历。那么最右边的最后一个就是需要的右视图
            Deque<TreeNode> deque = new LinkedList<>();
            deque.offer(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.poll();
                    if (i == size - 1) {
                        result.add(node.val);
                    }
                    // 把子树填充到队列
                    if (node.left != null) {
                        deque.offer(node.left);
                    }
                    if (node.right != null) {
                        deque.offer(node.right);
                    }
                }
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}