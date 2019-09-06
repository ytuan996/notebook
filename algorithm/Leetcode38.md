### [leetcode38](https://leetcode-cn.com/problems/count-and-say/)

报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：

```
1.     1
2.     11
3.     21
4.     1211
5.     111221
```

1 被读作  "one 1"  ("一个一") , 即 11。
11 被读作 "two 1s" ("两个一"）, 即 21。
21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。

给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。

注意：整数顺序将表示为一个字符串。

示例：
````
输入: 4
输出: "1211"
````

思路：

首先读懂题目，其实就是前一个的读法，转换为第二个的表示，比如第一个是 "1"，读做"一个一"，那么第二个就是"11";
然后第二个读作"两(2)个一"，那么第三个就是"21";....

1.首先需要实现一个函数 String convert(String str);传入上一个数，返回读法所表示的下一个数。

2.在convert中定义一个count，记录上一个数出现的"n个m",分别将n,m拼接到结果后面并返回。

3.创建一个n大小的数组strs，记录每一个表示的数字，循环到n,那么结果就是strs[n - 1];


参考代码：

```java

class Solution {
    public String countAndSay(int n) {
        if(n <= 0) 
            return "1";
        String[] strs = new String[n];
        strs[0] = "1";
        for(int i = 1; i < n; i++) {
            strs[i] = convert(strs[i - 1]);
        }
        return strs[n - 1];
    }
    
    private String convert(String s) {
        int len = s.length();
        int count = 1;
        
        StringBuffer sb = new StringBuffer("");
        for(int i = 0; i < len; i++) {
            if(i < len - 1 && s.charAt(i) == s.charAt(i + 1)){
                count++;
            }else {
                sb.append(count).append(s.charAt(i));
                count = 1;
            }
        }
        return new String(sb);
    }
}
```