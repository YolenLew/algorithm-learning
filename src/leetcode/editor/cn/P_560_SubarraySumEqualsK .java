//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
//
// 子数组是数组中元素的连续非空序列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1], k = 2
//输出：2
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3], k = 3
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2 * 10⁴
// -1000 <= nums[i] <= 1000
// -10⁷ <= k <= 10⁷
//
//
// Related Topics 数组 哈希表 前缀和 👍 2214 👎 0

package leetcode.editor.cn;

//java:和为 K 的子数组
class P_560_SubarraySumEqualsK {
    public static void main(String[] args){
        Solution solution = new P_560_SubarraySumEqualsK().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == k) {
                sum++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                num += nums[j];
                if (num == k) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}