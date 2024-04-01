//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。
//
// 返回 滑动窗口中的最大值 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
// 示例 2：
//
//
//输入：nums = [1], k = 1
//输出：[1]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
// 1 <= k <= nums.length
//
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 2663 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//java:滑动窗口最大值
class P_239_SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new P_239_SlidingWindowMaximum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow00(int[] nums, int k) {
            // 滑动窗口大小固定，求最值，使用LinkedList双端队列，队列最值以及索引前沿窗口的值
            Deque<Integer> window = new LinkedList<>();
            int[] result = new int[nums.length - k + 1];
            // 初始化第一个窗口
            for (int i = 0; i < k; i++) {
                // 维护初始窗口的最值以及最新索引的值：因为后进的值放到队尾
                // 第一种，如果最值在窗口最右侧，那么直接丢弃其他值，保留最右侧同时也是最大的值即可
                // 第二种，如果最值在窗口左侧或者中间位置，那么把最值放到队首，然后窗口右侧的新值放到队尾
                while (!window.isEmpty() && window.peekLast() < nums[i]) {
                    window.removeLast();
                }
                // 后加入的元素放最后，最大元素顶到最前面
                window.addLast(nums[i]);
            }
            result[0] = window.peekFirst();
            // 开始滑动窗口
            for (int i = k; i < nums.length; i++) {
                // 如果上一个窗口的最值是窗口的第一个值，那么需要移除
                if (window.peekFirst().equals(nums[i - k])) {
                    window.removeFirst();
                }
                // 滑动窗口，然后重新比较窗口的值，此处相当于对窗口值排序
                // 第一种情况：如果新值小于队列的值，那么直接将新值放到队尾
                // 第二种情况：如果新值大于队尾的值，那么一直移除队尾的小值，最后在队尾插入新值
                while (!window.isEmpty() && window.peekLast() < nums[i]) {
                    window.removeLast();
                }
                window.addLast(nums[i]);
                result[i - k + 1] = window.peekFirst();
            }
            return result;
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            // 滑动窗口大小固定，求最值，使用LinkedList双端队列，队列最值以及索引前沿窗口的值
            LinkedList<Integer> window = new LinkedList<>();
            int[] result = new int[nums.length - k + 1];
            // 初始化第一个窗口
            for (int i = 0; i < k; i++) {
                // 后加入的元素：如果比前面加入的、而且是队列尾部的元素都大，那么直接顶掉即可
                while (!window.isEmpty() && window.peekLast() <= nums[i]) {
                    window.removeLast();
                }
                // 最后：直接加入队尾
                window.addLast(nums[i]);
            }
            result[0] = window.peekFirst();

            for (int i = k; i < nums.length; i++) {
                // 先判断
                // 如果上一个窗口的最值是窗口的第一个值，那么需要移除
                if (window.peekFirst().equals(nums[i - k])) {
                    window.removeFirst();
                }

                // 继续追加元素
                // 滑动窗口，然后重新比较窗口的值，此处相当于对窗口值排序
                // 第一种情况：如果新值小于队列的值，那么直接将新值放到队尾
                // 第二种情况：如果新值大于队尾的值，那么一直移除队尾的小值，最后在队尾插入新值
                // 注意  window.peekLast() < nums[i] 不能相等，否则在入口会被删掉
                while (!window.isEmpty() && window.peekLast() < nums[i]) {
                    window.removeLast();
                }
                window.addLast(nums[i]);
                result[i - k + 1] = window.peekFirst();
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}