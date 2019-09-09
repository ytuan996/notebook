### 栈的压入压出序列

输入两个序列，判断第二个是否为第一个序列顺序压栈的出栈序列

- 思路：

需要一个辅助栈，循环出栈序列，如果栈为空或者栈顶元素不等于循环的当前元素，那么就依次把入栈序列压栈，
直到找到一个和当前元素相等，或者到了入栈顺序的全部元素。判断栈顶元素和当前序列是否相等，不等返回false，
循环完出栈队列后，返回true

- 参考代码：

```java

import java.util.Stack;

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
       if(pushA == null || popA == null) 
          return false;
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;
        for(int i = 0; i < popA.length; i++) {
            if(stack.isEmpty() || stack.peek() != popA[i]) {
                while(pushIndex < pushA.length) {
                    stack.push(pushA[pushIndex++]);
                    if(stack.peek() == popA[i]) {
                        break;
                    }
                }
            }
            if(stack.pop() != popA[i]) 
                return false;
        }
        return true;
    }
}
```