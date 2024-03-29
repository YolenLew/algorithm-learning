//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//
// 问总共有多少条不同的路径？
//
//
//
// 示例 1：
//
//
//输入：m = 3, n = 7
//输出：28
//
// 示例 2：
//
//
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
//
//
// 示例 3：
//
//
//输入：m = 7, n = 3
//输出：28
//
//
// 示例 4：
//
//
//输入：m = 3, n = 3
//输出：6
//
//
//
// 提示：
//
//
// 1 <= m, n <= 100
// 题目数据保证答案小于等于 2 * 10⁹
//
//
// Related Topics 数学 动态规划 组合数学 👍 2005 👎 0

package leetcode.editor.cn;

//java:不同路径
class P_62_UniquePaths {
    public static void main(String[] args) {
        Solution solution = new P_62_UniquePaths().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] cache;

        // 递归+备忘录的记忆化搜索，动态规划
        public int uniquePaths(int m, int n) {
            cache = new int[m][n];
            return dp(m - 1, n - 1);
        }

        // 定义：从 (0, 0) 到 (x, y) 有 dp(x, y) 条路径
        private int dp(int x, int y) {
            if (x == 0 && y == 0) {
                return 1;
            }
            if (x < 0 || y < 0) {
                return 0;
            }

            // 避免冗余计算
            if (cache[x][y] > 0) {
                return cache[x][y];
            }
            // 状态转移方程：
            // 到达 (x, y) 的路径数等于到达 (x - 1, y) 和 (x, y - 1) 路径数之和
            cache[x][y] = dp(x - 1, y) + dp(x, y - 1);
            return cache[x][y];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}