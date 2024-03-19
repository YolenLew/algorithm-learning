//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母组成
//
//
// Related Topics 字符串 动态规划 👍 7113 👎 0

package leetcode.editor.cn;

//java:最长回文子串
class P_5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new P_5_LongestPalindromicSubstring().new Solution();
        String res = solution.longestPalindrome("babad");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                // 回文串可能是奇数中心长度也可能是偶数中心长度
                // 以i为中心的回文串
                String s1 = findLongestPalindrome(s, i, i);
                // 以 i和i+1 为中心的回文串
                String s2 = findLongestPalindrome(s, i, i + 1);
                res = res.length() > s1.length() ? res : s1;
                res = res.length() > s2.length() ? res : s2;
            }
            return res;
        }

        // 在 s 中寻找以 s[l] 和 s[r] 为中心的最长回文串
        private String findLongestPalindrome(String s, int l, int r) {
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                // 双指针，向两边展开
                l--;
                r++;
            }
            // 返回以 s[l] 和 s[r] 为中心的最长回文串
            return s.substring(l + 1, r);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}