import java.util.ArrayList;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list1.add(l1.val);
        list2.add(l2.val);
        while (l1.next != null) {
            l1 = l1.next;
            System.out.println("l1:"+l1.val);
            list1.add(l1.val);
        }
        for (int o:list1)
            System.out.println("list1:"+o);
        while (l2.next != null) {
            l2 = l2.next;
            list2.add(l2.val);
        }
        int temp = 0;
        ArrayList<Integer> sumNum = new ArrayList<Integer>();
        int sum;
        int i = 0;
        for (i = 0; i < list1.size() && i < list2.size(); i++) {
            sum = list1.get(i) + list2.get(i) + temp;
            sumNum.add(sum % 10);
            temp = sum / 10;
        }
        if (i == list1.size()) {
            System.out.println("2:");
            for (int k = i; k < list2.size(); k++) {
                sum = list2.get(k) + temp;
                sumNum.add(sum % 10);
                temp = sum / 10;

            }

        } else {
            System.out.println("1:"+i);
            for (int k = i; k < list1.size(); k++) {
                System.out.println("list1:"+list1.get(k));
                System.out.println("temp:"+temp);
                sum = list1.get(k) + temp;
                sumNum.add(sum % 10);
                temp = sum / 10;
            }
        }
        if (temp != 0) {
            sumNum.add(temp);
        }
        ListNode listNode = new ListNode(sumNum.get(0));
        ListNode first = listNode;
        for (int k = 1; k <sumNum.size(); k++) {
            ListNode tempList = new ListNode(sumNum.get(k));
            listNode.next = tempList;
            listNode = tempList;

        }
        return first;
    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
