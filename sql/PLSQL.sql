------------------���ݿ����---------------

-----------------���ýű���ʾ------------
Set Serveroutput On;

create table cost_t1 as 
select base_cost,base_duration,unit_cost
from cost
where id=3;

--ʹ�ö�̬SQL��ʽ����ִ��DDL���
begin
execute immediate
 'create table test(id number(2))';
end;


-------------------------begin  end���---------------
declare
id number:=20;
begin
--...........
end;
-------------type���  ������¼��һ����¼�����˼������в�ͬ���Ե���Ϣ--
-------------------------record-----
declare
type t_cost_rec is record(--һ����¼
  base_cost cost.base_cost%type,--base_cost���������;���cost.base_costһ���ģ���%type
  base_duration cost.base_duration%type,
  unit_cost cost.unit_cost%type
);
v_cost t_cost_rec;
v_cost_1 t_cost_rec;
begin
  v_cost.base_cost:=5.9;--����ֵ
  v_cost.base_duration:=20;
  v_cost.unit_cost:=0.4;
  v_cost_1:=v_cost;
  select base_cost,base_duration,unit_cost
  into v_cost_1 from cost
  where id=2;--��cost��ȡ��id=2��һ����Ϣ�������ŵ�v_cost_1���γ�һ����¼
  
  -- insert into ����ʱֻ����Ψһ��һ����¼
  insert into cost_t1 values v_cost;--��һ����¼��v_cost����Ϣ���뵽��cost_t1��
  update cost_t1 set row=v_cost_1;--��д��cost_t1�е�����
  commit;
end;

select * from cost_t1;




--                              CURSOR                  

--------------------------------loopѭ��------------------
declare
cursor c_account is
select real_name from account;--����һ��cursor����ű�account��real_name�е���Ϣ
v_real_name varchar2(20);
begin 
  open c_account;--select ���ݵ�cursor��
  loop 
  fetch c_account into v_real_name;--fetch���������У���ֵ������
    exit when c_account%notfound;--��ǰһ��fetchû�з���ֵʱ������ֵΪtrue
    dbms_output.put_line(v_real_name);--��ֵ��ӡ����
    end loop;
  close c_account;--�ر���Դ��
end;

------------------------whileѭ��--------------------------
declare
cursor c_account is
select real_name from account;
v_real_name varchar2(20);
begin 
  open c_account;
   fetch c_account into v_real_name;
  while c_account%found loop--%found ���ǰһ��fetch���ؼ�¼����ֵΪtrue��û��ǰһ�����ͷ���null��û��fetch��û����ֵ���޷�����ѭ��������Ҫ��fetch
    dbms_output.put_line(v_real_name);
    fetch c_account into v_real_name;
  end loop;
  close c_account;--�ر���Դ��
end;


----------------------forѭ��------------------------------
declare
cursor c_account is
select real_name,birthdate from account;
begin
  for i in c_account loop--��cursor�е�ֵһ������i���в���
    dbms_output.put_line(i.real_name||'  '||round(((sysdate-i.birthdate)/365),0));
  end loop;
end;

--                        ����  collection

---------------------------�൱������-----------------
declare 
type t_indtab is table of number 
index by binary_integer;
v_indtab t_indtab;
begin
v_indtab(1):=1;
v_indtab(10):=10;
dbms_output.put_line(v_indtab(1)||'  '||v_indtab(10));
end;

------------------forѭ����ʾһ�������е�����Ԫ��------------
-----------��������,�൱��һ�����飬���������������---------------
declare
cursor c_account is
select real_name,id from account;
type t_account is table of varchar2(20)
index by binary_integer;--�±�����
v_account t_account;
begin
  for i in c_account loop--forѭ����ʾһ�������е�����Ԫ��
    v_account(i.id):=i.real_name;
    dbms_output.put_line(v_account(i.id));
  end loop;
end;

--------------------���� first��last��exists------------------
declare
cursor c_account is
select real_name,id from account;
type t_account is table of varchar2(20)--��������
index by binary_integer;--�±�����
v_account t_account;
begin
  for i in c_account loop
    v_account(i.id):=i.real_name;
  end loop;    --�Ͻ�          --�½�
  for i in v_account.first..v_account.last loop--i������Ϊinteger
    if v_account.exists(i) then--���i����������±꼯���У���ִ�д�ӡ
    dbms_output.put_line(v_account(i));
    end if;
  end loop;
