### 求数值的整数次方

不需要考虑大数问题

需要的考虑的特殊情况：1. 如果指数或者base等于0； 2. 指数小于0；3. 对于double类型的数据我们在
判断是否相等时，不能直接使用 '=='， 而是它们之间的误差在一个很小的范围内即可。

- 参考代码

```java

public class Solution {
    public double Power(double base, int exponent) {
        if(twoDoubleEquals(base, 0.0) && exponent < 0) {
            return 0.0;
        }
        int sign = 1;
        if(exponent < 0) {
            sign = -1;
            exponent = -exponent;
        }
        double result = powerUndign(base, exponent);
        
        if(sign  == -1) {
            result = 1 / result;
        }
        return result;
  }
    
    private static double powerUndign(double base, int exponent) {
        double res = 1.0;
        for(int i = 0; i < exponent; i++) {
            res = res * base;
        }
        return res;
    }
    
    private static boolean twoDoubleEquals(double d1, double d2) {
        if((d1 - d2) > -0.0000001 && (d1 - d2) < 0.0000001)
            return true;
        else
            return false;
    }
}
```

- 对于double类型的数据我们在判断是否相等时，不能直接使用 '=='， 而是它们之间的误差在一个很小的范围内即可。

- 更高效的 powerUndign 函数

我们知道 a^8 = a^4 * a^4 ; 那么我们也可以使用递归来计算 base^exponent = base^(2/exponent) * base^(exponent)
需要注意就是exponent为奇数的时候，需要最后乘以一次base

```

private static double powerUndign(double base, int exponent) {

        if(exponent == 0) 
            return 1.0;
        if(exponent == 1) 
            return base;
        
        double res = powerUndign(base, exponent >> 1);
        res *= res;
        
        if((exponent & 1) == 1) 
            res *= base;
            
        return res;
}
```