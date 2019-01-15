import java.text.NumberFormat;

public class Test {
    public static void main(String[] args) {
        String s="012a345";
       // System.out.println(s.lastIndexOf('a'));
        System.out.println(s.charAt(0)==s.charAt(0));
        String s1="PAYPALISHIRING";
        String s2="PAHNAPLSIIGYIR";

        String max = String.valueOf(Integer.MAX_VALUE);
        String min = String.valueOf(Integer.MIN_VALUE);

        System.out.println(max);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        System.out.println(nf.format(Math.pow(2,31)));
        System.out.println(min);
    }
}
