//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
//
// Related Topics 数组 回溯 👍 1538 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//java:全排列 II
class P_47_PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new P_47_PermutationsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有结果集合
        List<List<Integer>> result = new LinkedList<>();
        // 符合要求的路径（每一次的结果）,使用LinkedList是便于方便去除最后一个元素
        LinkedList<Integer> track = new LinkedList<>();
        // 标记路径中是否已经使用
        boolean[] used;

        public List<List<Integer>> permuteUnique(int[] nums) {
            // 关键：先排序，让重复的元素靠在一起，便于后续判断进行剪枝
            Arrays.sort(nums);
            used = new boolean[nums.length];
            backtrack(nums);
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
        private void backtrack(int[] nums) {
            // 判断：满足结束条件
            if (nums.length == track.size()) {
                result.add(new LinkedList<>(track));
                return;
            }
            // 遍历选择列表进行选择
            for (int i = 0; i < nums.length; i++) {
                // 判断条件：进行选择
                if (used[i]) {
                    continue;
                }
                // !used[i - 1]作用：固定相同的元素在排列中的相对位置，因为这是排列问题，相同的元素排列都是一致的，1，2，2‘和1，2’，2是一样的
                // 标准全排列算法之所以出现重复，是因为把相同元素形成的排列序列视为不同的序列，但实际上它们应该是相同的；而如果固定相同元素形成的序列顺序，当然就避免了重复。
                // 重复元素判断：如果碰到了重复元素，而且重复的上一个元素还没被使用，说明可以舍弃这次选择，因为重复的上一个元素排在更前面，已经做过选择了
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                // 路径添加选择
                track.add(nums[i]);
                used[i] = true;
                // 继续递归选择
                backtrack(nums);
                // 路径撤销选择
                track.removeLast();
                used[i] = false;
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}