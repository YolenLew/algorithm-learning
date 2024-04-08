//给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最小操作数。
//
// 在一次操作中，你可以使数组中的一个元素加 1 或者减 1 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：2
//解释：
//只需要两次操作（每次操作指南使一个元素加 1 或减 1）：
//[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
//
//
// 示例 2：
//
//
//输入：nums = [1,10,2,9]
//输出：16
//
//
//
//
// 提示：
//
//
// n == nums.length
// 1 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
//
//
// Related Topics 数组 数学 排序 👍 308 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:最小操作次数使数组元素相等 II
class P_462_MinimumMovesToEqualArrayElementsIi {
    public static void main(String[] args) {
        Solution solution = new P_462_MinimumMovesToEqualArrayElementsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minMoves2(int[] nums) {
            // 贪心 + 排序
            // 选择nums的中位数作为移动后的目标，可以使得移动数量最少
            Arrays.sort(nums);
            int n = nums.length, target = nums[(n - 1) / 2];
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans += Math.abs(nums[i] - target);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}