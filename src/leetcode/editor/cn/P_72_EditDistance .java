//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//
//
//
// 提示：
//
//
// 0 <= word1.length, word2.length <= 500
// word1 和 word2 由小写英文字母组成
//
//
// Related Topics 字符串 动态规划 👍 3326 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:编辑距离
class P_72_EditDistance {
    public static void main(String[] args) {
        Solution solution = new P_72_EditDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 备忘录：优化超时问题
        int[][] cache;

        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            cache = new int[m][n];
            Arrays.stream(cache).forEach(a -> Arrays.fill(a, -1));
            // i，j 初始化指向最后一个索引
            return dp(word1, m - 1, word2, n - 1);
        }

        // 定义：返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
        private int dp(String word1, int i, String word2, int j) {
            // base case
            if (i == -1) {
                // 还需操作步数
                return j + 1;
            }
            if (j == -1) {
                // 还需操作步数
                return i + 1;
            }

            // 查备忘录，避免重叠子问题
            if (cache[i][j] != -1) {
                return cache[i][j];
            }

            if (word1.charAt(i) == word2.charAt(j)) {
                // 相等则跳过无需操作，i, j 同时向前移动
                cache[i][j] = dp(word1, i - 1, word2, j - 1);
            } else {
                // 不相等，三选一：
                //  插入（insert）
                //  删除（delete）
                //  替换（replace）
                cache[i][j] = min(dp(word1, i, word2, j - 1) + 1, dp(word1, i - 1, word2, j) + 1,
                    dp(word1, i - 1, word2, j - 1) + 1);
            }

            return cache[i][j];
        }

        private int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}