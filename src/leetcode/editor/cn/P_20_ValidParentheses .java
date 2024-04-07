//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
// 每个右括号都有一个对应的相同类型的左括号。
//
//
//
//
// 示例 1：
//
//
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10⁴
// s 仅由括号 '()[]{}' 组成
//
//
// Related Topics 栈 字符串 👍 4349 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//java:有效的括号
class P_20_ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P_20_ValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid00(String s) {
            // 栈：先进后出，遇到左符号就入栈，遇到右符号就去栈里栈顶匹配
            Stack<Character> stack = new Stack<>();
            for (char ch : s.toCharArray()) {
                if (ch == '{' || ch == '[' || ch == '(') {
                    // 入栈
                    stack.push(ch);
                } else {
                    // 栈顶匹配
                    if (!stack.isEmpty() && leftChar(ch) == stack.peek()) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }

        private char leftChar(char rightChar) {
            if (rightChar == '}') {
                return '{';
            } else if (rightChar == ']') {
                return '[';
            } else {
                return '(';
            }
        }

        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        public boolean isValid(String s) {
            Deque<Character> stack = new LinkedList<>();
            for (char ch : s.toCharArray()) {
                if (ch == '(' || ch == '{' || ch == '[') {
                    // 左括号入栈
                    stack.push(ch);
                } else {
                    // 右括号：匹配栈顶元素
                    if (stack.isEmpty() || !stack.peek().equals(getLeftChar(ch))) {
                        return false;
                    } else {
                        stack.pop();
                    }
                }
            }

            return stack.isEmpty();
        }

        private Character getLeftChar(char rightChar) {
            if (rightChar == '}') {
                return '{';
            } else if (rightChar == ']') {
                return '[';
            } else {
                return '(';
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}