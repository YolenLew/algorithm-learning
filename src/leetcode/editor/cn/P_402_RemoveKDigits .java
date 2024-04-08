//给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
//
// 示例 1 ：
//
//
//输入：num = "1432219", k = 3
//输出："1219"
//解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
//
//
// 示例 2 ：
//
//
//输入：num = "10200", k = 1
//输出："200"
//解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
//
//
// 示例 3 ：
//
//
//输入：num = "10", k = 2
//输出："0"
//解释：从原数字移除所有的数字，剩余为空就是 0 。
//
//
//
//
// 提示：
//
//
// 1 <= k <= num.length <= 10⁵
// num 仅由若干位数字（0 - 9）组成
// 除了 0 本身之外，num 不含任何前导零
//
//
// Related Topics 栈 贪心 字符串 单调栈 👍 1033 👎 0

package leetcode.editor.cn;

//java:移掉 K 位数字
class P_402_RemoveKDigits {
    public static void main(String[] args) {
        Solution solution = new P_402_RemoveKDigits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 参考：https://leetcode.cn/problems/remove-k-digits/solutions/290203/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-5

        public String removeKdigits(String num, int k) {
            if (num.length() == k) {
                return "0";
            }
            // 需要保留和丢弃相邻的元素，因此使用栈这种在一端进行添加和删除的数据结构是再合适不过了
            StringBuilder stack = new StringBuilder();
            int remain = num.length() - k;
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                // 当前元素比栈顶元素小，说明栈顶元素要弹栈，当前元素视k值剩余大小情况压栈
                while (k > 0 && stack.length() != 0 && stack.charAt(stack.length() - 1) > c) {
                    // 删除栈顶元素
                    stack.setLength(stack.length() - 1);
                    k--;
                }

                // 前导0值处理
                if (c == '0' && stack.length() == 0) {
                    continue;
                }
                stack.append(c);
            }
            String result = stack.substring(0, stack.length() - k < 1 ? 0 : stack.length() - k);
            return result.length() == 0 ? "0" : result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}