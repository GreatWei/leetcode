import java.lang.reflect.Parameter;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.regex.Pattern;

public class Solution {

    /**
     * 最长子串
     */
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        if (s.length() > 0) {
            int count;
            max = 1;
            StringBuilder temp;
            for (int i = 0; i < s.length() - 1; i++) {
                count = 1;
                temp = new StringBuilder(String.valueOf(s.charAt(i)));
                for (int j = i + 1; j < s.length(); j++) {
                    int index = temp.indexOf(String.valueOf(s.charAt(j)));
                    if (index == -1) {
                        temp.append(s.charAt(j));
                        count++;
                    } else {
                        i = i + index;
                        break;
                    }
                }
                if (max < count) {
                    max = count;
                }
            }
        }

        return max;
    }

    /**
     * 效率为上4倍
     */
    public int lengthOfLongestSubstrings(String s) {
        int i = 0, j = 0, max = 0;

        for (Set<Character> seen = new HashSet<Character>(); j < s.length(); j++)
            if (!seen.add(s.charAt(j)))
                for (max = Math.max(max, j - i); s.charAt(i++) != s.charAt(j); seen.remove(s.charAt(i - 1))) ;

        return Math.max(max, j - i);
    }

    /**
     * a1+a2=target
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums.length >= 3) {
            for (int i = 0; i < nums.length - 1; i++)
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        result[0] = i;
                        result[1] = j;
                    }
                }
        } else {
            result[0] = 0;
            result[1] = 1;
        }


        return result;
    }

    /**
     * 中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Len = nums1.length;
        int nums2Len = nums2.length;
        int mindLen = (nums1Len + nums2Len) / 2 + 1;
        int[] all = new int[mindLen];
        int i = 0;
        int j = 0;
        int k = 0;
        double mid = 0;
        for (; i < nums1Len && j < nums2Len && k < mindLen; ) {
            if (nums1[i] > nums2[j]) {
                all[k++] = nums2[j++];
            } else {
                all[k++] = nums1[i++];
            }
        }
        while (i < nums1Len && k < mindLen) {
            all[k++] = nums1[i++];
        }

        while (j < nums2Len && k < mindLen) {
            all[k++] = nums2[j++];
        }

        if ((nums1Len + nums2Len) % 2 == 0) {
            mid = (all[mindLen - 2] + all[mindLen - 1]) / 2.0;
        } else {
            mid = all[mindLen - 1];
        }

        return mid;
    }

    /**
     * 最长回文子串
     */
    public static String longestPalindrome(String s) {

        if (s == null || s.length() < 1) return "";
        int length = s.length();
        boolean flag;
        int start = 0;
        int end = 0;
        for (int i = 0; i < length && (end - start + 1) <= (length - i); i++) {
            for (int z = length - 1; z >= i && (end - start + 1) < (z - i + 1); z--) {
                flag = true;
                for (int k = i, e = z; k < e; k++, e--) {
                    if (s.charAt(k) != s.charAt(e)) {
                        flag = false;
                        break;
                    }
                }
                if (flag && (end - start) < (z - i + 1)) {
                    start = i;
                    end = z;
                }
            }

        }
        return s.substring(start, end + 1);
    }

    public String longestPalindromes(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /***
     * N字形
     *
     *
     *
     * */
    public static String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < numRows && s.length() > i; i++) {
            stringBuilders[i] = new StringBuilder();
            if ((numRows - i - 1) == (numRows - 1) || i == (numRows - 1)) {
                for (int j = i; j < s.length(); ) {
                    //    System.out.println(j+"j:"+s.charAt(j));
                    stringBuilders[i].append(s.charAt(j));
                    j = j + 2 * (numRows - 1);
                }
            } else {
                stringBuilders[i].append(s.charAt(i));
                int a = 2 * (numRows - i - 1);
                for (int j = i + a; j < s.length(); ) {
                    stringBuilders[i].append(s.charAt(j));
                    a = 2 * (numRows - 1) - a;
                    j = j + a;
                }
            }
        }
        for (int i = 1; i < numRows; i++)
            stringBuilders[0].append(stringBuilders[i] == null ? "" : stringBuilders[i]);
        return stringBuilders[0].toString();
    }

    /***
     *
     * 整数反转
     *
     * */
    public static int reverse(int x) {
        String max = String.valueOf(Integer.MAX_VALUE);
        String min = String.valueOf(Integer.MIN_VALUE);
        String temp = String.valueOf(x);
        StringBuilder builder = new StringBuilder();
        int reuslt = 0;
        boolean flag = true;
        if ((temp.length() < max.length() && x >= 0) || (temp.length() < min.length() && x < 0)) {
            flag = true;
        } else {
            if (x >= 0) {
                for (int i = temp.length() - 1; i >= 0; i--) {
                    //  System.out.println(temp.charAt(i)+","+max.charAt(max.length() - i-1));
                    if (temp.charAt(i) > max.charAt(max.length() - i - 1)) {
                        flag = false;
                        break;
                    }
                    if (temp.charAt(i) < max.charAt(max.length() - i - 1)) {
                        break;
                    }
                }
            } else {
                for (int i = temp.length() - 1; i > 0; i--) {
                    if (temp.charAt(i) > min.charAt(min.length() - i)) {
                        flag = false;
                        break;
                    }
                    if (temp.charAt(i) < min.charAt(min.length() - i)) {
                        break;
                    }

                }
            }
        }
        if (flag) {
            for (int i = temp.length() - 1; i > 0; i--) {
                builder.append(temp.charAt(i));
            }
            if (x >= 0) {
                builder.append(temp.charAt(0));
                reuslt = Integer.valueOf(builder.toString());
            } else {
                reuslt = -Integer.valueOf(builder.toString());
            }
        }
        return reuslt;
    }

    /**
     * 将字符串转换成整数。
     */
    public static int myAtoi(String str) {
        str = str.trim();
        // System.out.println(str);
        int reuslt = 0;
        int i = 0;
        boolean flag1 = Pattern.matches("^[0-9]+([\\s\\S]+)?", str);
        boolean flag2 = Pattern.matches("^[\\-\\+][0-9]+([\\s\\S]+)?", str);
        String temp = "0123456789";
        String pre = "";
        String max = String.valueOf(Integer.MAX_VALUE);
        String min = String.valueOf(Integer.MIN_VALUE);
        StringBuilder stringBuilder = new StringBuilder();

        //System.out.println(flag1+","+flag2);
        if (!flag1 && !flag2) {
            return reuslt;
        }
        if (flag2) {
            pre = str.charAt(i) + "";
            i = 1;
            //  System.out.println("pre:"+pre);
        }
        for (; i < str.length(); i++) {
            if (temp.indexOf(str.charAt(i)) == -1) {
                break;
            }
            if (stringBuilder.length() == 0 && '0' == str.charAt(i)) {
                continue;
            }
            stringBuilder.append(str.charAt(i));
        }
        //  System.out.println(stringBuilder.toString());

        if (stringBuilder.length() > max.length() || stringBuilder.length() > (min.length() - pre.length())) {
            if (pre.equals("+") || pre.equals(""))
                reuslt = Integer.MAX_VALUE;
            else {
                reuslt = Integer.MIN_VALUE;
            }
        } else if (stringBuilder.length() == max.length() || stringBuilder.length() == (min.length() - pre.length())) {
            flag1 = true;
            if (pre.equals("+") || pre.equals("")) {
                for (int j = 0; j < stringBuilder.length(); j++) {
                    if (stringBuilder.toString().charAt(j) > max.charAt(j)) {
                        flag1 = false;
                        break;
                    }
                    if (stringBuilder.toString().charAt(j) < max.charAt(j)) {
                        break;
                    }
                }
                if (flag1) {
                    reuslt = Integer.valueOf(pre + stringBuilder.toString());
                } else {
                    reuslt = Integer.MAX_VALUE;
                }

            } else {
                for (int j = 0; j < stringBuilder.length(); j++) {
                    if (stringBuilder.toString().charAt(j) > min.charAt(j + 1)) {
                        flag1 = false;
                        break;
                    }
                    if (stringBuilder.toString().charAt(j) < min.charAt(j + 1)) {
                        break;
                    }
                }
                if (flag1) {
                    //  System.out.println("min:"+Integer.MIN_VALUE);
                    reuslt = Integer.valueOf(pre + stringBuilder.toString());
                } else {
                    reuslt = Integer.MIN_VALUE;
                }
            }

        } else {
            reuslt = Integer.valueOf((pre + stringBuilder).equals("") || "+-".indexOf(pre + stringBuilder) != -1 ? "0" : (pre + stringBuilder));
        }


        return reuslt;
    }

    public static boolean isPalindrome(int x) {
        boolean flag = true;
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static int maxArea(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }

    /**
     * 最长公共前缀
     */
    public static String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0 || strs[0].length() == 0) return "";
        int len = strs[0].length() - 1;//比较长度
        int temp = -1;//计数
        for (int j = 1; j < strs.length && len >= 0; j++)//比较字符串
        {
            temp = -1;
            for (int k = 0; k <= len && k < strs[j].length(); k++) {
                if (strs[j].charAt(k) != strs[0].charAt(k)) {
                    break;
                }
                temp++;
            }
            len = temp;
        }
        if (len >= 0) {
            return strs[0].substring(0, len + 1);
        } else {
            return "";
        }
    }

    /**
     * a + b + c = 0
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < len; i++) {
            int opposite = -nums[i];
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1, right = len - 1;
                while (left < right) {
                    int twoSum = nums[left] + nums[right];
                    if (twoSum == opposite) {
                        List<Integer> ans = new ArrayList<>();
                        ans.add(nums[i]);
                        ans.add(nums[left]);
                        ans.add(nums[right]);
                        res.add(ans);
                        left = moveLeft(nums, left, right);
                        right = moveRight(nums, left, right);
                    } else if (twoSum < opposite) {
                        left = moveLeft(nums, left, right);
                    } else {
                        right = moveRight(nums, left, right);
                    }
                }
            }
        }
        return res;
    }

    private static int moveLeft(int[] nums, int left, int right) {
        int num = nums[left++];
        while (left <= right) {
            if (nums[left] != num)
                break;
            left++;
        }
        return left;
    }

    private static int moveRight(int[] nums, int left, int right) {
        int num = nums[right--];
        while (left <= right) {
            if (nums[right] != num)
                break;
            right--;
        }
        return right;
    }

    /**
     * 合并k个排序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode first = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode temp = new ListNode(0);
            ListNode temp1 = temp;
            //归并
            while (first != null && lists[i] != null) {
                if (first.val > lists[i].val) {
                    temp.next = new ListNode(lists[i].val);
                    lists[i] = lists[i].next;
                } else {
                    temp.next = new ListNode(first.val);
                    first = first.next;
                }
                temp = temp.next;
            }
            if (first == null) {
                temp.next = lists[i];
            } else {
                temp.next = first;
            }
            first = temp1.next;
        }
        return first;
    }

    /**
     * 删除排序数组中的重复项
     */
    public static int removeDuplicates(int[] nums) {
        int len = 0;
        if (nums == null || nums.length == 0) return len;
        len++;
        for (int i = 1; i < nums.length; i++) {
            if (nums[len - 1] != nums[i]) {
                nums[len++] = nums[i];
            }
        }
        return len;
    }

    /**
     * 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        int index = -1;
        if (nums == null || nums.length == 0) return index;
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] == target) {
                index = i;
                break;
            } else if (nums[i] > target) {
                break;
            }
        }
        for (int j = nums.length - 1; j > i && index == -1; j--) {
            if (nums[j] == target) {
                index = j;
                break;
            } else if (nums[j] < target) {
                break;
            }
        }

        return index;
    }

    /**
     * 字符串相乘
     */
    public String multiply(String num1, String num2) {
        int next = 0;//进位
        int sumtemp = 0;//当前相乘的结果
        String sum = "";//最终结果
        int i = 0;
        for (; i < num2.length(); i++) {

            for (int j = num1.length() - 1; j >= 0; j--) {
                next = 0;
                int num11 = Integer.valueOf(num1.charAt(j));
                int num22 = Integer.valueOf(num2.charAt(i));
                sumtemp = num11 * num22 + next;
                if (sumtemp > 10) {
                    next = sumtemp / 10;
                }
            }
            for (int k = 0; k < num2.length() - i - 1; k++) {

            }
        }
        return sum;
    }

    //542. 01 矩阵
    public int[][] updateMatrix(int[][] matrix) {
        ArrayList<Integer> arrayListi = new ArrayList<Integer>();
        ArrayList<Integer> arrayListj = new ArrayList<Integer>();
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    arrayListi.add(i);
                    arrayListj.add(j);
                }
            }

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) continue;
                else {
                    int min = matrix.length + matrix[0].length;
                    for (int k = 0; k < arrayListi.size(); k++) {
                        int indexi = arrayListi.get(k);
                        int indexj = arrayListj.get(k);
                        //  System.out.println(indexi+","+indexj);
                        min = min > (Math.abs(i - indexi) + Math.abs(j - indexj)) ? (Math.abs(i - indexi) + Math.abs(j - indexj)) : min;

                    }
                    matrix[i][j] = min;
                }
            }

        return matrix;
    }

    //56. 合并区间

    /**
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    public int[][] merge(int[][] intervals) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(intervals[0][0]);
        arrayList.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; ) {

            i = i + 1;

            while (i < intervals.length) {
                for (int j = arrayList.size() - 1; j < arrayList.size(); j = j - 2) {
                    int j1 = arrayList.get(j - 1);
                    int j2 = arrayList.get(j);
                    if ((j1 <= intervals[i][0] && j2 >= intervals[i][1]) ||
                            (j1 >= intervals[i][0] && j2 <= intervals[i][1]) ||
                            (j1 <= intervals[i][0] && j2 >= intervals[i][1]) ||
                            (j1 >= intervals[i][0] && j2 >= intervals[i][1])
                    ) {
                        if (j1 < intervals[i][0]) {
                            intervals[i][0] = j1;
                        }
                        if (j2 > intervals[i][1]) {
                            intervals[i][1] = j2;
                        }
                        arrayList.remove(j);
                        arrayList.remove(j - 1);
                    } else {

                    }
                }
            }
            Collections.sort(arrayList);
        }
        int row = 2;
        int ret[][] = new int[row][2];
        for (int i = row - 1; i >= 0; i--) {

        }
        return ret;
    }

    public int majorityElement(int[] nums) {
//        int num = nums[0];
//        HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();
//        for (int i = 1; i < nums.length ; i++) {
//            if(hashMap.get(nums[i])==null){
//                hashMap.put(nums[i],1);
//            }else {
//                if((hashMap.get(nums[i])+1)>(nums.length/2)){
//                    num=nums[i];
//                    break;
//                }else {
//                    hashMap.put(nums[i],hashMap.get(nums[i])+1);
//                }
//            }
//        }
//        return num;
        int x = 0;
        int vote = 0;
        for (int num : nums) {
            if (vote == 0) x = num;
            vote += num == x ? 1 : -1;
        }
        return x;
    }

    //第k小
    public int findk(int arr1[], int arr2[], int k) {
        if (arr1.length > arr2.length) {
            int[] tmp = arr1;
            arr1 = arr2;
            arr2 = tmp;
        }

        int n = arr1.length;
        int m = arr2.length;

        int left = 0;
        int right = n - 1;
        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = k - i;
            if (arr1[i - 1] > arr2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }

        //  System.out.println(left);


        return Math.max(arr1[left - 1], arr2[k - left - 1]);
    }

    //17. 电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        String[][] tmp = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}, {"j", "k", "l"}, {"m", "n", "o"}, {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};

        LinkedList<String> linkedList = new LinkedList<String>();
        for (int i = 0; i < digits.length(); i++) {
            int len = linkedList.size();

            for (int j = 0; j < len; j++) {
                String lastStr = linkedList.pollFirst();
                for (String s : tmp[digits.charAt(i) - 50]) {
                    linkedList.addLast(lastStr + s);
                }
            }
            if (len == 0) {
                for (String s : tmp[digits.charAt(i) - 50]) {
                    linkedList.add(s);
                }
            }
        }
        List<String> list = new ArrayList<String>(linkedList);
        return list;

    }

    public List<String> findItinerary(List<List<String>> tickets) {

        String head = "JFK";
        String tail = null;
        boolean first = true;
        boolean lastIn = true;
        List<String> resualt = new ArrayList<String>();
        while (tickets.size() > 0) {
            int j = 0;
            boolean flag=false;
            int i=lastIn?tickets.size()-1:tickets.size()-2;

            for (; i >= 0; i--) {

                List<String> list = tickets.get(i);

            //    System.out.println(list.size());
                if (tail == null && list.get(0).equals(head)) {
                    if (first) {
                        resualt.add(list.get(0));
                    }
                    resualt.add(list.get(1));
                    tail = list.get(1);
                    j = i;
                    flag=true;
                } else if (list.get(0).equals(head) && tail.compareTo(list.get(1)) > 0) {
                    tail = list.get(1);
                    resualt.set(resualt.size() - 1, tail);
                    j = i;
                }


            }
            tail = null;

            if(!flag){
                lastIn=false;
                List<String> arr = new ArrayList<String>();
              //  System.out.println(resualt.size());
                arr.add(resualt.get(resualt.size()-2));
                arr.add(resualt.get(resualt.size()-1));
                tickets.add(arr);
                resualt.remove(resualt.size()-1);
                resualt.remove(resualt.size()-1);
                if(resualt.size()>0){
                    head=resualt.get(resualt.size()-1);
                }else {
                    head="JFK";
                    first=true;
                }
             }else {
                lastIn=true;
                tickets.remove(j);
                head = resualt.get(resualt.size() - 1);
                first = false;
            }

        }

        return resualt;

    }


}
