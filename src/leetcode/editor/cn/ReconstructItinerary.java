//给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 
//JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。 
//
// 说明: 
//
// 
// 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排
//序更靠前 
// 所有的机场都用三个大写字母表示（机场代码）。 
// 假定所有机票至少存在一种合理的行程。 
// 
//
// 示例 1: 
//
// 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
// 
//
// 示例 2: 
//
// 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。 
// Related Topics 深度优先搜索 图 
// 👍 257 👎 0

package leetcode.editor.cn;

import java.util.*;

public class ReconstructItinerary {

    public static void main(String[] args) {
        Solution solution = new ReconstructItinerary().new Solution();
        List<String> stringList1 = new ArrayList<String>();
        stringList1.add("JFK");
        stringList1.add("SFO");
        List<String> stringList2 = new ArrayList<String>();
        stringList2.add("JFK");
        stringList2.add("ATL");
        List<String> stringList3 = new ArrayList<String>();
        stringList3.add("SFO");
        stringList3.add("ATL");

        List<String> stringList4 = new ArrayList<String>();
        stringList4.add("ATL");
        stringList4.add("JFK");

        List<String> stringList5 = new ArrayList<String>();
        stringList5.add("ATL");
        stringList5.add("SFO");

        List<List<String>> lists = new ArrayList<List<String>>();
        lists.add(stringList1);
        lists.add(stringList2);
        lists.add(stringList3);
        lists.add(stringList4);
        lists.add(stringList5);

        List<String> s = solution.findItinerary(lists);
        for (String s1 : s) System.out.print(s1 + ",");
//        Map<String, String> map = new TreeMap<String, String>(solution.new MyComparator());
//        map.put("SFO", "1");
//        map.put("JFK", "1");
//        System.out.println("SFO".compareTo("JFK"));
//        System.out.println(map.keySet());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
            List<String> resualt = new ArrayList<String>();
            List<Integer> indexs = new ArrayList<Integer>();
            String tail = "JFK";
            resualt.add(tail);
            recursive(indexs, tickets, resualt, tail);
            return resualt;

        }

        public boolean recursive(List<Integer> indexs, List<List<String>> tickets, List<String> resualt, String tail) {
            Map<String, ArrayList<Integer>> map = new TreeMap<String, ArrayList<Integer>>(new MyComparator());
            for (int i = 0; i < tickets.size(); i++) {
                if (indexs.contains(i)) {
                    continue;
                }
                if (tail.equals(tickets.get(i).get(0))) {
                    if (tickets.size() == (indexs.size() + 1)) {
                        resualt.add(tickets.get(i).get(1));
                        return true;
                    }
                    if (map.get((tickets.get(i).get(1))) == null) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(i);
                        map.put(tickets.get(i).get(1), list);
                    } else {
                        ArrayList<Integer> list = map.get((tickets.get(i).get(1)));
                        list.add(i);
                        map.put(tickets.get(i).get(1), list);
                    }
                }
            }

            System.out.print(map.keySet().toString());
            for (String key : map.keySet()) {
                System.out.println(key);
                ArrayList<Integer> list = map.get(key);
                for (int i = 0; i < list.size(); i++) {
                    tail = tickets.get(list.get(i)).get(1);
                    resualt.add(tail);
                    indexs.add(list.get(i));
                    boolean end = recursive(indexs, tickets, resualt, tail);
                    if (end) {
                        return true;
                    } else {
                        resualt.remove(resualt.size() - 1);
                        indexs.remove(indexs.size() - 1);
                    }

                }
            }
            return false;
        }

        class MyComparator implements Comparator<String> {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    }
