//给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符
//合要求的单词则返回空字符串。
//
// 示例：
//
// 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
//输出： "dogwalker"
//解释： "dogwalker"可由"dog"和"walker"组成。
//
//
// 提示：
//
//
// 0 <= len(words) <= 200
// 1 <= len(words[i]) <= 100
//
//
// Related Topics 字典树 数组 哈希表 字符串 👍 56 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//java:最长单词
class P_Interview_1715_LongestWordLcci {
    public static void main(String[] args) {
        Solution solution = new P_Interview_1715_LongestWordLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestWord(String[] words) {
            // 哈希+回溯
            List<String> wordsList = new ArrayList<>(Arrays.asList(words));
            // 排序：长度降序、长度相等按字典升序
            wordsList.sort((a, b) -> a.length() != b.length() ? b.length() - a.length() : a.compareTo(b));

            // 遍历求解
            for (String word : wordsList) {
                if (dfs(word, 0, wordsList)) {
                    return word;
                }
            }

            return "";
        }

        // 回溯：从左到右裁剪target目标字符串为两段，与缓存池list匹配
        private boolean dfs(String target, int start, List<String> wordsList) {
            if (target.length() == start) {
                return true;
            }

            // 裁剪[start, i]范围内的字符串然后将[start, i]、[i+1, len -1]两段字符去list匹配
            for (int i = start; i < target.length(); i++) {
                // 排除目标单词target本身，题意要求由其他的至少两个单词组成
                if (i - start + 1 == target.length()) {
                    continue;
                }

                // 切出来[start,end]之间的字符作为一个候选单词进入下一轮递归
                String prefix = target.substring(start, i + 1);
                if (wordsList.contains(prefix) && dfs(target, i + 1, wordsList)) {
                    return true;
                }
            }

            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}