//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
//
//
//
// 示例 1：
//
//
//输入：nums = [10,2]
//输出："210"
//
// 示例 2：
//
//
//输入：nums = [3,30,34,5,9]
//输出："9534330"
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 10⁹
//
//
// Related Topics 贪心 数组 字符串 排序 👍 1255 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:最大数
class P_179_LargestNumber {
    public static void main(String[] args) {
        Solution solution = new P_179_LargestNumber().new Solution();
        System.out.println("10".compareTo("2"));
        System.out.println("10".compareTo("20"));
        System.out.println("30".compareTo("300"));
        System.out.println("30".compareTo("34"));
        System.out.println("0".compareTo("1"));
        System.out.println("0".compareTo("10"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 贪心解法
        // 对于 nums 中的任意两个值 a 和 b，我们无法直接从常规角度上确定其大小/先后关系。
        // 但我们可以根据「结果」来决定 a 和 b 的排序关系：
        // 如果拼接结果 ab 要比 ba 好，那么我们会认为 a 应该放在 b 前面。
        // 另外，注意我们需要处理前导零（最多保留一位）。
        //
        //作者：宫水三叶
        //链接：https://leetcode.cn/problems/largest-number/solutions/716725/gong-shui-san-xie-noxiang-xin-ke-xue-xi-vn86e/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public String largestNumber(int[] nums) {
            int n = nums.length;
            String[] strings = new String[n];
            for (int i = 0; i < nums.length; i++) {
                strings[i] = String.valueOf(nums[i]);
            }

            // 按拼接结果降序排列
            Arrays.sort(strings, (a, b) -> (b + a).compareTo(a + b));

            StringBuilder sb = new StringBuilder();
            for (String str : strings) {
                sb.append(str);
            }

            int k = 0;
            while (k < sb.length()-1 && sb.charAt(k) == '0') {
                k++;
            }
            return sb.substring(k);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}