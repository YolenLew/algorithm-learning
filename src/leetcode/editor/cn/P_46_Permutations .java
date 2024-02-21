//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
//
// Related Topics 数组 回溯 👍 2814 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//java:全排列
class P_46_Permutations {
    public static void main(String[] args) {
        Solution solution = new P_46_Permutations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 结果集合
        List<List<Integer>> result = new LinkedList<>();

        public List<List<Integer>> permute(int[] nums) {
            if (nums == null || nums.length == 0) {
                return result;
            }

            // 每一次符合要求的路径
            LinkedList<Integer> track = new LinkedList<>();
            // 标记路径中是否已经使用
            boolean[] used = new boolean[nums.length];
            // 回溯算法：排列组合
            backtrack(nums, track, used);
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
        private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
            // 判断：满足结束条件
            if (nums.length == track.size()) {
                result.add(new ArrayList<>(track));
                return;
            }
            // 遍历选择列表进行选择
            for (int i = 0; i < nums.length; i++) {
                // 排除不合法的选择
                if (used[i]) {
                    // nums[i] 已经在 track 路径中，跳过
                    continue;
                }
                // 做选择
                track.add(nums[i]);
                used[i] = true;
                // 递归进行下一层选择
                backtrack(nums, track, used);
                // 撤销路径
                track.removeLast();
                // 撤销选择
                used[i] = false;
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}