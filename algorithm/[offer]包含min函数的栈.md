### 包含min函数的栈

实现一个栈，包含push，pop，min函数，其中min函数返回栈中最小的元素。且要求时间复杂度为O(1)

- 思路：

如果我们使用原生的栈数据结构，不可变在O(1)时间内返回最小值，如果每次压栈时进行排序，那么无法保证后进栈的元素
会先出栈，就违背了栈这种数据结构的定义

当我们无法使用数据结构完成简单复杂度时，那么就考虑使用空间换时间，增加一个辅助栈。

每次除了把数据压入栈外，再把较小的元素压如辅助栈，那么min函数直接返回辅助栈的栈顶元素即可。

- 参考代码：

```java

import java.util.Stack;

public class Solution {

    Stack<Integer> data = new Stack<>();
    Stack<Integer> minData = new Stack<>();
    
    public void push(int node) {
        data.push(node);
        if(minData.isEmpty()) {
            minData.push(node);
        }else {
            if(node < minData.peek())
                minData.push(node);
            else
                minData.push(minData.peek());
        }
    }
    
    public void pop() {
        data.pop();
        minData.pop();
    }
    
    public int top() {
         return data.peek();
    }
    
    public int min() {
        return minData.peek();
    }
}
```
