//统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
//
// 请注意，你可以假定字符串里不包括任何不可打印的字符。
//
// 示例:
//
// 输入: "Hello, my name is John"
//输出: 5
//解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
//
//
// Related Topics 字符串 👍 223 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//java:字符串中的单词数
class P_434_NumberOfSegmentsInAString {
    public static void main(String[] args) {
        Solution solution = new P_434_NumberOfSegmentsInAString().new Solution();
        String str = "The one-hour drama series Westworld is a dark odyssey about the dawn of artificial "
            + "consciousness and the evolution of sin. Set at the intersection of the near future and the reimagined past, it explores a world in which every human appetite, no matter how noble or depraved, can be indulged.";

        System.out.println(solution.countSegments(str));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countSegments(String s) {
            String[] split = s.split(" ");
            List<String> result = Arrays.stream(split).filter(e -> !e.isEmpty()).collect(Collectors.toList());
            return result.size();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}