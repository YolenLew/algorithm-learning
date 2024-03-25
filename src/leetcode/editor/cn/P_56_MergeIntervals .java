//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2：
//
//
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
//
//
// 提示：
//
//
// 1 <= intervals.length <= 10⁴
// intervals[i].length == 2
// 0 <= starti <= endi <= 10⁴
//
//
// Related Topics 数组 排序 👍 2274 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//java:合并区间
class P_56_MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new P_56_MergeIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            // 数组排序：第一个元素升序、第二个元素降序排列
            Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

            List<int[]> result = new ArrayList<>();
            for (int[] interval : intervals) {
                if (result.isEmpty() || result.get(result.size() - 1)[1] < interval[0]) {
                    result.add(interval);
                } else {
                    // 合并区间：有重叠
                    result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], interval[1]);
                }
            }

            return result.toArray(new int[0][0]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}