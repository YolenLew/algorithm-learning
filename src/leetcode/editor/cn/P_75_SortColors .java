//给定一个包含红色、白色和蓝色、共 n 个元素的数组
// nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
//
// 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
//
//
//
//
// 必须在不使用库内置的 sort 函数的情况下解决这个问题。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
//
//
// 示例 2：
//
//
//输入：nums = [2,0,1]
//输出：[0,1,2]
//
//
//
//
// 提示：
//
//
// n == nums.length
// 1 <= n <= 300
// nums[i] 为 0、1 或 2
//
//
//
//
// 进阶：
//
//
// 你能想出一个仅使用常数空间的一趟扫描算法吗？
//
//
// Related Topics 数组 双指针 排序 👍 1750 👎 0

package leetcode.editor.cn;

//java:颜色分类
class P_75_SortColors {
    public static void main(String[] args) {
        Solution solution = new P_75_SortColors().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void sortColors(int[] nums) {
            // 双指针解法
            // 0数指针，1数指针
            int n0 = 0, n1 = 0;
            // 数字大的指针先移动，因为大数要放在后面，i也承担了指针数字2的作用。所以i先移动，其次n1，最后n0.
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                // 刷油漆的思路，遍历每个元素刷次2，
                // 222222222222222222222
                // 碰到几个比2小的从前面刷几次1
                // 111111111111122222222
                // 碰到几个比1小的从前面刷几次0
                // 000000111111122222222
                nums[i] = 2;
                if (num < 2) {
                    nums[n1] = 1;
                    n1++;
                }
                if (num < 1) {
                    nums[n0] = 0;
                    n0++;
                }
            }
        }

        public void sortColorsDoubleIndex(int[] nums) {
            // 双指针解法
            int len = nums.length;
            if (len < 2) {
                return;
            }

            // all in [0, zero) = 0
            // all in [zero, i) = 1
            // all in [two, len - 1] = 2

            // 循环终止条件是 i == two，那么循环可以继续的条件是 i < two
            // 为了保证初始化的时候 [0, zero) 为空，设置 zero = 0，
            // 所以下面遍历到 0 的时候，先交换，再加
            int zero = 0;

            // 为了保证初始化的时候 [two, len - 1] 为空，设置 two = len
            // 所以下面遍历到 2 的时候，先减，再交换
            int two = len;
            int i = 0;
            // 当 i == two 上面的三个子区间正好覆盖了全部数组
            // 因此，循环可以继续的条件是 i < two
            while (i < two) {
                if (nums[i] == 0) {
                    swap(nums, i, zero);
                    zero++;
                    i++;
                } else if (nums[i] == 1) {
                    i++;
                } else {
                    two--;
                    swap(nums, i, two);
                }
            }

        }

        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}