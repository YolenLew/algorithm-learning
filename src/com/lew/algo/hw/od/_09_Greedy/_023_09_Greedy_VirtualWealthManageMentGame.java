// (C卷,100分)- 虚拟理财游戏（Java & JS & Python & C）
// 题目描述
// 在一款虚拟游戏中生活，你必须进行投资以增强在虚拟游戏中的资产以免被淘汰出局。
//
// 现有一家Bank，它提供有若干理财产品 m 个，风险及投资回报不同，你有 N（元）进行投资，能接收的总风险值为X。
//
// 你要在可接受范围内选择最优的投资方式获得最大回报。
//
// 备注：
//
// 在虚拟游戏中，每项投资风险值相加为总风险值；
// 在虚拟游戏中，最多只能投资2个理财产品；
// 在虚拟游戏中，最小单位为整数，不能拆分为小数；
// 投资额*回报率=投资回报
// 输入描述
// 第一行：
//
// 产品数（取值范围[1,20]）
// 总投资额（整数，取值范围[1, 10000]）
// 可接受的总风险（整数，取值范围[1,200]）
// 第二行：产品投资回报率序列，输入为整数，取值范围[1,60]
//
// 第三行：产品风险值序列，输入为整数，取值范围[1, 100]
//
// 第四行：最大投资额度序列，输入为整数，取值范围[1, 10000]
//
// 输出描述
// 每个产品的投资额序列
//
// 用例
// 输入 5 100 10
// 10 20 30 40 50
// 3 4 5 6 10
// 20 30 20 40 30
// 输出 0 30 0 40 0
// 说明 投资第二项30个单位，第四项40个单位，总的投资风险为两项相加为4+6=10

package com.lew.algo.hw.od._09_Greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class _023_09_Greedy_VirtualWealthManageMentGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tmp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = tmp[0]; // 产品数
        int n = tmp[1]; // 总投资
        int x = tmp[2]; // 总风险

        // 产品回报率序列
        int[] back = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 产品风险值序列
        int[] risk = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 产品投资额序列
        int[] invest = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int max_invest_back = 0;
        int max_invest_back_risk = Integer.MAX_VALUE;
        HashMap<Integer, Integer> select = new HashMap<>();

        for (int i = 0; i < m; i++) {
            // 如果单个产品的投资风险未超过总风险，则投资单个产品
            if (risk[i] <= x) {
                // 产品I的投资额不能超过该产品的最大投资额，以及总投资
                int investI = Math.min(invest[i], n);
                // 产品投资回报
                int invest_back = investI * back[i];

                // 如果投资回报高于最优策略，那么选择该产品
                // **特别注意** 如果投资回报等于最优策略，那么继续比较风险值，如果风险值更小，则选择该产品
                if (invest_back > max_invest_back || (invest_back == max_invest_back
                    && risk[i] < max_invest_back_risk)) {
                    max_invest_back = invest_back;
                    max_invest_back_risk = risk[i];
                    select.clear(); // 因为是只选单个需要清空
                    select.put(i, investI);
                }
            } else {
                continue;
            }

            for (int j = i + 1; j < m; j++) {

                // 如果两个产品的风险和不超过了总风险，那么两个产品都选
                if (risk[i] + risk[j] <= x) {
                    // 产品I的投资额
                    int investI = 0;
                    // 产品J的投资额
                    int investJ = 0;

                    // 其中优先投资回报率大的
                    if (back[i] > back[j]) {
                        // 产品I回报率高，则能投多少投多少，最多不能超过min(总投资, 产品I的最多投资额)
                        investI = Math.min(n, invest[i]);
                        // 留给产品J的剩余钱未 n - investI, 而产品J最多投资invest[j]，因此取二者较小值
                        investJ = Math.min(n - investI, invest[j]);
                    } else if (back[i] < back[j]) {
                        // 产品J回报率高，则能投多少投多少，最多不能超过min(总投资, 产品J的最多投资额)
                        investJ = Math.min(n, invest[j]);
                        investI = Math.min(n - investJ, invest[i]);
                    } else {
                        // 产品I和产品J回报率相同，则看谁的风险值更小
                        if (risk[i] > risk[j]) {
                            // **特别注意** 产品I和产品J回报率相同，则看谁的风险值更小，如果产品J的风险值更小，则能投多少投多少
                            investJ = Math.min(n, invest[j]);
                            investI = Math.min(n - investJ, invest[i]);
                        } else {
                            // **特别注意** 产品I和产品J回报率相同，则看谁的风险值更小，如果产品I的风险值更小，则能投多少投多少
                            investI = Math.min(n, invest[i]);
                            investJ = Math.min(n - investI, invest[j]);
                        }
                    }

                    // 总投资回报
                    int invest_back = investI * back[i] + investJ * back[j];
                    // 总风险值
                    int invest_back_risk = risk[i] + risk[j];

                    // 如果投资回报高于最优策略，那么选择该产品
                    // **特别注意** 如果投资回报等于最优策略，那么继续比较风险值，如果风险值更小，则选择该策略
                    if (invest_back > max_invest_back || (invest_back == max_invest_back
                        && invest_back_risk < max_invest_back_risk)) {
                        max_invest_back = invest_back;
                        max_invest_back_risk = invest_back_risk;
                        select.clear();
                        // select的key记录产品的ID，val记录产品的投资额
                        if (investI > 0)
                            select.put(i, investI);
                        if (investJ > 0)
                            select.put(j, investJ);
                    }
                }
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < m; i++) {
            if (select.containsKey(i)) {
                sj.add(select.get(i) + "");
            } else {
                sj.add("0");
            }
        }

        System.out.println(sj);
    }
}
