//给定一个整数数组 asteroids，表示在同一行的小行星。
//
// 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
//
// 找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远
//不会发生碰撞。
//
//
//
// 示例 1：
//
//
//输入：asteroids = [5,10,-5]
//输出：[5,10]
//解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
//
// 示例 2：
//
//
//输入：asteroids = [8,-8]
//输出：[]
//解释：8 和 -8 碰撞后，两者都发生爆炸。
//
// 示例 3：
//
//
//输入：asteroids = [10,2,-5]
//输出：[10]
//解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
//
//
//
// 提示：
//
//
// 2 <= asteroids.length <= 10⁴
// -1000 <= asteroids[i] <= 1000
// asteroids[i] != 0
//
//
// Related Topics 栈 数组 模拟 👍 486 👎 0

package leetcode.editor.cn;

//java:小行星碰撞
class P_735_AsteroidCollision {
    public static void main(String[] args) {
        Solution solution = new P_735_AsteroidCollision().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] asteroidCollision(int[] asteroids) {

            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}