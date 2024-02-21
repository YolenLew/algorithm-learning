//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 示例 1：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"ABCCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"SEE"
//输出：true
//
//
// 示例 3：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"ABCB"
//输出：false
//
//
//
//
// 提示：
//
//
// m == board.length
// n = board[i].length
// 1 <= m, n <= 6
// 1 <= word.length <= 15
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
//
// Related Topics 数组 回溯 矩阵 👍 1770 👎 0

package leetcode.editor.cn;

//java:单词搜索
class P_79_WordSearch {
    public static void main(String[] args) {
        Solution solution = new P_79_WordSearch().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean exist(char[][] board, String word) {
            // 网格遍历+岛屿问题+DFS深度遍历搜索+回溯算法
            int height = board.length;
            int width = board[0].length;
            char[] wordArray = word.toCharArray();
            for (int row = 0; row < height; row++) {
                for (int column = 0; column < width; column++) {
                    // 递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。
                    if (dfs(board, wordArray, row, column, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        // 递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。
        private boolean dfs(char[][] board, char[] wordArray, int row, int column, int charIndex) {
            // 剪枝判断
            if (!isLegalIsland(board, row, column) || board[row][column] != wordArray[charIndex]) {
                return false;
            }
            // 满足条件判断
            if (charIndex == wordArray.length - 1) {
                return true;
            }
            // 标记已经遍历过的字符（使用空字符（Python: '' , Java/C++: '\0' ）做标记是为了防止标记字符与矩阵原有字符重复）
            board[row][column] = '\0';
            // 上下左右递归遍历
            boolean result =
                dfs(board, wordArray, row - 1, column, charIndex + 1) || dfs(board, wordArray, row + 1, column,
                    charIndex + 1) || dfs(board, wordArray, row, column - 1, charIndex + 1) || dfs(board, wordArray,
                    row, column + 1, charIndex + 1);
            // 遍历结束，还原字符
            board[row][column] = wordArray[charIndex];
            return result;
        }

        private boolean isLegalIsland(char[][] board, int i, int j) {
            return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}