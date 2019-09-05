### 将字符串转换为整数

- 头脑简单的代码

```java

class  Solution {
    
    public int srtToInt(String s) {
        
        int number = 0;
        
        char[] chars = s.toCharArray();
        for (int i = 0; i <chars.length; i++) {
            number = number * 10 + (chars[i] - '0');
        }
        return number;
    }
}
```

- 存在的问题：

1. 没有考虑到非法输入，比如字符串包含非数字字符，或者包含正负号。

2. 没有考虑到最大值的溢出问题

3. 异常处理；当无法转换时，怎么处理异常，还有空指针的判断

- 考虑完全的代码

```java

class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        if (str == null || str.equals("")) {
            return 0;
        }
        int sign = 1, start = 0;
        long res = 0;
        
        char[] chars = str.toCharArray();
        
        if (str.charAt(0) == '-'){
            sign = -1;
            start++;
        }else if (str.charAt(0) == '+'){
            sign = 1;
            start++;
        }
        
        for(int i = start; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                res = res * 10 + chars[i] - '0';
            }else {
                return (int) res * sign;
            }
            if(sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if(sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }
        
        return (int) res * sign;
    }
}

```
