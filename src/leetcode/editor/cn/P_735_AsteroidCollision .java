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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

//java:小行星碰撞
class P_735_AsteroidCollision {
    public static void main(String[] args) {
        Solution solution = new P_735_AsteroidCollision().new Solution();
        Deque<Integer> stack = new LinkedList<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

        System.out.println(stack.isEmpty());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty()) {
            System.out.print(stack.poll() + " ");
        }
        System.out.println(stack.isEmpty());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        for (Integer value : stack) {
            System.out.print(value + " ");
        }
        System.out.println(stack.isEmpty());

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 我们需要明确什么时候无脑入栈，什么时候需要判断，理解这两点就可以轻松解题了。
        //首先，循环每一个元素时，在什么情况下无脑入栈呢？
        //
        //栈为空
        //栈顶元素为负数(下一个为负数则一起向左，下一个为正数则分向两边)
        //当前元素为正数（栈顶为正一起向右，栈顶为负分向两边）
        //下来，我们需要看碰撞的场景又细分为什么情况：
        //
        //栈顶元素大于abs(当前元素)，当前元素被撞毁
        //栈顶元素等于abs(当前元素)，栈顶弹出和当前元素抵消
        //栈顶元素小于abs(当前元素)，栈顶弹出，并与新栈顶完成上述判断
        //
        //作者：清风Python
        //链接：https://leetcode.cn/problems/XagZNi/solutions/994096/shua-chuan-jian-zhi-offer-day17-zhan-i-0-5yho/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int[] asteroidCollision(int[] asteroids) {
            // 栈的思想：进行不断抵消
            Deque<Integer> stack = new ArrayDeque<>();
            int p = 0;
            while (p < asteroids.length) {
                if (stack.isEmpty() || stack.peekLast() < 0 || asteroids[p] > 0) {
                    // 无脑入栈情景
                    stack.addLast(asteroids[p]);
                } else if (stack.peekLast() <= -asteroids[p]) {
                    // 出栈情景
                    Integer topValue = stack.pollLast();
                    if (topValue < -asteroids[p]) {
                        // 把小的栈顶元素弹栈，同时跳过后续的p++，继续栈顶元素和当前的p元素比较
                        continue;
                    }
                }
                p++;
            }
            int[] ret = new int[stack.size()];
            for (int i = ret.length - 1; i >= 0; i--) {
                ret[i] = stack.pollLast();
            }
            return ret;
        }

        // 逻辑复杂的栈运算
        public int[] asteroidCollision90(int[] asteroids) {
            // 栈的思想：进行不断抵消
            Deque<Integer> stack = new LinkedList<>();
            stack.push(asteroids[0]);
            for (int i = 1; i < asteroids.length; i++) {
                int curr = asteroids[i];
                if (!stack.isEmpty() && stack.peek() > 0 && curr < 0 && Math.abs(stack.peek()) == Math.abs(curr)) {
                    stack.pop();
                    continue;
                }

                while (!stack.isEmpty() && stack.peek() > 0 && curr < 0) {
                    Integer top = stack.pop();
                    if (Math.abs(top) > Math.abs(curr)) {
                        curr = top;
                    } else if (Math.abs(top) == Math.abs(curr)) {
                        curr = 0;
                    }
                }
                if (curr != 0) {
                    stack.push(curr);
                }
            }
            int[] res = new int[stack.size()];
            int i = stack.size() - 1;
            for (Integer value : stack) {
                res[i--] = value;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}