### 二叉树中和为某个值的路径

输入一颗二叉树和一个整数，输出该二叉树中和为该整数的所有路径

- 思路：

也算是一个简单的回溯吧，分别对二叉树的左右节点进行尝试，并记录路径，如果满足就添加，
如果不满足的话需要减去对应的值以及从路径中删除。

- 参考代码：

```java

import java.util.ArrayList;

public class Solution {
    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<Integer>();
    
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null)
            listAll.add(new ArrayList<Integer>(list));
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size()-1);
        return listAll;
    }
}
```

- 或者

```java

import java.util.ArrayList;

public class Solution {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<Integer>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null)
            return new ArrayList<ArrayList<Integer>>();
        FindPath(root, path, 0, target);
        return res;
    }
    
    private void FindPath(TreeNode root, ArrayList<Integer> path, int curSum, int target) {
        curSum += root.val;
        path.add(root.val);
        
        if(curSum == target && root.left == null && root.right == null) {
            res.add(new ArrayList<Integer>(path));
        }
        if(root.left != null) {
            FindPath(root.left, path, curSum, target);
        }
        if(root.right != null) {
            FindPath(root.right, path, curSum, target);
        }
        curSum -= root.val;
        path.remove(path.size() - 1);
    }
}
```

