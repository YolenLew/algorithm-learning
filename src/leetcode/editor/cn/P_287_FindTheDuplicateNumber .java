//给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
//
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
//
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,3,4,2,2]
//输出：2
//
//
// 示例 2：
//
//
//输入：nums = [3,1,3,4,2]
//输出：3
//
//
// 示例 3 :
//
//
//输入：nums = [3,3,3,3,3]
//输出：3
//
//
//
//
//
//
// 提示：
//
//
// 1 <= n <= 10⁵
// nums.length == n + 1
// 1 <= nums[i] <= n
// nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
//
//
//
//
// 进阶：
//
//
// 如何证明 nums 中至少存在一个重复的数字?
// 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
//
//
// Related Topics 位运算 数组 双指针 二分查找 👍 2366 👎 0

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//java:寻找重复数
class P_287_FindTheDuplicateNumber {
    public static void main(String[] args) {
        Solution solution = new P_287_FindTheDuplicateNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findDuplicate(int[] nums) {
            // 二分查找
            // 取left = 1, 是因为给的数字是从1开始的，这里我们要根据数字来找，而不是下标
            int len = nums.length;
            // num范围在[1，n]之间，一共由n+1个数
            int left = 1, right = len - 1;
            while (left < right) {
                int count = 0;

                int mid = left + (right - left) / 2;
                for (int num : nums) {
                    if (num <= mid) {
                        count++;
                    }
                }

                if (count > mid) {
                    // 重复数在 left, mid 之间, 下一轮搜索的区间 [left..mid]
                    right = mid;
                } else {
                    // 下一轮搜索的区间 [mid + 1..right]
                    left = mid + 1;
                }
            }

            return left;
        }

        public int findDuplicateSet(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int len = nums.length;
            for (int num : nums) {
                if (!set.add(num)) {
                    return num;
                }
            }
            return len;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}