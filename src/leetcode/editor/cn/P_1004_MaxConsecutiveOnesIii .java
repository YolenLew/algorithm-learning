//给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释：[1,1,1,0,0,1,1,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。
//
// 示例 2：
//
//
//输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//输出：10
//解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// nums[i] 不是 0 就是 1
// 0 <= k <= nums.length
//
//
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 689 👎 0

package leetcode.editor.cn;

//java:最大连续1的个数 III
class P_1004_MaxConsecutiveOnesIii {
    public static void main(String[] args) {
        Solution solution = new P_1004_MaxConsecutiveOnesIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestOnes(int[] nums, int k) {
            // 左右指针：双指针，表示当前遍历的区间[left, right)，闭区间
            int left = 0, right = 0;
            // 记录最值：保存最大的满足题目要求的 子数组/子串 长度
            int res = 0;
            // 记录状态值：比如Map记录窗口、string记录合法字串、int记录合法个数等
            int zeros = 0;
            int n = nums.length;
            // 滑动窗口模板
            while (right < n) {
                // 判断并进行窗口内数据的一系列更新
                if (nums[right] == 0) {
                    zeros++;
                }
                // 判断左侧窗口是否要收缩：0的个数超过允许值
                while (zeros > k) {
                    if (nums[left] == 0) {
                        zeros--;
                    }
                    left++;
                }
                // 到 while 结束时，我们找到了一个符合题意要求的 子数组/子串
                // 需要更新结果
                res = Math.max(res, right - left + 1);

                // 移动右指针，去探索新的区间
                right++;
            }

            return res;
        }

        // 滑动窗口
        public int longestOnes90(int[] nums, int k) {
            int ans = 0;
            int n = nums.length;
            // 最多翻转k个0 -> 窗口最多允许k个0
            int left = 0;
            int right = 0;
            int windowCount = 0;
            while (right < n) {
                if (nums[right] == 0) {
                    windowCount++;
                }
                right++;

                // 缩小窗口
                while (windowCount > k) {
                    if (nums[left] == 0) {
                        windowCount--;
                    }
                    left++;
                }

                ans = Math.max(ans, right - left);
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}