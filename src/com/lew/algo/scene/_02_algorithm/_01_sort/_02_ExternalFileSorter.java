package com.lew.algo.scene._02_algorithm._01_sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 读取1TB的数字文件并进行排序
 *
 * @author Yolen
 * @date 2023/6/19
 */
public class _02_ExternalFileSorter {
    /**
     * 每次读取的行数
     */
    private static final int BUFFER_SIZE = 1_000;
    private static final String TEMP_FILE_PREFIX = "tempFile";

    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // 生成包含大量数字文件
        numFileGenerator();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            // 读取文件并分块排序
            int chunkIndex = 0;
            // 临时文件命名后缀
            int tmpFileIdx = 0;
            String line;
            String[] buffer = new String[BUFFER_SIZE];

            while ((line = reader.readLine()) != null) {
                buffer[chunkIndex++] = line;

                if (chunkIndex == BUFFER_SIZE) {
                    sortAndWriteChunk(buffer, chunkIndex, TEMP_FILE_PREFIX + tmpFileIdx);
                    chunkIndex = 0;
                    tmpFileIdx++;
                }
            }

            // 处理剩余的数据块
            if (chunkIndex > 0) {
                sortAndWriteChunk(buffer, chunkIndex, TEMP_FILE_PREFIX + tmpFileIdx);
            }

            // 合并排序后的文件
            mergeSortedChunks(outputFile, tmpFileIdx);

            System.out.println("Sorting completed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sortAndWriteChunk(String[] buffer, int length, String fileName) throws IOException {
        Arrays.sort(buffer, 0, length, new NumberStringComparator());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < length; i++) {
                writer.write(buffer[i]);
                writer.newLine();
            }
        }
    }

    private static void mergeSortedChunks(String outputFile, int tmpFileIdx) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            PriorityQueue<ChunkReader> priorityQueue = new PriorityQueue<>(Comparator.comparing(ChunkReader::getCurrentNum));
            List<ChunkReader> chunkReaders = new ArrayList<>();

            // 打开所有分块文件的读取器
            for (int i = 0; i <= tmpFileIdx; i++) {
                String fileName = TEMP_FILE_PREFIX + i;
                File file = new File(fileName);

                if (file.exists()) {
                    ChunkReader chunkReader = new ChunkReader(new BufferedReader(new FileReader(file)), fileName);
                    chunkReaders.add(chunkReader);
                    priorityQueue.offer(chunkReader);
                }
            }

            // 合并排序
            while (!priorityQueue.isEmpty()) {
                ChunkReader reader = priorityQueue.poll();
                writer.write(reader.getCurrentLine());
                writer.newLine();

                if (reader.nextLine()) {
                    priorityQueue.offer(reader);
                } else {
                    reader.close();
                    new File(reader.getFileName()).delete();
                }
            }

            // 关闭所有读取器
            for (ChunkReader reader : chunkReaders) {
                reader.close();
            }
        }
    }

    private static void numFileGenerator() throws IOException {
        // 随机生成包含大量数字的文件
        String outputFile = "input.txt";
        long[] doubles = ThreadLocalRandom.current().longs(0, 1999999999).limit(1_000_000L).toArray();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (long num : doubles) {
                writer.write(String.valueOf(num));
                writer.newLine();
            }
        }
    }

    static class ChunkReader {
        private final BufferedReader reader;
        private final String fileName;
        private String currentLine;

        ChunkReader(BufferedReader reader, String fileName) throws IOException {
            this.reader = reader;
            this.fileName = fileName;
            this.currentLine = reader.readLine();
        }

        String getCurrentLine() {
            return currentLine;
        }

        BigDecimal getCurrentNum() {
            return new BigDecimal(currentLine);
        }

        boolean nextLine() throws IOException {
            currentLine = reader.readLine();
            return currentLine != null;
        }

        String getFileName() {
            return fileName;
        }

        void close() throws IOException {
            reader.close();
        }
    }

    static class NumberStringComparator implements Comparator<String> {
        @Override public int compare(String s1, String s2) {
            // 假设每一行是一个任意长度的数字，使用字符串比较转换为BigDecimal进行排序
            BigDecimal num1 = new BigDecimal(s1);
            BigDecimal num2 = new BigDecimal(s2);
            return num1.compareTo(num2);
        }
    }
}
