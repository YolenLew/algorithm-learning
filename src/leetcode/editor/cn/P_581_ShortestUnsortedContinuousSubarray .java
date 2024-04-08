//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//
// 请你找出符合题意的 最短 子数组，并输出它的长度。
//
//
//
//
//
// 示例 1：
//
//
//
//
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,4]
//输出：0
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁴
// -10⁵ <= nums[i] <= 10⁵
//
//
//
//
// 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
//
// Related Topics 栈 贪心 数组 双指针 排序 单调栈 👍 1136 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:最短无序连续子数组
class P_581_ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        Solution solution = new P_581_ShortestUnsortedContinuousSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 双指针 + 排序
        //最终目的是让整个数组有序，那么我们可以先将数组拷贝一份进行排序，然后使用两个指针 i 和 j 分别找到左右两端第一个不同的地方，那么 [i,j][i, j][i,j] 这一区间即是答案。
        //
        //作者：宫水三叶
        //链接：https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/solutions/912231/gong-shui-san-xie-yi-ti-shuang-jie-shuan-e1le/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int findUnsortedSubarray(int[] nums) {
            int n = nums.length;
            int[] arr = nums.clone();
            Arrays.sort(arr);

            int left = 0;
            int right = n - 1;
            while (left <= right && arr[left] == nums[left]) {
                left++;
            }
            while (right >= left && arr[right] == nums[right]) {
                right--;
            }

            return right - left + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}