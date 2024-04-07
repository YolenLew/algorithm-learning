//给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
//
// 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
//
// 在执行完所有删除操作后，返回最终得到的字符串。
//
// 本题答案保证唯一。
//
//
//
// 示例 1：
//
// 输入：s = "abcd", k = 2
//输出："abcd"
//解释：没有要删除的内容。
//
// 示例 2：
//
// 输入：s = "deeedbbcccbdaa", k = 3
//输出："aa"
//解释：
//先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
//再删除 "bbb"，得到 "dddaa"
//最后删除 "ddd"，得到 "aa"
//
// 示例 3：
//
// 输入：s = "pbbcggttciiippooaais", k = 2
//输出："ps"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10^5
// 2 <= k <= 10^4
// s 中只含有小写英文字母。
//
//
// Related Topics 栈 字符串 👍 195 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//java:删除字符串中的所有相邻重复项 II
class P_1209_RemoveAllAdjacentDuplicatesInStringIi {
    public static void main(String[] args) {
        Solution solution = new P_1209_RemoveAllAdjacentDuplicatesInStringIi().new Solution();
        System.out.println(solution.removeDuplicates("deeedbbcccbdaa", 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicates(String s, int k) {
            // 栈解法：记录字符个数
            Deque<Integer> stack = new LinkedList<>();
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < sb.length(); i++) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    stack.push(1);
                } else {
                    int count = stack.pop() + 1;
                    if (count == k) {
                        sb.delete(i - k + 1, i + 1);
                        i = i - k;
                    } else {
                        stack.push(count);
                    }
                }
            }

            return sb.toString();
        }

        public String removeDuplicates90(String s, int k) {
            // 为每个字符设置计数器，达到k时就删除
            int[] count = new int[s.length()];
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < sb.length(); i++) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count[i] = 1;
                } else {
                    count[i] = count[i - 1] + 1;
                    if (count[i] == k) {
                        // 删除 [i-k+1,i]范围内的重复字符
                        sb.delete(i - k + 1, i + 1);
                        // 重置计算器
                        i = i - k;
                    }
                }
            }

            return sb.toString();
        }

        // 模拟栈：超时
        public String removeDuplicates100(String s, int k) {
            StringBuilder stack = new StringBuilder();
            int n = s.length();
            int i = 0;
            while (i < n) {
                if (stack.length() >= k - 1) {
                    String temp = stack.substring(stack.length() - k + 1);
                    if (isUniform(temp) && temp.charAt(0) == s.charAt(i)) {
                        stack.delete(stack.length() - k + 1, stack.length());
                        i++;
                        continue;
                    }
                }
                stack.append(s.charAt(i));
                i++;
            }
            return stack.toString();
        }

        // 辅助函数，用于检查字符串中的所有字符是否相同
        private boolean isUniform(String str) {
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) != str.charAt(0)) {
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}