### 二维数组中的查找

![二维数组中的查找](https://github.com/ytuan996/notebook/blob/master/image/%E4%BA%8C%E7%BB%B4%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E6%9F%A5%E6%89%BE.png?raw=true)

- 思路：

根据题目的意思，充分利用数组有序的这一个特点，分析可以发现，我们从二维数组的右上角开始，定义为cur，当target >cur时，
target在当前行的下方，当target<cur时，target在当前列的左侧。那么可以定义两个指针分别记录行数和列数。循环比较即可。
当然我们也可以从左下角开始；

- 参考代码：

```java

public class Solution {
    public boolean Find(int target, int [][] array) {
        if(array == null || array.length == 0) {
            return false;
        }
        int i = 0, j = array[0].length - 1;
        while(i < array.length && j >= 0) {
            int cur = array[i][j];
            if(target == cur)
                return true;
            else if(target < cur) 
                j--;
            else 
                i++;
        }
        return false;
    }
}
```

- 扩展题目(小米2019秋招笔试题)：

```

题目描述:
输入一个无序整数数组，调整数组中数字的顺序， 所有偶数位于数组的前半部分，使得所有奇数位于数组的后半部分。
要求时间复杂度为O(n)。

输入实例：

2 4 5 7 8 1

输出实例：

2 4 8 7 5 1
```

- 参考代码：

```java

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().split(" ");
        
        if(strs.length == 0) {
            System.out.println("");
        }
        
        int[] res = new int[strs.length];
        for(int i = 0; i < strs.length; i++) {
            res[i] = Integer.parseInt(strs[i]);
        }
        optArray(res);
        for(int i:res) {
            System.out.print(i + " ");
        }
    }
    
    private static void optArray(int[] arr) {
        int start = 0, end = arr.length - 1;
        
        while(start < end) {
            if(arr[start] % 2 == 0) {
                start++;
            }else {
                swap(arr, start, end);
                end--;
            }
        }
    }
    
    private static void swap(int[] arr, int p, int q) {
        if(p != q) {
            int temp = arr[p];
            arr[p] = arr[q];
            arr[q] = temp;
        }
    }
}
```