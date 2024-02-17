//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1：
//
//
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
//
//
// 示例 2：
//
//
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] 的值为 '0' 或 '1'
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 2421 👎 0

package leetcode.editor.cn;

//java:岛屿数量
class P_200_NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new P_200_NumberOfIslands().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 主方法：遍历所有元素，如果满足条件则记录
        public int numIslands(char[][] grid) {
            if (grid == null) {
                return 0;
            }
            int height = grid.length;
            int width = grid[0].length;
            int result = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j] == '1') {
                        // 发现岛屿
                        result++;
                        // 递归遍历：将相邻的岛屿填充为其他值（因为相连的岛屿其实就是一个岛屿）
                        dfs(grid, i, j);
                    }
                }
            }

            return result;
        }

        // 递归遍历：上下左右遍历相邻元素，类似二叉树的递归遍历。同时因为可能存在重复遍历以及越界的情况，需要进行判断
        private void dfs(char[][] grid, int i, int j) {
            // 判断是否越界
            if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0) {
                return;
            }

            // 判断是否是相邻的岛屿：如果不是相邻的岛屿，说明碰到了海洋或者是已访问的岛屿，直接return
            if (grid[i][j] != '1') {
                return;
            }

            // 如果是岛屿：说明是相邻的，直接置为2
            grid[i][j] = '2';

            // 递归遍历上、下、左、右元素
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}