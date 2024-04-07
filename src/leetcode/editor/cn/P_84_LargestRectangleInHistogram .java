//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
//
//
//
// 示例 1:
//
//
//
//
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
//
//
// 示例 2：
//
//
//
//
//输入： heights = [2,4]
//输出： 4
//
//
//
// 提示：
//
//
// 1 <= heights.length <=10⁵
// 0 <= heights[i] <= 10⁴
//
//
// Related Topics 栈 数组 单调栈 👍 2702 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//java:柱状图中最大的矩形
class P_84_LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new P_84_LargestRectangleInHistogram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestRectangleArea(int[] heights) {
            // 当前高度对应的最大面积的宽度是右边比他小的第一个 - 左边比它小的第一个 - 1 ，剩下的只要固定right也就是i,更新当前的高度cur和left,就可以求出每个高度所对应的最大面积了
            int n = heights.length;
            int result = 0;
            if (n == 0) {
                return result;
            }
            // 复制数组，左侧和右侧补充0值
            int[] newHeights = new int[n + 2];
            System.arraycopy(heights, 0, newHeights, 1, n);
            // 单调栈：记录高度
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < newHeights.length; i++) {
                // 单调递增栈：如果当前元素比栈顶元素小，说明当前元素是栈顶元素的右边界
                while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                    // 栈顶元素弹栈
                    Integer pop = stack.pop();
                    // 求高
                    int height = newHeights[pop];
                    // 求宽：右边界-左边界-1
                    int width = i - stack.peek() - 1;
                    result = Math.max(result, height * width);
                }
                stack.push(i);
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}