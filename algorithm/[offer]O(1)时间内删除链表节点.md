### O(1) 时间内删除链表节点

![]()

- 思路：

在常规情况下，我们删除链表节点的做法是找到待删除节点delNode的前一个节点pre，然后将pre.next = delNode.next;
最后释放delNode的空间。

如果我们想要在O(1)的时间内删除链表的节点，那么必须保证一个假设，那就是待删除的节点一定在链表中。然后我们将待
删除delNode的下一个节点next的值复制给delNode，然后将delNode的next指向delNode.next.next;最后释放delNode，
需要注意两个问题：如果删除的节点是链表的最后一个节点，也就是delNode不存在下一个节点，那么我们必须遍历链表找到
待删除节点的前一个节点，另外就是删了链表中唯一的一个节点，那么需要把头节点置为空。

- 参考代码

```java

public class Solution {

    public ListNode deleteNode(ListNode listNode, ListNode delNode) {
        if (listNode == null || delNode == null) {
            return listNode;
        }
        
         ListNode cur = listNode;
        if (delNode.next == null) {
            if (cur.next == null) {
                cur = null;
                listNode = null;
            }
            while(cur != null && cur.next.val != delNode.val) {
                cur = cur.next;
            }
            ListNode delNode = cur.next;
            cur.next = null;
            delNode = null;
            
        }else {
            ListNode  next = delNode.next;
            delNode.val = next.val;
            delNode.next = next.next;
            next == null;
        }
        
        return listNode;
    }
}
```