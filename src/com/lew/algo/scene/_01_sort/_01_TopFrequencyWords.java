package com.lew.algo.scene._01_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 一个1G大小的一个文件，里面每一行是一个词，词的大小不超过16字节，内存限制大小是1M。返回频数最高的100个词
 * <p>1.使用外部排序和堆的数据结构来实现
 * <p>2.顺序读取文件，对于每个词 x，取 Hash(x)%5000 ，存放到对应的 5000 个小文件中。这样，每个小文件大概为 200k 左右。然后加载每一个小文件并做Hash统计。最后使用堆排序取出前 100
 * 个高频词，使用归并排序进行总排序。
 *
 * @author Yolen
 * @date 2023/6/26
 */
public class _01_TopFrequencyWords {
    private static final int MAX_MEMORY_SIZE = 1_000_000;
    private static final int TOP_K = 100;

    public static void main(String[] args) {
        String inputFile = "input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            Map<String, Integer> wordCounts = new HashMap<>();
            String line;

            // 统计每个词的频数
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");

                for (String word : words) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }

            // 使用最小堆找出频数最高的TOP_K个词
            PriorityQueue<WordFrequency> minHeap =
                    new PriorityQueue<>(Comparator.comparingInt(WordFrequency::getFrequency));
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue());
                if (minHeap.size() < TOP_K) {
                    minHeap.offer(wordFrequency);
                } else if (wordFrequency.compareTo(minHeap.peek()) > 0) {
                    minHeap.poll();
                    minHeap.offer(wordFrequency);
                }
            }

            // 将堆中的结果按照频数从高到低进行排序
            List<WordFrequency> topWords = new ArrayList<>(minHeap);
            topWords.sort(Collections.reverseOrder());

            // 打印结果
            for (WordFrequency wordFrequency : topWords) {
                System.out.println(wordFrequency.getWord() + ": " + wordFrequency.getFrequency());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class WordFrequency implements Comparable<WordFrequency> {
        private final String word;
        private final int frequency;

        WordFrequency(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        String getWord() {
            return word;
        }

        int getFrequency() {
            return frequency;
        }

        @Override
        public int compareTo(WordFrequency other) {
            return Integer.compare(this.frequency, other.frequency);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WordFrequency that = (WordFrequency) o;
            return frequency == that.frequency &&
                    Objects.equals(word, that.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word, frequency);
        }

    }
}
