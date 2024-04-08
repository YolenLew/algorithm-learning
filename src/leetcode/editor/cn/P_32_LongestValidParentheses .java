//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
//
//
//
//
// 示例 1：
//
//
//
//
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
//
//
// 示例 2：
//
//
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
//
//
// 示例 3：
//
//
//输入：s = ""
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 3 * 10⁴
// s[i] 为 '(' 或 ')'
//
//
// Related Topics 栈 字符串 动态规划 👍 2461 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//java:最长有效括号
class P_32_LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P_32_LongestValidParentheses().new Solution();
        System.out.println(solution.longestValidParentheses("(()"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses00(String s) {
            // 1、明确状态：如何才能描述一个问题局面？只要给s的一部分字符串，求最长合法子串长度
            // 2、明确选择：对于一个括号，由 左括号 和 右括号 两种情况
            // 3、明确dp数组定义：dp[i]记录以 s[i-1] 结尾的最长合法括号子串长度
            // 4、根据选择，定义转移方程：根据「选择」对 dp[i]得到以下状态转移
            //     左括号：左括号不可能是合法括号子串的结尾 dp[i + 1] = 0
            //     右括号：需要匹配栈中的左括号索引判断记录
            // 5、明确base case或边界情况：s字符串的索引从0开始，dp[i]有效数据从1开始，dp[i]记录以 s[i-1] 结尾的最长合法括号子串长度

            // 记录左括号索引
            Stack<Integer> stack = new Stack<>();
            // dp数组：dp[i]记录以 s[i-1] 结尾的最长合法括号子串长度
            int[] dp = new int[s.length() + 1];

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                    // 左括号：左括号不可能是合法括号子串的结尾 dp[i + 1] = 0
                    dp[i + 1] = 0;
                } else {
                    // 匹配到右括号
                    if (!stack.isEmpty()) {
                        // 获取左括号索引，计算合法括号长度
                        Integer leftIndex = stack.pop();
                        int validLength = i + 1 - leftIndex;
                        // 当前位置的子串最长长度：当前长度+上一个合法括号长度的长度
                        dp[i + 1] = validLength + dp[leftIndex];
                    } else {
                        dp[i + 1] = 0;
                    }
                }
            }

            int res = 0;
            for (int len : dp) {
                res = Math.max(res, len);
            }

            return res;
        }

        // --------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------
        public int longestValidParentheses(String s) {
            int n = s.length();
            if (n == 0) {
                return 0;
            }
            // 记录左括号索引
            Deque<Integer> stack = new LinkedList<>();
            // dp数组：记录以i结尾的最长有效（格式正确且连续）括号子串的长度
            int[] dp = new int[n];
            // base case
            dp[0] = 0;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                    // 左括号：左括号不可能是合法括号子串的结尾
                    dp[i] = 0;
                } else {
                    // 右括号：需要与栈顶左括号匹配
                    if (stack.isEmpty()) {
                        dp[i] = 0;
                    } else {
                        // 与栈顶左括号匹配
                        // 获取左括号索引，计算合法括号长度
                        Integer leftIndex = stack.pop();
                        int validLength = i + 1 - leftIndex;
                        // 当前有效长度+左括号前一个合法子串的长度
                        int lastIndex = leftIndex > 0 ? leftIndex - 1 : 0;
                        dp[i] = validLength + dp[lastIndex];
                    }
                }
            }

            int res = Integer.MIN_VALUE;
            for (int len : dp) {
                res = Math.max(res, len);
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}