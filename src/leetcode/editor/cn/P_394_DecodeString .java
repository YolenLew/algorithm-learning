//给定一个经过编码的字符串，返回它解码后的字符串。
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
//
//
//
// 示例 1：
//
//
//输入：s = "3[a]2[bc]"
//输出："aaabcbc"
//
//
// 示例 2：
//
//
//输入：s = "3[a2[c]]"
//输出："accaccacc"
//
//
// 示例 3：
//
//
//输入：s = "2[abc]3[cd]ef"
//输出："abcabccdcdcdef"
//
//
// 示例 4：
//
//
//输入：s = "abc3[cd]xyz"
//输出："abccdcdcdxyz"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 30
//
// s 由小写英文字母、数字和方括号
// '[]' 组成
// s 保证是一个 有效 的输入。
// s 中所有整数的取值范围为
// [1, 300]
//
//
// Related Topics 栈 递归 字符串 👍 1725 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//java:字符串解码
class P_394_DecodeString {
    public static void main(String[] args) {
        Solution solution = new P_394_DecodeString().new Solution();
        char ch = '9';
        System.out.println(Character.getNumericValue(ch));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String decodeString(String s) {
            // 倍数栈
            Deque<Integer> numStack = new LinkedList<>();
            // 倍数记录器：记录重复倍数
            int k = 0;
            // 字母栈
            Deque<StringBuilder> letterStack = new LinkedList<>();
            StringBuilder result = new StringBuilder();
            for (char ch : s.toCharArray()) {
                // 数字入栈
                if (ch >= '0' && ch <= '9') {
                    k = ch - '0' + k * 10;
                } else if (Character.isLetter(ch)) {
                    // 普通字母类字符，直接拼接到结果字符串
                    result.append(ch);
                } else if (ch == '[') {
                    // 如果是左括号：将当前结果和倍数入栈，便于后续处理括号内的字符串
                    letterStack.push(result);
                    result = new StringBuilder();
                    // 倍数入栈
                    numStack.push(k);
                    k = 0;
                } else if (ch == ']') {
                    // 如果是右括号: 重复拼接当前结果字符串result，并将栈内结果弹栈
                    Integer count = numStack.pop();
                    StringBuilder temp = new StringBuilder();
                    for (int i = 0; i < count; i++) {
                        temp.append(result);
                    }

                    StringBuilder strCache = letterStack.pop();
                    strCache.append(temp);
                    result = strCache;
                }
            }

            return result.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}