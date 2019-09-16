# MySQL数据查询

### 单表查询

- 查询所有字段

```sql

select * from furits;
```

- 查询指定字段

```sql

select f_id, f_name from furits;
```

- 查询指定记录

 select clumns1, clumns2 from table where ...
 
 ```sql

select f_name, f_price from furits where f_price > 10.0;
```

- 带IN关键字的查询(相反则使用NOT IN)

```sql

select s_id, f_name from furits where s_id IN (101, 102) order by f_name;
```

- 带between .. and..关键字的查询(相反则使用 NOT BETWEEN ... AND ...)

```sql

select f_name, f_price from furits where f_price between 2.0 and 10.0;
```

- LIKE关键字的模糊查询('%'匹配任意字符，'_'匹配单个字符)

```sql

select f_name, f_price from  furits where f_name LIKE 'b%_g';
```

- 查询空值 (IS NULL, IS NOT NULL)

```sql

select s_name, s_tel, s_email from student where s_email IS NULL ;
```

- 带AND[交集]、OR[并集]的多条件查询

这里需要注意的是OR和IN关键字都可以实现相同的效果，但是IN的效率会更高。

- 对查询结果去重复(DISTINCT)

```sql

select distinct s_id from furits;
```

- 对查询结果进行排序(ORDER BY),并指定排序的方向(降序DESC,升序ASC)

```sql

select f_name, f_price from furits ORDER BY f_name;
```

- 分组查询(GROUP BY)

 ```sql

select s_id, count(*) as total from furits GROUP BY s_id;
```

- HAVING过滤分组(对已经查询出来的结果进行过滤)

```sql

select s_id, GROUP_CONCAT(f_name) AS names from furits having count(f_name) > 1;
```

- GROUP BY 和 ORDER BY 一起使用对分组进行排序

```sql

select s_id, f_name from furits GROUP BY s_id ORDER BY f_name;
```

使用 GROUP BY + SUM函数 + ORDER BY 对数据分组查询并求和

- 使用LIMIT限制查询结果(limit x, y [该范围包前不包后])

```sql

select * from furits limit 4;
```

### 使用聚合函数查询

函数名称 | 作用 |
-- | -- |
AVG()| 返回某列平均值 
COUNT()| 返回某列的函数
MAX()| 返回某列的最大值
MIN()|返回某列的最小值
SUM()| 返回某列的总和

- count()

使用count函数查询不同订单的水果种类

```sql

select o_number, count(f_id) from orders GROUP BY o_number;
```

### 连接查询

- 内连接查询

inner join 进行内连接查询(两表都必须满足条件)

```sql

select f.s_f, s_name, f_name, f_price from furits f inner join suppliers s on s.s_id = f.s_id;
```

- 外连接查询

外连接查询分为左连接、右连接、全连接

    左连接返回的是左表所有记录和右表符合条件的记录

    右连接返回的是右表所有记录和左表符合条件的记录
    
在customers 和order表中查询所有的客户以及订单号

```sql

select c.c_id, o.o_number from customers left outer join order o on o.c_id = c.c_id;
```

在一个查询语句中，用于连接查询的限定条件ON 和过滤条件Where 只能出现一次；

### 子查询

一个查询语句嵌套在另一个查询语句中，在嵌套子查询中，首先执行子查询，再将其结果作为外语句的执行条件。

子查询常用的操作符号：ANY(SOME), ALL,IN, EXISTS;

- 带ANY，SOME的子查询

ANY放在一个比较符号的后面，表示前面的表达式满足后面子查询的任意结果，则返回TRUE

在tab1中查询数字满足该数字大于tab2中的任意一个数字

```sql

select num1 from tab1 where num1 > ANY (select num2 from tab2);
```

- 带ALL关键字的子查询

all关键字的作用是表达式必须全部满足子查询中的结果

- 带EXISTS关键字的子查询

这样的嵌套查询的首先判断 EXISTS后面的子查询是否返回结果；true则执行外层查询，否则外层查询不执行。

```sql

select * from furits where EXISTS(select s_name from suppliers where s_id = 107);
```

EXISTS 还可以和条件表达式一起在where后面作为条件的判断

```sql

select * from furits where f_price > 10.0 and exists (select  s_name from suppliers where s_id = 101);
```

- 带IN关键字的子查询

内层查询返回一个数据序列，该数据列将与外层查询逐一比较。

查询具有f_id为c0订单的客户ID

```sql

select s_id from orders where o.id_num IN (select o_number from order where f_id = 'c0');
```

- 带比较运算符的子查询

前面带关键字的子查询读返回一个数据序列，但是在带符号的子查询中，子查询则返回一个确定的值；

联合 fruits 和 suppliers 查询贵州地区的供应商提供的水果种类

```sql

select s_id,f_name from furits where s_id = (select s_id from suppliers s where s.city='贵州');
```

### 合并查询结果

使用 union 关键字合并结果将去除重复， 使用 union all 则不去除重复。 

需要注意的是 union 前后的select语句的字段类型必须匹配，否则将报错。

