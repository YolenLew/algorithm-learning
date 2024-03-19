//给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//
// 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
//
//
//
//
//
//
//
// 示例 1 ：
//
//
//输入：nums = [2,2,1]
//输出：1
//
//
// 示例 2 ：
//
//
//输入：nums = [4,1,2,1,2]
//输出：4
//
//
// 示例 3 ：
//
//
//输入：nums = [1]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 10⁴
// -3 * 10⁴ <= nums[i] <= 3 * 10⁴
// 除了某个元素只出现一次以外，其余每个元素均出现两次。
//
//
// Related Topics 位运算 数组 👍 3106 👎 0

package leetcode.editor.cn;

//java:只出现一次的数字
class P_136_SingleNumber {
    public static void main(String[] args) {
        Solution solution = new P_136_SingleNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber(int[] nums) {
            // 异或解法：异或运算满足交换律，a^b^a=a^a^b=b,因此ans相当于nums[0]^nums[1]^nums[2]^nums[3]^nums[4].....
            // 然后再根据交换律把相等的合并到一块儿进行异或（结果为0），然后再与只出现过一次的元素进行异或，这样最后的结果就是，只出现过一次的元素（0^任意值=任意值）
            int res = 0;
            for (int num : nums) {
                res ^= num;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}