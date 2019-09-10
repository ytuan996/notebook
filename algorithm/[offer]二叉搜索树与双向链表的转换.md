###  二叉搜索树与双向链表的转换


- 思路：

按照二叉搜索树中序遍历的特点，对于每一颗二叉树来说，转换为双向链表之后，根节点的直接前驱是左子树的最后节点，
而直接后继是右子树的最左节点，实现转换的关键代码就是设置一个节点记录已经转换了的前驱，每遍历到一个节点的时候，
设置该节点的left为记录的前驱，如果前驱不为空，那么设置前驱的后继为当前节点，并更新前驱节点为当前节点；
最后设置左右子树直接调用；

```
    pRootOfTree.left = listHead;
    if(listHead != null)
        listHead.right = pRootOfTree;
    listHead = pRootOfTree;
```

```java

/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    TreeNode listHead = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null)
            return null;
        if(pRootOfTree.left != null)
            Convert(pRootOfTree.left);
        
        pRootOfTree.left = listHead;
        if(listHead != null)
            listHead.right = pRootOfTree;
        listHead = pRootOfTree;
        
        if(pRootOfTree.right != null) {
            Convert(pRootOfTree.right);
        }
        TreeNode head = listHead;
        while(head.left != null) {
            head = head.left;
        }
        return head;
    }
}
```