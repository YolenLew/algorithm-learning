package com.lew.algo.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yolen
 * @date 2024/1/9
 */
public class Top100 {
    public static void main(String[] args) {
        String str = "abcabcbb";
        int length = lengthOfLongestSubstring(str);
        System.out.println(length);
        str = "pwwkew";
        length = lengthOfLongestSubstring(str);
        System.out.println(length);
        str = "bbbbb";
        length = lengthOfLongestSubstring(str);
        System.out.println(length);
    }

    /**
     * 438. 找到字符串中所有字母异位词
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 滑动窗口，双指针
        // 目标字符Map：记录匹配字符串的目标字符及个数
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        // 窗口map：不断移动窗口进行匹配
        Map<Character, Integer> window = new HashMap<>();
        // 结果集
        List<Integer> result = new ArrayList<>();
        // 初始化左右指针、有效匹配词频
        int left = 0, right = 0, valid = 0;
        while (right < s.length()) {
            // 首先移动右指针匹配字符
            char endChar = s.charAt(right);
            right++;
            if (targetMap.containsKey(endChar)) {
                window.put(endChar, window.getOrDefault(endChar, 0) + 1);
                // 此时如果目标字符匹配且数目一致，则有效字符数加一
                if (window.get(endChar).equals(targetMap.get(endChar))) {
                    valid++;
                }
            }
            // 缩小窗口：当窗口长度大于等于目标长度时，尝试缩小窗口
            while (right - left >= p.length()) {
                if (valid == targetMap.size()) {
                    result.add(left);
                }
                char startChar = s.charAt(left);
                left++;
                // 更新窗口词频
                if (targetMap.containsKey(startChar)) {
                    if (targetMap.get(startChar).equals(window.get(startChar))) {
                        // 如果是匹配的字符且有效词频达到了，那么左指针移动之后，有效词频减一
                        valid--;
                    }
                    window.put(startChar, window.getOrDefault(startChar, 0) - 1);
                }
            }
        }
        return result;
    }

    public static void testLengthOfLongestSubstring(String[] args) {
        String str = "abcabcbb";
        int length = lengthOfLongestSubstring(str);
        System.out.println(length);
        str = "pwwkew";
        length = lengthOfLongestSubstring(str);
        System.out.println(length);
        str = "bbbbb";
        length = lengthOfLongestSubstring(str);
        System.out.println(length);
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * @param s s
     * @return maxLength
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        int max = 0;
        // 滑动窗口左指针，即子串的起始位置
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (charMap.containsKey(s.charAt(i))) {
                // 关键：遇到重复元素的就把重复元素下标+1作为子串的起始位置left，同时为了避免左指针倒退，所以需要比较取较大值
                left = Math.max(left, charMap.get(s.charAt(i)) + 1);
            }
            // 记录当前字符索引位置
            charMap.put(s.charAt(i), i);
            // 更新最大子串长度
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static void testThreeSum(String[] args) {
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> threeSum = threeSum(nums);
        System.out.println(threeSum);
        nums = new int[] {0, 1, 1};
        threeSum = threeSum(nums);
        System.out.println(threeSum);
    }

    /**
     * 三数之和
     *
     * @param nums nums
     * @return List<List < Integer>>
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                //如果遍历的起始元素大于0，就直接退出
                //原因，此时数组为有序的数组，最小的数都大于0了，三数之和肯定大于0
                if (nums[i] > 0) {
                    break;
                }
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //在将左指针和右指针移动的时候，先对左右指针的值，进行判断
                    //如果重复，直接跳过。
                    //去重，因为 i 不变，当此时 l取的数的值与前一个数相同，所以不用在计算，直接跳
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    //将 左指针右移，将右指针左移。
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
