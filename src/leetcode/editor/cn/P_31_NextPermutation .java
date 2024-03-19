//整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
//
//
// 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
//
//
// 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就
//是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
//
//
// 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
// 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
// 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
//
//
// 给你一个整数数组 nums ，找出 nums 的下一个排列。
//
// 必须 原地 修改，只允许使用额外常数空间。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[1,3,2]
//
//
// 示例 2：
//
//
//输入：nums = [3,2,1]
//输出：[1,2,3]
//
//
// 示例 3：
//
//
//输入：nums = [1,1,5]
//输出：[1,5,1]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
//
//
// Related Topics 数组 双指针 👍 2448 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:下一个排列
class P_31_NextPermutation {
    public static void main(String[] args) {
        Solution solution = new P_31_NextPermutation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void nextPermutation(int[] nums) {
            // 第一步，倒序遍历查找到下一个比当前数大的数
            //第二步，第二次倒序遍历找到第一个大于降序元素的元素
            //第三步，对第一个降序元素之后升序排列
            int len = nums.length;
            for (int i = len - 1; i > 0; i--) {
                // 第一步，从后向前 查找第一个 相邻升序 的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
                if (nums[i - 1] < nums[i]) {
                    // 将[i,end)重置为升序排列
                    Arrays.sort(nums, i, len);
                    for (int j = i; j < nums.length; j++) {
                        if (nums[j] > nums[i - 1]) {
                            // 比如123465 -> 12456 -> 123546的过程
                            // 第二步，第二次倒序遍历找到第一个大于降序元素的元素
                            int temp = nums[j];
                            nums[j] = nums[i - 1];
                            nums[i - 1] = temp;
                            return;
                        }
                    }
                }
            }
            // 如果找不到，按题目要求，升序排列即可
            Arrays.sort(nums);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}