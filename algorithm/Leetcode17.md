### [leetcode17](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)

电话号码键盘的字母组合

- 参考代码：

```java

class Solution {

    List<String> result;

    String letterMap[] = new String[]{
            " ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {

        result = new ArrayList<>();

        if (digits.equals("")) {
            return  result;
        }

        combination(digits, 0, "");

        return result;

    }
    
     private void combination(String digits, int index, String s) {

        if (index == digits.length()) {
            result.add(s);
            return;
        }

        char c = digits.charAt(index);
        assert (c >= '0' && c <= '9' && c != '1');

        // 当前的数字字符锁能对应的字母组合 比如："abc", "def" ...等
        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            combination(digits, index + 1, s + letters.charAt(i));
        }
        return;

    }
}

```