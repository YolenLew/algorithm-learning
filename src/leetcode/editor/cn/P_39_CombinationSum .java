//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
//
//
//
// 示例 1：
//
//
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。
//
// 示例 2：
//
//
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]]
//
// 示例 3：
//
//
//输入: candidates = [2], target = 1
//输出: []
//
//
//
//
// 提示：
//
//
// 1 <= candidates.length <= 30
// 2 <= candidates[i] <= 40
// candidates 的所有元素 互不相同
// 1 <= target <= 40
//
//
// Related Topics 数组 回溯 👍 2725 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//java:组合总和
class P_39_CombinationSum {
    public static void main(String[] args) {
        Solution solution = new P_39_CombinationSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有结果的结果集
        List<List<Integer>> result = new ArrayList<>();
        // 单次符合条件的集合
        LinkedList<Integer> stack = new LinkedList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            backtrack(candidates, target, 0, 0);
            return result;
        }

        // 回溯算法框架：
        //        result = []
        //        def backtrack(路径, 选择列表):
        //            if 满足结束条件:
        //            result.add(路径)
        //            return
        //
        //            for 选择 in 选择列表:
        //              做选择：将选择从选择列表移除；将选择添加到路径中去；
        //              backtrack(路径, 选择列表)
        //              撤销选择：路径取消选择；将选择再加入选择列表
        private void backtrack(int[] candidates, int target, int start, int sum) {
            // 满足条件判断
            if (sum == target) {
                result.add(new ArrayList<>(stack));
                return;
            }
            if (sum > target) {
                return;
            }

            // 遍历选择列表
            for (int i = start; i < candidates.length; i++) {
                // 选择元素
                sum += candidates[i];
                stack.addLast(candidates[i]);
                // 下钻递归选择
                backtrack(candidates, target, i, sum);
                // 撤消选择
                sum -= candidates[i];
                stack.removeLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}