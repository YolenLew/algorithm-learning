//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
//
//
// 0 <= a, b, c, d < n
// a、b、c 和 d 互不相同
// nums[a] + nums[b] + nums[c] + nums[d] == target
//
//
// 你可以按 任意顺序 返回答案 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// -10⁹ <= nums[i] <= 10⁹
// -10⁹ <= target <= 10⁹
//
//
// Related Topics 数组 双指针 排序 👍 1900 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//java:四数之和
class P_18_FourSum {
    public static void main(String[] args) {
        Solution solution = new P_18_FourSum().new Solution();
        int n1 = 1000000000;
        System.out.println(n1 * 4);


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            return nSum(nums, target, 4, 0);
        }

        // N数之和：最终可分解两数之和
        private List<List<Integer>> nSum(int[] nums, long target, int N, int start) {
            int length = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (N < 2 || N > length) {
                return res;
            }

            if (N == 2) {
                // 两数之和
                int left = start;
                int right = nums.length - 1;
                while (left < right) {
                    long lValue = nums[left];
                    long rValue = nums[right];
                    long sum = lValue + rValue;
                    if (sum == target) {
                        res.add(new ArrayList<>(Arrays.asList(nums[left], nums[right])));
                        while (left < right && nums[left] == lValue) {
                            left++;
                        }
                        while (right > left && nums[right] == rValue) {
                            right--;
                        }
                    } else if (sum < target) {
                        while (left < right && nums[left] == lValue) {
                            left++;
                        }
                    } else if (sum > target) {
                        while (right > left && nums[right] == rValue) {
                            right--;
                        }
                    }
                }
            } else {
                // 递归分解N数之和：递归计算 (n-1)Sum 的结果
                for (int i = start; i < nums.length; i++) {
                    // 剪枝处理
                    if (i > start && nums[i] == nums[i - 1]) {
                        continue;
                    }

                    List<List<Integer>> subResult = nSum(nums, target - nums[i], N - 1, i + 1);
                    for (List<Integer> subList : subResult) {
                        subList.add(nums[i]);
                        res.add(subList);
                    }
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}