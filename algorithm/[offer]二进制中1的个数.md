### 二进制中1的个数

关于二进制的位运算，总共有五种运算符：或，异或，与，左移，右移。

![](https://github.com/ytuan996/notebook/blob/master/image/%E4%BD%8D%E8%BF%90%E7%AE%97%E7%AC%A6%E8%A7%84%E5%BE%8B.png?raw=true)

左移：其实就是原来的数乘以2

右移：原来的数除以2

![](https://github.com/ytuan996/notebook/blob/master/image/%E4%BA%8C%E8%BF%9B%E5%88%B6%E4%B8%AD1%E7%9A%84%E4%B8%AA%E6%95%B0.png?raw=true)

思路：

把一个整数减去1，再和原整数做与运算，会把原整数的最右边一个1变为0，那么我们求二进制中1的个数，就是循环减再与运算，
直到原整数 = 0；

```java

public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        while(n != 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }
}
```

- 相关的题目

一句话判断一个数是不是2的整数次方？

如果一个数是2的整数次方，那么它的二进制中只有一位是1；根据前面的经验，可以把该整数减去1，然后和本身做与运算后判断
是否为0即可；

```
return n & (n - 1) == 0 ? true: false;
```

输入两个整数 m 和 n， 计算需要改变 m 二进制的多少位才能的到 n？

分为两步解决：首先得到 m 和 n 的异或，然后计算异或结果中 1 的位数。

- 记住一个定律：一个数减去1后再和原来的数做与运算，得到的结果相当于把整数二进制的最右边一个1变为0。