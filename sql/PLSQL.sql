------------------数据库对象---------------

-----------------设置脚本显示------------
Set Serveroutput On;

create table cost_t1 as 
select base_cost,base_duration,unit_cost
from cost
where id=3;

--使用动态SQL方式才能执行DDL语句
begin
execute immediate
 'create table test(id number(2))';
end;


-------------------------begin  end语句---------------
declare
id number:=20;
begin
--...........
end;
-------------type语句  创建记录，一条记录包含了几个表中不同属性的信息--
-------------------------record-----
declare
type t_cost_rec is record(--一条记录
  base_cost cost.base_cost%type,--base_cost的数据类型就是cost.base_cost一样的，用%type
  base_duration cost.base_duration%type,
  unit_cost cost.unit_cost%type
);
v_cost t_cost_rec;
v_cost_1 t_cost_rec;
begin
  v_cost.base_cost:=5.9;--赋初值
  v_cost.base_duration:=20;
  v_cost.unit_cost:=0.4;
  v_cost_1:=v_cost;
  select base_cost,base_duration,unit_cost
  into v_cost_1 from cost
  where id=2;--从cost中取出id=2的一条信息，把它放到v_cost_1中形成一条记录
  
  -- insert into 插入时只能是唯一的一条记录
  insert into cost_t1 values v_cost;--把一条记录（v_cost）信息插入到表cost_t1中
  update cost_t1 set row=v_cost_1;--改写表cost_t1中的数据
  commit;
end;

select * from cost_t1;




--                              CURSOR                  

--------------------------------loop循环------------------
declare
cursor c_account is
select real_name from account;--声明一个cursor，存放表account中real_name列的信息
v_real_name varchar2(20);
begin 
  open c_account;--select 数据到cursor中
  loop 
  fetch c_account into v_real_name;--fetch，检索当行，赋值给变量
    exit when c_account%notfound;--当前一个fetch没有返回值时，返回值为true
    dbms_output.put_line(v_real_name);--把值打印出来
    end loop;
  close c_account;--关闭资源。
end;

------------------------while循环--------------------------
declare
cursor c_account is
select real_name from account;
v_real_name varchar2(20);
begin 
  open c_account;
   fetch c_account into v_real_name;
  while c_account%found loop--%found 如果前一条fetch返回记录，则值为true，没有前一条，就返回null；没有fetch就没返回值，无法进行循环，所以要先fetch
    dbms_output.put_line(v_real_name);
    fetch c_account into v_real_name;
  end loop;
  close c_account;--关闭资源·
end;


----------------------for循环------------------------------
declare
cursor c_account is
select real_name,birthdate from account;
begin
  for i in c_account loop--把cursor中的值一个个给i进行操作
    dbms_output.put_line(i.real_name||'  '||round(((sysdate-i.birthdate)/365),0));
  end loop;
end;

--                        集合  collection

---------------------------相当于数组-----------------
declare 
type t_indtab is table of number 
index by binary_integer;
v_indtab t_indtab;
begin
v_indtab(1):=1;
v_indtab(10):=10;
dbms_output.put_line(v_indtab(1)||'  '||v_indtab(10));
end;

------------------for循环显示一个集合中的所有元素------------
-----------集合类型,相当于一个数组，可以用来存放数据---------------
declare
cursor c_account is
select real_name,id from account;
type t_account is table of varchar2(20)
index by binary_integer;--下标类型
v_account t_account;
begin
  for i in c_account loop--for循环显示一个集合中的所有元素
    v_account(i.id):=i.real_name;
    dbms_output.put_line(v_account(i.id));
  end loop;
end;

--------------------方法 first，last和exists------------------
declare
cursor c_account is
select real_name,id from account;
type t_account is table of varchar2(20)--集合类型
index by binary_integer;--下标类型
v_account t_account;
begin
  for i in c_account loop
    v_account(i.id):=i.real_name;
  end loop;    --上界          --下界
  for i in v_account.first..v_account.last loop--i的类型为integer
    if v_account.exists(i) then--如果i这个数据在下标集合中，就执行打印
    dbms_output.put_line(v_account(i));
    end if;
  end loop;
end;

-----------------------------nest和prior--------------------------
declare
cursor c_account is
select real_name,id from account;
type t_account is table of varchar2(20)--集合类型
index by binary_integer;--下标类型
v_account t_account;
v_index account.id%type;
begin
  for i in c_account loop
    v_account(i.id):=i.real_name;
  end loop; 
  v_index:=v_account.first;
  while v_index<=v_account.last loop
     dbms_output.put_line(v_account(v_index));--返回v_account的下一个下标
      v_index:=v_account.next(v_index);
  end loop;
