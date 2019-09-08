### 二叉树的子结构判断

输入两颗二叉树，判断是否是从属关系

- 思路：

解决这个问题需要分为两步：首先找到A中节点和B根节点相同的节点；然后判断以该节点为根的左右结构是否和B一致

在解决树形问题时，首先考虑如何利用递归

```java

public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean res = false;
        
        if(root1 != null && root2 != null) {
            if(root1.val == root2.val) {   // 节点相同，进一步判断左右子结构
                res =  tree1HaveTree2(root1, root2);
            }
            if(!res)  // 往左查找
                res = HasSubtree(root1.left, root2);
            if(!res)
                res = HasSubtree(root1.right, root2);  // 往右查找
        }
        return res;
    }
    
    // 具备相同根节点的是否一致
    private boolean tree1HaveTree2(TreeNode tree1, TreeNode tree2) {
        // 递归结束的条件
        if(tree2 == null) {  // 子树遍历完成，返回true
            return true;
        }
        if(tree1 == null) {  // 较大的树先结束，不包含，返回false
            return false;
        }
        if(tree1.val != tree2.val)  // 判断当前节点是否一致
            return false;
        // 循环查看左右子结构
        return tree1HaveTree2(tree1.left, tree2.left) 
            && tree1HaveTree2(tree1.right, tree2.right);
    }
}
```