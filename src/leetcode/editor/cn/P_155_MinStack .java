//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//
// 实现 MinStack 类:
//
//
// MinStack() 初始化堆栈对象。
// void push(int val) 将元素val推入堆栈。
// void pop() 删除堆栈顶部的元素。
// int top() 获取堆栈顶部的元素。
// int getMin() 获取堆栈中的最小元素。
//
//
//
//
// 示例 1:
//
//
//输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
//
//
//
//
// 提示：
//
//
// -2³¹ <= val <= 2³¹ - 1
// pop、top 和 getMin 操作总是在 非空栈 上调用
// push, pop, top, and getMin最多被调用 3 * 10⁴ 次
//
//
// Related Topics 栈 设计 👍 1728 👎 0

package leetcode.editor.cn;

import java.util.Stack;

//java:最小栈
class P_155_MinStack {
    public static void main(String[] args) {
        MinStack solution = new P_155_MinStack().new MinStack();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MinStack {
        //关键就是，每个元素入栈时，还要记下来当前栈中的最小值。
        //比方说，可以用一个额外的栈 minStk 来记录栈中每个元素入栈时的栈中的最小元素是多少，这样每次删除元素时就能快速得到剩余栈中的最小元素了
        // 记录栈中的所有元素
        Stack<Integer> stk = new Stack<>();
        // 阶段性记录栈中的最小元素
        Stack<Integer> minStk = new Stack<>();

        public MinStack() {

        }

        public void push(int val) {
            stk.push(val);

            // 维护 minStk 栈顶为全栈最小元素
            if (minStk.isEmpty() || minStk.peek() >= val) {
                // 新插入的这个元素就是全栈最小的
                minStk.push(val);
            } else {
                // 新插入的这个元素比较大
                minStk.push(minStk.peek());
            }
        }

        public void pop() {
            stk.pop();
            minStk.pop();
        }

        public int top() {
            return stk.peek();
        }

        public int getMin() {
            // minStk 栈顶为全栈最小元素
            return minStk.peek();
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}