### 合并两个排序的链表

![]()

- 思路：

1. 首先进行非空判断，如果list1为空直接返回list2，否则直接返回list1，如果同时为空，那么返回null；
2. 声明一个ListNode,表示合并后的头节点mergeNode；
3. 然后就是递归调用，如果list1.val < list2.val;那么mergeNode = list1,递归调用merge(list1.next,list2);
同理递归调用list2；
4.返回合并后的头节点mergeNode;

- 参考代码：

```java

public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        if(list1 == null && list2 == null) {
            return null;
        }
         ListNode mergeNode = null;
        if(list1.val < list2.val) {
            mergeNode = list1;
            mergeNode.next = Merge(list1.next, list2);
        }else {
            mergeNode = list2;
            mergeNode.next = Merge(list1, list2.next);
        }
        return mergeNode;
    }
}
```