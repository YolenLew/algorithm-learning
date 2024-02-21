//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
// 你可以按 任何顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
//
// 示例 2：
//
//
//输入：n = 1, k = 1
//输出：[[1]]
//
//
//
// 提示：
//
//
// 1 <= n <= 20
// 1 <= k <= n
//
//
// Related Topics 回溯 👍 1588 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

//java:组合
class P_77_Combinations {
    public static void main(String[] args) {
        Solution solution = new P_77_Combinations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有结果的集合
        List<List<Integer>> result = new LinkedList<>();
        // 记录回溯算法的递归路径
        LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> combine(int n, int k) {
            // 最小值为1，从1开始选择
            backtrack(n, k, 1);
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
        private void backtrack(int n, int k, int start) {
            // 满足条件判断
            if (track.size() == k) {
                result.add(new LinkedList<>(track));
                return;
            }
            // 遍历选择列表进行选择
            for (int i = start; i <= n; i++) {
                // 做选择
                track.add(i);
                // 递归选择
                backtrack(n, k, i + 1);
                // 撤销选择
                track.removeLast();
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}