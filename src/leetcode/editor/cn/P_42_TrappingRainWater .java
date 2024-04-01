//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
//
//
// 示例 1：
//
//
//
//
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
//
//
// 示例 2：
//
//
//输入：height = [4,2,0,3,2,5]
//输出：9
//
//
//
//
// 提示：
//
//
// n == height.length
// 1 <= n <= 2 * 10⁴
// 0 <= height[i] <= 10⁵
//
//
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 5081 👎 0

package leetcode.editor.cn;

//java:接雨水
class P_42_TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new P_42_TrappingRainWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            // 将每个柱子的雨水累加即总量
            // 关键点：需要获取每个柱子左右两侧的最大高度
            int sum = 0;
            // 注意，最两边的列存不了水
            // 初始化左右指针：分别指向左右两边界的列；
            int left = 1, right = height.length - 2;
            // 左指针的左边最大高度、右指针的右边最大高度
            int maxLeft = height[0], maxRight = height[height.length - 1];
            while (left <= right) {
                maxLeft = Math.max(maxLeft, height[left]);
                maxRight = Math.max(maxRight, height[right]);
                // 左指针的leftMax比右指针的rightMax矮
                // 说明：左指针的右边至少有一个板子 > 左指针左边所有板子
                // 根据水桶效应，保证了左指针当前列的水量决定权在左边
                // 那么可以计算左指针当前列的水量：左边最大高度-当前列高度
                if (maxLeft < maxRight) {
                    sum += maxLeft - height[left];
                    left++;
                } else {
                    sum += maxRight - height[right];
                    right--;
                }
            }

            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}