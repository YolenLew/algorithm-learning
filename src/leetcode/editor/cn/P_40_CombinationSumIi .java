//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用 一次 。
//
// 注意：解集不能包含重复的组合。
//
//
//
// 示例 1:
//
//
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//]
//
// 示例 2:
//
//
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//]
//
//
//
// 提示:
//
//
// 1 <= candidates.length <= 100
// 1 <= candidates[i] <= 50
// 1 <= target <= 30
//
//
// Related Topics 数组 回溯 👍 1516 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//java:组合总和 II
class P_40_CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new P_40_CombinationSumIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有结果的集合
        List<List<Integer>> result = new ArrayList<>();
        // 单词满足条件的路径
        LinkedList<Integer> track = new LinkedList<>();
        // 元素和
        int trackSum = 0;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            if (candidates == null || candidates.length == 0) {
                return result;
            }
            // 排序，让相同的元素靠在一起
            Arrays.sort(candidates);
            backtrack(candidates, target, 0);
            return result;
        }

        private void backtrack(int[] candidates, int target, int start) {
            // 满足条件判断
            if (trackSum == target) {
                result.add(new ArrayList<>(track));
                return;
            }
            // 题目限制元素都是大于0，而且已经排序了，所以无需继续遍历
            if (trackSum > target) {
                return;
            }

            // 遍历选择列表进行选择
            for (int i = start; i < candidates.length; i++) {
                // 重复元素，需进行剪枝
                // 遍历过程想象为二叉树，这里的剪枝逻辑，就是同一层级，值相同的相邻树枝，只遍历第一条
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                // 选择判断
                trackSum += candidates[i];
                track.addLast(candidates[i]);
                // 下钻递归选择
                backtrack(candidates, target, i + 1);
                // 撤销选择
                trackSum -= candidates[i];
                track.removeLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}