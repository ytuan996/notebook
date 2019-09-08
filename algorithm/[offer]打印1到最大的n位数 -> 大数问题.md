### 打印从 1 到 n 的 所有数

如果我们直接使用下面的方法，那么会出现溢出的问题

```java

public class Solution {
    public void printMaxNDigit(int n) {
        
        int number = 1;
        for(int i = 0; i < n; i++) {
            number *= 10;
        }
        
        for(int i = 1; i < number; i++) {
            System.out.print(i + "");
        }
    }
}
```

- 必须实现在字符串上模拟加法

```

public boolean increment(char[] number) {
    boolean isOverFlow = false;
    int ntakeOver = 0;
    
    int len = number.length;
    
    for(int i = len - 1; i >=0; i--) {
        int sum = number[i] - '0' + ntakeOver;
        
        if(i == len - 1) {  // 当前是个位
            sum++;
        }
        
        if(sum >= 10) {
            if(i ==0) // 当前是最高位
                isOverFolw = true;
            else {
                sum -= 10;
                ntakeOver = 1;
                number[i] = '0' + sum;
            }
        }else {
            number[i] = '0' + sum;
            break;
        }
    }
    return isOverFlow;
}
```
- 将字符数组输出为整数

```

public void printNumber(char[] number) {
    boolean isBeginWith0 = true;
    int len = number.length;
    
    for(int i = 0; i < len; i++) {
        if(isBeginWiith0 && number[i] != '0') 
            isBeginWiith0 = false;
        if(!isBeginWiith0) {
            System.out.print(number[i]);
        }
    }
    System.out.print(' ');
}
```
- 最后整合实现打印1到n位的数字

```

public void printMaxNDigit(int n) {
    
    if(n <= 0) {
        return;
    }
    
    char[] number = new char[n];
    while(!increment(number)) {
        printNumber(number);
    }
}
```

- 将问题转换为 0～9 的全排列问题， 并使用递归实现

- 如何实现两个任意整数的加法