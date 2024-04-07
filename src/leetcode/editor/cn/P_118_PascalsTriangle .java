//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
//
//
//
//
//
// 示例 1:
//
//
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//
//
// 示例 2:
//
//
//输入: numRows = 1
//输出: [[1]]
//
//
//
//
// 提示:
//
//
// 1 <= numRows <= 30
//
//
// Related Topics 数组 动态规划 👍 1124 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//java:杨辉三角
class P_118_PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new P_118_PascalsTriangle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<>();
            if (numRows < 1) {
                return result;
            }
            // 初始化第一层元素
            result.add(Collections.singletonList(1));
            for (int i = 1; i < numRows; i++) {
                List<Integer> currentList = new ArrayList<>();
                currentList.add(1);
                List<Integer> preList = result.get(i - 1);
                int preSize = preList.size();
                for (int j = 0; j < preSize - 1; j++) {
                    currentList.add(preList.get(j) + preList.get(j + 1));
                }
                currentList.add(1);

                result.add(currentList);
            }

            return result;
        }

        public List<List<Integer>> generate00(int numRows) {
            List<List<Integer>> result = new ArrayList<>();
            if (numRows < 1) {
                return result;
            }
            result.add(Collections.singletonList(1));
            for (int i = 1; i < numRows; i++) {
                List<Integer> lastList = result.get(i - 1);
                List<Integer> currentList = new ArrayList<>();
                currentList.add(1);
                for (int index = 1; index <= i - 1; index++) {
                    currentList.add(lastList.get(index - 1) + lastList.get(index));
                }
                currentList.add(1);
                result.add(currentList);
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}