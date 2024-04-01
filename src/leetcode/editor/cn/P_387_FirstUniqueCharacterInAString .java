//给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
//
//
//
// 示例 1：
//
//
//输入: s = "leetcode"
//输出: 0
//
//
// 示例 2:
//
//
//输入: s = "loveleetcode"
//输出: 2
//
//
// 示例 3:
//
//
//输入: s = "aabb"
//输出: -1
//
//
//
//
// 提示:
//
//
// 1 <= s.length <= 10⁵
// s 只包含小写字母
//
//
// Related Topics 队列 哈希表 字符串 计数 👍 728 👎 0

package leetcode.editor.cn;

import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//java:字符串中的第一个唯一字符
class P_387_FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        Solution solution = new P_387_FirstUniqueCharacterInAString().new Solution();
        // 2
        int loveleetcode = solution.firstUniqChar("loveleetcode");
        System.out.println(loveleetcode);
        loveleetcode = solution.firstUniqChar("aadadaad");
        // -1
        System.out.println(loveleetcode);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstUniqCharXXXXXX(String s) {
            Map<Character, Map.Entry<Integer, Integer>> cache = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (cache.containsKey(ch)) {
                    Entry<Integer, Integer> entry = cache.get(ch);
                    entry.setValue(entry.getValue() + 1);
                } else {
                    SimpleEntry<Integer, Integer> entry = new SimpleEntry<>(i, 1);
                    cache.put(ch, entry);
                }
            }

            // 遍历
            return cache.entrySet().stream().filter(e -> e.getValue().getValue() == 1)
                .min(Comparator.comparing(e -> e.getValue().getKey())).map(e -> e.getValue().getKey()).orElse(-1);
        }

        // 哈希解法
        public int firstUniqChar00(String s) {
            Map<Character, Integer> frequency = new HashMap<Character, Integer>();
            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
            }
            for (int i = 0; i < s.length(); ++i) {
                if (frequency.get(s.charAt(i)) == 1) {
                    return i;
                }
            }
            return -1;
        }

        // 数组解法
        public int firstUniqChar(String s) {
            int[] chars = new int[26];
            for (int i = 0; i < s.length(); i++) {
                chars[s.charAt(i) - 'a'] += 1;
            }

            for (int i = 0; i < s.length(); i++) {
                if (chars[s.charAt(i) - 'a'] == 1) {
                    return i;
                }
            }

            return -1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}