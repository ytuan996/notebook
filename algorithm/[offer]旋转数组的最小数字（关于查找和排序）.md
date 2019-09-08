### 关于面试的排序和查找

- 查找

对于查找来说，无外乎顺序查找、二分查找、哈希表查找、二分搜索树。

对于在排序的数组中查找一个数字或者统计某个数字，都可以使用二分查找；

哈希表主要的优点在于能够在O(1)时间内查找元素，但是空间需求较大。比如解决
"第一次只出现一次的字符"问题

二分搜索树：主要考察这种数据结构

- 排序

比较七大基本排序算法的优劣，可以从所需的额外空间，时间复杂度，排序是否稳定多方面比较
并且能够信手拈来的写出每个排序算法的各种实现。

### 查找旋转数组的最小数字


- 思路：

使用二分查找法的思想，如果返回mid的值大于start，那么最小值在后半部分，即[mid, end];
如果mid的小于satrt，那么最小值在前半部分，即[start,mid]；结束查找的条件是start和end
相邻，即start+1=end。

- 参考代码

```java

import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0) {
            return 0;
        }
        return minNumber(array, 0, array.length);
    }
    
    private int minNumber(int[]arr, int start, int end) {
        if(start + 1 == end) {
            return arr[end];
        }
        int mid = (start + end) / 2;
        if(arr[mid] >= arr[start]) {
            return minNumber(arr, mid, end);
        }else if (arr[mid] <= arr[start]) {
            return minNumber(arr, start, mid);
        }
        return 0;
    }
}
```