import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
//        String s="012a345";
//       // System.out.println(s.lastIndexOf('a'));
//        System.out.println(s.charAt(0)==s.charAt(0));
//        String s1="PAYPALISHIRING";
//        String s2="PAHNAPLSIIGYIR";
//
//        String max = String.valueOf(Integer.MAX_VALUE);
//        String min = String.valueOf(Integer.MIN_VALUE);
//
//        System.out.println(max);
//        NumberFormat nf = NumberFormat.getInstance();
//        nf.setGroupingUsed(false);
//        System.out.println(nf.format(Math.pow(2,31)));
//        System.out.println(min);
//        Pattern pattern=   Pattern.compile("^\\d+");
//        Matcher matcher = pattern.matcher("   42".trim());
//        System.out.println(matcher.matches());
//        System.out.println(Pattern.matches("^[0-9]+([^0-9]+)?|[\\-\\+][0-9]+([^0-9]+)?","  -195454lsfgsdf".trim()));
        System.out.println(Pattern.matches("^[0-9]+([\\s\\S]+)?", "    88827 6    U".trim()));
        String[] staffs = new String[]{"Tom", "Bob", "Jane"};
        //  Set<Integer> staffsSet = new HashSet<Integer>(Arrays.asList(staffs));
        // staffsSet.add("Mary"); // ok
        //   staffsSet.remove("Tom"); // ok

        Map<String, String> map = new HashMap<String, String>();
        map.put("b", "4");
        map.put("a", "5");
        map.put("c", "3");
        map.put("d", "5");
        //通过map.keySet()方法
        //方法一：通过得到key的值，然后获取value;
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + "  " + value);
        }
        "".charAt(1);
        ConcurrentHashMap hashMap= new ConcurrentHashMap<String,Object>();
        hashMap.put("1",1);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Thread());

    }
}
