//在给定的 m x n 网格
// grid 中，每个单元格可以有以下三个值之一：
//
//
// 值 0 代表空单元格；
// 值 1 代表新鲜橘子；
// 值 2 代表腐烂的橘子。
//
//
// 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
//
// 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
//
//
//
// 示例 1：
//
//
//
//
//输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
//输出：4
//
//
// 示例 2：
//
//
//输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
//输出：-1
//解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
//
//
// 示例 3：
//
//
//输入：grid = [[0,2]]
//输出：0
//解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 10
// grid[i][j] 仅为 0、1 或 2
//
//
// Related Topics 广度优先搜索 数组 矩阵 👍 809 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//java:腐烂的橘子
class P_994_RottingOranges {
    public static void main(String[] args) {
        Solution solution = new P_994_RottingOranges().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 记录新鲜橘子数量
        int freshCount = 0;

        // BFS广度优先搜索
        public int orangesRotting(int[][] grid) {
            int height = grid.length;
            int width = grid[0].length;
            Queue<int[][]> corruptQueue = new LinkedList<>();
            // 第一次遍历：记录所有新鲜橘子数量以及登记所有的腐败橘子
            for (int row = 0; row < height; row++) {
                for (int column = 0; column < width; column++) {
                    int value = grid[row][column];
                    if (value == 1) {
                        // 新鲜橘子数量
                        freshCount++;
                    } else if (value == 2) {
                        // 腐败橘子位置
                        corruptQueue.add(new int[][] {{row, column}});
                    }
                }
            }
            // 第二次遍历：开始腐败并记录轮次
            int round = 0;
            while (freshCount > 0 && !corruptQueue.isEmpty()) {
                round++;
                // 本轮次的橘子开始BFS腐败
                int size = corruptQueue.size();
                for (int i = 0; i < size; i++) {
                    int[][] corruptLocation = corruptQueue.poll();
                    int row = corruptLocation[0][0];
                    int column = corruptLocation[0][1];
                    // 依次腐败上下左右方向的新鲜橘子
                    corrupt(grid, row - 1, column, corruptQueue);
                    corrupt(grid, row + 1, column, corruptQueue);
                    corrupt(grid, row, column - 1, corruptQueue);
                    corrupt(grid, row, column + 1, corruptQueue);
                }
            }
            return freshCount > 0 ? -1 : round;
        }

        private void corrupt(int[][] grid, int row, int column, Queue<int[][]> corruptQueue) {
            // 校验行列
            if (!isLegalSite(grid, row, column)) {
                return;
            }
            // 寻找新鲜的橘子进行腐败
            if (grid[row][column] != 1) {
                return;
            }
            // 腐败橘子
            grid[row][column] = 2;
            freshCount--;
            corruptQueue.add(new int[][] {{row, column}});
        }

        private boolean isLegalSite(int[][] grid, int row, int column) {
            return row >= 0 && column >= 0 && row < grid.length && column < grid[0].length;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}