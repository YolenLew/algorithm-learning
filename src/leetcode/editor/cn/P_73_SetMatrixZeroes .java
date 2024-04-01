//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
//输出：[[1,0,1],[0,0,0],[1,0,1]]
//
//
// 示例 2：
//
//
//输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[0].length
// 1 <= m, n <= 200
// -2³¹ <= matrix[i][j] <= 2³¹ - 1
//
//
//
//
// 进阶：
//
//
// 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
// 你能想出一个仅使用常量空间的解决方案吗？
//
//
// Related Topics 数组 哈希表 矩阵 👍 1037 👎 0

package leetcode.editor.cn;

//java:矩阵置零
class P_73_SetMatrixZeroes {
    public static void main(String[] args) {
        Solution solution = new P_73_SetMatrixZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void setZeroes(int[][] matrix) {
            // 用matrix第一行和第一列记录该行该列是否有0,作为标志位
            // 但是对于第一行,和第一列要设置一个标志位,为了防止自己这一行(一列)也有0的情况
            int row = matrix.length;
            int column = matrix[0].length;
            boolean row0 = false;
            boolean column0 = false;

            // 判断 第一行 是否有0
            for (int i = 0; i < column; i++) {
                if (matrix[0][i] == 0) {
                    row0 = true;
                    break;
                }
            }
            // 判断 第一列 是否有0
            for (int i = 0; i < row; i++) {
                if (matrix[i][0] == 0) {
                    column0 = true;
                    break;
                }
            }

            // 第一行和第一列标记0
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

            // 重置0：注意要重第二行第二列开始，因为第一行第一列是标记位
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < column; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            // 第一行置0
            if (row0) {
                for (int j = 0; j < column; j++) {
                    matrix[0][j] = 0;
                }
            }

            // 第一列置0
            if (column0) {
                for (int i = 0; i < row; i++) {
                    matrix[i][0] = 0;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}