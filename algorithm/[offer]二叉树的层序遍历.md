### 二叉树的层序遍历

使用一个队列的辅助数据结构

```java

import java.util.*;

public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if(root == null) 
            return new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            res.add(curNode.val);
            if(curNode.left != null) 
                queue.add(curNode.left);
            if(curNode.right != null)
                queue.add(curNode.right);
        }
        return res;
    }
}
```