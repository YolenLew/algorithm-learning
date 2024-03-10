//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
//
//
// 示例 1：
//
//
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶
//
// 示例 2：
//
//
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
//
//
//
//
// 提示：
//
//
// 1 <= n <= 45
//
//
// Related Topics 记忆化搜索 数学 动态规划 👍 3456 👎 0

package leetcode.editor.cn;

//java:爬楼梯
class P_70_ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new P_70_ClimbingStairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            // 使用递归存在超时问题，需要使用空间换时间，初始化数组
            int[] dp = new int[n + 1];
            // 初始化1阶、2阶
            dp[1] = 1;
            if (n >= 2) {
                dp[2] = 2;
            }
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        // 动态规划f(n) = f(n - 1) + f(n -2)：递归解法，超时
        public int climbStairsFail(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            return climbStairsFail(n - 1) + climbStairsFail(n - 2);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}