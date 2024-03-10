//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
//
//
//
// 示例 1：
//
//
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
//
//
// 示例 2：
//
//
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
//
//
// 示例 3：
//
//
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 300
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 20
// s 和 wordDict[i] 仅由小写英文字母组成
// wordDict 中的所有字符串 互不相同
//
//
// Related Topics 字典树 记忆化搜索 数组 哈希表 字符串 动态规划 👍 2431 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//java:单词拆分
class P_139_WordBreak {
    public static void main(String[] args) {
        Solution solution = new P_139_WordBreak().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 成员变量：便于快速匹配单词表
        HashSet<String> wordSet;
        // 备忘录，-1 代表未计算，0 代表无法凑出，1 代表可以凑出
        int[] memo;

        public boolean wordBreak(String s, List<String> wordDict) {
            // 子问题：f(k)，字符串为 k 能使用字典中的单词拼接而成
            // 递推关系/状态转移方程：f(k) = f(k - word) + word | word in wordDict
            // 计算顺序：自上向下的循环方法
            this.wordSet = new HashSet<>(wordDict);
            // 初始化备忘录
            memo = new int[s.length()];
            // 备忘录，-1 代表未计算，0 代表无法凑出，1 代表可以凑出
            Arrays.fill(memo, -1);
            return dp(s, 0);
        }

        // 求解 子问题f(k) 也就是 s[start...] 能否被拼出来
        private boolean dp(String s, int start) {
            if (start == s.length()) {
                //  整个 s 都被匹配完成，找到一个合法答案
                return true;
            }
            // 剪枝：从备忘录取出已计算的结果
            if (memo[start] != -1) {
                return memo[start] == 1;
            }

            // 遍历选择列表
            int length = s.length();
            for (int i = 1; start + i <= length; i++) {
                String prefix = s.substring(start, i + start);
                if (wordSet.contains(prefix)) {
                    // 继续递归遍历 f(s - prefix)
                    boolean dp = dp(s, i + start);
                    if (dp) {
                        // 子问题s[start...]有解
                        memo[start] = 1;
                        return true;
                    }
                }
            }

            // 所有单词都尝试了仍未结束，说明此子问题s[start...] 无解
            memo[start] = 0;
            return false;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SolutionBackTrack {
        // 回溯算法计算
        // 成员变量，字符串列表
        List<String> wordDict;
        // 记录是否找到一个合法的答案
        boolean found = false;
        // 记录回溯算法的路径
        LinkedList<String> track = new LinkedList<>();

        public boolean wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            backtrack(s, 0);
            return found;
        }

        // 回溯算法：元素无重可复选
        private void backtrack(String s, int start) {
            // base case：找到答案，无需继续寻找
            if (found) {
                return;
            }

            // 终结条件
            if (start == s.length()) {
                // 整个 s 都被匹配完成，找到一个合法答案
                found = true;
                return;
            }

            // 遍历选择列表
            for (String word : wordDict) {
                // 选择单词：看看哪个单词能够匹配 s[i..] 的前缀
                int length = word.length();
                // 判断选择条件：前缀与选择元素匹配
                if (start + length <= s.length() && s.substring(start, start + length).equals(word)) {
                    // 找到一个单词匹配 s[i..i+len)
                    // 做选择
                    track.addLast(word);
                    // 进入回溯树的下一层，继续匹配 s[i+len..]
                    backtrack(s, start + length);
                    // 撤销选择
                    track.removeLast();
                }
            }

        }
    }
}