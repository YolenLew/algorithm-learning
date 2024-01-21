//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 请你实现时间复杂度为
//O(n) 并且只使用常数级别额外空间的解决方案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,0]
//输出：3
//
//
// 示例 2：
//
//
//输入：nums = [3,4,-1,1]
//输出：2
//
//
// 示例 3：
//
//
//输入：nums = [7,8,9,11,12]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5 * 10⁵
// -2³¹ <= nums[i] <= 2³¹ - 1
//
//
// Related Topics 数组 哈希表 👍 2036 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:缺失的第一个正数
class P_41_FirstMissingPositive {
    public static void main(String[] args) {
        Solution solution = new P_41_FirstMissingPositive().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int tmp = 1;
            Map<Integer, Integer> numMap = new HashMap<>();
            for (int num : nums) {
                if (num > 0 && tmp >= num && numMap.getOrDefault(num, 0).equals(0)) {
                    tmp++;
                }
                numMap.put(num, numMap.getOrDefault(num, 0) + 1);
            }
            while (numMap.getOrDefault(tmp, 0) != 0) {
                tmp++;
            }
            return tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}