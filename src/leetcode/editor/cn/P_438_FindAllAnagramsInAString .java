//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
//
//
// 示例 1:
//
//
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//
//
// 示例 2:
//
//
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//
//
//
//
// 提示:
//
//
// 1 <= s.length, p.length <= 3 * 10⁴
// s 和 p 仅包含小写字母
//
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1360 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//java:找到字符串中所有字母异位词
class P_438_FindAllAnagramsInAString {
    public static void main(String[] args) {
        Solution solution = new P_438_FindAllAnagramsInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            // 滑动窗口，双指针
            // 目标字符Map：记录匹配字符串的目标字符及个数
            Map<Character, Integer> targetMap = new HashMap<>();
            for (char c : p.toCharArray()) {
                targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
            }
            // 窗口map：不断移动窗口进行匹配
            Map<Character, Integer> window = new HashMap<>();
            // 结果集
            List<Integer> result = new ArrayList<>();
            // 初始化左右指针、有效匹配词频
            int left = 0, right = 0, valid = 0;
            while (right < s.length()) {
                // 首先移动右指针匹配字符
                char endChar = s.charAt(right);
                right++;
                if (targetMap.containsKey(endChar)) {
                    window.put(endChar, window.getOrDefault(endChar, 0) + 1);
                    // 此时如果目标字符匹配且数目一致，则有效字符数加一
                    if (window.get(endChar).equals(targetMap.get(endChar))) {
                        valid++;
                    }
                }
                // 缩小窗口：当窗口长度大于等于目标长度时，尝试缩小窗口
                while (right - left >= p.length()) {
                    if (valid == targetMap.size()) {
                        result.add(left);
                    }
                    char startChar = s.charAt(left);
                    left++;
                    // 更新窗口词频
                    if (targetMap.containsKey(startChar)) {
                        if (targetMap.get(startChar).equals(window.get(startChar))) {
                            // 如果是匹配的字符且有效词频达到了，那么左指针移动之后，有效词频减一
                            valid--;
                        }
                        window.put(startChar, window.getOrDefault(startChar, 0) - 1);
                    }
                }
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}