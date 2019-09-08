### 二叉树的镜像

输入一颗二叉树， 输出二叉树的镜像

- 思路：

前序遍历这颗二叉树，针对该树的所有节点，如果存在子节点，那么就交换该节点的左右节点，直到该树的所有非叶子节点交换
完成后，得到的就是该树的镜像了。

```java

public class Solution {
    public void Mirror(TreeNode root) {
        if(root == null)
            return;
        if(root.left != null || root.right != null) {
            TreeNode node = root.left;
            root.left = root.right;
            root.right = node;
        }
        Mirror(root.left);
        Mirror(root.right);
    }
}
```

- 仔细一看就是二叉树的前序遍历，只是将打印的操作换成了交换左右节点；