end;

-----------------------------nest��prior--------------------------
declare
cursor c_account is
select real_name,id from account;
type t_account is table of varchar2(20)--��������
index by binary_integer;--�±�����
v_account t_account;
v_index account.id%type;
begin
  for i in c_account loop
    v_account(i.id):=i.real_name;
  end loop; 
  v_index:=v_account.first;
  while v_index<=v_account.last loop
     dbms_output.put_line(v_account(v_index));--����v_account����һ���±�
      v_index:=v_account.next(v_index);
  end loop;
end;


----------------------------collect------�߼��ɶ�----------------
declare
type t_account_rec is record(--�ȶ���һ����¼
real_name varchar2(20),
age number(3)
);
type t_account_arr is table of t_account_rec --ָ��������ϵ�ÿһ��Ԫ�ص�����
index by binary_integer;
v_account t_account_arr;
sum_age number(5):=0;
begin
select real_name,round (((sysdate-birthdate)/365),0) 
bulk collect into v_account--�����еļ�¼�ŵ������У�---�߼��϶�
from account;--������
for i in v_account.first..v_account.last loop
  sum_age:=sum_age+v_account(i).age;
  dbms_output.put_line(v_account(i).real_name||'  '||v_account(i).age||'  '||sum_age);
end loop;
end;

----------------------------Ԥ�����쳣------------------------
declare
type t_account is record(
id account.id%type,
realname account.real_name%type,
id2 account.id%type
);
v_account t_account;
begin
v_account.id:=1020;--�Ҳ���ʱ��  no data found
v_account.realname:='lisi';
select id into v_account.id2 from account
where id=v_account.id;
  if v_account.id =v_account.id2 then
    dbms_output.put_line('di���Ѿ�����');
    dbms_output.put_line(v_account.id||'  '||v_account.realname);
end if;
--�쳣��Ĳ���
exception
when no_data_found then
    dbms_output.put_line('di�Ų�����');
end;


---------------------------��Ԥ�����쳣----------------
declare
e_npkey exception ;
pragma exception_init(e_npkey,-2291);--����һ���쳣



open
fetch bulk collect into



--����
exec test1;   --���ù���
exec test2;

---------------------------�󶨱���---------------
begin
  for i in 1..10 loop--i ��1��10����ֵ�����У�һ��һ������
    execute immediate -- ���ԶԶ�����Ƶ����ݸ�ֵ����
      insert into test values(:1,:2) using i,i;
  end loop;
end;

-- ---------------------------����  procedure-----------------------
------------------------------in out in out------------------------
declare
v_c1 varchar2(20):='abc';
v_c2 varchar2(20):='abc';
v_c3 varchar2(20):='abc';
begin
  test1(v_c1,v_c2,v_c3);--ʵ��
  dbms_output.put_line('���ù��̽�����v_c1��ֵ:'||v_c1);--in ����v_c1��ԭֵ abc
  dbms_output.put_line('���ù��̽�����v_c2��ֵ:'||v_c2);--out ���ع����е�ֵb  �������޹�
  dbms_output.put_line('���ù��̽�����v_c3��ֵ:'||v_c3);-- in out ����adbc �������й�
end;


--------------------------------����  return-----------------------
--------------�����ж���return��䣬��ֻ��һ��return��䱻ִ��-----------
declare
v_c1 varchar2(20):='abcd';
begin
    v_c1:=fun_test1(v_c1);
    dbms_output.put_line(v_c1);
end;

--------------------�ú����ķ���   ����id�ŷ����û������ֺ�����---------
declare
v_c1 number(4):=1018;
show varchar2(50);
begin
    show:=fun_show(v_c1); 
   dbms_output.put_line(show);
end;

-------------------------�ù��̵ķ���   ����id�ŷ����û������ֺ�����--------
declare
v_c1 number(4):=1018;
show varchar2(50);
begin
   test_show(v_c1,show); 
   dbms_output.put_line(show);
end;



------------------------------��----package----------------------
-------�����е��ú���--------

BEGIN
  dbms_test.pro(1010);
END;


-------------------------------������---------------------------
-------DML������-------
---���������Ǹ����ֵ�����仯���������ı������Ҳ�����˱仯----------
drop table test_22 purge;
drop table test_11 purge;
SELECT * FROM test_22;
SELECT * FROM test_11;
--�ȴ������ӱ�
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