//给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
//
// 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
//
//
// 0 <= j <= nums[i]
// i + j < n
//
//
// 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//
//
// 示例 2:
//
//
//输入: nums = [2,3,0,1,4]
//输出: 2
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 10⁴
// 0 <= nums[i] <= 1000
// 题目保证可以到达 nums[n-1]
//
//
// Related Topics 贪心 数组 动态规划 👍 2453 👎 0

package leetcode.editor.cn;

//java:跳跃游戏 II
class P_45_JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new P_45_JumpGameIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int jump(int[] nums) {
            // 记录每次能到达的右边界
            int end = 0;
            // 记录最远跳到的位置
            int farthest = 0;
            // 记录步骤次数
            int step = 0;
            // 注意：这里有个小细节，因为是起跳的时候就 + 1 了，如果最后一次跳跃刚好到达了最后一个位置，那么遍历到最后一个位置的时候就会再次起跳，这是不允许的，因此不能遍历最后一个位置
            for (int i = 0; i < nums.length - 1; i++) {
                farthest = Math.max(farthest, nums[i] + i);
                // 到达一次跳跃中的右边界，说明要继续新的一次跳跃了
                if (i == end) {
                    step++;
                    // 下一次跳跃区间的最右边界限
                    end = farthest;
                }
            }
            return step;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}