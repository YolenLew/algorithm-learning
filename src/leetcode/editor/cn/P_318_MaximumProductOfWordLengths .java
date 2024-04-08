//给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，并且这两个单词不含有公共字母
//。如果不存在这样的两个单词，返回 0 。
//
//
//
// 示例 1：
//
//
//输入：words = ["abcw","baz","foo","bar","xtfn","abcdef"]
//输出：16
//解释：这两个单词为 "abcw", "xtfn"。
//
// 示例 2：
//
//
//输入：words = ["a","ab","abc","d","cd","bcd","abcd"]
//输出：4
//解释：这两个单词为 "ab", "cd"。
//
// 示例 3：
//
//
//输入：words = ["a","aa","aaa","aaaa"]
//输出：0
//解释：不存在这样的两个单词。
//
//
//
//
// 提示：
//
//
// 2 <= words.length <= 1000
// 1 <= words[i].length <= 1000
// words[i] 仅包含小写字母
//
//
// Related Topics 位运算 数组 字符串 👍 521 👎 0

package leetcode.editor.cn;

//java:最大单词长度乘积
class P_318_MaximumProductOfWordLengths {
    public static void main(String[] args) {
        Solution solution = new P_318_MaximumProductOfWordLengths().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 作者：彤哥来刷题啦
        //链接：https://leetcode.cn/problems/maximum-product-of-word-lengths/solutions/1106275/tong-ge-lai-shua-ti-la-zhao-dao-ti-mu-de-y37h/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int maxProduct(String[] words) {
            int n = words.length;
            int[] nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = convert(words[i]);
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    // 对比两个单词是否包含相同的字母，只需要把它们对应的 int 数值做 & 运算，看是不是等于 0 即可，如果等于 0 ，说明没有相同的位
                    if ((nums[i] & nums[j]) == 0) {
                        ans = Math.max(ans, words[i].length() * words[j].length());
                    }
                }
            }

            return ans;
        }

        // 将单词转换为一个对应的int值
        private int convert(String word) {
            int ans = 0;
            for (int i = 0; i < word.length(); i++) {
                // 具体来说，(word.charAt(i) - 'a') 的部分是将字符串中的每个字符转换成一个数字
                // 其中 'a' 的ASCII码值被减去，以便将 'a' 转换成0，'b'转换成1，以此类推。
                // 例如，如果当前字符是 'c'，那么它会被转换成数字2。
                // 然后，1 << (word.charAt(i) - 'a') 部分是将1向左移动相应的位数，从而创建一个只有特定位为1，其余位为0的整数。
                // 例如，如果当前字符是 'c'，则生成的二进制数为000...0100，即4。
                // 最后，位或操作符 |= 将一个新的值与 ans 进行按位或操作 a |= b也就是 a= a|b;
                // ans |= ... 将这个新生成的整数与 ans 进行按位或操作，将其加入到 ans 中
                ans |= 1 << (word.charAt(i) - 'a');
            }
            return ans;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}