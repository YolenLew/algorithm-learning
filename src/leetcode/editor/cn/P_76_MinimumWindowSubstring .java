//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
//
//
// 注意：
//
//
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
//
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//解释：整个字符串 s 是最小覆盖子串。
//
//
// 示例 3:
//
//
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。
//
//
//
// 提示：
//
//
// m == s.length
// n == t.length
// 1 <= m, n <= 10⁵
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在
//o(m+n) 时间内解决此问题的算法吗？
//
// Related Topics 哈希表 字符串 滑动窗口 👍 2797 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//java:最小覆盖子串
class P_76_MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new P_76_MinimumWindowSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow00(String s, String t) {
            // 目标词频Map：记录目标字符串中的字符及词频
            Map<Character, Integer> targetMap = new HashMap<>();
            for (char c : t.toCharArray()) {
                targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
            }
            // 窗口Map：存储滑动窗口中有效字符及出现的次数
            Map<Character, Integer> window = new HashMap<>();
            // 初始化左右指针、有效字符统计数量
            int left = 0, right = 0, valid = 0;
            // 记录最小覆盖子串的起始索引及窗口字符长度（因为要返回子串）
            int start = 0, length = Integer.MAX_VALUE;
            while (right < s.length()) {
                char headChar = s.charAt(right);
                right++;
                // 比对并更新移动窗口的数据
                if (targetMap.containsKey(headChar)) {
                    // 判断是否是目标字符，如果是，则累加并比较数量；此外，如果达到目标词频，则有效字符数量也累计
                    window.put(headChar, window.getOrDefault(headChar, 0) + 1);
                    if (window.get(headChar).equals(targetMap.get(headChar))) {
                        valid++;
                    }
                }
                // 移动左指针：缩小窗口，寻找最小字串
                while (targetMap.size() == valid) {
                    if (right - left < length) {
                        start = left;
                        length = right - left;
                    }
                    char tailChar = s.charAt(left);
                    left++;
                    // 更新移动窗口的数据
                    if (targetMap.containsKey(tailChar)) {
                        // 移动窗口后，左指针去除了；所以要判断有效字符长度，如果有效数匹配了，那么左指针移动后，有效数就要减去1
                        if (targetMap.get(tailChar).equals(window.get(tailChar))) {
                            valid--;
                        }
                        window.put(tailChar, window.get(tailChar) - 1);
                    }
                }
            }
            return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
        }

        public String minWindow(String s, String t) {
            // 目标词频Map：记录目标字符串中的字符及词频
            Map<Character, Integer> targetMap = new HashMap<>();
            for (char c : t.toCharArray()) {
                targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
            }
            // 窗口Map：存储滑动窗口中有效字符及出现的次数
            Map<Character, Integer> window = new HashMap<>();
            // 初始化左右指针、有效字符统计数量
            int left = 0, right = 0, valid = 0;
            // 记录最小覆盖子串的起始索引及窗口字符长度（因为要返回子串）
            int start = 0, length = Integer.MAX_VALUE;

            while (right < s.length()) {
                char startChar = s.charAt(right);
                right++;
                // 比对并更新移动窗口的数据
                if (targetMap.containsKey(startChar)) {
                    window.put(startChar, window.getOrDefault(startChar, 0) + 1);
                    if (Objects.equals(window.get(startChar), targetMap.get(startChar))) {
                        valid++;
                    }
                }

                // 移动左指针：缩小窗口，寻找最小字串
                while (targetMap.size() == valid) {
                    // 更新子串长度
                    if (right - left < length) {
                        start = left;
                        length = right - left;
                    }
                    char endChar = s.charAt(left);
                    left++;
                    // 更新窗口词频
                    if (targetMap.containsKey(endChar)){
                        if (Objects.equals(window.get(endChar), targetMap.get(endChar))) {
                            valid--;
                        }
                        window.put(endChar, window.get(endChar) - 1);
                    }
                }

            }
            return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}