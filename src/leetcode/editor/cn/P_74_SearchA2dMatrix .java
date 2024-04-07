//给你一个满足下述两条属性的 m x n 整数矩阵：
//
//
// 每行中的整数从左到右按非严格递增顺序排列。
// 每行的第一个整数大于前一行的最后一个整数。
//
//
// 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
//
//
// 示例 2：
//
//
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
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
// 1 <= m, n <= 100
// -10⁴ <= matrix[i][j], target <= 10⁴
//
//
// Related Topics 数组 二分查找 矩阵 👍 900 👎 0

package leetcode.editor.cn;

//java:搜索二维矩阵
class P_74_SearchA2dMatrix {
    public static void main(String[] args) {
        Solution solution = new P_74_SearchA2dMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix00(int[][] matrix, int target) {
            // 关键：通过将二维数组展开成一维数组
            int row = matrix.length;
            int column = matrix[0].length;
            // 把二维数组映射到一维
            int left = 0;
            int right = row * column - 1;
            // 于是便可转换为基本的二分搜索问题
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midValue = getValue(matrix, mid);
                if (midValue == target) {
                    return true;
                } else if (midValue > target) {
                    right = mid - 1;
                } else if (midValue < target) {
                    left = mid + 1;
                }
            }

            return false;
        }

        // 通过一维索引转换为二维坐标获取元素
        private int getValue(int[][] matrix, int index) {
            int width = matrix[0].length;
            int row = index / width;
            int column = index % width;
            return matrix[row][column];
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            // 关键：通过将二维数组展开成一维数组
            int row = matrix.length;
            int column = matrix[0].length;
            int left = 0;
            int right = row * column - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int value = myGetValue(matrix, mid);
                if (value == target) {
                    return true;
                } else if (value > target) {
                    right = mid - 1;
                } else if (value < target) {
                    left = mid + 1;
                }
            }

            return false;
        }

        private int myGetValue(int[][] matrix, int index) {
            int column = matrix[0].length;
            int x = index / column;
            int y = index % column;

            return matrix[x][y];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}