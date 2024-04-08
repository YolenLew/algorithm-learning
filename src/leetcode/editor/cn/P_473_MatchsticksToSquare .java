//你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能
//折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
//
// 如果你能使这个正方形，则返回 true ，否则返回 false 。
//
//
//
// 示例 1:
//
//
//
//
//输入: matchsticks = [1,1,2,2,2]
//输出: true
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
//
//
// 示例 2:
//
//
//输入: matchsticks = [3,3,3,3,4]
//输出: false
//解释: 不能用所有火柴拼成一个正方形。
//
//
//
//
// 提示:
//
//
// 1 <= matchsticks.length <= 15
// 1 <= matchsticks[i] <= 10⁸
//
//
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 👍 526 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:火柴拼正方形
class P_473_MatchsticksToSquare {
    public static void main(String[] args) {
        Solution solution = new P_473_MatchsticksToSquare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean makesquare(int[] matchsticks) {
            int totalLen = Arrays.stream(matchsticks).sum();
            if (totalLen % 4 != 0) {
                return false;
            }

            // 为了减少搜索量，需要对火柴长度从大到小进行排序
            Arrays.sort(matchsticks);
            for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
                int temp = matchsticks[i];
                matchsticks[i] = matchsticks[j];
                matchsticks[j] = temp;
            }

            // 记录每条边长
            int[] edges = new int[4];

            return dfs(0, matchsticks, edges, totalLen / 4);
        }

        // 站在火柴的视角，每个火柴需要做出选择
        // index : 第 index 个球开始做选择
        // bucket : 桶
        public boolean dfs(int index, int[] matchsticks, int[] bucket, int len) {
            if (index == matchsticks.length) {
                return true;
            }

            // 火柴遍历选择列表进行选择
            for (int i = 0; i < bucket.length; i++) {
                // 剪枝优化
                if (i > 0 && bucket[i] == bucket[i - 1])
                    continue;
                // 做选择：放入 i 号桶
                bucket[i] += matchsticks[index];

                // 处理下一个球，即 nums[index + 1]
                if (bucket[i] <= len && dfs(index + 1, matchsticks, bucket, len)) {
                    return true;
                }

                bucket[i] -= matchsticks[index];
            }

            // k 个桶都不满足要求
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}