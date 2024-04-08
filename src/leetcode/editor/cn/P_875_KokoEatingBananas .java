//珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
//
// 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后
//这一小时内不会再吃更多的香蕉。
//
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
//
// 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：piles = [3,6,7,11], h = 8
//输出：4
//
//
// 示例 2：
//
//
//输入：piles = [30,11,23,4,20], h = 5
//输出：30
//
//
// 示例 3：
//
//
//输入：piles = [30,11,23,4,20], h = 6
//输出：23
//
//
//
//
// 提示：
//
//
// 1 <= piles.length <= 10⁴
// piles.length <= h <= 10⁹
// 1 <= piles[i] <= 10⁹
//
//
// Related Topics 数组 二分查找 👍 600 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:爱吃香蕉的珂珂
class P_875_KokoEatingBananas {
    public static void main(String[] args) {
        Solution solution = new P_875_KokoEatingBananas().new Solution();
        int[] piles = {332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,
            290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184};
        int k = 823855818;
        System.out.println(solution.minEatingSpeed(piles, k));
        int[] piles1 = {805306368,805306368,805306368};
        int k1 = 1000000000;
        System.out.println(solution.minEatingSpeed(piles1, k1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            int n = piles.length;
            if (n > h) {
                return -1;
            }
            Arrays.sort(piles);
            if (n == h) {
                return piles[n - 1];
            }
            int left = 1;
            int right = piles[n - 1];
            int ans = Integer.MAX_VALUE;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                // 判断能否吃完
                if (finish(piles, mid, h)) {
                    ans = Math.min(ans, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return ans;
        }

        private boolean finish(int[] piles, int speed, int hour) {
            long cost = 0;
            for (int pile : piles) {
                cost += pile / speed;
                if (pile % speed > 0) {
                    cost++;
                }
            }

            return cost <= hour;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}