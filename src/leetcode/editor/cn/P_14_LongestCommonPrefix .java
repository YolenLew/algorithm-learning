//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
//
//
// 示例 1：
//
//
//输入：strs = ["flower","flow","flight"]
//输出："fl"
//
//
// 示例 2：
//
//
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 200
// 0 <= strs[i].length <= 200
// strs[i] 仅由小写英文字母组成
//
//
// Related Topics 字典树 字符串 👍 3097 👎 0

package leetcode.editor.cn;

//java:最长公共前缀
class P_14_LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new P_14_LongestCommonPrefix().new Solution();
        System.out.println("abc".startsWith(""));
        System.out.println("abc".substring(0, 0));
        System.out.println("abc".substring(0, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            // 以第一个字符串的列数为基准
            // 初始公共前缀为第一个字符串
            String commonPrefix = strs[0];

            // 遍历剩余的字符串
            for (int i = 1; i < strs.length; i++) {
                // 比较当前字符串与当前最长公共前缀，更新最长公共前缀
                commonPrefix = getCommonPrefix(commonPrefix, strs[i]);
                if ("".equals(commonPrefix)) {
                    return "";
                }
            }

            return commonPrefix;
        }

        // 辅助函数，用于获取两个字符串的公共前缀
        private String getCommonPrefix(String str1, String str2) {
            int index = 0;
            while (index < str1.length() && index < str2.length() && str1.charAt(index) == str2.charAt(index)) {
                index++;
            }

            return str1.substring(0, index);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}