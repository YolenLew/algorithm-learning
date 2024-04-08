//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
// 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
//
//
//
// 示例 1:
//
//
//输入: num1 = "2", num2 = "3"
//输出: "6"
//
// 示例 2:
//
//
//输入: num1 = "123", num2 = "456"
//输出: "56088"
//
//
//
// 提示：
//
//
// 1 <= num1.length, num2.length <= 200
// num1 和 num2 只能由数字组成。
// num1 和 num2 都不包含任何前导零，除了数字0本身。
//
//
// Related Topics 数学 字符串 模拟 👍 1330 👎 0

package leetcode.editor.cn;

//java:字符串相乘
class P_43_MultiplyStrings {
    public static void main(String[] args) {
        Solution solution = new P_43_MultiplyStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String multiply(String num1, String num2) {
            // 竖式乘法：每一位单独乘法的结果累加
            int n1 = num1.length();
            int n2 = num2.length();
            // 结果数组: 最多为 m + n 位数
            int[] ans = new int[n1 + n2];
            // 从个位数开始逐位相乘
            for (int i = n1 - 1; i >= 0; i--) {
                for (int j = n2 - 1; j >= 0; j--) {
                    int mutiply = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    // 乘积在 res 对应的索引位置.注意：p2也就是更往后一位的低位，只和当前计算结果有关
                    int p1 = i + j;
                    int p2 = i + j + 1;
                    // 结果叠加
                    int sum = mutiply + ans[p2];
                    ans[p1] += sum / 10;
                    ans[p2] = sum % 10;
                }
            }

            // 跳过高位前缀的0
            int i = 0;
            while (i < ans.length && ans[i] == 0) {
                i++;
            }

            // 将计算结果转化成字符串
            StringBuilder sb = new StringBuilder();

            for (; i < ans.length; i++) {
                sb.append(ans[i]);
            }

            return sb.toString().isEmpty() ? "0" : sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}