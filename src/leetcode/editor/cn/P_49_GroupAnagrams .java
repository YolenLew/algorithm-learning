//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
//
//
//
// 示例 1:
//
//
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// 示例 2:
//
//
//输入: strs = [""]
//输出: [[""]]
//
//
// 示例 3:
//
//
//输入: strs = ["a"]
//输出: [["a"]]
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 10⁴
// 0 <= strs[i].length <= 100
// strs[i] 仅包含小写字母
//
//
// Related Topics 数组 哈希表 字符串 排序 👍 1857 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//java:字母异位词分组
class P_49_GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new P_49_GroupAnagrams().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
                char[] charArray = str.toCharArray();
                Arrays.sort(charArray);
                return new String(charArray);
            })).values());
        }

        public List<List<String>> groupAnagrams01(String[] strs) {

            // 创建一个 HashMap 用于存储异位词分组的结果，键为排序后的字符串，值为对应分组的字符串列表
            Map<String, List<String>> map = new HashMap<>();

            // 遍历输入的字符串数组
            for (String str : strs) {
                // 对当前字符串进行排序，作为 HashMap 的键
                String sortedStr = sortString(str);

                // 如果 HashMap 中不存在以 sortedStr 为键的列表，则创建一个新列表并放入 HashMap 中
                if (!map.containsKey(sortedStr)) {
                    map.put(sortedStr, new ArrayList<>());
                }

                // 将当前字符串添加到对应的列表中
                map.get(sortedStr).add(str);
            }

            // 将 HashMap 的值（即所有异位词分组）转换为 List 并返回
            return new ArrayList<>(map.values());
        }

        // 对字符串进行排序的辅助方法
        public String sortString(String str) {
            char[] charArray = str.toCharArray();
            // 对字符数组进行排序
            Arrays.sort(charArray);
            // 将排序后的字符数组转换为字符串并返回
            return new String(charArray);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}