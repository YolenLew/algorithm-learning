//给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素
// 。
//
// 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1
//。
//
//
//
// 示例 1:
//
//
//输入: nums = [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数；
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
//
//
// 示例 2:
//
//
//输入: nums = [1,2,3,4,3]
//输出: [2,3,4,-1,4]
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 10⁴
// -10⁹ <= nums[i] <= 10⁹
//
//
// Related Topics 栈 数组 单调栈 👍 923 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//java:下一个更大元素 II
class P_503_NextGreaterElementIi {
    public static void main(String[] args) {
        Solution solution = new P_503_NextGreaterElementIi().new Solution();
        int[] nums1 = {1, 2, 1};
        int[] ints = solution.nextGreaterElements(nums1);
        System.out.println(Arrays.toString(ints));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int[] nextGreaterElements00(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> s = new Stack<>();
            // 数组长度加倍模拟环形数组
            for (int i = 2 * n - 1; i >= 0; i--) {
                // 索引 i 要求模，其他的和模板一样
                while (!s.isEmpty() && s.peek() <= nums[i % n]) {
                    s.pop();
                }
                res[i % n] = s.isEmpty() ? -1 : s.peek();
                s.push(nums[i % n]);
            }
            return res;
        }

        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            // 循环数组，遍历两次
            int[] result = new int[n];
            Arrays.fill(result, Integer.MAX_VALUE);
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < n * 2 - 1; i++) {
                int idx = i % n;
                while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                    Integer topIdx = stack.pop();
                    result[topIdx] = nums[idx];
                }
                stack.push(idx);
            }

            for (int i = 0; i < result.length; i++) {
                if (result[i] == Integer.MAX_VALUE) {
                    result[i] = -1;
                }
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}