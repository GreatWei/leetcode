public class Solution {

    public static int lengthOfLongestSubstring(String s) {
        char strs[] = s.toCharArray();
        int max = 1;
        int count = 1;
        char temp = strs[0];

        for (int i = 0; i < strs.length - 1; i++)
            for (int j = i + 1; j < strs.length; j++) {
                System.out.println("strs:"+strs[j]);
                System.out.println("temp:"+temp);
                System.out.println("strs[j]!= temp:"+(strs[j]!= temp));
                if (strs[j]!= temp) {
                    count++;
                  //  max=count;
                } else {
                    if (max<count){
                        max=count;
                    }
                        count = 1;
                        temp = strs[j];
                        i = j;
                        break;

                }
               // System.out.println("max:"+max);
            }
        return max;
    }
}
