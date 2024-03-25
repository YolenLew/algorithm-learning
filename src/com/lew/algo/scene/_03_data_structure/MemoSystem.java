// 请设计一个备忘录系统，要求有以下功能：
//** • addEvent(int startDate, String content, int num, int period) – 自日期 startDate 开始（startDate 包含在内），添加 num 个以
// period 为间隔周期待办事项 content。例如若命令为 addEvent(0,"a",4,2)，表示于日期 0，2，4，6 分别添加待办事项 "a"。**
//** o 返回本次操作共计新增待办事项的数量。若部分日期上已存在该事项，无论该事项是否设置为已完成，这些日期不需添加该事项。**
//** • finishEvent(int date, String content) – 将日期 date 上的 content 事项设置为已完成。**
//** o 若该日期上不存在此事项，或该日期上此事项已完成，返回 false；**
//** o 若设置操作完成，则返回 true。**
//** • removeEvent(int date, String content) – 移除日期 date 上的 content 事项。**
//** o 若该日期上不存在此事项，返回 false；**
//** o 若移除操作完成，则返回 true。**
//** • queryTodo(int startDate, int endDate) – 查询从 startDate 到 endDate（起止日期均包含在内）这段日期内所有的未完成的 content
// 事项，并将查询到的事项以字符串形式按日期升序记于数组后返回，若查询到相同日期的多条待办事项，则将其按字典序排列。**
//** 示例 1：**
//** 输入：["MemoSystem","addEvent","queryTodo","finishEvent","removeEvent"]**
//** [[],[2,"eat a burger",2,3],[0,5],[2,"eat a burger"],[2,"eat a burger"]]**
//** 输出：[null,2,["eat a burger","eat a burger"],true,true]**
//** 解释：**
//** MemoSystem ms = MemoSystem();**
//** ms.addEvent(2,"eat a burger",2,3); // 自日期 2 开始，添加 2 个间隔周期为 3 的待办事项 "eat a burger"，即在日期 2、5 添加该待办事项。本次操作共新添加了 2
// 个待办事项，返回 2**
//** ms.queryTodo(0,5); //查询自日期 0 至日期 5 的所有未完成的事项，返回数组 ["eat a burger","eat a burger"]**
//** ms.finishEvent(2,"eat a burger"); // 将系统中日期为 2 的待办事项 "eat a burger" 设置为已完成，返回 true**
//** ms.removeEvent(2,"eat a burger"); // 移除系统中日期为 2 的事项 "eat a burger"，返回 true**
//** 注：输出中的 null 表示此对应函数无输出（其中：C 的构造函数有返回值，但是也是无需输出）**
//** Yangpeng y00*46对所有人说说： 02:18 PM
//** 示例 2：**
//** 输入：**
//** ["MemoSystem","addEvent","addEvent","removeEvent","finishEvent","addEvent","addEvent","queryTodo","queryTodo",
// "finishEvent","addEvent"]**
//** [[],[2,"save files",1,1],[2,"eat a burger",1,1],[2,"drink water"],[1,"drink water"],[0,"eat a burger",5,2],[2,
// "save files",1,1],[0,4],[10,11],[2,"eat a burger"],[2,"eat a burger",1,1]]**
//** 输出：[null,1,1,false,false,4,0,["eat a burger","eat a burger","save files","eat a burger"],[],true,0]**
//** 解释：**
//** MemoSystem ms = MemoSystem();**
//** 第 3 个命令 ms.addEvent(2,"eat a burger",1,1); // 同一个日期可以增加不同的待办事项，本次操作共新添加了 1 个待办事项，返回 1**
//** 第 6 个命令 ms.addEvent(0,"eat a burger",5,2); // 自日期 0 开始，添加 5 个间隔周期为 2 的待办事项 "eat a burger"，即在日期 0，2，4，6，8
// 添加该待办事项。由于日期 2 的记录中已存在该事项，因此本次操作共新添加了 4 个待办事项，返回 4**
//** 第 8 个命令 ms.queryTodo(0,4); // 查询自日期 0 至日期 4 的所有未完成的事项，按日期返回，其中日期 2 的待办事项需按字典序排列，返回数组 ["eat a burger","eat a
// burger","save files","eat a burger"]**
//** 第 9 个命令 ms.queryTodo(10,11); // 查询自日期 10 至日期 11 的所有未完成的事项，返回空数组 []**
//** 注：输出中的 null 表示此对应函数无输出（其中：C 的构造函数有返回值，但是也是无需输出）**
//** 提示：**
//** 1 <= addEvent,finishEvent,removeEvent,queryTodo 总操作数 <= 1000**
//** 0 <= startDate <= endDate <= 1000**
//** 0 < num, period, 0 <= startDate + (num - 1) * period <= 1000**
//** 1 <= content.length <= 20 ，仅包含小写字母、大写字母与空格**
//** 0 <= date <= 1000**
//** queryTodo 函数对于 Java/Js/Python/Go 语言，如果返回的记录为空，则返回空数组**
//
//作者：牛客621799504号
//链接：https://www.nowcoder.com/discuss/353158979616448512
//来源：牛客网

package com.lew.algo.scene._03_data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MemoSystem {
    static Map<Integer, Map<String, Boolean>> map = new HashMap<>();

    public static void main(String[] args) {
        MemoSystem ms = new MemoSystem();
        System.out.println(ms.addEvent(2, "save files", 1, 1));
        System.out.println(ms.addEvent(2, "eat a burger", 1, 1));
        System.out.println(ms.removeEvent(2, "drink water"));
        System.out.println(ms.finishEvent(1, "drink water"));
        System.out.println(ms.addEvent(0, "eat a burger", 5, 2));
        System.out.println(ms.addEvent(2, "save files", 1, 1));

        String[] ss = ms.queryTodo(0, 4);
        System.out.println(Arrays.toString(ss));
        String[] ss1 = ms.queryTodo(10, 11);
        System.out.println(Arrays.toString(ss1));
        System.out.println(ms.finishEvent(2, "eat a burger"));
        System.out.println(ms.addEvent(2, "eat a burger", 1, 1));

    }

    int addEvent(int startDate, String content, int num, int period) {
        int pos = startDate;
        int cnt = 0;
        for (int i = 0; i < num; i++) {
            Map<String, Boolean> map1 = map.getOrDefault(pos, new HashMap<>());
            if (!map1.containsKey(content)) {
                map1.put(content, false);
                map.put(pos, map1);
                cnt++;
            }
            pos += period;

        }
        return cnt;
    }

    boolean finishEvent(int date, String content) {
        Map<String, Boolean> map1 = map.get(date);
        if (map1 == null || !map1.containsKey(content) || map1.get(content)) {
            return false;
        }
        map1.put(content, true);
        map.put(date, map1);
        return true;
    }

    boolean removeEvent(int date, String content) {
        Map<String, Boolean> map1 = map.get(date);
        if (map1 == null || !map1.containsKey(content)) {
            return false;
        }
        map1.remove(content);
        map.put(date, map1);
        return true;
    }

    String[] queryTodo(int startDate, int endDate) {
        List<String> list = new ArrayList<>();
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = startDate; i <= endDate; i++) {
            Map<String, Boolean> map1 = map.get(i);
            if (map1 != null) {
                for (String s : map1.keySet()) {
                    if (!map1.get(s)) {
                        pq.add(s);
                    }
                }
                while (!pq.isEmpty()) {
                    list.add(pq.poll());
                }
            }

        }
        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}