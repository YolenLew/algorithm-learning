//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
//
// Related Topics 字符串 动态规划 回溯 👍 3519 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//java:括号生成
class P_22_GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new P_22_GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有的结果集合
        List<String> result = new ArrayList<>();
        // 满足条件的单次结果
        LinkedList<Character> stack = new LinkedList<>();

        public List<String> generateParenthesis(int n) {
            // 可用的左括号和右括号数量初始化为 n
            backtrack(n, n);
            return result;
        }

        // 可用的左括号数量为 left 个，可用的右括号数量为 right 个
        private void backtrack(int left, int right) {
            // 可用的左括号数量大，说明本次组合不合法
            if (left > right) {
                return;
            }
            // 数量小于 0 肯定是不合法的
            if (left < 0 || right < 0) {
                return;
            }
            // 当所有括号都恰好用完时，得到一个合法的括号组合
            if (left == 0 && right == 0) {
                result.add(appendAsString(stack));
                return;
            }

            // 遍历选择列表进行选择：本案例列表只有两种选择左括号和右括号
            // 必须保证先选左括号，然后再选右括号
            stack.addLast('(');
            // 递归下一层回溯树
            backtrack(left - 1, right);
            // 撤销选择
            stack.removeLast();

            stack.addLast(')');
            // 递归下一层回溯树
            backtrack(left, right - 1);
            // 撤销选择
            stack.removeLast();
        }

        private String appendAsString(LinkedList<Character> stack) {
//            return stack.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
            StringBuilder sb = new StringBuilder(stack.size());
            for (Character ch : stack) {
                sb.append(ch);
            }
            return sb.toString();
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}