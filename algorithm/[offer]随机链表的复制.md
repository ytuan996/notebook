### 随机链表的复制

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

- 思路：

首先将链表简单的复制，也就是复制链表的每个节点，然后使用next连接起来；然后再复制每个节点的random指针

- 参考代码：

```java

/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
public class Solution {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null) 
            return null;
        RandomListNode cloneHead = cloneNodes(pHead);
        return cloneRandom(pHead,cloneHead);
    }
    
    // 简单链表的复制
    private RandomListNode cloneNodes(RandomListNode pHead) {
        RandomListNode cloneHead = null;
        if(pHead != null) {
            RandomListNode clone = new RandomListNode(pHead.label);
            cloneHead = clone;
            cloneHead.next = cloneNodes(pHead.next);
        }
        return cloneHead;
    }
    // 随机指针的复制
    private RandomListNode cloneRandom(RandomListNode pHead, RandomListNode cloneHead) {
        
        RandomListNode cloneNode = cloneHead;
        
        if(pHead != null && cloneHead != null) {
            if(pHead.random != null) {
                RandomListNode randomClone = new RandomListNode(pHead.random.label);
                cloneNode.random = randomClone;
                cloneNode.next = cloneRandom(pHead.next, cloneHead.next);
            }else {
                cloneNode.random = null;
                cloneNode.next = cloneRandom(pHead.next, cloneHead.next);
            }
            
        }
        return cloneNode;
    }
}
```
