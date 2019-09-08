### 两个栈实现队列

![]()

- 思路：

1.入队： 直接插入stack1;

2. 出队： 首先判断stack2是否为空，不为空直接弹出。为空那么将stack1一次压入stack2，再弹出。

- 参考代码：

```java

import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if(!stack2.isEmpty()) {
            return stack2.pop();
        }else {
            if(!stack1.isEmpty()) {
                while(!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }else {
                return -1;
            }
        }
    }
}
```

- 扩展：

两个队列模拟栈



实现思路：

总是需要保证其中一个队列为空，以便出队的时候辅助操作；

1. 入栈： 直接插入到不为空队列的尾部；
2. 出栈： 将不为空队列的元素依次复制到另一个为空的队列，只剩下最后一个，就是待出栈的元素。