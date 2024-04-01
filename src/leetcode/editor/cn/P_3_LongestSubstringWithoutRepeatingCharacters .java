//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 10⁴
// s 由英文字母、数字、符号和空格组成
//
//
// Related Topics 哈希表 字符串 滑动窗口 👍 10047 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:无重复字符的最长子串
class P_3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new P_3_LongestSubstringWithoutRepeatingCharacters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            // 记录字符的起点索引
            Map<Character, Integer> charMap = new HashMap<>();
            int longest = 0;
            int left = 0;

            char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (charMap.containsKey(charArray[i])) {
                    left = Math.max(charMap.get(charArray[i]) + 1, left);
                }

                // 记录或更新当前字符索引位置
                charMap.put(charArray[i], i);
                longest = Math.max(longest, i - left + 1);
            }

            return longest;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}