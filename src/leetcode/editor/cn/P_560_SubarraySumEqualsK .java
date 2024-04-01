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

import java.util.HashMap;
import java.util.Map;

//java:和为 K 的子数组
class P_560_SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new P_560_SubarraySumEqualsK().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 前缀和+哈希解法
        public int subarraySum(int[] nums, int k) {
            // 思路：通过计算前缀和，我们可以将问题转化为求解两个前缀和之差等于k的情况
            // 前缀和Map：记录数组连续元素的和，key是前缀和，value是个数，不用求索引，记录个数即可
            Map<Integer, Integer> preSumMap = new HashMap<>();
            // 初始化前缀和为0的次数为1：比如[3..]和3这种情况，需要这种边界值
            preSumMap.put(0, 1);
            // 统计个数
            int count = 0;
            // 前缀和
            int preSum = 0;
            for (int i = 0; i < nums.length; i++) {
                preSum += nums[i];
                //  问题转化为求解两个前缀和之差等于k的情况
                if (preSumMap.containsKey(preSum - k)) {
                    count += preSumMap.get(preSum - k);
                }

                preSumMap.put(preSum, preSumMap.getOrDefault(preSum, 0) + 1);
            }

            return count;
        }

        // 暴力双循环解法
        public int subarraySum00(int[] nums, int k) {
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