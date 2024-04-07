//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
//
//
//
// 示例 1：
//
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//
// 示例 2：
//
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//
// 示例 3：
//
//
//输入：nums = [], target = 0
//输出：[-1,-1]
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
// nums 是一个非递减数组
// -10⁹ <= target <= 10⁹
//
//
// Related Topics 数组 二分查找 👍 2660 👎 0

package leetcode.editor.cn;

//java:在排序数组中查找元素的第一个和最后一个位置
class P_34_FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new P_34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 二分查找框架
        // 参考：https://labuladong.online/algo/essential-technique/binary-search-framework/#%E4%B8%89%E3%80%81%E5%AF%BB%E6%89%BE%E5%8F%B3%E4%BE%A7%E8%BE%B9%E7%95%8C%E7%9A%84%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[] {-1, -1};
            }
            int leftIdx = findLeftIdx(nums, target);
            int rightIdx = findRightIdx(nums, target);
            return new int[] {leftIdx, rightIdx};
        }

        private int findLeftIdx(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    // 继续左移，寻找是否还有相等的值
                    right = mid - 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
            }
            if (left < 0 || left >= nums.length) {
                return -1;
            }

            return nums[left] == target ? left : -1;
        }

        private int findRightIdx(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    // 继续右移，寻找是否还有相等的值
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
            }
            if (left - 1 < 0 || left - 1 >= nums.length) {
                return -1;
            }

            return nums[left - 1] == target ? left - 1 : -1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}