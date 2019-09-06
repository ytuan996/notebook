### 替换空格

![替换空格](https://github.com/ytuan996/notebook/blob/master/image/%E6%9B%BF%E6%8D%A2%E7%A9%BA%E6%A0%BC.png?raw=true)

- 思路：

1 统计空格的数量，计算替换后总的字符数

2 根据字符数创建字符数组

3 从后往前复制字符，遇到空格后就替换

- 参考代码

```java

public class Solution {
    public String replaceSpace(StringBuffer str) {
    	if(str == null || str.length() == 0) 
            return str.toString();
        
        // 首先统计str中空格的数量
        int spaceNum = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        
        // 然后创建一个字符数组，记录替换后的每个字符，长度为str的长度加上spaceNum * 2 
        // 因此 每个空格 换为 %20 后，字符数增加了2个
        char[] chars = new char[str.length() + spaceNum * 2];
        
        // 声明两个指针，分别从字符数组的末尾和str的末尾从后往前复制
        int charLen = chars.length - 1, strLen = str.length() - 1;
        while(strLen >= 0) {
            if(str.charAt(strLen) == ' ') {
                chars[charLen--] = '0';
                chars[charLen--] = '2';
                chars[charLen--] = '%';
            }else {
                chars[charLen--] = str.charAt(strLen);
            }
            strLen--;
        }
        return new String(chars);
    }
}
```
