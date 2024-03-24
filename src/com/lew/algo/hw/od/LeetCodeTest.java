package com.lew.algo.hw.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author luyonglang
 * @date 2024/1/3
 */
public class LeetCodeTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x, ListNode nextNode) {
            val = x;
            next = nextNode;
        }
    }

    public void testAsciiSorting() {
        String str1 = "0356";
        String str2 = "3560";
        assertTrue(str1.compareTo(str2) < 0);
    }

    private void assertTrue(boolean b) {
        if (!b) {
            throw new RuntimeException("assert not pass!");
        }
    }

    // ------------------------ ------- ------------------------
    // ------------------------ 动态规划 ------------------------
    // ------------------------ ------- ------------------------

    /**
     * 279. 完全平方数
     *
     * @param n n
     * @return 完全平方数
     */
    public int numSquares(int n) {
        // 子问题：f(k)，返回 和为 k 的完全平方数的最少数量
        // 递推关系/状态转移方程：f(k) = Math.min(f(k-1*1) + 1, f(k-2*2) + 1,..., f(k - i**) + 1);
        // 计算顺序：自底向上的、使用 dp 数组的循环方法
        // dp[i]: 和为 i 的完全平方数的最少数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 198. 打家劫舍
     *
     * @param nums nums
     * @return int
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 子问题：f(k)，从 k 个房子中能偷到的最大金额
        // 当前位于K的房子，你可以选择：1、偷
        // 2、不偷。第一种方案不能偷k-1的房子，所以f(k)=H(k-1)+f(k-2)；第二种方案可以偷前(k-1)个房子中的任意房子，所以f(k)=f(k-1)，最终小偷在两种方案中做权衡，f(k)=max(H
        // (k-1)+f(k-2),f(k-1))
        // 递推关系/状态转移方程：f(k) = Math.max(f(k-1), f(k - 2) + value(k-1));
        // 计算顺序：自底向上的、使用 dp 数组的循环方法
        int n = nums.length;
        int[] dp = new int[n + 1];
        // 初始化基本值
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= nums.length; k++) {
            dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k - 1]);
        }

        return dp[n];
    }

    // ------------------------ ------- ------------------------
    // -------------------------- 堆 ---------------------------
    // ------------------------ ------- ------------------------

    /**
     * 347. 前 K 个高频元素(辅助Map解法)
     *
     * @param nums nums
     * @param k    k
     * @return 前 K 个高频元素
     */
    public int[] topKFrequentQueue(int[] nums, int k) {
        // 辅助map：key-元素，value-元素个数
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int num : nums) {
            numsMap.put(num, numsMap.getOrDefault(num, 0) + 1);
        }
        // 根据数量进行排序：求最大的k个高频元素，使用小顶堆，只保存k个高频元素
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(numsMap::get));
        for (Integer num : numsMap.keySet()) {
            if (queue.size() < k) {
                queue.offer(num);
            } else {
                if (numsMap.get(num) > numsMap.get(queue.peek())) {
                    queue.poll();
                    queue.offer(num);
                }
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    /**
     * 347. 前 K 个高频元素(辅助Map解法)
     *
     * @param nums nums
     * @param k    k
     * @return 前 K 个高频元素
     */
    public int[] topKFrequentList(int[] nums, int k) {
        // 辅助map：key-元素，value-元素个数
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int num : nums) {
            numsMap.put(num, numsMap.getOrDefault(num, 0) + 1);
        }
        // 根据个数进行排序
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(numsMap.entrySet());
        entries.sort((e1, e2) -> e2.getValue() - e1.getValue());
        int[] result = new int[k];
        for (int i = 0; i < result.length; i++) {
            result[i] = entries.get(i).getKey();
        }
        return result;
    }

    /**
     * 215. 数组中的第K个最大元素
     *
     * @param nums nums
     * @param k    k
     * @return 第K个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        // 小顶堆，堆顶是最小元素
        // 转换：找第 K 大元素，其实就是整个数组排序以后后半部分最小的那个元素。因此，可以维护一个有 K 个元素的最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            if (i >= k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    // ------------------------ ------- ------------------------
    // -------------------------- 栈 ---------------------------
    // ------------------------ ------- ------------------------

    /**
     * 84. 柱状图中最大的矩形(单调栈：结合哨兵寻找左右边界) 当前高度对应的最大面积 的 宽度 是 右边比他小的第一个 - 左边比它小的第一个 - 1
     * ，剩下的只要固定right也就是i,更新当前的高度cur和left,就可以求出每个高度所对应的最大面积了
     *
     * @param heights heights
     * @return 最大的矩形
     */
    public int largestRectangleArea(int[] heights) {
        // 当前高度对应的最大面积的宽度是右边比他小的第一个 - 左边比它小的第一个 - 1 ，剩下的只要固定right也就是i,更新当前的高度cur和left,就可以求出每个高度所对应的最大面积了
        int n = heights.length;
        int result = 0;
        if (n == 0) {
            return result;
        }
        // 复制数组，左侧和右侧补充0值
        int[] newHeights = new int[n + 2];
        System.arraycopy(heights, 0, newHeights, 1, n);
        // 单调栈：记录高度
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < newHeights.length; i++) {
            // 单调递增栈：如果当前元素比栈顶元素小，说明当前元素是栈顶元素的右边界
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                // 栈顶元素弹栈
                Integer pop = stack.pop();
                // 求高
                int height = newHeights[pop];
                // 求宽：右边界-左边界-1
                int width = i - stack.peek() - 1;
                result = Math.max(result, height * width);
            }
            stack.push(i);
        }

        return result;
    }

    /**
     * 84. 柱状图中最大的矩形(暴力循环：寻找矩形左右边界)
     *
     * @param heights heights
     * @return 最大的矩形
     */
    public int largestRectangleAreaViolenc(int[] heights) {
        int n = heights.length;
        int result = 0;
        if (n == 0) {
            return result;
        }
        for (int i = 0; i < n; i++) {
            int currHeight = heights[i];
            // 分别循环寻找左边界
            int left = i;
            while (left > 0 && heights[left - 1] >= currHeight) {
                left--;
            }
            int right = i;
            while (right < n - 1 && heights[right + 1] >= currHeight) {
                right++;
            }
            int currArea = (right - left + 1) * currHeight;
            result = Math.max(result, currArea);
        }

        return result;
    }

    /**
     * 739. 每日温度
     *
     * @param temperatures temperatures
     * @return 下一个更高温度出现在几天后
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        // 初始化结果数组，用于存储每个位置等待多少天才会升高温度，初始值为0
        int[] result = new int[length];
        // 辅助栈：栈中存放的是温度较小的位置索引
        Stack<Integer> minousIndexStack = new Stack<>();
        for (int i = 0; i < length; i++) {
            // 栈不为空且当前温度大于栈顶温度时
            while (!minousIndexStack.isEmpty() && temperatures[i] > temperatures[minousIndexStack.peek()]) {
                // 获取栈顶位置索引：弹出栈顶元素
                Integer popIndex = minousIndexStack.pop();
                // 计算并更新结果数组对应位置的天数差（即需要等待的天数）
                result[popIndex] = i - popIndex;
            }

            // 当前温度索引入栈
            minousIndexStack.push(i);
        }
        return result;
    }

    /**
     * 394. 字符串解码
     *
     * @param s s
     * @return 字符串解码
     */
    public String decodeString(String s) {
        // 括号内嵌套括号，需要从内向外生成与拼接字符串，这与栈的先入后出特性对应
        // 结果栈：每次碰到左括号，需要把结果入栈，处理括号内的字符串
        Stack<StringBuilder> resultStack = new Stack<>();
        // 倍数栈: 每次碰到倍数k，需要把k入栈，处理括号内的字符串
        Stack<Integer> kStack = new Stack<>();
        // 结果字符串
        StringBuilder result = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            // 如果是数字
            if (ch >= '0' && ch <= '9') {
                // 可能是多位数的情况，比如126这种
                k = ch - '0' + k * 10;
            } else if (ch == '[') {
                // 如果是左括号：将当前结果和倍数入栈，便于后续处理括号内的字符串
                resultStack.push(result);
                kStack.push(k);
                // 同时重置当前结果，便于后续处理括号内的字符串
                result = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // 如果是右括号: 重复拼接当前结果字符串result，并将栈内结果弹栈
                Integer stackCount = kStack.pop();
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < stackCount; i++) {
                    temp.append(result);
                }
                StringBuilder stackStr = resultStack.pop();
                stackStr.append(temp);
                result = stackStr;
            } else {
                // 普通字母类字符，直接拼接到结果字符串
                result.append(ch);
            }
        }
        return result.toString();
    }

    // ------------------------ ------- ------------------------
    // ------------------------ 二分查找 ------------------------
    // ------------------------ ------- ------------------------

    /**
     * 4. 寻找两个正序数组的中位数
     *
     * @param nums1 nums1
     * @param nums2 nums2
     * @return 中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        // 先合并两个数组，然后求中位数
        // 记录合并的个数
        int count = 0;
        // nums1已合并的个数
        int i = 0;
        // nums2已合并的个数
        int j = 0;
        while (count < m + n) {
            // s1数组是否已遍历完
            if (i == m) {
                // 循环遍历s2填充即可
                while (j < n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            // s2数组是否已遍历完
            if (j == n) {
                // 循环遍历s2填充即可
                while (i < m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }

            // 归并合并
            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }

        // 求中位数
        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * @param nums nums
     * @return 最小值
     */
    public int findMin(int[] nums) {
        int length = nums.length;
        // 寻找搜索区间
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[length - 1]) {
                left = mid + 1;
            } else if (nums[mid] < nums[length - 1]) {
                right = mid;
            }
        }
        return nums[right];
    }

    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums   nums
     * @param target target
     * @return 搜索旋转排序数组
     */
    public int searchRotatedArray(int[] nums, int target) {
        // 关键点：寻找搜索区间。虽然经历了旋转，但必然会有一个是单调增长区间，不断在单调区间搜索即可
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 寻找单调区间
            if (nums[left] <= nums[mid]) {
                // 左半区是单调递增区间，且目标值小于中间值
                // 然后进一步判断左边界值，看是否位于该区间
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // 说明目标值在右侧区间
                    left = mid + 1;
                }
            } else {
                // 右半区是单调区间，或目标值在右半区
                // 然后进一步判断边界值，看是否位于该区间
                if (nums[right] >= target && target > nums[mid]) {
                    // 左指针移到右半区
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 35. 搜索插入位置
     *
     * @param nums   nums
     * @param target target
     * @return 插入位置
     */
    public int searchInsert(int[] nums, int target) {
        // 实际就是寻找目标的左边界索引
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                // 寻找左边界：保持左指针不变，不断左移右指针进行搜索
                right = mid;
            }
        }
        return left;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums   nums
     * @param target target
     * @return 查找元素的第一个和最后一个位置
     */
    public int[] searchRange(int[] nums, int target) {
        int lastIndex = search(nums, target);
        int firstIndex = search02(nums, target);
        return new int[] {firstIndex, lastIndex};
    }

    /**
     * 704. 二分查找（可寻找右侧边界值写法）
     *
     * @param nums   nums
     * @param target target
     * @return 索引
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 固定右指针，移动左指针，从第一个找到的目标值索引位置不断向右搜索
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left - 1 < 0 || left - 1 >= nums.length) {
            return -1;
        }
        return nums[left - 1] == target ? left - 1 : -1;
    }

    /**
     * 704. 二分查找（可寻找左侧边界值写法）
     *
     * @param nums   nums
     * @param target target
     * @return 索引
     */
    public int search02(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 收缩【搜索区间】的上界，在区间[left, mid-1]不断继续搜索，即从第一个找到的目标值继续往左收缩，达到锁定左侧边界的目的
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left < 0 || left >= nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * 704. 二分查找
     *
     * @param nums   nums
     * @param target target
     * @return 索引
     */
    public int search01(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public void testArrays() {
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result = trap(nums);
        assertEquals(6, result);
        nums = new int[] {4, 2, 0, 3, 2, 5};
        result = trap(nums);
        assertEquals(9, result);
    }

    private void assertEquals(Object o1, Object o2) {
        System.out.println();
    }

    public void testListNode() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        boolean palindrome = isPalindrome(head);
        assertTrue(palindrome);
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root 二叉树
     * @return 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        // 分解解法：二叉树的最大深度，也就是求左右子树的最大深度
        if (root == null) {
            return 0;
        }
        int rootMaxDepth = 0;
        // 求左右子树的最大深度
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        rootMaxDepth = Math.max(leftMaxDepth, rightMaxDepth);
        return rootMaxDepth + 1;
    }

    /**
     * 合并K个排序链表(优先级队列)
     *
     * @param lists lists
     * @return ListNode
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 小顶堆优先级队列
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
        // 首先遍历链表，将节点放到优先级队列中
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        // 然后依次将节点取出
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (!queue.isEmpty()) {
            ListNode small = queue.poll();
            curr.next = small;
            curr = curr.next;
            if (small.next != null) {
                queue.offer(small);
            }
        }

        return dummy.next;
    }

    /**
     * 合并K个排序链表
     *
     * @param lists lists
     * @return ListNode
     */
    public ListNode mergeKListsDC(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 分治法：合并k个分解成两两合并
        return mergeKListsDivide(lists, 0, lists.length - 1);
    }

    private ListNode mergeKListsDivide(ListNode[] lists, int left, int right) {
        // 递归(分解)结束条件：无法继续拆分了
        if (left == right) {
            return lists[left];
        }

        // 求中间节点：递归分解
        int mid = left + (right - left) / 2;
        ListNode leftNode = mergeKListsDivide(lists, left, mid);
        ListNode rightNode = mergeKListsDivide(lists, mid + 1, right);

        // 分治后：两两合并
        return mergeTwoList(leftNode, rightNode);
    }

    /**
     * 合并K个排序链表
     *
     * @param lists lists
     * @return ListNode
     */
    public ListNode mergeKListsLoop(ListNode[] lists) {
        // 顺序两两合并有序链表
        ListNode result = null;
        for (ListNode node : lists) {
            result = mergeTwoList(result, node);
        }
        return result;
    }

    private ListNode mergeTwoList(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy, left = a, right = b;
        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }
        curr.next = left != null ? left : right;
        return dummy.next;
    }

    public void testSortList() {
        ListNode head = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(3);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode result = sortList(head);
    }

    /**
     * 148. 排序链表
     *
     * @param head head
     * @return ListNode
     */
    public ListNode sortList(ListNode head) {
        // 时间复杂度是O(nlogn) 的排序算法包括归并排序、堆排序和快速排序（快速排序的最差时间复杂度是O(n^2))，其中最适合链表的排序算法是归并排序。

        return head;
    }

    public void testReverseKGroup() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode result = reverseKGroup(head, 2);
    }

    /**
     * 25. K 个一组翻转链表
     *
     * @param head h
     * @param k    k
     * @return ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 递归+反转链表：K个一组反转链表，实际就是将区间的链表进行反转[first, Node(k+1))（左闭右开区间），然后递归将所有的k个一组的链表反转即可
        if (head == null) {
            return null;
        }
        // 获取k个节点
        ListNode first = head;
        ListNode exclude = head;
        for (int i = 0; i < k; i++) {
            if (exclude == null) {
                // 如果不足k个，无需反转
                return head;
            }
            exclude = exclude.next;
        }
        // 针对[first, Node(k+1))（左闭右开区间）进行反转：此时头节点由first变成了反转后的newFirst，而尾节点则变成了first
        ListNode newFirst = reverseRangeList(first, exclude);
        // 递归调用，将后续的节点递归调用进行反转，同时追加到新的尾节点first中去
        first.next = reverseKGroup(exclude, k);
        return newFirst;
    }

    /**
     * 反转指定区间的链表
     */
    public ListNode reverseRangeList(ListNode head, ListNode exclude) {
        // 双指针解法：当前指针遍历链表，并记录上一指针
        ListNode curr = head, pre = null, next;
        while (curr != exclude) {
            // 遍历链表
            next = curr.next;
            // 修改当前节点的引用：反转了，需要指向上一节点
            curr.next = pre;
            // 记录当前节点：本次标记完成，本次节点记录到pre指针
            pre = curr;
            // 移动遍历的指针节点
            curr = next;
        }
        // 反转后，pre就是头节点
        return pre;
    }

    /**
     * 反转链表
     *
     * @param head head
     * @return ListNode
     */
    public ListNode reverseListNew(ListNode head) {
        // 双指针解法：当前指针遍历链表，并记录上一指针
        ListNode curr = head, pre = null, next;
        while (curr != null) {
            // 遍历链表
            next = curr.next;
            // 修改当前节点的引用：反转了，需要指向上一节点
            curr.next = pre;
            // 记录当前节点：本次标记完成，本次节点记录到pre指针
            pre = curr;
            // 移动遍历的指针节点
            curr = next;
        }
        // 反转后，pre就是头节点
        return pre;
    }

    public void testSwapPairs() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode listNode = swapPairs(head);
    }

    /**
     * 24. 两两交换链表中的节点(递归解法)
     *
     * @param head head
     * @return ListNode
     */
    public ListNode swapPairs(ListNode head) {
        // 递归解法：两两比对，然后后续节点递归调用解决
        if (head == null || head.next == null) {
            return head;
        }
        // 获取交换后的首节点
        ListNode newFirst = head.next;
        head.next = swapPairs(newFirst.next);
        newFirst.next = head;

        return newFirst;
    }

    /**
     * 24. 两两交换链表中的节点(遍历解法)
     *
     * @param head head
     * @return ListNode
     */
    public ListNode swapPairsTraverse(ListNode head) {
        // 链表技巧：设置一个哑节点，应对空指针问题
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode fast = dummy.next;
        ListNode slow = dummy.next;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            ListNode temp = fast.next;
            pre.next = fast;
            fast.next = slow;
            slow.next = temp;
            // 移动各个节点
            pre = slow;
            fast = temp;
            slow = temp;
        }

        return dummy.next;
    }

    public void testRemoveNthFromEnd() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode listNode = removeNthFromEnd(node1, 2);
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * <p>
     * 栈「先进后出」的原则，我们弹出栈的第 nnn 个节点就是需要删除的节点，并且目前栈顶的节点就是待删除节点的前驱节点
     *
     * @param head head
     * @param n    n
     * @return ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 链表技巧：设置一个哑链表，应对空指针问题
        ListNode dummy = new ListNode(0, head);
        // 遍历链表依次放到栈中
        Deque<ListNode> deque = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            deque.push(cur);
            cur = cur.next;
        }
        // 然后出栈，倒数第个为需要删除的节点，且此时栈顶元素就是删除节点的前驱节点
        while (n > 0) {
            deque.pop();
            n--;
        }
        ListNode peek = deque.peek();
        peek.next = peek.next.next;
        return dummy.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     *
     * @param head head
     * @param n    n
     * @return ListNode
     */
    public ListNode removeNthFromEndDoubleIndex(ListNode head, int n) {
        // 链表技巧：设置一个哑链表，应对空指针问题
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 双指针：快指针先跑n步，然后两个指针再同时跑，快指针结束时慢指针刚好到倒数的索引位置
        ListNode fast = dummy;
        ListNode slow = dummy;
        // 快指针先跑n步
        while (n >= 0) {
            fast = fast.next;
            n--;
        }
        // 然后快慢指针同时跑
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 删除倒数的第N个结点
        slow.next = slow.next.next;
        return dummy.next;
    }

    public void testMergeTwoLists() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        boolean palindrome = isPalindrome(node1);
        assertTrue(palindrome);
    }

    /**
     * 21. 合并两个有序链表
     *
     * @param list1 list1
     * @param list2 list2
     * @return ListNode
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        // 双指针分别遍历两个链表
        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                curr.next = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                curr.next = new ListNode(p2.val);
                p2 = p2.next;
            }
            curr = curr.next;
        }
        curr.next = p1 == null ? p2 : p1;
        return dummy.next;
    }

    /**
     * 21. 合并两个有序链表
     *
     * @param list1 list1
     * @param list2 list2
     * @return ListNode
     */
    public ListNode mergeTwoListsDoubleIndex(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        // 双指针分别遍历两个链表
        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                curr.next = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                curr.next = new ListNode(p2.val);
                p2 = p2.next;
            }
            curr = curr.next;
        }
        curr.next = p1 == null ? p2 : p1;
        return dummy.next;
    }

    public void testisPalindrome() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        boolean palindrome = isPalindrome(node1);
        assertTrue(palindrome);
    }

    /**
     * 234. 回文链表(快慢指针法，O(1)空间复杂度)
     *
     * @param head head
     * @return boolean
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 快慢指针：找到前半部分链表的尾节点
        ListNode firstHalfEnd = getEndOfFirstHalf(head);
        // 反转后半部分链表：便于后续回文判断
        ListNode reverseSecondHalfStart = reverseList(firstHalfEnd.next);
        boolean result = true;
        // 判断是否回文：遍历前半段和反转的后半段节点值
        ListNode p1 = head;
        ListNode p2 = reverseSecondHalfStart;

        while (result && p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 复原原链表节点顺序
        firstHalfEnd.next = reverseList(reverseSecondHalfStart);

        // 结果返回
        return result;
    }

    private ListNode getEndOfFirstHalf(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            // 慢指针移动一步，快指针移动两步
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 234. 回文链表
     *
     * @param head head
     * @return boolean
     */
    public boolean isPalindromeArray(ListNode head) {
        if (head == null) {
            return true;
        }
        // 复制到数组
        List<Integer> copyList = new ArrayList<>();
        while (head != null) {
            copyList.add(head.val);
            head = head.next;
        }
        // 双指针遍历判断
        int left = 0, right = copyList.size() - 1;
        while (left < right) {
            if (!Objects.equals(copyList.get(left), copyList.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 反转链表
     *
     * @param head head
     * @return ListNode
     */
    public ListNode reverseList(ListNode head) {
        // 双指针解法：当前指针遍历链表，并记录上一指针
        ListNode curr = head, pre = null;
        while (curr != null) {
            ListNode temp = curr.next;
            // 修改当前节点的引用：指向上一节点
            curr.next = pre;
            // 记录当前节点：本次标记完成，本次节点记录到pre指针
            pre = curr;
            // 移动到下一节点
            curr = temp;
        }
        return pre;
    }

    /**
     * 相交链表
     *
     * @param headA headA
     * @param headB headB
     * @return ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 设A的长度为a+c，B的长度为b+c；其中c为A、B的公共部分；
        // 拼接AB、BA：A+B=a+c+b+c B+A=b+c+a+c；由于a+c+b=b+c+a，因此二者必定在c的起始点处相遇
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {
            // A遍历完自身的a+c，继续遍历拼接的B
            // 将两条链从尾端对齐，如果相交，则会提前在相交点相遇，如果没有相交点，则会在最后null相遇
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    public void testSpiralOrder() {
        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> result = spiralOrder(nums);
        List<Integer> expected = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        assertEquals(expected, result);
        nums = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        result = spiralOrder(nums);
        expected = Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        assertEquals(expected, result);
        nums = new int[][] {{2, 5, 8}, {4, 0, -1}};
        result = spiralOrder(nums);
        expected = Arrays.asList(2, 5, 8, -1, 0, 4);
        assertEquals(expected, result);
    }

    /**
     * 54. 螺旋矩阵
     *
     * @param matrix matrix
     * @return List<Integer>
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // 结果集
        List<Integer> result = new ArrayList<>();
        // 定义边界值：依次按从左到右、从上到下、从右往左、从下往上循环遍历
        int left = 0, right = matrix[0].length - 1;
        int top = 0, buttom = matrix.length - 1;
        while (true) {
            // 1.从左往右遍历（高度不变，长度递增）
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            // 即将往下遍历时，先判断是否触碰下边界
            if (++top > buttom) {
                break;
            }
            // 2.然后从上往下遍历
            for (int i = top; i <= buttom; i++) {
                result.add(matrix[i][right]);
            }
            // 即将往左遍历时，先判断是否触碰左边界
            if (--right < left) {
                break;
            }
            // 3.然后从右往左遍历
            for (int i = right; i >= left; i--) {
                result.add(matrix[buttom][i]);
            }
            // 即将往上遍历时，先判断是否触碰上边界
            if (--buttom < top) {
                break;
            }

            // 4.最后从下往上遍历（长度位置不变，高度递减）
            for (int i = buttom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            // 即将往右遍历时，先判断是否触碰右边界
            if (++left > right) {
                break;
            }
        }
        return result;
    }

    public void testSetZeroes() {
        int[][] nums = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] result = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        setZeroes(nums);
        assertArrayEquals(nums, result);
        nums = new int[][] {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(nums);
        result = new int[][] {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}};
        assertArrayEquals(nums, result);
    }

    private void assertArrayEquals(int[][] nums, int[][] result) {
        System.out.println();
    }

    /**
     * 73. 矩阵置零
     */
    public void setZeroes(int[][] matrix) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> columns = new ArrayList<>();

        // 找出0元素，记录行列索引
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        // 元素置0处理
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rows.contains(i) || columns.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void testFirstMissingPositive() {
        int[] nums = {1, 2, 0};
        int result = firstMissingPositive(nums);
        assertEquals(3, result);
        nums = new int[] {3, 4, -1, 1};
        result = firstMissingPositive(nums);
        assertEquals(2, result);
        nums = new int[] {7, 8, 9, 11, 12};
        result = firstMissingPositive(nums);
        assertEquals(1, result);
        nums = new int[] {1};
        result = firstMissingPositive(nums);
        assertEquals(2, result);
    }

    /**
     * 41. 缺失的第一个正数
     */
    public int firstMissingPositive(int[] nums) {
        // 将数组视为哈希表，要找的数就在 [1, N + 1]
        // 里，就把1这个数放到下标为0的位置，2这个数放到下标为1的位置，按照这种思路整理一遍数组。然后我们再遍历一次数组，第1个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数
        int length = nums.length;
        // 这个思想就相当于我们自己编写哈希函数，这个哈希函数的规则特别简单，那就是数值为 i 的数映射到下标为 i - 1 的位置
        for (int i = 0; i < length; i++) {
            while (nums[i] > 0 && nums[i] <= length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        // 交换位置
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 238. 除自身以外数组的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        // 利用前缀和的思想：当前位置的结果就是它左部分的乘积再乘以它右部分的乘积。因此需要进行两次遍历，第一次遍历用于求左部分的乘积，第二次遍历在求右部分的乘积的同时，再将最后的计算结果一起求出来。
        int length = nums.length;
        // 结果集：存储乘积
        int[] result = new int[length];
        // 边界值：代表第一个元素左边和最后一个元素右边的乘积，因为任意数乘以1都还是自身
        int tmp = 1;
        result[0] = 1;
        // 求左侧乘积
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        // 求右侧乘积以及总的乘积
        for (int i = length - 2; i >= 0; i--) {
            // 先求右侧乘积
            tmp *= nums[i + 1];
            result[i] = result[i] * tmp;
        }
        return result;
    }

    /**
     * 56. 合并区间
     */

    public void testMergeArrays() {
        int[][] nums = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = merge(nums);
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        assertArrayEquals(expected, result);
        nums = new int[][] {{1, 4}, {1, 5}};
        result = merge(nums);
        expected = new int[][] {{1, 5}};
        assertArrayEquals(expected, result);
        nums = new int[][] {{1, 4}, {2, 3}};
        result = merge(nums);
        expected = new int[][] {{1, 4}};
        assertArrayEquals(expected, result);

    }

    public int[][] merge(int[][] intervals) {
        // 数组排序：第一个元素升序、第二个元素降序排列
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        // 结果集：合并重叠区间后的数组
        int[][] result = new int[intervals.length][2];
        // 结果集最后一个元素索引
        int lastIdx = -1;
        for (int[] interval : intervals) {
            if (lastIdx == -1 || interval[0] > result[lastIdx][1]) {
                // 不重叠的数组，直接添加到结果集
                result[++lastIdx] = interval;
            } else {
                // 有重叠的区间，取重叠区间起始点（因为已经排序了，起点肯定小），以及终点的最大值合并（终点取重叠区间终点的最大值）
                result[lastIdx][1] = Math.max(result[lastIdx][1], interval[1]);
            }
        }
        return Arrays.copyOf(result, lastIdx + 1);
    }

    public void testSubarraySum() {
        int[] nums = {1, 1, 1};
        int result = subarraySum(nums, 2);
        assertEquals(2, result);
        nums = new int[] {1, 2, 3};
        result = subarraySum(nums, 3);
        assertEquals(2, result);
        nums = new int[] {8, 2, -2, 3};
        result = subarraySum(nums, 8);
        assertEquals(2, result);
    }

    /**
     * 560. 和为 K 的子数组
     */
    public int subarraySum(int[] nums, int k) {
        // 思路：通过计算前缀和，我们可以将问题转化为求解两个前缀和之差等于k的情况
        // 前缀和Map：记录数组连续元素的和，key是前缀和，value是个数，不用求索引，记录个数即可
        Map<Integer, Integer> preSumMap = new HashMap<>();
        // 初始化前缀和为0的次数为1：比如[3..]和3这种情况，需要这种边界值
        preSumMap.put(0, 1);
        // 前缀和
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;
            // 问题转化为求解两个前缀和之差等于k的情况
            if (preSumMap.containsKey(preSum - k)) {
                // 遍历数组时，每次累加得到的和 sum 都会被记录在 map 中，并且会检查是否存在 sum - k 的键。如果存在，表示从之前某个位置到当前位置的子数组和为 k。这是因为 sum - (sum - k)
                // = k，也就是说，如果我们在累加和为 sum 的位置发现了之前出现过的累加和 sum - k，那么说明从上次 sum - k 出现到当前位置的子数组和为 k。因此，我们可以通过 map.get(sum
                // - k) 来获取到符合条件的子数组的个数
                count += preSumMap.get(preSum - k);
            }
            //
            preSumMap.put(preSum, preSumMap.getOrDefault(preSum, 0) + 1);
        }

        return count;
    }

    public void testMinSubArrayLen() {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int result = minSubArrayLen(7, nums);
        assertEquals(2, result);
    }

    /**
     * 209. 长度最小的子数组
     *
     * @param target target
     * @param nums   nums
     * @return 数组中满足其总和大于等于 target 的长度最小的连续子数组并返回其长度
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;

        // 滑动窗口：右边界主动滑动，左边界被动移动的方法
        // 左右指针：双指针，表示当前遍历的区间[left, right)，闭区间
        int left = 0, right = 0;
        // 记录最值：保存最大的满足题目要求的 子数组/子串 长度
        int res = n + 1;
        // 记录状态值：比如Map记录窗口、string记录合法字串、int记录合法个数等，本题中就是target
        int windowSum = 0;

        // 滑动窗口模板
        while (right < n) {
            // num[r]进入窗口
            // 判断并进行窗口内数据的一系列更新
            windowSum += nums[right];

            // 判断左侧窗口是否要收缩：窗口的和达标了，此时尝试缩小窗口
            while (windowSum >= target) {
                // 找到了一个符合题意要求的 子数组/子串
                // 需要更新结果
                res = Math.min(res, right - left + 1);
                windowSum -= nums[left];
                // 缩小窗口：左指针一直移动直至不满足条件(窗口可能只有1个元素)
                left++;
            }

            // 移动右指针，去探索新的区间
            right++;
        }

        return res == n + 1 ? 0 : res;
    }

    // ------------------------ ------- ------------------------
    // ------------------------ 二分查找 ------------------------
    // ------------------------ ------- ------------------------

    /**
     * 1004. 最大连续1的个数 III
     *
     * @param nums nums
     * @param k    k
     * @return 最大连续1的个数
     */
    public int longestOnes(int[] nums, int k) {

        // 左右指针：双指针，表示当前遍历的区间[left, right)，闭区间
        int left = 0, right = 0;
        // 记录最值：保存最大的满足题目要求的 子数组/子串 长度
        int res = 0;
        // 记录状态值：比如Map记录窗口、string记录合法字串、int记录合法个数等
        int zeros = 0;

        int n = nums.length;
        // 滑动窗口模板
        while (right < n) {
            // 判断并进行窗口内数据的一系列更新
            if (nums[right] == 0) {
                zeros++;
            }

            // 判断左侧窗口是否要收缩：0的个数超过允许值
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }

            // 到 while 结束时，我们找到了一个符合题意要求的 子数组/子串
            // 需要更新结果
            res = Math.max(res, right - left + 1);

            // 移动右指针，去探索新的区间
            right++;
        }

        return res;
    }

    public void testFindAnagrams() {
        String s = "cbaebabacd", p = "abc";
        List<Integer> anagrams = findAnagrams(s, p);
        assertEquals(Arrays.asList(0, 6), anagrams);
        s = "abab";
        p = "ab";
        anagrams = findAnagrams(s, p);
        assertEquals(Arrays.asList(0, 1, 2), anagrams);
        s = "baa";
        p = "aa";
        anagrams = findAnagrams(s, p);
        assertEquals(Collections.singletonList(1), anagrams);
    }

    /**
     * 438. 找到字符串中所有字母异位词
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 结果集：存储异位词的起始索引
        List<Integer> resultIndex = new ArrayList<>();
        // 目标词频Map：记录目标字符串中的字符及词频
        Map<Character, Integer> target = new HashMap<>();
        for (char c : p.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }
        // 窗口Map：存储滑动窗口中有效字符出现的次数
        Map<Character, Integer> window = new HashMap<>();
        // 初始化窗口指针、有效字符长度
        int left = 0, right = 0, valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 比对并更新窗口的数据
            if (target.containsKey(c)) {
                // 判断是否是目标字符，如果是，则累加并比较数量；数量相等，则有效字符数量也累计
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(target.get(c))) {
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (right - left >= p.length()) {
                if (valid == target.size()) {
                    // 有效字符个数与目标字符串长度一致，说明是匹配的异位词，记录起始索引
                    resultIndex.add(left);
                }
                char startChar = s.charAt(left);
                left++;
                // 进行窗口数据更新
                if (target.containsKey(startChar)) {
                    // 移动窗口后，左指针去除了；所以要判断有效字符长度，如果有效数匹配了，那么左指针移动后，有效数就要减去1
                    if (window.get(startChar).equals(target.get(startChar))) {
                        valid--;
                    }
                    window.put(startChar, window.get(startChar) - 1);
                }
            }
        }

        return resultIndex;
    }

    public void testLengthOfLongestSubstring() {

    }

    /**
     * 3. 无重复字符的最长子串
     *
     * @param s s
     */
    public int lengthOfLongestSubstring(String s) {
        // 双指针+滑动窗口
        // 字符数组记录出现的字符
        int[] existedChars = new int[128];
        int start = 0, max = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (existedChars[c] > 0) {
                start = Math.max(start, existedChars[c]);
            }
            max = Math.max(max, end - start + 1);
            // 记录当前字符索引，加一是指后续拿出来直接用做新的起点
            existedChars[c] = end + 1;
        }

        return max;
    }

    public void testTrap() {
        // 6
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int area = trap(nums);
        // 9
        nums = new int[] {4, 2, 0, 3, 2, 5};
        area = trap(nums);
    }

    /**
     * 42. 接雨水
     *
     * @param height height
     */
    public int trap(int[] height) {
        // 将每个柱子的雨水累加即总量
        int sum = 0;
        // 关键点：需要获取每个柱子左右两侧的最大高度
        // 左右指针：分别指向左右两边界的列；
        int left = 0, right = height.length - 1;
        // 左指针的左边最大高度、右指针的右边最大高度
        int maxLeft = height[left], maxRight = height[right];
        // 注意，最两边的列存不了水
        left++;
        right--;
        // 移动左右指针：比较两侧最值，然后向中间靠拢
        while (left <= right) {
            maxLeft = Math.max(maxLeft, height[left]);
            maxRight = Math.max(maxRight, height[right]);
            // 结合两个辅助指针：左侧最大值，右侧最大值，哪一侧的最值确定，就移动并求解柱子的雨水
            if (maxLeft < maxRight) {
                // 左指针的leftMax比右指针的rightMax矮
                // 说明：左指针的右边至少有一个板子 > 左指针左边所有板子
                // 根据水桶效应，保证了左指针当前列的水量决定权在左边
                // 那么可以计算左指针当前列的水量：左边最大高度-当前列高度
                sum += maxLeft - height[left];
                left++;
            } else {
                sum += maxRight - height[right];
                right--;
            }
        }
        return sum;
    }

    /**
     * 42. 接雨水
     *
     * @param height height
     */
    public int trapWithArray(int[] height) {
        // 将每个柱子的雨水累加即总量
        // 结合两个辅助数组
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        maxLeft[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = Math.max(height[i - 1], maxLeft[i - 1]);
        }

        maxRight[0] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i + 1], maxRight[i + 1]);
        }
        int sum = 0;
        // 每个柱子的雨水累加即总量
        for (int i = 1; i < height.length - 1; i++) {
            int maxHeight = Math.min(maxLeft[i], maxRight[i]);
            if (height[i] < maxHeight) {
                sum += (maxHeight - height[i]);
            }
        }
        return sum;
    }

    public void testThreeSum() {
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int area = maxArea(nums);
        nums = new int[] {1, 1};
        area = maxArea(nums);
    }

    /**
     * 15. 三数之和
     *
     * @param nums nums
     */
    public List<List<Integer>> threeSum(int[] nums) {

        return new ArrayList<>();
    }

    public void testMaxArea() {
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int area = maxArea(nums);
        nums = new int[] {1, 1};
        area = maxArea(nums);
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height height
     */
    public int maxArea(int[] height) {
        // 双指针解法：每次移动短板指针
        // 因为：宽度变小的时候：总是替换最小的高度，高度变大有可能乘积变大；如果替换掉最大的高度，乘积一定变小。这样好理解一点。
        int area = 0, left = 0, right = height.length - 1;
        while (left < right) {
            area = Math.max(area, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return area;
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height height
     */
    public int maxAreaCostLongTime(int[] height) {
        int area = 0;
        int right = 1;
        for (int i = 0; i < height.length; i++) {
            while (right < height.length) {
                int min = Math.min(height[i], height[right]);
                int length = right - i;
                area = Math.max(area, min * length);
                right++;
            }
            right = i + 1;
        }
        return area;
    }

    public void testGroupAnagrams() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams1(strs);
    }

    public void testLongestConsecutive() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int length = longestConsecutive(nums);
        nums = new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        length = longestConsecutive(nums);
    }

    public void testMoveZeroes() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        nums = new int[] {0};
        moveZeroes(nums);
        nums = new int[] {-1, 1, -66, 3, 12};
        moveZeroes(nums);
    }

    /**
     * 283. 移动零 双指针法（快慢指针法）： 通过一个快指针和慢指针在一个for循环下完成两个for循环的工作。
     *
     * @param nums nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        // 双指针解法：如果数组没有0，那么快慢指针始终指向同一个位置，每个位置自己和自己交换；如果数组有0，快指针先走一步，此时慢指针对应的就是0，所以要交换。
        // index指针用于找中间点0
        int zeroIndex = 0;
        // i指针用于找中间点右侧非0元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[zeroIndex];
                nums[zeroIndex++] = temp;
            }
        }
    }

    /**
     * 283. 移动零
     *
     * @param nums nums
     */
    public void moveZeroes222(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] != 0) {
                    nums[i] = nums[j];
                    nums[j] = 0;
                    break;
                }
            }
        }
    }

    /**
     * 283. 移动零(不符合：打乱了顺序)
     *
     * @param nums nums
     */
    public void moveZeroesError(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        int zeroIndex = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (zeroIndex <= i) {
                return;
            }
            if (nums[i] == 0) {
                nums[i] = nums[zeroIndex];
                nums[zeroIndex] = 0;
                zeroIndex--;
            }
        }
    }

    /**
     * 128. 最长连续序列
     * https://leetcode.cn/problems/longest-consecutive-sequence/description/?envType=study-plan-v2&envId=top-100-liked
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 去重
        Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toCollection(LinkedHashSet::new));
        int longest = 1;
        for (Integer num : numsSet) {
            if (!numsSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;
                while (numsSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                longest = Math.max(longest, currentLength);
            }
        }

        return longest;
    }

    /**
     * 49. 字母异位词分组 https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked
     *
     * @param strs strs
     * @return List<List < String>>
     */
    public static List<List<String>> groupAnagrams1(String[] strs) {
        if (strs.length == 1 && strs[0].isEmpty()) {
            return Collections.singletonList(Collections.singletonList(""));
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            List<String> strList = map.getOrDefault(key, new ArrayList<>());
            strList.add(str);
            map.putIfAbsent(key, strList);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 49. 字母异位词分组 https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked
     *
     * @param strs strs
     * @return List<List < String>>
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            return new String(charArray);
        })).values());
    }
}