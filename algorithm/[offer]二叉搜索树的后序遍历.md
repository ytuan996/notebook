###  二叉搜索树的后序遍历

给定一个序列，判断该序列是否是二叉搜索树的后序遍历结果

- 思路

分析二叉搜索树的后序遍历结果发现，序列的最后一个元素就是二叉搜索树的根节点，然后前面分为两部分，一部分是左子树，
另一部分是右子树；然后递归这个过程，递归的终止条件是只剩下一个元素，也就是没有子树的叶子节点

- 参考代码：

```java

public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        
        int n = sequence.length;
        
        if (n <= 0) 
            return false;
        
        return isSquenceOfBST(sequence, 0, n - 1);
    }
    
    private boolean isSquenceOfBST(int arr[], int start, int end) {
        
        int rootVal = arr[end];
        
        int index = 0;
        
        while (index < end) {
            if (arr[index] > rootVal)
                break;
            index++;
        }
        
        for (int i = index; i < end; i++) {
            if (arr[i] < rootVal)
                return false;
        }
        
        boolean left = true;
        if (index > 0)
            left = isSquenceOfBST(arr, start, index - 1);
        
        boolean right = true;
        if (index < end)
            right = isSquenceOfBST(arr, index, end - 1);
        
        return left && right;
        
    }
}
```

- 类似的题目，给出一个序列判断该序列是不是二叉搜索树的前序遍历，那么二叉搜索树的前序遍历特点就是根节点在
第一个元素。其他都是一样的。

- 经验

关于二叉树的遍历序列处理问题，我们可以根据序列首先找出二叉树的根节点。然后再拆分为左子树和右子树递归解决问题。
