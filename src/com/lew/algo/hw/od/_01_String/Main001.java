package com.lew.algo.hw.od._01_String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main001 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] commands = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    System.out.println(getResult(commands));
  }

  public static int getResult(int[] commands) {
    ArrayList<String> screen = new ArrayList<>();
    ArrayList<String> clip = new ArrayList<>();
    boolean isSelect = false;

    for (int command : commands) {
      switch (command) {
        case 1: // a
          if (isSelect) screen.clear();
          screen.add("a");
          isSelect = false;
          break;
        case 2: // ctrl-c
          if (isSelect) {
            clip.clear();
            clip.addAll(screen);
          }
          break;
        case 3: // ctrl-x
          if (isSelect) {
            clip.clear();
            clip.addAll(screen);
            screen.clear();
            isSelect = false;
          }
          break;
        case 4: // ctrl-v
          if (isSelect) screen.clear();
          screen.addAll(clip);
          isSelect = false;
          break;
        case 5: // ctrl-a
          if (screen.size() != 0) isSelect = true;
          break;
      }
    }

    return screen.size();
  }
}