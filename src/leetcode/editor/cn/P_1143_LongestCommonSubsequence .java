//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//
//
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
//
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
//
//
//
// 示例 1：
//
//
//输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
//
//
// 示例 2：
//
//
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
//
//
// 示例 3：
//
//
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
//
//
//
//
// 提示：
//
//
// 1 <= text1.length, text2.length <= 1000
// text1 和 text2 仅由小写英文字符组成。
//
//
// Related Topics 字符串 动态规划 👍 1528 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:最长公共子序列
class P_1143_LongestCommonSubsequence {
    public static void main(String[] args) {
        Solution solution = new P_1143_LongestCommonSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private char[] s, t;
        private int[][] cache;

        public int longestCommonSubsequence(String text1, String text2) {
            // 递归搜索 + 保存计算结果 = 记忆化搜索
            s = text1.toCharArray();
            t = text2.toCharArray();
            int n = s.length;
            int m = t.length;
            cache = new int[n][m];
            // -1 表示没有访问过
            Arrays.stream(cache).forEach(a -> Arrays.fill(a, -1));
            // 注意：最长公共子序列，解决两个字符串的动态规划问题，一般都是用两个指针 i, j 分别指向两个字符串的最后，然后一步步往前移动，缩小问题的规模。
            return dfs(n - 1, m - 1);
        }

        // 动态规划的问题，可以先想到dfs求解子问题的公式，才能更好的想出递推公式
        // 子序列本质就是选或者不选
        // 子问题：s的前i个字母和t的前j个字母的LCS长度
        private int dfs(int i, int j) {
            if (i < 0 || j < 0) {
                return 0;
            }

            if (cache[i][j] != -1) {
                return cache[i][j];
            }

            if (s[i] == t[j]) {
                cache[i][j] = dfs(i - 1, j - 1) + 1;
                return cache[i][j];
            }

            cache[i][j] = Math.max(dfs(i - 1, j), dfs(i, j - 1));
            return cache[i][j];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}