end;


----------------------------collect------高集成度----------------
declare
type t_account_rec is record(--先定义一条记录
real_name varchar2(20),
age number(3)
);
type t_account_arr is table of t_account_rec --指定这个集合的每一个元素的类型
index by binary_integer;
v_account t_account_arr;
sum_age number(5):=0;
begin
select real_name,round (((sysdate-birthdate)/365),0) 
bulk collect into v_account--把所有的记录放到集合中，---高集合度
from account;--批处理
for i in v_account.first..v_account.last loop
  sum_age:=sum_age+v_account(i).age;
  dbms_output.put_line(v_account(i).real_name||'  '||v_account(i).age||'  '||sum_age);
end loop;
end;

----------------------------预定义异常------------------------
declare
type t_account is record(
id account.id%type,
realname account.real_name%type,
id2 account.id%type
);
v_account t_account;
begin
v_account.id:=1020;--找不到时候  no data found
v_account.realname:='lisi';
select id into v_account.id2 from account
where id=v_account.id;
  if v_account.id =v_account.id2 then
    dbms_output.put_line('di号已经存在');
    dbms_output.put_line(v_account.id||'  '||v_account.realname);
end if;
--异常后的操作
exception
when no_data_found then
    dbms_output.put_line('di号不存在');
end;


---------------------------非预定义异常----------------
declare
e_npkey exception ;
pragma exception_init(e_npkey,-2291);--申明一个异常



open
fetch bulk collect into



--过程
exec test1;   --调用过程
exec test2;

---------------------------绑定变量---------------
begin
  for i in 1..10 loop--i 从1到10，把值赋给列，一个一个赋；
    execute immediate -- 可以对多个类似的数据赋值处理
      insert into test values(:1,:2) using i,i;
  end loop;
end;

-- ---------------------------过程  procedure-----------------------
------------------------------in out in out------------------------
declare
v_c1 varchar2(20):='abc';
v_c2 varchar2(20):='abc';
v_c3 varchar2(20):='abc';
begin
  test1(v_c1,v_c2,v_c3);--实参
  dbms_output.put_line('调用过程结束后v_c1的值:'||v_c1);--in 返回v_c1的原值 abc
  dbms_output.put_line('调用过程结束后v_c2的值:'||v_c2);--out 返回过程中的值b  与输入无关
  dbms_output.put_line('调用过程结束后v_c3的值:'||v_c3);-- in out 返回adbc 与输入有关
end;


--------------------------------函数  return-----------------------
--------------可以有多条return语句，但只有一条return语句被执行-----------
declare
v_c1 varchar2(20):='abcd';
begin
    v_c1:=fun_test1(v_c1);
    dbms_output.put_line(v_c1);
end;

--------------------用函数的方法   根据id号返回用户的名字和年龄---------
declare
v_c1 number(4):=1018;
show varchar2(50);
begin
    show:=fun_show(v_c1); 
   dbms_output.put_line(show);
end;

-------------------------用过程的方法   根据id号返回用户的名字和年龄--------
declare
v_c1 number(4):=1018;
show varchar2(50);
begin
   test_show(v_c1,show); 
   dbms_output.put_line(show);
end;



------------------------------包----package----------------------
-------过程中调用函数--------

BEGIN
  dbms_test.pro(1010);
END;


-------------------------------触发器---------------------------
-------DML触发器-------
---触发器就是父表的值发生变化后，依赖他的表的数据也发生了变化----------
drop table test_22 purge;
drop table test_11 purge;
SELECT * FROM test_22;
SELECT * FROM test_11;
--先创建父子表
create table test_11 (
c1 number(2) primary key
);
create table test_22(
c1 number (2) primary key,
c2 number(2) constraint test_22_fk references test_11(c1)
);
insert into test_11 values(2);
insert into test_22 values(1,1);
insert into test_22 values(2,1);
insert into test_22 values(3,2);
insert into test_22 values(4,1);

create or replace trigger t_test_pk 
after update on test_11
for each row
declare
begin
    if :new.c1 <> :old.c1 then
      update test_22 set c2 = :new.c1
      where c2 = :old.c1;
   end if;
end;
drop trigger t_test_pk;
update test_11 set c1=9 where c1=6;
after trigger t_test_pk disable;
select 