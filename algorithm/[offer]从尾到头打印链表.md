### 从尾到头打印链表

- 链表相关的算法题目

1. 链表常规的插入、删除 
2. 从尾到头输出链表 --> 使用辅助栈，或者递归解决
3. O(1)时间删除链表节点  --> 将待删除节点delNode.next.val复制非delNode,然后直接删除delNode。
4. 链表中的倒数第K个节点 
5. 反转链表 --> 三个指针的变化，pre,next,cur; next = cur.next; cur.next = pre; pre = cur; cur = next;到链表结尾时，
 pre就是反转后链表的头节点
6. 合并两个排序的链表
7. 两个链表的第一个公共节点
8. 环形链表
9. 双向链表

下面是第二类，从头到尾输出链表

![]()

思路：

对于从头到尾打印链表，可以有三种实现的方式：递归、栈、先反转再打印

- 递归参考代码：

```java

import java.util.*;
public class Solution {
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode != null) {
                printListFromTailToHead(listNode.next);
                list.add(listNode.val);
        }
        return list;
    }
}
```
- 栈参考代码

```java

import java.util.*;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode == null) {
            return new ArrayList<Integer>();
        }
        Stack<Integer> stack = new Stack<>();
        while(listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        while(!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
}
```

- 反转参考代码

```java

import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode == null) {
            return new ArrayList<Integer>();
        }
        ListNode pre = null, next = null, cur = listNode;
        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        while(pre != null) {
            res.add(pre.val);
            pre = pre.next;
        }
        return res;
    }
}
```
- 扩展题目(小米2019秋招)

找出单向链表中的一个节点，该节点到尾指针的距离为K

题目描述
```

找出单向链表中的一个节点，该节点到尾指针的距离为K。链表的倒数第0个结点为链表的尾指针。要求时间复杂度为O(n)。
链表结点定义如下：
struct ListNode
{
    int m_nKey;
    ListNode* m_pNext;
}
链表节点的值初始化为1，2，3，4，5，6，7。

请自觉实现一个链表，将1到7依次加入链表，然后再寻找倒数第K个节点。要求加节点与找节点的操作复杂度均为O(n)。
```

啰嗦了半天，其实就是自己构建一个单链表，然后初始化添加值 1～7，最后返回倒数第K个节点的值

- 参考代码

```java

import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for(int i = 1; i < 8; i++) {
            list.insert(i);
        }
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Node fast = list.head, slow = list.head;
        while(n - 1 > 0) {
            fast = fast.next;
            n--;
        }
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        System.out.println(slow.val);
    }
}
class LinkedList {
    
    Node head = null;
    
    public LinkedList() {
        head = null;
    }
    
    public void insert(int val) {
        Node newNode = new Node(val);
        if(head == null) {
            head = newNode;
        }else {
             Node cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
    }
}
class Node {
    int val;
    Node next;
    public Node(int val) {
        this.val = val;
        this.next = null;
    }
}
```
