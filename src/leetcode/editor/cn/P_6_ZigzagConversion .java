//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
//
// 请你实现这个将字符串进行指定行数变换的函数：
//
//
//string convert(string s, int numRows);
//
//
//
// 示例 1：
//
//
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
//
//
//示例 2：
//
//
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
//
//
// 示例 3：
//
//
//输入：s = "A", numRows = 1
//输出："A"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 由英文字母（小写和大写）、',' 和 '.' 组成
// 1 <= numRows <= 1000
//
//
// Related Topics 字符串 👍 2295 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//java:Z 字形变换
class P_6_ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new P_6_ZigzagConversion().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 参考： 作者：Krahets
        // 链接：https://leetcode.cn/problems/zigzag-conversion/solutions/21610/zzi-xing-bian-huan-by-jyd/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public String convert(String s, int numRows) {
            if (numRows < 2)
                return s;
            List<StringBuilder> rows = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }

            // 行下标索引
            int i = 0;
            // 行转向索引
            int flag = -1;
            for (char ch : s.toCharArray()) {
                rows.get(i).append(ch);
                if (i == 0 || i == numRows - 1) {
                    // 行转向：上 -> 下 |  下 -> 上
                    flag = -flag;
                }

                i += flag;
            }

            StringBuilder res = new StringBuilder();
            for (StringBuilder row : rows)
                res.append(row);
            return res.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}