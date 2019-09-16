### MySQL 常规的数据查询练习

```sql

create table employee (
	e_no int(11) primary key ,
	e_name varchar(50) not null,
	e_gender char(2) not null,
	dept_no int(11) not null,
	e_job varchar(50) not null,
	e_salary int(11) not null,
	hire_date DATE not null,

	constraint dept_fk foreign key(dept_no) references dept(d_no)
);

create table dept(
	d_no int(11) primary key,
	d_name varchar(50) not null,
	d_location varchar(50) 
);

-- 1. 在emp表中，查询所有记录的e_no, e_name, e_salary
select e_no, e_name, e_salary from employee ;

-- 2. 在emp表中查询dept_no为10 和20 的所有记录
select * from employee where dept_no = 10 OR dept_no = 20;
select * from employee where dept_no IN (10, 20);

-- 3. 在emp表中查询工资范围在800～2500的员工信息
select  from employee where e_salary between 800 and 2500;

-- 4. 在emp中，查询部门编号为20的员工信息
select * from employee where dept_no = 20;

--5. 在emp中，查询每个部门最高工资的员工信息
select * from where e_salary = (select max(e_salary) from employee) group by dept_no;
select dept_no, max(e_salary) from employee group by dept_no;

-- 查询员工ytuan所在的部门以及所在地
select d.d_name, d.d_location from dept d inner join employee e on d.d_no = e.dept_no and e.e_name = 'ytuan';
select d.d_name, d.d_location from dept d, employee e where d.d_no = e.dept_no and e.e_name = 'ytuan';
select d.d_name, d.d_location from dept d where d_no =(select dept_no from employee where e_name='ytuan');

-- 使用连接查询，查询所有员工的部门和部门信息
select e.e_name, d.dept_no, d.d_name, d.d_location from employee e inner join dept d on d.d_no = e.dept_no;

-- 在emp中， 计算每个部门各有多少员工
select dept_no, count(e_no)  totalEmp from employee group by dept_no;

-- 在emp中，计算不同类型职工的总工资数
select e_job, sum(e_salary) totalSal from employee group by e_job;

-- 在emp中，计算不同部门的平均工资
select dept_no, avg(e_salary) avgSal from employee group by dept_no;

-- 在emp中，查询工资低于1500的员工信息
select * from employee where e_salary < 1500;

-- 在emp中，将查询记录先按部门编号降序排列，再按工资升序排列
select * from employee order by dept_no desc, e_salary desc;

-- 在emp表中，查询员工名字以字母‘A'或者‘S'开头的员工信息
select * from employee where e_name LIKE 'A%' OR e_name LIKE 'S%';
select * from employee where e_name REGEXP '^[AS]'

-- 在emp中，查询目前为止，工龄大于等于10年的员工信息[有关于日期的比较]
select * from employee where YEAR(CURDATE()) - TEAR(hire_date) >= 10;

```