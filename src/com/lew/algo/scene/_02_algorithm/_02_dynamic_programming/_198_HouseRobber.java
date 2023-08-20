package com.lew.algo.scene._02_algorithm._02_dynamic_programming;

/**
 * 动态规划：<a href="https://leetcode.cn/problems/house-robber/">198. 打家劫舍</a>
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * </p>
 * <p>
 * 动态规划的的四个解题步骤是：
 * <p>
 * 定义子问题
 * 写出子问题的递推关系
 * 确定 DP 数组的计算顺序
 * 空间优化（可选）
 *
 * @author Yolen
 * @date 2023/8/20
 */
public class _198_HouseRobber {
    /**
     * d[k] = max{d[k-1], d[k-2]+num[k-1]}
     *
     * @param nums nums
     * @return 最值
     */
    public int rob(int[] nums) {
        int pre = 0;
        int curr = 0;

        for (int num : nums) {
            int tmp = Math.max(curr, pre + num);
            pre = curr;
            curr = tmp;
        }

        return curr;
    }

    public int robFirst(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= length; k++) {
            dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k - 1]);
        }

        return dp[length];
    }


    public static void main(String[] args) {
        _198_HouseRobber robber = new _198_HouseRobber();
        int[] nums = {2, 7, 9, 3, 1};
        int rob = robber.rob(nums);
        int robFirst = robber.robFirst(nums);
        System.out.println(rob);
        System.out.println(robFirst);
    }
}
