//给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
//
// 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
//
//
//
// 示例 1：
//
//
//输入：s = "hello"
//输出："holle"
//
//
// 示例 2：
//
//
//输入：s = "leetcode"
//输出："leotcede"
//
//
//
// 提示：
//
//
// 1 <= s.length <= 3 * 10⁵
// s 由 可打印的 ASCII 字符组成
//
//
// Related Topics 双指针 字符串 👍 344 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//java:反转字符串中的元音字母
class P_345_ReverseVowelsOfAString {
    public static void main(String[] args) {
        Solution solution = new P_345_ReverseVowelsOfAString().new Solution();
        System.out.println(solution.reverseVowels("hello"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private Set<Character> vowelSet =
            new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        public String reverseVowels(String s) {
            // 双指针遍历：一个指针从头向尾遍历，一个指针从尾到头遍历
            int left = 0;
            int right = s.length() - 1;
            // 一边遍历一边存储
            char[] result = new char[s.length()];
            while (left <= right) {
                char cl = s.charAt(left);
                char cr = s.charAt(right);
                if (!vowelSet.contains(cl)) {
                    result[left] = cl;
                    left++;
                } else if (!vowelSet.contains(cr)) {
                    result[right] = cr;
                    right--;
                } else {
                    result[left] = cr;
                    result[right] = cl;
                    left++;
                    right--;
                }
            }

            return new String(result);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}