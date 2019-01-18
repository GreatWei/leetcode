import java.lang.reflect.Parameter;
import java.util.HashSet;
import java.util.Set;
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
        int l=0;
        int r=height.length-1;
       while (l<r){
           max = Math.max(max,Math.min(height[l],height[r])*(r-l));
           if(height[l]>height[r]){
               r--;
           }else {
               l++;
           }
       }
        return max;
    }

    public static String longestCommonPrefix(String[] strs) {

        return null;
    }
}
