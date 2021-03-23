import com.sun.xml.internal.ws.util.StringUtils;

import javax.swing.tree.TreeNode;
import javax.xml.stream.events.Characters;
import java.util.*;

/*
判出口（终点、越界）->剪枝->扩展->标记->递归->还原

void dfs()//参数用来表示状态
{
    if(到达终点状态，越界或者是不合法状态)
        return;
    for(扩展方式)
    {
        if(扩展方式所达到状态合法)
        {
            修改操作;//根据题意来添加，area++
            标记；eg:grid[x][y]=0,一定要修改！！
            dfs（）；
            (还原标记)；
            //是否还原标记根据题意
            //如果加上（还原标记）就是 回溯法
        }
    }
}
*/
public class solution {
    public static void main(String[] args) {
        String s = "abpcplea";
        int[][] nums2 = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        List<String> d = new ArrayList<>(Arrays.asList("ale", "apple", "monkey", "plea"));
        int[] nums1 = {1,5,11,5};
        Object result = maxAreaOfIsland(nums2);
        System.out.println("result: " + result);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int res =0;
        int[][] dire = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
        for(int i=0;i< grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                int cur = 0;
                Deque<Integer> si = new LinkedList<>();
                Deque<Integer> sj = new LinkedList<>();
                si.push(i);
                sj.push(j);
                while (!si.isEmpty()){
                    int x = si.pop();
                    int y = sj.pop();
                    if(x>= grid.length || x<0 || y<0 || y>=grid[0].length || grid[x][y]==0){
                        continue;
                    }
                    cur++;
                    grid[x][y]=0;
                    for(int index=0;index<4;index++){
                        si.push(x+dire[index][0]);
                        sj.push(y+dire[index][1]);
                    }
                }
                res=Math.max(res,cur);
            }
        }
        return res;
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

