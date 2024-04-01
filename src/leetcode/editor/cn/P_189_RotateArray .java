//给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
//
//
//
// 示例 1:
//
//
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右轮转 1 步: [7,1,2,3,4,5,6]
//向右轮转 2 步: [6,7,1,2,3,4,5]
//向右轮转 3 步: [5,6,7,1,2,3,4]
//
//
// 示例 2:
//
//
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释:
//向右轮转 1 步: [99,-1,-100,3]
//向右轮转 2 步: [3,99,-1,-100]
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -2³¹ <= nums[i] <= 2³¹ - 1
// 0 <= k <= 10⁵
//
//
//
//
// 进阶：
//
//
// 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
//
//
// Related Topics 数组 数学 双指针 👍 2074 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:轮转数组
class P_189_RotateArray {
    public static void main(String[] args) {
        Solution solution = new P_189_RotateArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate00(int[] nums, int k) {
            int length = nums.length;
            int[] result = Arrays.copyOf(nums, nums.length);
            for (int i = 0; i < length; i++) {
                int idx = (i + k) >= length ? (i + k) % length : (i + k);
                nums[idx] = result[i];
            }
        }

        public void rotate90(int[] nums, int k) {
            int n = nums.length;
            // O(n)：额外数组，利用变换后的索引对应关系求解
            int[] copy = Arrays.copyOf(nums, n);
            for (int i = 0; i < n; i++) {
                // 向右轮转 k 个位置，原来是i，旋转后是 i+k，注意索引越界
                int targetIdx = (i + k) % n;
                nums[targetIdx] = copy[i];
            }
        }

        public void rotate(int[] nums, int k) {
            // 找规律得到的翻转数组方法
            // nums = "----->-->"; k =3
            // result = "-->----->";
            //
            // reverse "----->-->" we can get "<--<-----"
            // reverse "<--" we can get "--><-----"
            // reverse "<-----" we can get "-->----->"
            // this visualization help me figure it out :)

            // 防止索引越界
            int n = nums.length;
            k = k % n;
            // 首先对整个数组实行翻转，这样子原数组中需要翻转的子数组，就会跑到数组最前面。
            reverse(nums, 0, n - 1);
            // 这时候，从 kkk 处分隔数组，左右两数组，各自进行翻转即可。
            reverse(nums, 0, k - 1);
            reverse(nums, k, n - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                start++;
                end--;
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}