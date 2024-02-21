//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
//
// Related Topics 哈希表 字符串 回溯 👍 2758 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

//java:电话号码的字母组合
class P_17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new P_17_LetterCombinationsOfAPhoneNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有结果的集合
        List<String> result = new LinkedList<>();
        // 数字与字母之间的映射
        String[] mapping = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.isEmpty()) {
                return result;
            }
            backtrack(digits, 0, new StringBuilder());
            return result;
        }

        private void backtrack(String digits, int start, StringBuilder track) {
            // 判断是否满足结果条件
            if (digits.length() == track.length()) {
                result.add(track.toString());
                return;
            }
            // 根据数字获取对应的字母列表
            char numChar = digits.charAt(start);
            // map_string的下表是从0开始一直到9， c-'0'就可以取到相对的数组下标位置
            // 比如c=2时候，2-'0'，获取下标为2,letter_map[2]就是"abc"
            int pos = numChar - '0';
            String mapString = mapping[pos];
            // 遍历选择列表进行选择
            for (char letter : mapString.toCharArray()) {
                // 添加路径
                track.append(letter);
                // 递归进行选择
                backtrack(digits, start + 1, track);
                // 撤销选择
                track.deleteCharAt(track.length() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}