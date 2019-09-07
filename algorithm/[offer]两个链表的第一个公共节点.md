### 两个链表的第一个公共节点

输入两个链表，找出他们的第一个公共节点


- 蛮力解法：

遍历第一个链表，每遍历到一个节点，在遍历第二个链表，查找是否有节点等于当前节点，如果有，那么则返回该节点；这种方式的复杂度为O(mn)。

- 栈的解法：

使用两个辅助栈，分别将两个链表的节点依次放入栈中，然后弹栈查看是否相等，直到最后一个相等的节点，就是公共节点。这样时间复杂度为O(m+n),
空间复杂度为O(m+n);

- 计算长度差的解法

分析发现特点，两个交叉链表是'Y'形的，那么我们分别计算两个链表的长度，长的先移动长度差步，然后一起移动直到链表尾，第一个相等的节点就是。

- 参考代码：

```java

public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
         if(pHead1 == null || pHead2 == null) {
             return null;
         }
        int p1Len = getListLen(pHead1);
        int p2Len = getListLen(pHead2);
        
        int k = p1Len - p2Len;
        ListNode p1Index = pHead1, p2Index = pHead2;
        if(k > 0) {
            while(k > 0) {
                p1Index = p1Index.next;
                k--;
            }
        }else if(k < 0) {
            while(k < 0) {
                p2Index = p2Index.next;
                k++;
            }
        }
        while(p1Index != null && p2Index != null) {
            if(p1Index.val == p2Index.val) {
                return p1Index;
            }
            p1Index = p1Index.next;
            p2Index = p2Index.next;
        }
        return null;
    }
    
    private static int getListLen(ListNode head) {
        int len = 0;
        while(head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}
```