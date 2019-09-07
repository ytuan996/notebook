### 重建二叉树

根据前序遍历和后序遍历的特点，前序遍历的第一个就是二叉树的根节点，然后遍历中序结果，找到根节点的位置，那么根节点
左边的都是根节点的左子树，右边的都是根节点的右子树。然后递归调用即可

```java

public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        
        return construct(pre,0, pre.length - 1, in, 0, in.length - 1);
        
    }
    
    private TreeNode construct(int pre[], int startPre, int endPre, int in[], int startIn, int endIn) {
        
        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        
        // 根节点
        int rootVal = pre[startPre];
        TreeNode root = new TreeNode(rootVal);
        root.left = root.right = null;
        
        // 在中序序列中查找root
        int rootIndex = -1;
        for (int i = 0; i <= endIn; i++) {
            if (in[i] == rootVal) {
                rootIndex = i;
            }
        }
        int offSet = rootIndex - startIn - 1;
        
        // 构建左子树
            root.left = construct(pre, startPre + 1, startPre + offSet + 1, in, startIn, startIn + offSet);
        // 构建右子树
            root.right = construct(pre, startPre + offSet + 2, endPre, in, rootIndex + 1, endIn);
        return root;
    }
    
}
```