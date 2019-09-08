### 跳台阶

首先是一个 斐波那契数列问题；

可以使用递归，但是效率太低：

```java

public class Solution {
    public int Fibonacci(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
```

或者使用变量暂存结果，循环更新变量

```java

public class Solution {
    public int Fibonacci(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        int zero = 0, one = 1,res= 0;
        
        for(int i = 2; i <= n; i++) {
            res = zero + one;
            zero = one;
            one = res;
        }
        return res;
    }
}
```

然后用了一只笨笨的青蛙类比了一下斐波那契数列：

就是有个青蛙跳台阶，一次可以跳1步，或者2步，问n步的跳法有多少种

```java

public class Solution {
    public int JumpFloor(int target) {
        if(target <= 0)
            return 0;
        if(target == 1 || target == 2)
            return target;
        
        int one = 1, two = 2, res = 0;
        for(int i = 3; i <= target; i++) {
            res = one + two;
            one = two;
            two = res;
        }
        return res;
    }
}
```

最后还来了一只会飞的青蛙，除了可以跳1步，2步，还可以飞n步，那么现在它的跳法有多少种？

```java

public class Solution {
    public int JumpFloorII(int target) {
        if (target <= 2)
            return target;

        int memo[] = new int[target + 1];

        memo[1] = 1;
        memo[2] = 2;

        for (int i = 3; i <= target; i++) {
            for (int j = 0; j < i; j++) {
                memo[i] += memo[j];
            }
            memo[i]++;
        }

        return memo[target];
    }
}
```