//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
//
//
//
// 示例 1：
//
//
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：4
//
//
// 示例 2：
//
//
//输入：matrix = [["0","1"],["1","0"]]
//输出：1
//
//
// 示例 3：
//
//
//输入：matrix = [["0"]]
//输出：0
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 300
// matrix[i][j] 为 '0' 或 '1'
//
//
// Related Topics 数组 动态规划 矩阵 👍 1647 👎 0

package leetcode.editor.cn;

//java:最大正方形
class P_221_MaximalSquare {
    public static void main(String[] args) {
        Solution solution = new P_221_MaximalSquare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 动态规划：参考作者labuladong
        // 一个全是 1 的正方形有什么特点，如何根据小的正方形推导出大的正方形（状态转移方程）。
        // 当 matrix[i][j] 为 1，且它的左边、上边、左上边都存在正方形时，matrix[i][j] 才能够作为一个更大的正方形的右下角
        // 定义这样一个二维 dp 数组：以 matrix[i][j] 为右下角元素的最大的全为 1 正方形矩阵的边长为 dp[i][j]
        // 状态转移方程：如果 matrix[i][j] == 1，那么
        // 参考「水桶效应」，最大边长取决于边长最短的那个正方形dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]) + 1;
        public int maximalSquare(char[][] matrix) {
            int rows = matrix.length;
            int columns = matrix[0].length;

            // 定义：以 matrix[i][j] 为右下角元素的全为 1 正方形矩阵的最大边长为 dp[i][j]。
            int[][] dp = new int[rows][columns];

            // base case：首行首列的边长可以先初始化
            for (int i = 0; i < columns; i++) {
                dp[0][i] = matrix[0][i] - '0';
            }
            for (int i = 0; i < rows; i++) {
                dp[i][0] = matrix[i][0] - '0';
            }

            // 状态转移求解
            for (int i = 1; i < rows; i++) {
                for (int j = 1; j < columns; j++) {
                    if (matrix[i][j] == '0') {
                        // 值为 0 不可能是正方形的右下角
                        continue;
                    }
                    // 类似「水桶效应」，最大边长取决于边长最短的那个正方形
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }

            int len = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    len = Math.max(len, dp[i][j]);
                }
            }

            return len * len;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}