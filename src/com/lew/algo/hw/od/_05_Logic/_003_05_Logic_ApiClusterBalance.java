// (C卷,100分)- API集群负载统计（Java & JS & Python & C）
// 题目描述
// 某个产品的RESTful API集合部署在服务器集群的多个节点上，近期对客户端访问日志进行了采集，需要统计各个API的访问频次，根据热点信息在服务器节点之间做负载均衡，现在需要实现热点信息统计查询功能。
//
// RESTful API是由多个层级构成，层级之间使用 / 连接，如 /A/B/C/D 这个地址，A属于第一级，B属于第二级，C属于第三级，D属于第四级。
//
// 现在负载均衡模块需要知道给定层级上某个名字出现的频次，未出现过用0表示，实现这个功能。
//
// 输入描述
// 第一行为N，表示访问历史日志的条数，0 ＜ N ≤ 100。
//
// 接下来N行，每一行为一个RESTful API的URL地址，约束地址中仅包含英文字母和连接符 / ，最大层级为10，每层级字符串最大长度为10。
//
// 最后一行为层级L和要查询的关键字。
//
// 输出描述
// 输出给定层级上，关键字出现的频次，使用完全匹配方式（大小写敏感）。
//
// 用例
// 输入 5
/// huawei/computing/no/one
/// huawei/computing
/// huawei
/// huawei/cloud/no/one
/// huawei/wireless/no/one
// 2 computing
// 输出 2
// 说明 在第二层级上，computing出现了2次，因此输出2
// 输入 5
/// huawei/computing/no/one
/// huawei/computing
/// huawei
/// huawei/cloud/no/one
/// huawei/wireless/no/one
// 4 two
// 输出 0
// 说明 存在第四层级的URL上，没有出现two，因此频次是0
//
//
// 题目解析
// 本题应该就是一个逻辑模拟题，以及考察集合的应用。

package com.lew.algo.hw.od._05_Logic;

import java.util.Scanner;

/**
 * @author luyonglang
 * @date 2024/3/11
 */
public class _003_05_Logic_ApiClusterBalance {

    public static void main(String[] args) {
        // 输入
        Scanner scanner = new Scanner(System.in);
        // 总共日志行数
        int total = scanner.nextInt();
        String[] logs = new String[total];
        for (int i = 0; i < total; i++) {
            // 注意：此处有坑
            // logs[i] = scanner.nextLine();
            logs[i] = scanner.next();
        }
        // System.out.println(Arrays.toString(logs));
        // 目标层级
        int level = scanner.nextInt();
        // 目标服务器
        String targetServer = scanner.next();
        // System.out.println(level + targetServer);

        // 遍历日志，统计目标服务器日志次数
        int count = 0;
        for (String log : logs) {
            // 将日志按照 "/" 分割，注意split函数会将 "/a/b/c" 会分割出数组 ["", "a", "b", "c"]，因此a,b,c的层级就是其索引值
            String[] serverArray = log.substring(1).split("/");
            // 一定要先判断temp数组是否够长，否则会数组越界
            // 因为上面赋值的时候数组可能只有一个元素，但要查元素2，就会越界
            if (serverArray.length >= level) {
                if (targetServer.equals(serverArray[level - 1])) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
