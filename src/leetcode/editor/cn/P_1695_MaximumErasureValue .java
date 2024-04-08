//给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
//
// 返回 只删除一个 子数组可获得的 最大得分 。
//
// 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
//
//
//
// 示例 1：
//
//
//输入：nums = [4,2,4,5,6]
//输出：17
//解释：最优子数组是 [2,4,5,6]
//
//
// 示例 2：
//
//
//输入：nums = [5,2,1,2,5,2,1,2,5]
//输出：8
//解释：最优子数组是 [5,2,1] 或 [1,2,5]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// 1 <= nums[i] <= 10⁴
//
//
// Related Topics 数组 哈希表 滑动窗口 👍 79 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:删除子数组的最大得分
class P_1695_MaximumErasureValue {
    public static void main(String[] args) {
        Solution solution = new P_1695_MaximumErasureValue().new Solution();
        int[] nums = {187,470,25,436,538,809,441,167,477,110,275,133,666,345,411,459,490,266,987,965,429,166,809,340,467,318,125,165,809,610,31,585,970,306,42,189,169,743,78,810,70,382,367,490,787,670,476,278,775,673,299,19,893,817,971,458,409,886,434};

        System.out.println(solution.maximumUniqueSubarray(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximumUniqueSubarray(int[] nums) {
            // 滑动窗口
            int left = 0;
            int right = 0;
            int windowSum = 0;
            int ans = 0;
            // 记录出现的元素及次数
            Map<Integer, Integer> countMap = new HashMap<>();

            while (right < nums.length) {
                int currNum = nums[right];
                windowSum += currNum;
                countMap.merge(currNum, 1, Integer::sum);

                // 判断缩小窗口：出现的元素有重复
                while (left <= right && countMap.getOrDefault(currNum, 0) > 1) {
                    int leftNum = nums[left];
                    windowSum -= leftNum;
                    countMap.put(leftNum, countMap.get(leftNum) - 1);

                    left++;
                }

                ans = Math.max(ans, windowSum);
                right++;
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}