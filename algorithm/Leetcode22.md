### [leetcode22](https://leetcode-cn.com/problems/generate-parentheses/)

给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

示例： n = 3

```

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

- 思路：

1. 使用递归尝试所有可能，并将符合条件的加入结果中。

2. 判断括号是否为有效时，使用一个balance标记，'('balance++,'）'balance--, balance != 0则不合法。

参考代码：

```java

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char[] chars = new char[n * 2];
        combernation(chars, 0, res, 0, 0, n);
        return res;
    }
    
    private void combernation(char[] chars, int pos, List<String> res,int open, int close, int max) {
        if(pos == chars.length) {
            res.add(new String(chars));
            return;
        }else {
            if(open < max) {
                chars[pos] = '(';
                combernation(chars, pos + 1, res, open + 1, close, max);
            }
            if(close < open) {
                chars[pos] = ')';
                combernation(chars, pos + 1, res, open, close + 1, max);
            }
        }
    }
}
```