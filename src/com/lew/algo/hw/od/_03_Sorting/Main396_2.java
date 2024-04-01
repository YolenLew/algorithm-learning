package com.lew.algo.hw.od._03_Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

public class Main396_2 {

    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int allSize = Integer.parseInt(sc.nextLine());

        MyFileSystem myFileSystem = new MyFileSystem();
        myFileSystem.allSize = allSize;

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] ss = sc.nextLine().split(" ");

            if (ss.length == 2) {
                myFileSystem.get(ss[1]);
            }

            if (ss.length == 3) {

                if (Integer.parseInt(ss[2]) > allSize) {
                    System.out.println("NONE");
                    return;
                }

                myFileSystem.put(ss[1], Integer.parseInt(ss[2]));
            }
        }

        List<MyFile> files = myFileSystem.files;
        if (files.isEmpty()) {
            System.out.println("NONE");
            return;
        }

        List<String> names = new ArrayList<>();
        names.addAll(myFileSystem.names);
        Collections.sort(names);

        StringJoiner sb = new StringJoiner(",");

        for (String myFile : names) {
            sb.add(myFile);
        }

        System.out.println(sb.toString());

    }

    static class MyFileSystem {
        int allSize; // 总大小
        int usedSize; // 已用大小
        List<MyFile> files;
        Set<String> names;

        public MyFileSystem() {
            files = new ArrayList<>();
            names = new HashSet<>();
        }

        public void put(String name, int size) {
            if (names.contains(name)) {
                return;
            }

            //内存不够了
            while (allSize - usedSize < size) {
                MyFile file = files.get(files.size() - 1);
                usedSize = usedSize - file.size;
                files.remove(file);
                names.remove(file.name);
            }

            MyFile myFile = new MyFile();
            myFile.name = name;
            myFile.updateTime = count++;
            myFile.size = size;
            myFile.frq = 1;
            files.add(myFile);
            names.add(name);
            usedSize = usedSize + size;

            Collections.sort(files, new Comparator<MyFile>() {
                @Override
                public int compare(MyFile o1, MyFile o2) {
                    if (o1.frq != o2.frq) {
                        return o2.frq - o1.frq;
                    }
                    return o2.updateTime - o1.updateTime;
                }
            });
        }

        public void get(String fileName) {
            if (!names.contains(fileName)) {
                return;
            }
            for (MyFile file : files) {
                if (file.name.equals(fileName)) {
                    file.updateTime = count++;
                    file.frq = file.frq + 1;

                    Collections.sort(files, new Comparator<MyFile>() {
                        @Override
                        public int compare(MyFile o1, MyFile o2) {
                            if (o1.frq != o2.frq) {
                                return o2.frq - o1.frq;
                            }
                            return o2.updateTime - o1.updateTime;
                        }
                    });
                    break;
                }
            }
        }
    }

    static class MyFile {
        String name;
        int updateTime;
        int size;
        int frq;
    }
}
