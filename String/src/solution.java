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

/*
判出口（终点、越界）->剪枝->扩展->标记->递归->还原

void dfs()//参数用来表示状态
{
    if(到达终点状态)
    {
        ...//根据题意添加
        return;
    }
    if(越界或者是不合法状态)
        return;
    if(特殊状态)//剪枝
        return ;
    for(扩展方式)
    {
        if(扩展方式所达到状态合法)
        {
            修改操作;//根据题意来添加
            标记；
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
                res=Math.max(res, dfs(grid,i,j,dire));
            }
        }
        return res;
    }
    public static int dfs(int[][] grid, int x, int y, int[][] dire){
        if(x> grid.length || x<0 || y<0 || y>grid[0].length || grid[x][y]==0){
            return 0;
        }
        grid[0][0]=0;
        int res=1;
        for(int[] di:dire){
            res+=dfs(grid,x+di[0],y+di[1],dire);
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

