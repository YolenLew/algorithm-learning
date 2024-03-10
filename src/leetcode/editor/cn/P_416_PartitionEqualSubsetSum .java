//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。
//
// 示例 2：
//
//
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100
//
//
// Related Topics 数组 动态规划 👍 2024 👎 0

package leetcode.editor.cn;

//java:分割等和子集
class P_416_PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new P_416_PartitionEqualSubsetSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPartition(int[] nums) {
            // 问题转换：给一个可装载重量为 sum / 2 的背包和 N 个物品，每个物品的重量为 nums[i]。现在让你装物品，是否存在一种装法，能够恰好将背包装满？

            // 明确状态：如何才能描述一个问题局面？只要给几个物品和一个背包的容量限制，就形成了一个背包问题。所以状态有两个，就是「背包的容量」和「可选择的物品」。
            // 明确选择：对于一个物品，由 装进背包 和 不装进背包 两种情况
            // 明确dp数组定义：dp[i][j] = x 表示，对于前 i 个物品（i 从 1 开始计数），当前背包的容量为 j 时，若 x 为 true，则说明可以恰好将背包装满，若 x 为
            // false，则说明不能恰好将背包装满。
            // 根据选择，定义转移方程：根据「选择」对 dp[i][j] 得到以下状态转移
            //     不装进背包：dp[i][j] = dp[i-1][j]，继承之前的结果
            //     装进背包：dp[i][j] = dp[i-1][j - num[i-1]]，如果 j - nums[i-1] 的重量可以被恰好装满，那么只要把第 i 个物品装进去，也可恰好装满 j
            //     的重量；否则的话，重量 j 肯定是装不满的。
            // 明确base case或边界情况：base case 就是 dp[..][0] = true 和 dp[0][..] =
            // false，因为背包没有空间的时候，就相当于装满了，而当没有物品可选择的时候，肯定没办法装满背包

            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 和为奇数，不可能划分成两个和相等的集合
            if (sum % 2 != 0) {
                return false;
            }
            int n = nums.length;
            sum = sum / 2;
            // 物品从1开始算
            // 对于前i个物品，背包容量为j时，能否装满背包
            boolean[][] dp = new boolean[n + 1][sum + 1];
            // 初始化base case：背包容量为0时，肯定能装满
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }

            // 对于前i个物品，背包容量为j时，能否装满背包
            for (int i = 1; i <= n; i++) {
                // 背包容量，最小值为1，最大值为sum，0值前面base case已处理
                for (int j = 1; j <= sum; j++) {
                    // 判断背包容量
                    if (j - nums[i - 1] < 0) {
                        // 容量不足了，不能装入了，只能沿用上一次结果
                        dp[i][j] =  dp[i - 1][j];
                    } else {
                        // 容量足够，可选择 不装入背包 或 装入
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                    }
                }
            }

            return dp[n][sum];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}