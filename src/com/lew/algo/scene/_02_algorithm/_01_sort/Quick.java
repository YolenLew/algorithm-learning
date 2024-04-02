package com.lew.algo.scene._02_algorithm._01_sort;

import java.util.Random;

/**
 * 经典算法之快速排序
 *
 * @author Yolen
 * @date 2024/4/1
 */
class Quick {

    public static void sort(int[] nums) {
        // 为了避免出现耗时的极端情况，先随机打乱
        shuffle(nums);
        // 排序整个数组（原地修改）
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 对 nums[lo..hi] 进行切分
        // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
        int p = partition(nums, lo, hi);

        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }

    // 对 nums[lo..hi] 进行切分: 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
    private static int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        // 关于区间的边界控制需格外小心，稍有不慎就会出错
        // 我这里把 i, j 定义为开区间，同时定义：
        // [lo, i) <= pivot；(j, hi] > pivot
        // 之后都要正确维护这个边界区间的定义
        int i = lo + 1, j = hi;
        // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
        while (i <= j) {
            while (i < hi && nums[i] <= pivot) {
                i++;
                // 此 while 结束时恰好 nums[i] > pivot
            }
            while (j > lo && nums[j] > pivot) {
                j--;
                // 此 while 结束时恰好 nums[j] <= pivot
            }
            // 此时 [lo, i) <= pivot && (j, hi] > pivot

            if (i >= j) {
                break;
            }
            // 此时：nums[i] >= priot 且 nums[j] <= priot
            // 所以需要交换这两个位置的值
            swap(nums, i, j);
        }
        // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
        // 需要跟j位置交换：因为j移动到了小于pivot的地方，把j放左边，lo放右边符合要求
        swap(nums, lo, j);
        // 这个时候j就是下一个分界点
        return j;
    }

    // 洗牌算法，将输入的数组随机打乱
    private static void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 生成 [i, n - 1] 的随机数
            int r = i + rand.nextInt(n - i);
            swap(nums, i, r);
        }
    }

    // 原地交换数组中的两个元素
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void sortMy(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // 寻找分界点并排序
        int p = partitionMy(nums, lo, hi);

        // 继续分区排序
        partitionMy(nums, lo, p - 1);
        partitionMy(nums, p + 1, hi);
    }

    // 分区排序
    private static int partitionMy(int[] nums, int lo, int hi) {
        // 选定分界点lo
        int pirvot = nums[lo];

        int i = lo + 1;
        int j = hi;

        while (i <= j) {
            while (i < hi && nums[i] < pirvot) {
                i++;
            }

            while (j > lo && nums[j] > pirvot) {
                j--;
            }

            // 判断是否交叉
            if (i >= j) {
                break;
            }

            // 交换i、j的值
            swap(nums, i, j);
        }

        // 最后：交换lo和j的值
        swap(nums, lo, j);
        return j;
    }
}