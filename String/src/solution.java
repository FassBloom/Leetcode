import com.sun.xml.internal.ws.util.StringUtils;

import javax.swing.tree.TreeNode;
import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class solution {
    public static void main(String[] args) {
        /*int a = 1;
        int n = 3;
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {5, 0, 0, 6, 1, 6, 2, 2, 4};
        List<Integer> aa = new ArrayList<>();
        String[] s = {"dog", "racecar", "car"};*/
        ListNode tail = new ListNode(3);
        ListNode sec = new ListNode(2,tail);
        ListNode fir = new ListNode(1,sec);
        ListNode head = new ListNode(0,fir);
        ListNode dummy = new ListNode(0,head);
        ListNode fir2 = fir;
        fir2.val = 11;
        fir2.next = dummy;
        System.out.println("fir: "+fir.val+fir.next);
        System.out.println("fir2: "+fir2.val+fir2.next);
        ListNode result = removeNthFromEnd2(head,2);
        System.out.println("result: "+result);
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}

