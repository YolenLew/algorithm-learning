//中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
//
//
// 例如 arr = [2,3,4] 的中位数是 3 。
// 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
//
//
// 实现 MedianFinder 类:
//
//
// MedianFinder() 初始化 MedianFinder 对象。
// void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
// double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10⁻⁵ 以内的答案将被接受。
//
//
// 示例 1：
//
//
//输入
//["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//[[], [1], [2], [], [3], []]
//输出
//[null, null, null, 1.5, null, 2.0]
//
//解释
//MedianFinder medianFinder = new MedianFinder();
//medianFinder.addNum(1);    // arr = [1]
//medianFinder.addNum(2);    // arr = [1, 2]
//medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
//medianFinder.addNum(3);    // arr[1, 2, 3]
//medianFinder.findMedian(); // return 2.0
//
// 提示:
//
//
// -10⁵ <= num <= 10⁵
// 在调用 findMedian 之前，数据结构中至少有一个元素
// 最多 5 * 10⁴ 次调用 addNum 和 findMedian
//
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 961 👎 0

package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//java:数据流的中位数
class P_295_FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder solution = new P_295_FindMedianFromDataStream().new MedianFinder();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {
        // 建立一个小顶堆和大顶堆，各保存列表的一半元素
        Queue<Integer> smallQueue;
        Queue<Integer> largeQueue;

        public MedianFinder() {
            // 小顶堆，存储的是比较大的元素，堆顶是其中的最小值
            smallQueue = new PriorityQueue<>();
            // 大顶堆，存储的是比较小的元素，堆顶是其中的最大值
            largeQueue = new PriorityQueue<>(Comparator.reverseOrder());
        }

        public void addNum(int num) {
            // 注意：约定smallQueue的元素数量不小于大顶堆的元素数量
            // 小顶堆存储的是比较大的元素
            if (smallQueue.size() == largeQueue.size()) {
                // 两者大小相等，先存到大顶堆，让大顶堆内部进行排序，将较大值放到顶，之后再弹栈放到小顶堆
                largeQueue.offer(num);
                smallQueue.offer(largeQueue.poll());
            } else {
                // 说明小顶堆的数量比较大，此时需要先放入小顶堆，将较小值放到顶，之后再弹栈放到大顶堆
                smallQueue.offer(num);
                largeQueue.offer(smallQueue.poll());
            }
        }

        public double findMedian() {
            return smallQueue.size() != largeQueue.size() ? smallQueue.peek() :
                (smallQueue.peek() + largeQueue.peek()) / 2.0;
        }
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}