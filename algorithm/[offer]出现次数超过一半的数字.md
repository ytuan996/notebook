### 出现次数超过一半的数字

- 排序法

首先对数组升序排列，然后循环一遍数组，如果后一个和前一个的元素相等，那么计数器加一，否则计数器置1，
如果n大于了数组的一般，那么就返回结果。特殊用例处理，数组为null，或者数组的长度为0 或者1；

时间复杂度为O(N *logN)

```java

import java.util.Arrays;
public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array == null || array.length == 0) {
            return 0;
        }
        if(array.length == 1)
            return array[0];
        Arrays.sort(array);
        int n = 1;
        for(int i = 0; i < array.length - 1; i++) {
            if(array[i + 1] == array[i]) {
                n++;
            }else {
                n = 1;
            }
            if(n > array.length / 2) {
                return array[i];
            }
        }
        return 0;
    }
}
```

- 快排partition法

出现次数超过一般的元素，如果存在那么一定是数组的中位数，按照快排partition的思想，如果每次partition后返回的
index大于中位数所在的位置，那么中位数应该位于左边，如果index小于中位数所在的位置，那么中位数就在右边，循环
直到index 等于中位数的位置，那么就是结果；需要考虑的特殊情况就是数组非法，或者数组中不存在元素出现次数超过长度的一半。

```java

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        // 检查输入的合法性
        if(!checkInputAvlid(array)) {
            return 0;
        }
        int len = array.length;
        int middle = len >> 1;
        int index = partition(array, 0, len - 1);
        while(index != middle) {
            if(index > middle) {
                index = partition(array, 0, index - 1);
            }else if(index < middle) {
                index = partition(array, index + 1, len - 1);
            }
        }
        // 检查index的有效性，即array[index]对应的数字出现次数是否超过了一半
        if(!checkResultAvlid(array, index))
            return 0;
        return array[index];
    }

    private int partition(int[] arr, int low, int high) {

       int  pivot = arr[low];

        // 优化2. 减少不必要的交换
        int ele = pivot;

        while (low < high) {

            while (low < high && arr[high] >= pivot) {
                high--;
            }
            //ArrayUtils.swap(arr,low, high);
            arr[low] = arr[high];

            while (low < high && arr[low] <= pivot) {
                low++;
            }
            //ArrayUtils.swap(arr,low, high);
            arr[high] = arr[low];
        }

        arr[low] = pivot;

        return low;
    }

    private boolean checkInputAvlid(int[] arr) {
        if(arr == null || arr.length <= 0)
            return false;
        return true;
    }

    private boolean checkResultAvlid(int[] arr, int index) {
        if(index >= arr.length)
            return false;

        int n = arr[index], num = 0;
        for( int i = 0; i < arr.length; i++) {
            if(arr[i] == n) {
                num++;
            }
        }
        if(num > (arr.length >> 1))
            return true;

        return false;
    }
}
```
- 数组特点法

根据题目我们可以发现，要求元素在数组中出现的次数大于长度的一半，也就是该元素出现的次数比其他元素出现的次数综合还多，
我们可以设置两个变量，分别存储结果元素和出现的次数，前后相同，次数加一，不同就减一，次数小于等于0时，更新结果元素。
如果最后元素出现的次数大于0的，就是结果。

```java

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        // 检查输入的合法性
        if(!checkInputAvlid(array)) {
            return 0;
        }
        int res = array[0], times = 1;
        int len = array.length;
        for(int i = 1; i < len; i++) {
            if(array[i] == array[i - 1]) {
                times++;
            }else {
                times--;
            }
            if(times <= 0) {
                res = array[i];
                times = 1;
            }
        }
        if(times >= 1) {
            if(checkResultAvlid(array, res)) {
                return res;
            }
        }
        return 0;
    }

    private boolean checkInputAvlid(int[] arr) {
        if(arr == null || arr.length <= 0)
            return false;
        return true;
    }

    private boolean checkResultAvlid(int[] arr, int n) {
        int  num = 0;
        for( int i = 0; i < arr.length; i++) {
            if(arr[i] == n) {
                num++;
            }
        }
        if(num > (arr.length >> 1))
            return true;
        return false;
    }
}
```