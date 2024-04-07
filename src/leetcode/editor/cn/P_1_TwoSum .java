//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//
// 你可以按任意顺序返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
//
//
// 示例 2：
//
//
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
//
//
// 示例 3：
//
//
//输入：nums = [3,3], target = 6
//输出：[0,1]
//
//
//
//
// 提示：
//
//
// 2 <= nums.length <= 10⁴
// -10⁹ <= nums[i] <= 10⁹
// -10⁹ <= target <= 10⁹
// 只会存在一个有效答案
//
//
//
//
// 进阶：你可以想出一个时间复杂度小于 O(n²) 的算法吗？
//
// Related Topics 数组 哈希表 👍 18458 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:两数之和
class P_1_TwoSum {
    public static void main(String[] args) {
        Solution solution = new P_1_TwoSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 参考：https://leetcode.cn/problems/two-sum/solutions/434597/liang-shu-zhi-he-by-leetcode-solution/comments/1629741
        // 使用哈希表的方法，一开始简单的以为先遍历数组建立哈希表，再遍历数组在哈希表里找值，看了题解的代码，一度认为代码少了一次遍历，想了半天才明白错的是我。
        // 两个元素x，y必然是一前一后出现的，如果存在符合条件的解，在遍历到x时，哈希表里没有符合的y，此时把x加入到了哈希表里，当遍历到y时，就可以在哈希表里找到对应的x了，所以只需要一次遍历，妙啊。
        // 原来LeetCode第一题都不会的那个人就是我。
        public int[] twoSum(int[] nums, int target) {
            // 只需遍历一次
            Map<Integer, Integer> numIdxMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++){
                if (numIdxMap.containsKey(target - nums[i])) {
                    return new int[]{i, numIdxMap.get(target - nums[i])};
                }
                numIdxMap.put(nums[i], i);
            }
            return new int[0];
        }


        public int[] twoSum90(int[] nums, int target) {
            // 哈希前缀和
            Map<Integer, Integer> numIdxMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                numIdxMap.put(nums[i], i);
            }

            for (int i = 0; i < nums.length; i++) {
                if (numIdxMap.containsKey(target - nums[i]) && i != numIdxMap.get(target - nums[i])) {
                    return new int[] {i, numIdxMap.get(target - nums[i])};
                }
            }

            return new int[] {-1, -1};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}