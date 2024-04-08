//给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
//
//
// nums.length == n
// nums[i] 是 正整数 ，其中 0 <= i < n
// abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
// nums 中所有元素之和不超过 maxSum
// nums[index] 的值被 最大化
//
//
// 返回你所构造的数组中的 nums[index] 。
//
// 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
//
//
//
// 示例 1：
//
// 输入：n = 4, index = 2,  maxSum = 6
//输出：2
//解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
//
//
// 示例 2：
//
// 输入：n = 6, index = 1,  maxSum = 10
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= n <= maxSum <= 10⁹
// 0 <= index < n
//
//
// Related Topics 贪心 二分查找 👍 211 👎 0

package leetcode.editor.cn;

//java:有界数组中指定下标处的最大值
class P_1802_MaximumValueAtAGivenIndexInABoundedArray {
    public static void main(String[] args) {
        Solution solution = new P_1802_MaximumValueAtAGivenIndexInABoundedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 作者：liberg
        // 链接：https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/solutions/670810/shu-xue-guo-cheng-mo-ni-zui-qing-xi-de-j-gz30/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int maxValue(int n, int index, int maxSum) {
            // 过程模拟，从 index 开始往两边扩充：维护一个 [l,r] 范围，
            // 每次往范围内每个位置 +1 ，通过这种方式维护一个向上生长的“三角形”。
            int l = index, r = index;
            int ans = 1;
            // 整个数组一开始全部填充为1，
            // rest记录先全部填充1后，剩下1的个数
            int rest = maxSum - n;
            while (l > 0 || r < n - 1) {
                int len = r - l + 1;
                if (rest >= len) {
                    // 当前[l,r]范围全部+1
                    rest -= len;
                    ans++;
                    // 往左右两边扩
                    l = Math.max(0, l - 1);
                    r = Math.min(n - 1, r + 1);
                } else {
                    break;
                }
            }
            // 扩大到整个数组之后，剩余的值“雨露均沾”一下
            ans += rest / n;
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}