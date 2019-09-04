### [Leetcode19](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。保证 n 总是有效的。

- 示例

```

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
```

- 思路：

使用快慢指针；声明两个指针fast，slow，首先让fast先走n步，然后再同时移动fast，slow两个指针，直到fast.next == null,
也就是fast到达了最后一个指针，那么slow指针就指向删除节点的前一个节点，执行删除，返回链表头节点 head

- 参考代码：

```java

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) 
            return head;
        
        ListNode fast = head, slow = head;
        
        for(int i = 0; i < n; i++) {
            fast = fast.next;
        }
        
        if(fast == null)
            return head.next;
        
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        ListNode deleteNode = slow.next;
        slow.next = deleteNode.next;
        deleteNode = null;
        return head;   
    }
}
```