//给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
//
// 两个相邻元素间的距离为 1 。
//
//
//
// 示例 1：
//
//
//
//
//输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
//输出：[[0,0,0],[0,1,0],[0,0,0]]
//
//
// 示例 2：
//
//
//
//
//输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
//输出：[[0,0,0],[0,1,0],[1,2,1]]
//
//
//
//
// 提示：
//
//
// m == mat.length
// n == mat[i].length
// 1 <= m, n <= 10⁴
// 1 <= m * n <= 10⁴
// mat[i][j] is either 0 or 1.
// mat 中至少有一个 0
//
//
// Related Topics 广度优先搜索 数组 动态规划 矩阵 👍 921 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//java:01 矩阵
class P_542_Zero1Matrix {
    public static void main(String[] args) {
        Solution solution = new P_542_Zero1Matrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] updateMatrix(int[][] mat) {
            int rows = mat.length;
            int columns = mat[0].length;

            // 记录答案的结果数组
            int[][] ans = new int[rows][columns];
            // 初始化全部填充特殊值 -1，代表未计算，
            // 待会可以用来判断坐标是否已经计算过，避免重复遍历
            for (int[] row : ans) {
                Arrays.fill(row, -1);
            }

            Queue<int[]> queue = new LinkedList<>();
            // 初始化队列，把那些值为 0 的坐标放到队列里
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (mat[i][j] == 0) {
                        queue.offer(new int[] {i, j});
                        ans[i][j] = 0;
                    }
                }
            }

            // 执行 BFS 算法框架，从值为 0 的坐标开始向四周扩散
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int x = arr[0];
                int y = arr[1];
                // 上下左右遍历寻找
                for (int[] offset : offsets) {
                    int nextX = x + offset[0];
                    int nextY = y + offset[1];
                    // 确保相邻的这个坐标没有越界且之前未被计算过
                    // 如果四邻域的点是 -1，表示这个点是未被访问过的 1
                    // 所以这个点到 0 的距离就可以更新成 matrix[x][y] + 1。
                    if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < columns && ans[nextX][nextY] == -1) {
                        // 填充当前节点
                        queue.offer(new int[] {nextX, nextY});
                        ans[nextX][nextY] = ans[x][y] + 1;
                    }
                }
            }

            return ans;
        }

        private int[][] offsets = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        private void dfs(int[][] mat, int[][] ans, int x, int y) {
            // 下标越界判断
            if (x < 0 || x >= mat.length || y < 0 || y >= mat[0].length) {
                return;
            }

            // 上下左右递归遍历，寻找0
            for (int[] offset : offsets) {
                dfs(mat, ans, x + offset[0], y + offset[1]);
            }

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}