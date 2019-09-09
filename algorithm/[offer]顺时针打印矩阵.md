### 顺指针打印矩阵

在矩阵中循环打印，考虑使用循环每次打印一圈，起点依次为[0,0],[1,1],[2,2]...,循环结束的条件为 rows > start*2,
还需要注意每次打印的条件。

- 参考代码：

```java

import java.util.ArrayList;
public class Solution {
    
    ArrayList<Integer> result = null;
    
    public ArrayList<Integer> printMatrix(int [][] matrix) {
       
        int rows = matrix.length;
        
        if (rows <= 0)
            return new ArrayList();
        
        int cloums = matrix[0].length;
        
        result = new ArrayList<>();
        
        int start = 0;
        while (cloums > start * 2 && rows > start * 2) {
            printMatrixCircle(matrix, start++);
        }
        return result;
    }
    
    // 打印二维矩阵的一圈
    private void printMatrixCircle(int [][] matrix, int index) {
        
        int endRow = matrix.length - 1 - index;
        int endCloum = matrix[0].length - 1 -index;
        
        // 从左到右
        for (int i = index; i <= endCloum; i++) {
            result.add(matrix[index][i]);
        }
        
        // 从上到下
        if (index < endRow) {
            for (int i = index + 1; i <= endRow; i++)
                result.add(matrix[i][endCloum]);
        }
        
        // 从右到左
        if (index < endCloum && index < endRow) {
            for (int i = endCloum - 1; i >= index; i--) {
                result.add(matrix[endRow][i]);
            }
        }
        
        // 从下到上
        if (endRow > index && endCloum > index) {
            for (int i = endRow - 1; i > index; i--) {
                result.add(matrix[i][index]);
            }
        }
    }
}
```