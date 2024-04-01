//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
//
//
// 示例 2：
//
//
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 10
// -100 <= matrix[i][j] <= 100
//
//
// Related Topics 数组 矩阵 模拟 👍 1645 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//java:螺旋矩阵
class P_54_SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new P_54_SpiralMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            // 结果集
            List<Integer> result = new ArrayList<>();
            // 定义边界值：依次按从左到右、从上到下、从右往左、从下往上循环遍历
            int left = 0, right = matrix[0].length - 1;
            int top = 0, buttom = matrix.length - 1;

            while (true) {
                // 1.从左往右遍历（高度不变，长度递增）
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
                top++;
                if (top > buttom) {
                    break;
                }

                // 2.然后从上往下遍历
                for (int i = top; i <= buttom; i++) {
                    result.add(matrix[i][right]);
                }
                right--;
                if (right < left) {
                    break;
                }

                // 3.然后从右往左遍历
                for (int i = right; i >= left; i--) {
                    result.add(matrix[buttom][i]);
                }
                // 即将往上遍历时，先判断是否触碰上边界
                buttom--;
                if (buttom < top) {
                    break;
                }

                // 4.最后从下往上遍历（长度位置不变，高度递减）
                for (int i = buttom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                // 即将往右遍历时，先判断是否触碰右边界
                left++;
                if (left > right) {
                    break;
                }
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}