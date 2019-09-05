#### [leetcode28](https://leetcode-cn.com/problems/implement-strstr/)

实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:

```
输入: haystack = "hello", needle = "ll"
输出: 2
```

###### 思路：

1. 如果needle的长度大于haystaack的长度，那么直接返回-1；
2. 在haystack上设置一个窗口，大小为needle的长度，范围为[0,hayLen];
3. 如果haystack.substring(start,end).equals(needle),那么返回start的值，否则返回-1；其中end为needle的最后一位表示的下标
4. 滑动窗口的使用需要注意循环的结束条件。

参考代码：

```java 

public int strStr(String haystack, String needle) {
    int hayLen = haystack.length();
    int len = needle.length();
    if (hayLen < len)
        return -1;
    int start = 0, end = len - 1;
    while (end < hayLen) {
        if (haystack.substring(start, end + 1).equals(needle)) {
            return start;
        }
        start++;
        end++;
    }
    return -1;
}
```


