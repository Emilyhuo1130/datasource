insert into cost (
ID,
NAME,
cost_type,
BASE_DURATION,
BASE_COST,
UNIT_COST,
DESCR,
STATUS,
CREATIME)
values (seq_cost_id.nextval,
'34wrt',
0,
null,
null,
null,
'xxxxxxxxxx',
1,
to_date('08-6月 -13 03:09:24','DD-MON -RR hh24:mi:ss'));


insert into cost(id,name，CREATIME,status)values(
seq_cost_id.nextval,
'sss44ss',
to_date('22-4月 -12 22:12:34','DD-MON -RR hh24:mi:ss'),
1);

--创建序列--
create sequence seq_cost_id increment by 1 start with 11;
create sequence seq_account_id increment by 1 start with 1021;
create sequence seq_admininfo_id increment by 1 start with 1030;

--------
select sysdate from dual;
update account set  pause_date=null where id=1005;

update account set status=1,PAUSE_DATE=sysdate where id=1005;
