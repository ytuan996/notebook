### 反转一个单链表

```

 private ListNode reverse(ListNode head) {
        ListNode pre = null, next = null, cur = head;
        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
```

- 扩展(携程2019秋招) 每K个一组反转链表


```
题目描述
给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

说明：
1. 你需要自行定义链表结构，将输入的数据保存到你的链表中；
2. 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换；
3. 你的算法只能使用常数的额外空间
```

- 思路： 将链表分为三部分来分析，已反转，待反转，未反转

1.链表分区为已翻转部分+待翻转部分+未翻转部分

2.每次翻转前，要确定翻转链表的范围，这个必须通过 k 此循环来确定

3.需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来

4.初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾

5.经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next

6.翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环

7.特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可

8.时间复杂度为 O(n*K)O(n∗K) 最好的情况为 O(n)O(n) 最差的情况未 O(n^2)O(n 2

9.空间复杂度为 O(1)O(1) 除了几个必须的节点指针外，我们并没有占用其他空间


- 参考代码

```java

import java.util.Scanner;
public class Main{
    public static void main(String[]args) {
        Scanner in = new Scanner(System.in);
        
        LinkedList list = new LinkedList();
        String[] strs = in.nextLine().split(" ");
        for(String s : strs) {
            list.insert(Integer.parseInt(s));
        }
        int k = in.nextInt();
        list.head = reverseList(list.head, k);
        ListNode cur = list.head;
        while(cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }
    
    private static ListNode reverseList(ListNode head, int k) {
        // 设置一个哑节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 设置两个指针，分别指向待删除部分的前驱，待删除部分的末尾
        ListNode pre = dummy;
        ListNode end = dummy;

         while (end.next != null) {
            for (int i = 0; i < k && end != null; i++){
                end = end.next;
            } 
            if (end == null) break;

            ListNode start = pre.next;  // 反转链表的头节点
            ListNode next = end.next;   // 记录反转之后的直接后继

            // 反转待反转的部分
            end.next = null;
            pre.next = reverse(start);

            start.next = next;  // 衔接已反转和未反转的部分

            // 更新指针，准备下一次反转
            pre = start;  
            end = pre;
        }
        return dummy.next;
    }
    
    private static ListNode reverse(ListNode head) {
        ListNode pre = null, next = null, cur = head;
        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
class LinkedList {
    ListNode head = null;
    public LinkedList () {    }
    
    public void insert(int val) {
        ListNode newNode = new ListNode(val);
        if(head == null) {
            head = newNode;
            return;
        }else {
            ListNode cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
            return;
        }
    }
}
class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
```