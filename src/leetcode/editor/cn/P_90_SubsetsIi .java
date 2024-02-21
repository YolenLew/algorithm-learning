//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
//
//
// Related Topics 位运算 数组 回溯 👍 1194 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//java:子集 II
class P_90_SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new P_90_SubsetsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有结果的集合
        List<List<Integer>> result = new ArrayList<>();
        // 单词满足条件的路径
        LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            // 首先对数组排序：让相同的元素排在一起
            Arrays.sort(nums);
            backtrack(nums, 0);
            return result;
        }

        private void backtrack(int[] nums, int start) {
            // 子集问题可类比前序遍历：进入之后便可将路径添加到结果集
            // 前序位置，每个节点的值都是一个子集
            result.add(new ArrayList<>(track));
            // 遍历选择列表进行路径选择
            for (int i = start; i < nums.length; i++) {
                // 重复元素判断
                // 遍历过程想象为二叉树，这里的剪枝逻辑，就是同一层级，值相同的相邻树枝，只遍历第一条
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }

                // 路径选择
                track.addLast(nums[i]);
                // 递归下钻选择
                backtrack(nums, i + 1);
                // 撤销选择
                track.removeLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}