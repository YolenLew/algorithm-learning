//给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
//
// 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
//
// 返回一个表示每个字符串片段的长度的列表。
//
//
//示例 1：
//
//
//输入：s = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
//
// 示例 2：
//
//
//输入：s = "eccbbbbdec"
//输出：[10]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 500
// s 仅由小写英文字母组成
//
//
// Related Topics 贪心 哈希表 双指针 字符串 👍 1103 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//java:划分字母区间
class P_763_PartitionLabels {
    public static void main(String[] args) {
        Solution solution = new P_763_PartitionLabels().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> partitionLabels(String s) {
            // 同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。因此需要遍历字符串，得到每个字母最后一次出现的下标位置
            Map<Character, Integer> lastMap = new HashMap<>();
            for (int i = 0; i < s.toCharArray().length; i++) {
                lastMap.put(s.charAt(i), i);
            }
            List<Integer> partition = new ArrayList<>();
            int start = 0, end = 0;
            for (int i = 0; i < s.toCharArray().length; i++) {
                end = Math.max(lastMap.get(s.charAt(i)), end);
                if (end == i) {
                    // 达到一个分区的最右边界，便可切分
                    partition.add(end - start + 1);
                    start = end + 1;
                }
            }
            return partition;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}