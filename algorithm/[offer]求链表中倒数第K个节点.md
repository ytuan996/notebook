### 求链表中倒数第K个节点

- 自以为完美的代码

```java

class Solution{
    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        
        ListNode fast = head,slow = head;
        
        for(int i = 0; i < k - 1; i++) {
            fast = fast.next();
        }
        while(fast.next != null) {
            fast = fast.next;
            slow = slow,next;
        }
        return slow;
    }
}

```

- 没有考虑到的问题：

1. 当链表中的节点小于k的时候，在for循环中会报空指针异常

- 完美的代码：

```java

class Solution{
    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        
        ListNode fast = head,slow = head;
        
        for(int i = 0; i < k - 1; i++) {
            fast = fast.next();
            //当fast为null的时候，直接返回null
            if (fast == null) {
                return null;
            }
        }
        while(fast.next != null) {
            fast = fast.next;
            slow = slow,next;
        }
        return slow;
    }
}
```