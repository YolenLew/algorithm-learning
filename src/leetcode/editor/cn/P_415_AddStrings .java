//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
//
// 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
//
//
//
// 示例 1：
//
//
//输入：num1 = "11", num2 = "123"
//输出："134"
//
//
// 示例 2：
//
//
//输入：num1 = "456", num2 = "77"
//输出："533"
//
//
// 示例 3：
//
//
//输入：num1 = "0", num2 = "0"
//输出："0"
//
//
//
//
//
//
// 提示：
//
//
// 1 <= num1.length, num2.length <= 10⁴
// num1 和num2 都只包含数字 0-9
// num1 和num2 都不包含任何前导零
//
//
// Related Topics 数学 字符串 模拟 👍 823 👎 0

package leetcode.editor.cn;

//java:字符串相加
class P_415_AddStrings {
    public static void main(String[] args) {
        Solution solution = new P_415_AddStrings().new Solution();
        String sum = solution.addStrings("11", "123");
        System.out.println(sum);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String addStrings(String num1, String num2) {
            StringBuilder sb = new StringBuilder();

            // 双指针解法： 设定 i，j 两指针分别指向 num1，num2 尾部，模拟人工加法
            int i = num1.length() - 1;
            int j = num2.length() - 1;
            int carry = 0;

            while (i >= 0 || j >= 0) {
                int x = i >= 0 ? num1.charAt(i) - '0' : 0;
                int y = j >= 0 ? num2.charAt(j) - '0' : 0;
                int sum = x + y + carry;
                carry = sum / 10;
                sb.append(sum % 10);
                i--;
                j--;
            }

            if (carry > 0) {
                sb.append(carry);
            }

            return sb.reverse().toString();
        }

        public String addStrings90(String num1, String num2) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder(num1).reverse();
            StringBuilder sb2 = new StringBuilder(num2).reverse();
            int n1 = num1.length();
            int n2 = num2.length();

            int carry = 0;
            int index = 0;
            int x = 0;
            int y = 0;
            int sum = 0;
            while (index < n1 || index < n2) {
                if (index >= n1) {
                    x = 0;
                    y = Character.getNumericValue(sb2.charAt(index));
                } else if (index >= n2) {
                    x = Character.getNumericValue(sb1.charAt(index));
                    y = 0;
                } else {
                    x = Character.getNumericValue(sb1.charAt(index));
                    y = Character.getNumericValue(sb2.charAt(index));
                }
                sum = (x + y + carry) % 10;
                carry = (x + y + carry) / 10;

                sb.append(sum);
                index++;
            }

            if (carry > 0) {
                sb.append(carry);
            }

            return sb.reverse().toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}