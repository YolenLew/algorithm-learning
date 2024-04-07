//给定一个包含大写字母和小写字母的字符串
// s ，返回 通过这些字母构造成的 最长的回文串 。
//
// 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
//
//
//
// 示例 1:
//
//
//输入:s = "abccccdd"
//输出:7
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
//
//
// 示例 2:
//
//
//输入:s = "a"
//输出:1
//
//
// 示例 3：
//
//
//输入:s = "aaaaaccc"
//输出:7
//
//
//
// 提示:
//
//
// 1 <= s.length <= 2000
// s 只由小写 和/或 大写英文字母组成
//
//
// Related Topics 贪心 哈希表 字符串 👍 591 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:最长回文串
class P_409_LongestPalindrome {
    public static void main(String[] args) {
        Solution solution = new P_409_LongestPalindrome().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 「回文串」是指倒序后和自身完全相同的字符串，即具有关于中心轴对称的性质。观察发现，
        //
        //当回文串长度为偶数时，则所有字符都出现了偶数次；
        //当回文串长度为奇数时，则位于中心的字符出现了奇数次，其余所有字符出现偶数次；
        //
        //作者：Krahets
        //链接：https://leetcode.cn/problems/longest-palindrome/solutions/1693273/409-zui-chang-hui-wen-chuan-by-jyd-ne80/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int longestPalindrome(String s) {
            // 统计各字符数量
            HashMap<Character, Integer> counter = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                counter.merge(s.charAt(i), 1, Integer::sum);
            }

            // 组装回文串
            int res = 0;
            int odd = 0;
            for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
                Integer count = entry.getValue();
                int rem = count % 2;
                res += count - rem;
                if (rem != 0) {
                    odd = 1;
                }
            }

            return res + odd;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}