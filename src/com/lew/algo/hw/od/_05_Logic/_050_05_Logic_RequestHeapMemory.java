// (C卷,100分)- 堆内存申请（Java & JS & Python & C）
// 在线OJ刷题
// 题目详情 - 堆内存申请 - Hydro
//
// 题目描述
// 有一个总空间为100字节的堆，现要从中新申请一块内存，内存分配原则为：优先紧接着前一块已使用内存，分配空间足够且最接近申请大小的空闲内存。
//
// 输入描述
// 第1行是1个整数，表示期望申请的内存字节数
//
// 第2到第N行是用空格分割的两个整数，表示当前已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整数分别表示偏移地址和内存块大小，如：
//
// 0 1
// 3 2
//
// 表示 0 偏移地址开始的 1 个字节和 3 偏移地址开始的 2 个字节已被分配，其余内存空闲。
//
// 输出描述
// 若申请成功，输出申请到内存的偏移；
//
// 若申请失败，输出 -1。
//
// 备注
// 若输入信息不合法或无效，则申请失败
// 若没有足够的空间供分配，则申请失败
// 堆内存信息有区域重叠或有非法值等都是无效输入
// 用例
// 输入 1
// 0 1
// 3 2
// 输出 1
// 说明 堆中已使用的两块内存是偏移从0开始的1字节和偏移从3开始的2字节，空闲的两块内存是偏移从1开始2个字节和偏移从5开始95字节，根据分配原则，新申请的内存应从1开始分配1个字节，所以输出偏移为1。

package com.lew.algo.hw.od._05_Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class _050_05_Logic_RequestHeapMemory {
    static class Memory {
        // 内存块起始位置
        int offset;
        // 内存块大小
        int size;

        public Memory(int offset, int size) {
            this.offset = offset;
            this.size = size;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 要申请的内存大小
        int malloc_size = Integer.parseInt(sc.nextLine());

        // 已占用的内存
        ArrayList<Memory> used_memory = new ArrayList<>();
        // 注意注意：不定行数的输入写法要记住！！！
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            // 本地测试使用空行作为结束条件
            if (line.length() == 0)
                break;

            int[] tmp = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            used_memory.add(new Memory(tmp[0], tmp[1]));
        }

        System.out.println(getResult(malloc_size, used_memory));
        System.out.println(getMyResult(malloc_size, used_memory));

    }

    public static void main01(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 申请大小
        int allocateSize = Integer.parseInt(sc.nextLine());

        List<Memory> usedList = new ArrayList<>();
        while (sc.hasNextLine()) {
            int[] heapArray = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            usedList.add(new Memory(heapArray[0], heapArray[1]));
        }

        System.out.println(getMyResult(allocateSize, usedList));
    }

    private static int getMyResult(int allocateSize, List<Memory> usedList) {
        // 申请的内存大小非法，则返回-1
        if (allocateSize <= 0 || allocateSize > 100) {
            return -1;
        }
        // 对占用的内存按照起始位置升序
        usedList.sort(Comparator.comparing(h -> h.offset));

        // 记录最优的申请内存起始位置
        int bestHeapStart = 0;
        // 记录最接近申请内存的空闲内存块大小
        int minAllocateHeapSize = 100;
        // 记录上一次空闲的内存起始位置
        int lastFreeIndex = 0;

        // 遍历已申请内存块
        for (Memory usedMemory : usedList) {
            // 如果占用的内存起始位置 小于 前面一个空闲内存块起始位置，则存在占用内存区域重叠
            // 如果占用的内存起始位置 大于 99，则非法
            if (usedMemory.offset < lastFreeIndex || usedMemory.offset > 99) {
                return -1;
            }
            // 如果占用的内存的大小少于0，则非法
            // 如果占用的内存的大小超过该内存起始位置往后所能申请到的最大内存大小，则无效
            if (usedMemory.size <= 0 || usedMemory.size > 100 - usedMemory.offset) {
                return -1;
            }

            // 空闲内存块地址范围是：free_offset ~ used.offset - 1
            if (usedMemory.offset > lastFreeIndex) {
                // 空闲内存块大小
                int freeMemorySize = usedMemory.offset - lastFreeIndex;
                if (freeMemorySize >= allocateSize && freeMemorySize < minAllocateHeapSize) {
                    bestHeapStart = lastFreeIndex;
                    minAllocateHeapSize = freeMemorySize;
                }
            }

            // 更新：空闲内存的起始位置 = （当前占用内存的结束位置 + 1） = （当前占用内存的起始位置 + 占用大小）
            lastFreeIndex = usedMemory.offset + usedMemory.size;
        }

        // 收尾
        // 当遍历完used_memory后，free_offset会如上图所示指向最后一块空闲内存起始位置，此时该空闲内存块大小为 100 -
        // free_offset，我们需要判断下这个空闲内存块是不是足够申请内存，且是最接近申请内存大小的
        int tailFreeMemory = 100 - lastFreeIndex;
        if (tailFreeMemory >= allocateSize && tailFreeMemory < minAllocateHeapSize) {
            bestHeapStart = lastFreeIndex;
        }

        return bestHeapStart;
    }

    public static int getResult(int malloc_size, ArrayList<Memory> used_memory) {
        // 申请的内存大小非法，则返回-1
        if (malloc_size <= 0 || malloc_size > 100) {
            return -1;
        }

        // 记录最优的申请内存起始位置
        int malloc_offset = -1;
        // 记录最接近申请内存的空闲内存块大小
        int min_malloc_size = 100;

        // 对占用的内存按照起始位置升序
        used_memory.sort((a, b) -> a.offset - b.offset);

        // 记录（相对于已占用内存的前面一个）空闲内存块的起始位置
        int free_offset = 0;

        for (Memory used : used_memory) {
            // 如果占用的内存起始位置 小于 前面一个空闲内存块起始位置，则存在占用内存区域重叠
            // 如果占用的内存起始位置 大于 99，则非法
            if (used.offset < free_offset || used.offset > 99)
                return -1;

            // 如果占用的内存的大小少于0，则非法
            // 如果占用的内存的大小超过该内存起始位置往后所能申请到的最大内存大小，则无效
            if (used.size <= 0 || used.size > 100 - used.offset)
                return -1;

            // 空闲内存块地址范围是：free_offset ~ used.offset - 1
            if (used.offset > free_offset) {
                // 空闲内存块大小
                int free_memory_size = used.offset - free_offset;

                // 如果该空闲内存块大小足够，且最接近申请的内存大小
                if (free_memory_size >= malloc_size && free_memory_size < min_malloc_size) {
                    malloc_offset = free_offset;
                    min_malloc_size = free_memory_size;
                }
            }

            // 更新：空闲内存的起始位置 = （当前占用内存的结束位置 + 1） = （当前占用内存的起始位置 + 占用大小）
            free_offset = used.offset + used.size;
        }

        // 收尾
        // 当遍历完used_memory后，free_offset会如上图所示指向最后一块空闲内存起始位置，此时该空闲内存块大小为 100 -
        // free_offset，我们需要判断下这个空闲内存块是不是足够申请内存，且是最接近申请内存大小的
        int last_free_memory_size = 100 - free_offset;
        if (last_free_memory_size >= malloc_size && last_free_memory_size < min_malloc_size) {
            malloc_offset = free_offset;
        }

        return malloc_offset;
    }
}