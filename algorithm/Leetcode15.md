### [leetcode15](https://leetcode-cn.com/problems/3sum/)

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。


- 思路：

标签：数组遍历

1.首先对数组进行排序，排序后固定一个数 nums[i]，再使用左右指针指向nums[i]后面的两端，数字分别为nums[L] 和 nums[R]，计算三个数的和 sums 判断是否满足为 0，满足则添加进结果集

2.如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环

3.如果 nums[i] == nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过

4.当 sum == 0 时，nums[L] == nums[L+1] 则会导致结果重复，应该跳过，L++

5.当 sum== 0 时，nums[R] == nums[R−1] 则会导致结果重复，应该跳过，R--

6.时间复杂度：O(n^2)，n 为数组长度

参考代码：

```java

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        int len = nums.length;
        
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < len - 2;i++) {
            
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            
            int L = i+1;
            int R = len-1;
            
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }

        }
        return res;
    }
}
```