//按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
//
// n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
//
//
//
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[["Q"]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
//
//
// Related Topics 数组 回溯 👍 2032 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//java:N 皇后
class P_51_NQueens {
    public static void main(String[] args) {
        Solution solution = new P_51_NQueens().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 所有满足条件的结果集
        List<List<String>> result = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            // 初始化棋盘
            List<String> board = new ArrayList<>();
            for (int row = 0; row < n; row++) {
                StringBuilder chess = new StringBuilder();
                for (int clumn = 0; clumn < n; clumn++) {
                    chess.append(".");
                }
                board.add(chess.toString());
            }
            // 递归层级遍历棋盘
            backtrack(board, 0);
            return result;
        }

        // 回溯算法框架：
        //        result = []
        //        def backtrack(路径, 选择列表):
        //            if 满足结束条件:
        //            result.add(路径)
        //            return
        //
        //            for 选择 in 选择列表:
        //              做选择：将选择从选择列表移除；将选择添加到路径中去；
        //              backtrack(路径, 选择列表)
        //              撤销选择：路径取消选择；将选择再加入选择列表
        private void backtrack(List<String> board, int row) {
            // base case判断：
            if (board.size() == row) {
                result.add(new ArrayList<>(board));
                return;
            }

            // 遍历选择列表
            for (int colunm = 0; colunm < board.size(); colunm++) {
                // 选择判断：左上、上方、右上是否碰到皇后棋子, 说明棋盘不合法
                if (matchQueen(board, row, colunm)) {
                    continue;
                }

                // 选择皇后
                StringBuilder chess = new StringBuilder(board.get(row));
                chess.setCharAt(colunm, 'Q');
                board.set(row, chess.toString());
                // 下一层递归选择
                backtrack(board, row + 1);
                // 撤消选择
                chess.setCharAt(colunm, '.');
                board.set(row, chess.toString());
            }
        }

        /* 是否可以在 board[row][col] 放置皇后 */
        private boolean matchQueen(List<String> stack, int row, int colunm) {
            int n = stack.size();
            // 左上是否碰到皇后棋子
            for (int i = row - 1, j = colunm - 1; i >= 0 && j >= 0; i--, j--) {
                if (stack.get(i).charAt(j) == 'Q') {
                    return true;
                }
            }

            // 上方(即同一列)是否碰到皇后棋子
            for (int i = 0; i < row; i++) {
                if (stack.get(i).charAt(colunm) == 'Q') {
                    return true;
                }
            }

            // 右上是否碰到皇后棋子
            for (int i = row - 1, j = colunm + 1; i >= 0 && j < n; i--, j++) {
                if (stack.get(i).charAt(j) == 'Q') {
                    return true;
                }
            }

            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}