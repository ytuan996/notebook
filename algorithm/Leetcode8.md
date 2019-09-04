### [leetcode8](https://leetcode-cn.com/problems/string-to-integer-atoi/)

请你来实现一个 atoi 函数，使其能将字符串转换成整数。
首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
在任何情况下，若函数不能进行有效的转换时，请返回 0。

- 说明：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

- 示例 3:
```

输出: 4193
输入: "4193 with words"
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

```

思路：

1.首先去除字符串左右空格,不符合条件的直接return 0;

2.sign是符号位,start指针指向第一个数字的位置,如果第一位为数字,则sign=1,start=0,否则firstChar接收字符串第一个字符,若为“+”、“-”,sign分别赋值1、-1,start自增1,

3.从字符串第一个为数字的位置开始遍历,res为无符号结果,如果res大于Integer最大值且sign=1,输出Integer的最大值,反之输出Integer最小值,如果遍历到不为数字的字符,则直接返回res*sign;

4.如果遍历时该字符串未超范围,且无非数字字符,则返回res * sign;

参考代码：

```
class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        if(str == null || str.equals(""))
            return 0;
        
        char c = str.charAt(0);
        int sign = 1, start =0;
        long res = 0;
        
        if (c == '+'){
            sign = 1;
            start++;
        }
        if(c == '-') {
            sign = -1;
            start++;
        }
        
        for(int i = start; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i)))
                return (int) res * sign;
            res = res * 10 + str.charAt(i) - '0';
            if(sign == 1 && res > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if(sign == -1 && res > Integer.MAX_VALUE)
                return Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }
}

```