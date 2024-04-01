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
        int[] nums = new int[] {3, 4, -1, 1};
        System.out.println(solution.firstMissingPositive(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstMissingPositive00(int[] nums) {
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

        // 原地算法时间O(n)、空间O(1)
    /*
      使用座位交换法
      根据题目描述可知，缺失的第一个整数是 [1, len + 1] 之间，
      那么我们可以遍历数组，然后将对应的数据填充到对应的位置上去，比如 1 就填充到 nums[0] 的位置， 2 就填充到 nums[1]
      如果填充过程中， nums[i] < 1 && nums[i] > len，那么直接忽视舍弃
      填充完成，我们再遍历一次数组，如果对应的 nums[i] != i + 1，那么这个 i + 1 就是缺失的第一个正数

      比如 nums = [7, 8, 9, 10, 11], len = 5
      我们发现数组中的元素都无法进行填充，直接舍弃跳过，
      那么最终遍历数组的时候，我们发现 nums[0] != 0 + 1，即第一个缺失的是 1

      比如 nums = [3, 1, 2], len = 3
      填充过后，我们发现最终数组变成了 [1, 2, 3]，每个元素都对应了自己的位置，那么第一个缺失的就是 len + 1 == 4
*/
        public int firstMissingPositive(int[] nums) {
            int length = nums.length;
            for (int i = 0; i < length; i++) {
                // 交换元素num到对应的num-1索引位置
                while (nums[i] > 0 && nums[i] <= length && nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                    // 对应的数据填充到对应的位置上去，比如 1 就填充到 nums[0] 的位置
                    swap(nums, i, nums[i] - 1);
                }
            }

            // 再遍历一次数组，如果对应的 nums[i] != i + 1，那么这个 i + 1 就是缺失的第一个正数
            for (int i = 0; i < length; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            return length + 1;
        }

        // 对应的数据填充到对应的位置上去，比如 1 就填充到 nums[0] 的位置
        private void swap(int[] nums, int sourceIdx, int targetIdx) {
            int tmp = nums[sourceIdx];
            nums[sourceIdx] = nums[targetIdx];
            nums[targetIdx] = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}