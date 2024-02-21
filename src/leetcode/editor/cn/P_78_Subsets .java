//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// nums 中的所有元素 互不相同
//
//
// Related Topics 位运算 数组 回溯 👍 2247 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

//java:子集
class P_78_Subsets {
    public static void main(String[] args) {
        Solution solution = new P_78_Subsets().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有结果集
        List<List<Integer>> result = new LinkedList<>();
        // 单次结果的路径集
        LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> subsets(int[] nums) {
            // 一层一层累加并子集
            backtrack(nums, 0);
            return result;
        }

        // 类比为遍历多叉树，把所有节点的值收集起来。解法为一层一层遍历收集
        private void backtrack(int[] nums, int level) {
            // 类比二叉树的先序遍历，进入后就收集
            result.add(new LinkedList<>(track));
            // 回溯核心框架：遍历选择列表进行路径选择。因为通过level树的层级进行控制，所以无需额外的标识
            for (int i = level; i < nums.length; i++) {
                // 进行选择
                track.addLast(nums[i]);
                // 继续递归下一层的选择:通过 level 参数控制树枝的遍历，避免产生重复的子集
                backtrack(nums, i + 1);
                // 最后撤销选择
                track.removeLast();
            }

        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}