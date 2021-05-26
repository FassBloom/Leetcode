
import javax.swing.tree.TreeNode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Algorithm {
    public static void main(String[] args) {
        //10 100 20 10
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4,n5);
        /*ListNode n3 = new ListNode(3,n4);
        ListNode n2 = new ListNode(2,n3);
        ListNode n1 = new ListNode(1,n2);*/
        ListNode res = reverseBetween(n4,1,2);
       // ArrayList<Integer> ret = list.stream().distinct().collect(Collectors.toList())
        //ArrayList<Integer> res = BucketSort(new ArrayList<>(Arrays.asList(1,1,4,1)),10);
        System.out.println(res);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        int cnt = 0;
        ListNode dummy = new ListNode (0,head);
        ListNode cur = dummy;
        while(cnt<left-1){
            cur=cur.next;
            cnt++;
        }
        ListNode pre = cur;
        cur = cur.next;
        for(;cnt<right-1;cnt++){
            ListNode remove = cur.next;
            cur.next = remove.next;
            remove.next = pre.next;
            pre.next = remove;
        }
        return dummy.next;
    }

    /**
     * 桶排序
     *
     * @param array
     * @param bucketSize
     * @return
     */
    public static ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2) {
            return array;
        }
        int max = array.get(0), min = array.get(0);
        // 找到最大值最小值
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
            if (array.get(i) < min) {
                min = array.get(i);
            }
        }
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) { // 如果带排序数组中有重复数字时  感谢 @见风任然是风 朋友指出错误
                for (int j = 0; j < bucketArr.get(i).size(); j++) {
                    resultArr.add(bucketArr.get(i).get(j));
                }
            } else {
                if (bucketCount == 1) {
                    bucketSize--;
                }
                ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
                for (int j = 0; j < temp.size(); j++)
                    resultArr.add(temp.get(j));
            }
        }
        return resultArr;
    }
    /*public static void main(String[] args) throws ParseException {


        int a = 1;
        int n = 3;
        List<String> s11 = new ArrayList<>(Arrays.asList("1.333","2",null,"0.99"));
        List<String> s12 = s11.stream().map(num->num==null? null:String.format("%.2f", Double.parseDouble(num))).collect(Collectors.toList());
        //System.out.println(s12);
        int[] nums1 = {2,4,1};
        List<Integer> aa = new ArrayList<>();
        List<String> s = new ArrayList<>(Arrays.asList("hot","dot","tog","cog"));
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        String ss = "aeavvjg";
        char[] chs = {'c', 'f', 'j'};
        *//*TreeNode tail1 = new TreeNode(3);
        TreeNode tail2 = new TreeNode(3);
        TreeNode tail0 = new TreeNode(2, null, tail2);
        TreeNode tail01 = new TreeNode(2, null, tail1);
        TreeNode tail00 = new TreeNode(5, tail01, tail0);*//*
     *//*Object res = maxProfit(2,nums1);
        System.out.println(res);*//*
     *//*String str1 = "abc";
        String str2 = new String("def");
        String str3 = "abc";
        String str4 = str2.intern();
        String str5 = "def";
        str1.hashCode();
        System.out.println(str1 == str3);//true
        System.out.println(str2 == str4);//false
        System.out.println(str4 == str5);//true
        HashMap o = new HashMap();
        o.put(" ","");*//*
        ListNode n0 = new ListNode(0);
        ListNode n1 = new ListNode(1,n0);
        ListNode n2 = new ListNode(2,n1);
        ListNode node = reverseList(n2);
        //Object obj = roundTo2digits("0.995");
        System.out.println(node.val);
        Deque<Integer> stack = new LinkedList<>();
        stack.push(9);
    }*/

    public static ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }


    public int[] levelOrder(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                res.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        Collections.reverse(res);
        int[] r = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(i);
        }
        return r;
    }

    public static List<String> fillFollowUpNulls(List<String> valueList, int len) {
        ArrayList<Integer> r = new ArrayList<>();
        String fill = valueList.get(0);
        HashSet<Integer> s = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (valueList.get(i) == null) {
                valueList.set(1, fill);
            } else {
                fill = valueList.get(i);
            }
        }
        return new ArrayList<>();
    }


    private static String roundTo2digits(String num) {
        return num == null ? null : String.format("%.2f", Double.parseDouble(num));
    }

    public static List<String> fillFollowUpNulls(List<String> valueList) {
        int slow = 0, fast = 0;
        int len = valueList.size();
        while (fast != len) {

        }

        return valueList;
    }

    public static int maxProfit(int k, int[] prices) {
        int[] dp = new int[2 * k];
        for (int i = 0; i < 2 * k; i++) {
            if (i % 2 == 0) {
                dp[i] = -prices[0];
            }
        }
        int[] newdp = new int[2 * k];
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k; j++) {
                newdp[j] = (j % 2 == 1) ? Math.max(dp[j], dp[j] + prices[i]) : Math.max(dp[j], dp[j] - prices[i]);
                dp[j] = newdp[j];
            }
        }
        return dp[2 * k - 1];
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
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

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

}

