//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。
//
//
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
//
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
//
// 示例 2：
//
//
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
//
//
//
// 提示：
//
//
// 1 <= numCourses <= 2000
// 0 <= prerequisites.length <= 5000
// prerequisites[i].length == 2
// 0 <= ai, bi < numCourses
// prerequisites[i] 中的所有课程对 互不相同
//
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1880 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//java:课程表
class P_207_CourseSchedule {
    public static void main(String[] args) {
        Solution solution = new P_207_CourseSchedule().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 拓扑问题；有向无环图；入度表；BFS广度优先遍历
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 入度数组：记录课程的入度（即课程所依赖的多少门课程）
            int[] indegrees = new int[numCourses];
            // 邻接节点集合：当前节点指向哪些节点
            List<List<Integer>> adjacencyList = new ArrayList<>();
            // 初始化
            for (int i = 0; i < numCourses; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            // 邻接节点标记
            for (int[] prerequisite : prerequisites) {
                int learnCourse = prerequisite[0];
                int reliantCourse = prerequisite[1];
                // 入度数量累加
                indegrees[learnCourse]++;
                // 根据先修课程的信息，记录至邻接节点
                adjacencyList.get(reliantCourse).add(learnCourse);
            }
            // 零入度的课程队列
            Queue<Integer> zeroIndegreeQueue = new LinkedList<>();
            for (int i = 0; i < indegrees.length; i++) {
                if (indegrees[i] == 0) {
                    zeroIndegreeQueue.add(i);
                }
            }
            // 遍历零入度的课程，然后将其指向的课程入度减一，然后继续BFS遍历
            while (!zeroIndegreeQueue.isEmpty()) {
                // 取出零入度的课程
                Integer zeroCourse = zeroIndegreeQueue.poll();
                // 学完一个课程，待学习的课程数减1
                numCourses--;
                // 将其指向的课程入度减一
                List<Integer> nextCourses = adjacencyList.get(zeroCourse);
                for (Integer nextCours : nextCourses) {
                    indegrees[nextCours]--;
                    if (indegrees[nextCours] == 0) {
                        zeroIndegreeQueue.add(nextCours);
                    }
                }
            }
            // 判断是否学完课程
            return numCourses == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}