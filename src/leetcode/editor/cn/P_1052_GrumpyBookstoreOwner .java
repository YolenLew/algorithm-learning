//有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 n 的整数数组 customers ，其中 customers[i]
// 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
//
// 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
//
// 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
//
// 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
//
// 请你返回 这一天营业下来，最多有多少客户能够感到满意 。
//
// 示例 1：
//
//
//输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
//输出：16
//解释：书店老板在最后 3 分钟保持冷静。
//感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
//
//
// 示例 2：
//
//
//输入：customers = [1], grumpy = [0], minutes = 1
//输出：1
//
//
//
// 提示：
//
//
// n == customers.length == grumpy.length
// 1 <= minutes <= n <= 2 * 10⁴
// 0 <= customers[i] <= 1000
// grumpy[i] == 0 or 1
//
//
// Related Topics 数组 滑动窗口 👍 278 👎 0

package leetcode.editor.cn;

//java:爱生气的书店老板
class P_1052_GrumpyBookstoreOwner {
    public static void main(String[] args) {
        Solution solution = new P_1052_GrumpyBookstoreOwner().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 可以先将原本就满意的客户加入答案，同时将对应的 customers[i] 变为 0。
        //之后的问题转化为：在 customers 中找到连续一段长度为 minutes 的子数组，使得其总和最大。这部分就是我们应用技巧所得到的客户。
        //
        //作者：宫水三叶
        //链接：https://leetcode.cn/problems/grumpy-bookstore-owner/solutions/616352/hua-dong-chuang-kou-luo-ti-by-ac_oier-nunu/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
            int n = customers.length;
            int sum = 0;
            for (int i = 0; i < customers.length; i++) {
                if (grumpy[i] == 0) {
                    sum += customers[i];
                    customers[i] = 0;
                }
            }

            // 在 customers 中找到连续一段长度为 minutes 的子数组，使得其总和最大
            int max = 0;
            int curr = 0;
            for (int i = 0; i < customers.length; i++) {
                // 固定窗口移动
                curr += customers[i];
                if (i >= minutes) {
                    curr -= customers[i - minutes];
                }

                max = Math.max(max, curr);
            }

            return sum + max;
        }

        //  作者：程序厨
        // 链接：https://leetcode.cn/problems/grumpy-bookstore-owner/solutions/616279/ni-jue-dui-ke-yi-yi-xia-du-dong-de-hua-d-ww72/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int maxSatisfied91(int[] customers, int[] grumpy, int minutes) {
            // 划分三个窗口进行移动
            int winsum = 0;
            int rightsum = 0;
            int len = customers.length;
            //右区间的值
            for (int i = minutes; i < len; ++i) {
                if (grumpy[i] == 0) {
                    rightsum += customers[i];
                }
            }
            //窗口的值
            for (int i = 0; i < minutes; ++i) {
                winsum += customers[i];
            }
            int leftsum = 0;
            //窗口左边缘
            int left = 1;
            //窗口右边缘
            int right = minutes;
            int maxcustomer = winsum + leftsum + rightsum;
            while (right < customers.length) {
                //重新计算左区间的值，也可以用 customer 值和 grumpy 值相乘获得
                if (grumpy[left - 1] == 0) {
                    leftsum += customers[left - 1];
                }
                //重新计算右区间值
                if (grumpy[right] == 0) {
                    rightsum -= customers[right];
                }
                //窗口值
                winsum = winsum - customers[left - 1] + customers[right];
                //保留最大值
                maxcustomer = Math.max(maxcustomer, winsum + leftsum + rightsum);
                //易懂窗口
                left++;
                right++;
            }
            return maxcustomer;
        }

        public int maxSatisfied90(int[] customers, int[] grumpy, int minutes) {
            // 滑动窗口

            // 先计算总的感到满意的客户数量
            int n = customers.length;
            int happySum = 0;
            for (int i = 0; i < n; i++) {
                if (grumpy[i] == 0) {
                    happySum += customers[i];
                }
            }

            int ans = 0;
            int left = 0;
            int right = minutes - 1;

            while (right < n) {
                int newSum = happySum;

                for (int i = left; i <= right; i++) {
                    if (grumpy[i] == 1) {
                        newSum += customers[i];
                    }
                }

                ans = Math.max(ans, newSum);
                left++;
                right++;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}