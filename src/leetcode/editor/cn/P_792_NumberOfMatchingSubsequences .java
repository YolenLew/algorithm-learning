//给定字符串 s 和字符串数组 words, 返回 words[i] 中是s的子序列的单词个数 。
//
// 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
//
//
// 例如， “ace” 是 “abcde” 的子序列。
//
//
//
//
// 示例 1:
//
//
//输入: s = "abcde", words = ["a","bb","acd","ace"]
//输出: 3
//解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
//
//
// Example 2:
//
//
//输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
//输出: 2
//
//
//
//
// 提示:
//
//
// 1 <= s.length <= 5 * 10⁴
// 1 <= words.length <= 5000
// 1 <= words[i].length <= 50
// words[i]和 s 都只由小写字母组成。
//
//
//
// Related Topics 字典树 数组 哈希表 字符串 二分查找 动态规划 排序 👍 403 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//java:匹配子序列的单词数
class P_792_NumberOfMatchingSubsequences {
    public static void main(String[] args) {
        Solution solution = new P_792_NumberOfMatchingSubsequences().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 参考作者：宫水三叶
        // 链接：https://leetcode.cn/problems/number-of-matching-subsequences/solutions/1975585/by-ac_oier-u1ox/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int numMatchingSubseq(String s, String[] words) {
            int n = s.length(), ans = 0;
            // 统计每个字符在s中的索引
            // 避免每个 word[i], 都去遍历一次原串， 单次遍历记下 单前字符在数组中出现的各个位置
            // 用于存储字符串s，每个字符出现的下表位置数组，数组单调递增 e.g. {"a", [1,3,5]}
            Map<Character, List<Integer>> char2IdxMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                List<Integer> list = char2IdxMap.getOrDefault(s.charAt(i), new ArrayList<>());
                list.add(i);
                char2IdxMap.put(s.charAt(i), list);
            }

            for (String word : words) {
                boolean match = true;
                // 用于存储当前匹配字符串在 原字符串的位置，应该是单调递增的，否则不符合题意
                int currIndex = -1;
                // 比对当前word中的每个字符
                for (int i = 0; i < word.length() && match; i++) {
                    char c = word.charAt(i);
                    List<Integer> indexList = char2IdxMap.getOrDefault(c, new ArrayList<>());
                    // 二分法找出比当前 currIndex 大的下一个下标, 找不到在后续判断
                    int l = 0, r = indexList.size() - 1;
                    // 注意：循环结束时，l==r，此时再判断indexList.get(r)的索引值是否符合 大于 currIndex，如果大于说明合法
                    while (l < r) {
                        int mid = (l + r) >> 1;
                        if (indexList.get(mid) > currIndex) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }

                    // 如果当前字符在原串不存在 || 当前下标满足递增要求，e.g.  当前匹配 "abc", a在原串index = 4, b在原串的index = 2 ，不符合题意
                    if (r < 0 || indexList.get(r) <= currIndex) {
                        match = false;
                    } else {
                        // 匹配正确，下标递增
                        currIndex = indexList.get(r);
                    }
                }
                if (match) {
                    ans++;
                }

            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}