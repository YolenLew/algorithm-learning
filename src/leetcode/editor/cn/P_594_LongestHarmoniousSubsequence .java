//和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
//
// 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
//
// 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,3,2,2,5,2,3,7]
//输出：5
//解释：最长的和谐子序列是 [3,2,2,2,3]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,4]
//输出：2
//
//
// 示例 3：
//
//
//输入：nums = [1,1,1,1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2 * 10⁴
// -10⁹ <= nums[i] <= 10⁹
//
//
// Related Topics 数组 哈希表 计数 排序 滑动窗口 👍 399 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//java:最长和谐子序列
class P_594_LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        Solution solution = new P_594_LongestHarmoniousSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findLHSHash00(int[] nums) {
            // 哈希表解法：枚举了 xxx 后，遍历数组找出所有的 xxx 和 x+1x + 1x+1的出现的次数。我们也可以用一个哈希映射来存储每个数出现的次数，这样就能在 O(1)O(1)O(1) 的时间内得到
            // xxx 和 x+1x + 1x+1 出现的次数
            //
            //作者：力扣官方题解
            //链接：https://leetcode.cn/problems/longest-harmonious-subsequence/solutions/1110137/zui-chang-he-xie-zi-xu-lie-by-leetcode-s-8cyr/
            //来源：力扣（LeetCode）
            //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : nums) {
                countMap.merge(num, 1, Integer::sum);
            }
            int res = 0;
            for (Integer num : countMap.keySet()) {
                if (countMap.containsKey(num + 1)) {
                    res = Math.max(res, countMap.get(num) + countMap.get(num + 1));
                }
            }

            return res;
        }

        public int findLHS(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return 0;
            }
            Arrays.sort(nums);
            int ans = 0;
            // 滑动窗口双指针解法
            int left = 0;
            int right = 1;
            while (left < n) {
                // 扩大窗口
                while (right < n && nums[right] <= nums[left] + 1) {
                    right++;
                }

                // 需要满足最大值和最小值之间的差别 正好是 1，所以要校验
                if (nums[right - 1] == nums[left] + 1) {
                    ans = Math.max(ans, right - left);
                }

                left++;
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}