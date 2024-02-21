//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
//
// 回文串 是正着读和反着读都一样的字符串。
//
//
//
// 示例 1：
//
//
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
//
//
// 示例 2：
//
//
//输入：s = "a"
//输出：[["a"]]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 16
// s 仅由小写英文字母组成
//
//
// Related Topics 字符串 动态规划 回溯 👍 1731 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//java:分割回文串
class P_131_PalindromePartitioning {
    public static void main(String[] args) {
        Solution solution = new P_131_PalindromePartitioning().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有满足的结果集
        List<List<String>> result = new ArrayList<>();
        // 单词符合条件的结果
        LinkedList<String> stack = new LinkedList<>();

        public List<List<String>> partition(String s) {
            // 分割字符串，统计符合回文字符条件的所有组合(元素可重复不可复选的排列问题)
            backtrack(s, 0);
            return result;
        }

        private void backtrack(String s, int start) {
            // base case判断：已经遍历完全所有字符
            if (s.length() == start) {
                result.add(new ArrayList<>(stack));
                return;
            }
            // 遍历选择列表进行选择
            for (int i = start; i < s.length(); i++) {
                // 选择条件判断：是否符合回文串特征
                if (!isPalindrome(s, start, i)) {
                    continue;
                }
                // 添加回文串
                stack.addLast(s.substring(start, i + 1));
                // 下一层递归选择
                backtrack(s, i + 1);
                // 撤消选择
                stack.removeLast();
            }

        }

        // 用双指针技巧判断 s[lo..hi] 是否是一个回文串
        private boolean isPalindrome(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}