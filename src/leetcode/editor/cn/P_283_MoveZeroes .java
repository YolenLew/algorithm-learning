//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
//
//
//
// 示例 1:
//
//
//输入: nums = [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
//
// 示例 2:
//
//
//输入: nums = [0]
//输出: [0]
//
//
//
// 提示:
//
//
//
// 1 <= nums.length <= 10⁴
// -2³¹ <= nums[i] <= 2³¹ - 1
//
//
//
//
// 进阶：你能尽量减少完成的操作次数吗？
//
// Related Topics 数组 双指针 👍 2329 👎 0

package leetcode.editor.cn;

//java:移动零
class P_283_MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new P_283_MoveZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            // 快慢指针解法：移动0到后面，反思路就是把非0移动到前面
            // 假定0索引是0
            int slowZeroIdx = 0;
            int fastNonZeroIdx = 0;
            while (fastNonZeroIdx < nums.length) {
                // 寻找非0数
                if (nums[fastNonZeroIdx] != 0) {
                    // 交换
                    int tmp = nums[fastNonZeroIdx];
                    nums[fastNonZeroIdx] = nums[slowZeroIdx];
                    nums[slowZeroIdx] = tmp;
                    slowZeroIdx++;
                }
                fastNonZeroIdx++;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}