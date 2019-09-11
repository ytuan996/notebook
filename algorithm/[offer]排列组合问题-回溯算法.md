### 字符串的全排列

 给定一个没有重复数字的序列，返回所有可能的全排列
 
 - 思路：
 
 求字符串的排列，可以分成两步来走：首先求出所有可能出现在第一个位置的字符(即第一个字符和后面所有的字符交换)，
 然后把字符分为两部分，第一个和后面的所有字符，固定第一个字符，递归求后面所有的字符序列。
 
 ```java

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if(nums == null || nums.length == 0) {
            return res;
        }

        int len = nums.length;

        backTrace(len, nums, res, 0);

        return res;
    }

    private void backTrace(int len, int[] aux, List<List<Integer>> res, int index) {
        // 递归结束的条件
        if(len == index) {
            ArrayList<Integer> item = new ArrayList<>();
            for(int k : aux) {
                item.add(k);
            }
            res.add(item);
        }
        for(int i = index; i < len; i++) {
            swap(aux, i, index);
            backTrace(len, aux, res, index + 1);
            // 回溯
            swap(aux, i, index);
        }
    }

    private void swap(int[] nums, int k, int l) {
        if(k == l) {
            return;
        }
        int tmp = nums[k];
        nums[k] = nums[l];
        nums[l] = tmp;
    }
}
```

### 组合

给出两个整数n和k,返回[1, n] 中k个数的所有组合

```java

class Solution {
     public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < k) {
            return res;
        }
        List<Integer> aux = new ArrayList<>();
        generateCombine(n, k, 1, aux, res);
        return res;
    }

    private void generateCombine(int n, int k, int index, List<Integer> aux, List<List<Integer>> res) {
        if (aux.size() == k) {
            res.add(new ArrayList<>(aux));
        }
        for (int i = index; i <= n; i++) {
            aux.add(i);
            generateCombine(n, k, i + 1, aux, res);
            aux.remove(aux.size() - 1);
        }
    }
}
```

