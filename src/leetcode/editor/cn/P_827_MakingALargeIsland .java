//给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
//
// 返回执行此操作后，grid 中最大的岛屿面积是多少？
//
// 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
//
//
//
// 示例 1:
//
//
//输入: grid = [[1, 0], [0, 1]]
//输出: 3
//解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
//
//
// 示例 2:
//
//
//输入: grid = [[1, 1], [1, 0]]
//输出: 4
//解释: 将一格0变成1，岛屿的面积扩大为 4。
//
// 示例 3:
//
//
//输入: grid = [[1, 1], [1, 1]]
//输出: 4
//解释: 没有0可以让我们变成1，面积依然为 4。
//
//
//
// 提示：
//
//
// n == grid.length
// n == grid[i].length
// 1 <= n <= 500
// grid[i][j] 为 0 或 1
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 397 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//java:最大人工岛
class P_827_MakingALargeIsland {
    public static void main(String[] args) {
        Solution solution = new P_827_MakingALargeIsland().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestIsland(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 1;
            }
            // 标记岛屿序号及面积的map
            Map<Integer, Integer> areaIndexMap = new HashMap<>();
            // 岛屿序号标记符号，因为0和1被使用了，需要从2开始
            int index = 2;
            // 第一次遍历：计算所有岛屿的面积，并进行标记
            int height = grid.length;
            int width = grid[0].length;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    // 寻找岛屿
                    if (grid[i][j] == 1) {
                        areaIndexMap.put(index, calculateAreas(grid, i, j, index));
                        index++;
                    }
                }
            }
            // 没有岛屿说明全是海洋，直接返回
            if (areaIndexMap.size() == 0) {
                return 1;
            }

            int maxArea = 0;
            // 第二次遍历：尝试填海造陆，并从map取出对应编号的岛屿面积累加
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    // 寻找海洋
                    if (grid[i][j] == 0) {
                        Set<Integer> queryResult = fillIslands(grid, i, j);
                        if (queryResult.isEmpty()) {
                            // 说明周围没有岛屿
                            continue;
                        }
                        maxArea = Math.max(maxArea,
                            queryResult.stream().map(areaIndexMap::get).reduce(Integer::sum).orElse(0) + 1);
                    }
                }
            }

            if (maxArea == 0) {
                // 说明全是岛屿，没有海洋，直接返回岛屿面积即可
                return areaIndexMap.get(2);
            }
            return maxArea;
        }

        // 填海，计算相连岛屿后最大面积
        private Set<Integer> fillIslands(int[][] grid, int row, int column) {
            Set<Integer> result = new HashSet<>();
            // 遍历上下左右四个格子，寻找岛屿并获取岛屿的面积
            if (isLegalIsland(grid, row - 1, column) && grid[row - 1][column] != 0) {
                result.add(grid[row-1][column]);
            }
            if (isLegalIsland(grid, row + 1, column) && grid[row + 1][column] != 0) {
                result.add(grid[row+1][column]);
            }
            if (isLegalIsland(grid, row, column - 1) && grid[row][column - 1] != 0) {
                result.add(grid[row][column - 1]);
            }
            if (isLegalIsland(grid, row, column + 1) && grid[row][column + 1] != 0) {
                result.add(grid[row][column + 1]);
            }
            return result;
        }

        // 先把所有的岛屿面积计算出来
        private int calculateAreas(int[][] grid, int row, int column, int index) {
            // 越界判断
            if (!isLegalIsland(grid, row, column)) {
                return 0;
            }
            // 海洋或已访问岛屿判断
            if (grid[row][column] != 1) {
                return 0;
            }
            // 如果是1，说明是同属于一个岛屿的相邻小岛，将其标记序号
            grid[row][column] = index;
            // 累加上下左右的面积
            return 1 + calculateAreas(grid, row - 1, column, index) + calculateAreas(grid, row + 1, column, index)
                + calculateAreas(grid, row, column - 1, index) + calculateAreas(grid, row, column + 1, index);
        }

        private boolean isLegalIsland(int[][] grid, int i, int j) {
            return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}