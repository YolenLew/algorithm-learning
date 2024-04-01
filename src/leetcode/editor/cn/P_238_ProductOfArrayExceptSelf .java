//给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
//
// 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
//
// 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
//
//
//
// 示例 1:
//
//
//输入: nums = [1,2,3,4]
//输出: [24,12,8,6]
//
//
// 示例 2:
//
//
//输入: nums = [-1,1,0,-3,3]
//输出: [0,0,9,0,0]
//
//
//
//
// 提示：
//
//
// 2 <= nums.length <= 10⁵
// -30 <= nums[i] <= 30
// 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内
//
//
//
//
// 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
//
// Related Topics 数组 前缀和 👍 1755 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.stream.IntStream;

//java:除自身以外数组的乘积
class P_238_ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        Solution solution = new P_238_ProductOfArrayExceptSelf().new Solution();
        int[] nums = IntStream.rangeClosed(1, 4).toArray();
        System.out.println(Arrays.toString(solution.productExceptSelf(nums)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 前缀和解法：不能用除法，那么就是左边的积 乘以 右边的积
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];
            int[] leftProduct = new int[n];
            int[] rightProduct = new int[n];
            leftProduct[0] = 1;
            rightProduct[n - 1] = 1;
            // 求左边的积
            for (int i = 1; i < n; i++) {
                leftProduct[i] = nums[i - 1] * leftProduct[i - 1];
            }
            // 求右边的积
            for (int i = n - 2; i >= 0; i--) {
                rightProduct[i] = nums[i + 1] * rightProduct[i + 1];
            }
            for (int i = 0; i < n; i++) {
                result[i] = leftProduct[i] * rightProduct[i];
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}