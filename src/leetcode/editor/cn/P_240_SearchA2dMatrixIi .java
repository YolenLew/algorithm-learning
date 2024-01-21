//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
//
//
// 每行的元素从左到右升序排列。
// 每列的元素从上到下升序排列。
//
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
//
//
// 示例 2：
//
//
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= n, m <= 300
// -10⁹ <= matrix[i][j] <= 10⁹
// 每行的所有元素从左到右升序排列
// 每列的所有元素从上到下升序排列
// -10⁹ <= target <= 10⁹
//
//
// Related Topics 数组 二分查找 分治 矩阵 👍 1409 👎 0

package leetcode.editor.cn;

//java:搜索二维矩阵 II
class P_240_SearchA2dMatrixIi {
    public static void main(String[] args) {
        Solution solution = new P_240_SearchA2dMatrixIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            // 数组有序排列，选择一个切入点为右上角，小于目标时往下，大于目标时往左
            int left = matrix[0].length - 1, down = 0;
            while (left >= 0 && down <= matrix.length - 1) {
                if (matrix[down][left] == target) {
                    return true;
                } else if (matrix[down][left] > target) {
                    left--;
                } else {
                    down++;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}