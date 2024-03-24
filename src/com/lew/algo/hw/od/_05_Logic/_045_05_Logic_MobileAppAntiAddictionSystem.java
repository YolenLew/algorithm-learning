// (C卷,100分)- 手机App防沉迷系统（Java & JS & Python & C）
// 题目描述
// 智能手机方便了我们生活的同时，也侵占了我们不少的时间。“手机App防沉迷系统”能够让我们每天合理地规划手机App使用时间，在正确的时间做正确的事。
//
// 它的大概原理是这样的：
//
// 在一天24小时内，可以注册每个App的允许使用时段
// 一个时间段只能使用一个App
// App有优先级，数值越高，优先级越高。注册使用时段时，如果高优先级的App时间和低优先级的时段有冲突，则系统会自动注销低优先级的时段，如果App的优先级相同，则后添加的App不能注册。
// 请编程实现，根据输入数据注册App，并根据输入的时间点，返回时间点使用的App名称，如果该时间点没有注册任何App，请返回字符串“NA”。
//
// 输入描述
// 第一行表示注册的App数量 N（N ≤ 100）
//
// 第二部分包括 N 行，每行表示一条App注册数据
//
// 最后一行输入一个时间点，程序即返回该时间点使用的App
//
// 2
// App1 1 09:00 10:00
// App2 2 11:00 11:30
// 09:30
//
// 数据说明如下：
//
// N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
// 优先级1~5，数字越大，优先级越高
// 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
// 起始时间需小于结束时间，否则注册不上
// 注册信息中的时间段包含起始时间点，不包含结束时间点
// 输出描述
// 输出一个字符串，表示App名称，或NA表示空闲时间
//
// 用例
// 输入 1
// App1 1 09:00 10:00
// 09:30
// 输出 App1
// 说明 App1注册在9点到10点间，9点半可用的应用名是App1
// 输入 2
// App1 1 09:00 10:00
// App2 2 09:10 09:30
// 09:20
// 输出 App2
// 说明 App1和App2的时段有冲突，App2优先级高，注册App2之后，App1自动注销，因此输出App2。
// 输入 2
// App1 1 09:00 10:00
// App2 2 09:10 09:30
// 09:50
// 输出 NA
// 说明 无

package com.lew.algo.hw.od._05_Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _045_05_Logic_MobileAppAntiAddictionSystem {
    // 将时间字符串转换为分钟表示的整数
    public static int convertToMinutes(String time) {
        // 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
        String[] arr = time.split(":");
        int hour = Integer.parseInt(arr[0]);
        int minute = Integer.parseInt(arr[1]);
        return hour * 60 + minute;
    }

    // 将输入字符串转换为应用程序对象
    public static App convertToApp(String input) {
        String[] arr = input.split(" ");
        String name = arr[0];
        int priority = Integer.parseInt(arr[1]);
        int startTime = convertToMinutes(arr[2]);
        int endTime = convertToMinutes(arr[3]);
        return new App(name, priority, startTime, endTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入应用程序的数量
        int numApps = scanner.nextInt();
        scanner.nextLine(); // 消耗掉换行符

        // 存储应用程序对象的数组
        List<App> apps = new ArrayList<>();
        // 读取并转换应用程序信息
        for (int i = 0; i < numApps; i++) {
            String input = scanner.nextLine();
            App app = convertToApp(input);
            // 如果开始时间大于结束时间，则忽略该应用程序
            if (app.startTime > app.endTime) {
                continue;
            }

            // 与当前应用程序时间重叠的应用程序
            List<App> overlappingApps = new ArrayList<>();
            int maxPriority = -1;
            for (int j = 0; j < apps.size(); j++) {
                App tmp = apps.get(j);
                if (app.hasOverlap(tmp)) {
                    overlappingApps.add(tmp);
                    maxPriority = Math.max(maxPriority, tmp.priority);
                }
            }
            // 当前App与已有的App的优先级相同，则后添加的App不能注册
            // 高优先级的App时间和低优先级的时段有冲突，则系统会自动注销低优先级的时段（ 当前App优先级低于其他冲突的App 则也不能注册）
            if (app.priority <= maxPriority) {
                continue;
            } else {
                // 将之前已经注册优先级较低有时段有冲突的App 注销掉
                apps.removeAll(overlappingApps);
            }

            // 注册当前App
            apps.add(app);
        }

        int givenTime = convertToMinutes(scanner.nextLine());
        // 给定时间
        String answer = "NA";
        // 遍历结果列表，找到包含给定时间的应用程序
        for (App app : apps) {
            if (app.startTime <= givenTime && givenTime < app.endTime) {
                answer = app.name;
                break;
            }
        }
        // 输出结果
        System.out.println(answer);
    }

    static class App {
        String name;
        // 应用程序名称
        int priority;
        // 优先级
        int startTime;
        // 开始时间（以分钟为单位）
        int endTime;

        // 结束时间（以分钟为单位）
        // 构造函数，用于初始化应用程序对象
        public App(String name, int priority, int startTime, int endTime) {
            this.name = name;
            this.priority = priority;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        // 判断当前应用程序与另一个应用程序是否有时间重叠
        public boolean hasOverlap(App other) {
            return this.startTime < other.endTime && other.startTime < this.endTime;
        }
    }
}
