import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Algorithm {
    public static void main(String[] args) throws ParseException {
        int a = 1;
        int n = 3;
        int[] nums1 = {2,4,1};
        List<Integer> aa = new ArrayList<>();
        List<String> s = new ArrayList<>(Arrays.asList("hot","dot","tog","cog"));
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        String ss = "aeavvjg";
        char[] chs = {'c', 'f', 'j'};
        /*TreeNode tail1 = new TreeNode(3);
        TreeNode tail2 = new TreeNode(3);
        TreeNode tail0 = new TreeNode(2, null, tail2);
        TreeNode tail01 = new TreeNode(2, null, tail1);
        TreeNode tail00 = new TreeNode(5, tail01, tail0);*/
        Object res = maxProfit(2,nums1);
        System.out.println(res);

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
        return dp[2*k - 1];
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
    }

}

