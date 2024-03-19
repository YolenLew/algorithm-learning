//给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
//
//
// 示例 1：
//
//
//输入：nums = [3,2,3]
//输出：3
//
// 示例 2：
//
//
//输入：nums = [2,2,1,1,1,2,2]
//输出：2
//
//
//
//提示：
//
//
// n == nums.length
// 1 <= n <= 5 * 10⁴
// -10⁹ <= nums[i] <= 10⁹
//
//
//
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
//
// Related Topics 数组 哈希表 分治 计数 排序 👍 2155 👎 0

package leetcode.editor.cn;

//java:多数元素
class P_169_MajorityElement {
    public static void main(String[] args) {
        Solution solution = new P_169_MajorityElement().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            // 题目告诉你一定存在一个众数，它出现的次数过半，那么如果你把这个众数元素想象成正电粒子，其他的所有元素都想象成负电粒子，那么它们混合起来会怎样？
            // 在正负粒子混合的过程中，整体的带电性可能在正负间波动，但最终的结果一定是正电
            // 目标众数值
            int target = 0;
            // 目标众数值的次数：碰到自己就累加，不是就减1，类比正负中和
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (count == 0) {
                    // 当计数器为 0 时，假设 nums[i] 就是众数
                    target = nums[i];
                    count = 1;
                } else if (target == nums[i]) {
                    // 如果遇到的是目标众数，计数器累加
                    count++;
                } else {
                    count--;
                }
            }

            return target;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}