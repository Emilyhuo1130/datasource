--���ݿ�day01--
----------nvl����-------------------------
--nvl(p1,p2)p1=nullʱ����p2,���򷵻�p1---
select id,nvl(base_cost,0) from cost;
select * from cost where 1=1 and id=nvl(id,7);
--distinct ������������ظ�--
select distinct unix_host from service;
-----��ϲ����ظ�-------
select distinct unix_host,create_date from service;

---************************   where�Ӿ��ѯ   ***********************-----
------��ÿ�µĹ̶�������5.9Ԫ,�����������ʱ������̶����á�------
select     id,
               base_duration * 12 ann_duration, 
            base_cost*12 ann_cost,
            unit_cost 
from cost 
where base_cost = 5.9;
----ÿ��Ĺ̶�������70.8Ԫ��---
select     id,
               base_duration * 12 ann_duration, 
            base_cost*12 ann_cost,
            unit_cost 
from cost 
where base_cost * 12 = 70.8;

------�г�unix������ip��ַ��os�ʺ������������ֻ����os�ʺţ�huangr----
select unix_host,os_username from service 
where os_username = 'huangr';
---upper,ת��Ϊ��д---
select unix_host,os_username from service 
where upper(os_username) = 'HUANGR';
---lower,ת��ΪСд---
select unix_host,os_username from service 
where lower(os_username) = 'huangr';
---initcap,����ĸ��д---
select unix_host,os_username from service 
where initcap(os_username) = 'Huangr';

--�г��¹̶�������5Ԫ��10Ԫ֮����ʷ���Ϣ����ʾ�ʷ�ID���°�����ʱ�����¹̶����ã���λ���á�
select id,base_duration,base_cost,unit_cost 
from cost
where base_cost >= 5 and
          base_cost <= 10;
          --between--
select id,base_duration,base_cost,unit_cost 
from cost
where base_cost between 5 and 10;

--�¹̶�������5.9Ԫ��8.5Ԫ��10.5Ԫ�ĺ����ǵ���5.9Ԫ���ߵ���8.5Ԫ���ߵ���10.5Ԫ
--in��ʹ��---
select id,base_duration,base_cost,unit_cost 
from cost
where base_cost in (5.9,8.5,10.5,null);
select id,base_duration,base_cost,unit_cost 
from cost
where base_cost in (5.9,8.5,10.5)
          or base_cost is null;
        
---like--d��ʹ��----  ��h��ͷ������
select os_username from service where os_username like 'h%';

select os_username from service 
where os_username like 'h\_%' escape '\';

------------is  null ��  not null--------------
select base_cost,unit_cost from cost 
where unit_cost is null;

select base_cost,unit_cost from cost 
where unit_cost is not null;
--�¹̶����ò�����5.9Ԫ��8.5Ԫ��10.5Ԫ�ĺ����ǲ�����5.9Ԫ���Ҳ�����8.5Ԫ���Ҳ�����10.5Ԫ��
select id,base_duration,base_cost,unit_cost from cost
where nvl(base_cost,0) <> 5.9 
and nvl(base_cost,0) <> 8.5 
and   nvl(base_cost,0) <> 10.5;

select id,base_duration,base_cost,unit_cost from cost
where nvl(base_cost,0) not in  (5.9,8.5,10.5);

select id,base_duration,base_cost,unit_cost from cost
where base_cost not in (5.9,8.5,10.5,null);

-----------����ͽ���------------
----order by colname asc����ʾ�������У�asc����ʡ�ԣ�
----order by colname desc����ʾ�������У�
--����������-----
select id,unix_host,os_username,create_date
from service
order by create_date;
--���¹̶����ã�����
select id,base_cost,base_duration
from cost
order by base_cost desc;
----���ñ���-----����----
select id,base_cost*12 ann_cost,base_duration ann_duration
from cost
order by base_cost desc;

--order by�Ӿ������Ը����У�������Ȱ���һ���������У�����ֵһ�����ٰ��ڶ��н������С�
select id,unix_host,os_username,create_date
from service
order by unix_host,create_date desc;

--------round������ʾ�������룬trunc������ʾ��ȡ��ʾ������------------
select    base_cost,
            round(base_cost,1) round1,
            round(base_cost) round2,
            trunc(base_cost,1) trunc1,
            trunc(base_cost) trunc2
from cost;
---------------------------��ʾ��ǰ���ں�ʱ��-----------------------
--�������ڸ�ʽ
alter session set nls_date_format = 'yyyy mm dd hh24:mi:ss';
select sysdate from dual;
--���ڵĴ���----
create table test(c1 date);
insert into test values ('01-JAN-08');
insert into test values ('2008-08-08');--������--
insert into test values (to_date('2008-08-08','yyyy-mm-dd'));
select c1 from test;
select to_char(c1,'yyyy-mm-dd') from test;


insert into test values (to_date('2008-08-08 08:08:08','yyyy-mm-dd hh24:mi:ss'));
select to_char(c1,'yyyy-mm-dd hh24:mi:ss') from test;


----os�ʺŵĴ�����Ϣ��create_date�У�
--to_char����ͨ��ͨ����ʽ˵�����Ի�ȡ�ꡢ�¡��ա�ʱ���֡����е�����һ���֣���Ҫ���·���Ϣ��ʽ'mm'��
select os_username,create_date from service 
where to_char(create_date,'mm') = '03';--���3�·ݵ�

select os_username,create_date from service 
where to_char(create_date,'fmmm') = '3';

select os_username,create_date from service 
where to_char(create_date,'mm') = 3;

select user,create_date from service 
where to_number(to_char(create_date,'mm')) = 3;


-----------------------------to_number������ע������---------------------
--��to_number����������ַ���Ϊ'ab'����ϵͳ������ת�����ֵ��ʮ���Ƶģ���Ҫ���ַ��������������ַ���
select to_number('ab') from dual;--������invalid number��

--��ʾ�¹̶����ã���λ���ã���λ����Ϊnull����ʾno unit cost--
select base_cost,'   ',nvl(to_char(unit_cost),'no unit cost') unit_cost
from cost;



------------------------------day 03---   ��������---------------------------
--��ʾ�����죬���죬���죬���ȵ�ʱ���֣��롣
alter session set nls_date_format = 'yyyy mm dd hh24:mi:ss';
select sysdate-1,sysdate,sysdate+1 from dual;--sysdate+1��ʾ���ڼ�һ���
--��ʾ��ʮ����֮���ʱ�䣬���ȵ�ʱ���֣��롣 ʮ����Ϊ 1/144��
alter session set nls_date_format = 'yyyy mm dd hh24:mi:ss';
select sysdate,sysdate + 1/144 from dual;
--�г�unix������ip��ַ��os�ʺ�������ͨ���ڣ���ͨ����
select unix_host,os_username,create_date,
        round(sysdate - create_date) days
from service;

--�г�ҵ���ʺ�ID��unix������ip��ַ��os�ʺţ���ͨʱ�䣬��ͨ���������������ͨ�����ӳ����̵�˳����ʾ
select id,unix_host,os_username,create_date,round(sysdate - create_date) days
from service
order by sysdate - create_date desc;
select id,unix_host,os_username,create_date, round(sysdate - create_date) days
from service
order by 5 desc;  --�����а�����
select id,unix_host,os_username,create_date, round(sysdate - create_date) days
from service
order by create_date;

--�г��ϸ��µĽ��죬���죬�¸��µĽ��죬���ȵ�ʱ���֡���
alter session set nls_date_format = 'yyyy mm dd hh24:mi:ss';
select add_months(sysdate,-1),sysdate,add_months(sysdate,1) from dual;
--�г���ǰ�µ����һ�죬��ȷ��ʱ���֡��롣
select last_day(sysdate) from dual;
--���°�����ʱ��Ϊ20Сʱ����λ������5�֣�Ϊ40Сʱ��3�֣��������䣨��CASE WHENʵ�֣�
select     base_duration,base_cost,
            case when  base_duration = 20 then unit_cost + 0.05
                 when  base_duration = 40 then unit_cost + 0.03
            else
                 unit_cost
            end new_unit_cost
from cost;
--����cost�е���Ϣ,������ʹ����50Сʱ,ÿ���ʷѱ�׼�¸ø����ٷ���
select     base_cost,base_duration,
            case when nvl(base_duration,0) < 50
                 then nvl(base_cost,0) + 
                          (50 - nvl(base_duration,0)) * nvl(unit_cost,0)
            else
                 base_cost
            end  new_cost
from cost;
--�°�����ʱ����20��40Сʱ֮�䣬��λ������5�֣��°�����ʱ������40Сʱ����λ������3��
select base_duration,base_cost,
        case when base_duration between 20 and 40 
             then unit_cost + 0.05
             when base_duration > 40
             then unit_cost + 0.03
        else
             unit_cost
        end  new_unit_cost
from cost;
--���°�����ʱ��Ϊ20Сʱ����λ������5�֣�Ϊ40Сʱ��3�֣��������䣨��decodeʵ�֣�
--decode�������ڽ����ͬ��¼��Ҫ��ͬ����ʽ�����⡣
--��base_duration= 20,��ô��������ֵΪunit_cost + 0.05;��base_duration= 40,
--��ô��������ֵΪunit_cost + 0.03;���򷵻�unit_costֵ��
--��û�����һ������unit_cost����������ֵΪnull��
select base_duration,base_cost,
           decode(base_duration,20,unit_cost + 0.05,
                                 40,unit_cost + 0.03,
                                unit_cost) new_unit_cost
from cost;

--��λ���õ��ܺͣ�ƽ��ֵ�����ֵ����Сֵ������
---------------------------sum���  avg��ƽ��ֵ max�����ֵ min��Сֵ count����----------------------------
select sum(unit_cost) sum1,avg(unit_cost) avg1,
max(unit_cost) max1,min(unit_cost) min1,
count(unit_cost) cntfrom cost;

select avg(nvl(unit_cost,0)),sum(unit_cost)/count(*) 
from cost;

select avg(unit_cost),count(unit_cost) 
from cost 
where unit_cost is null;

--�г�unix��������ip��ַ�͸û����Ͽ�ͨ��os�ʺ�����
--group ��ͬ�ķ�һ��
select unix_host,count(os_username) from service
group by unix_host;

--��ϰһ���г�192.168.0.26�����Ͽ�ͨ��os�ʺ������������ֻ��ʾ��ͨ��os�ʺ�����
--��ϰ�����г�192.168.0.26�����Ͽ�ͨ��os�ʺ��������������ʾunix������ip��ַ����ͨ��os�ʺ�����
select unix_host,count(os_username) cnt
from service
where unix_host = '192.168.0.26'
group by unix_host;

select max(unix_host),count(os_username) cnt
from service
where unix_host = '192.168.0.26';

select min(unix_host),count(os_username) cnt
from service
where unix_host = '192.168.0.26';

--�г�unix������ip��ַ����ͨʱ�䣬��ͨ��os�ʺ�����
select unix_host,to_char(create_date,'yyyymmdd') create_date,
        count(os_username) cnt
from service
group by unix_host,to_char(create_date,'yyyymmdd');
--�г�unix������ip��ַ����ͨ��os�ʺ������������ֻ������ͨ��os�ʺ�������2���ġ�
select unix_host,count(os_username) cnt
from service
group by unix_host
having count(os_username) > 2;--������������������ļ��㡣having�Ӿ�ִ����select�Ӿ�֮ǰ��
--�г�unix������ip��ַ����ͨʱ�䣬��ͨ��os�ʺ������������ֻ������ͨ��os�ʺ�������1���ġ�
select unix_host,to_char(create_date,'yyyymmdd') create_date,
        count(os_username) cnt
from service
group by unix_host,to_char(create_date,'yyyymmdd')
having count(os_username) > 1;

--��ʾunix������ip��ַ��os�ʺ�������ͨʱ�䣬�������ֻ������ͨʱ����С��os�ʺš�
select unix_host,os_username,create_date from service
where create_date = (select min(create_date) from service);
--��ʾunix������ip��ַ,��ͨ����,os�ʺ���,�������ֻ������ͨ���ڱ�unix������192.168.0.26�ϵ�huangrong��ġ�
select unix_host, create_date,os_username from service
where create_date >
                (select create_date from service
                 where os_username = 'huangr'
                 and unix_host = '192.168.0.26');
                 
--�г��Ƽ��˵Ŀͻ�����
select real_name from account
where id in (select recommender_id from account);
--��ʾunix������ip��ַ,��ͨ����,os�ʺ���,�������ֻ����ÿ̨unix�����������翪ͨ��os�ʺš�
select unix_host,os_username,create_date from service
where (unix_host,create_date) in 
(select unix_host,min(create_date) from service
group by unix_host);

--��ʾunix������ip��ַ,��ͨ����,os�ʺ���,�������ֻ������ͨ���ڱ�����unix�����������翪ͨ����������os�ʺš�
select unix_host,os_username,create_date from service
where (unix_host,to_char(create_date,'yyyymmdd')) in 
(select unix_host,
            to_char(min(create_date) + 9,'yyyymmdd')
from service
group by unix_host);



--********************************    day 04******************************88
--��ʾunix������ip��ַ,��ͨ����,os�ʺ���,�������ֻ����os�ʺŵĿ�ͨ������ƽ����ͨ����������Щ��¼��
select unix_host os_username ,create_date,
        round(sysdate - create_date) open_age
from service o
where round(sysdate - create_date) >
            (select avg(round(sysdate - create_date))
             from service i
             where o.unix_host = i.unix_host);
             
---��ʾ�ͻ�����,�������ֻ����������Զ�̵�¼ҵ��Ŀͻ���
--exists �����Ӳ�ѯ
select real_name from account o
where exists
(select 1 from service i
where o.id = i.account_id);

--��ʾ�ͻ�����,unix������ip��ַ��os�ʺ���,��ͨ����(�������ֻ������ͨ��Զ�̵�¼ҵ��Ŀͻ�)��
--jion
column real_name format a12
column os_username format a12
column unix_host format a16

select a.real_name,s.unix_host,s.os_username,s.create_date
from account a join service s
on a.id = s.account_id;

--�ͻ�huangrong����Щunix��������������Զ�̵�¼ҵ��
select a.real_name,s.unix_host,s.os_username,s.create_date
from account a join service s
on a.id = s.account_id
and a.real_name = 'huangrong';

--�г��ͻ������Լ���ͨ��Զ�̵�¼ҵ�������
select a.id,max(a.real_name),count(a.id)
from account a join service s
on a.id = s.account_id
group by a.id;

--�г��ͻ������Լ������Ƽ���
select a1.real_name customer,
            a2.real_name recommender
from account a1 join account a2
on a1.recommender_id = a2.id;


--��Щ�ͻ����Ƽ���
select distinct a2.id,a2.real_name
from account a1 join account a2
on  a1.recommender_id = a2.id;
------

------************************day 05 ����ѯ**************************88-------------
--�г��ͻ������Լ������Ƽ���
select a1.real_name customer,
        nvl(a2.real_name,'No Recommender') recommender
from account a1 left join account a2
on a1.recommender_id = a2.id;

--�г��ͻ������Լ�����ͨ��Զ�̵�¼ҵ�����Ϣ 
select a.id,a.real_name,s.unix_host,s.os_username 
from account a left join service s
on a.id = s.account_id;

--�г��ͻ������Լ���ͨ��Զ�̵�¼ҵ�������
select a.id,max(a.real_name),count(s.id)
from account a left join service s
on a.id = s.account_id
group by a.id;

--��Щ�ͻ������Ƽ���
select a1.real_name recommender
from account a1 left join account a2
on a1.id = a2.recommender_id
where a2.id is null;

--��ЩUNIX��������û��os�ʺ�weixb
select h.id from host h left join service s
on h.id = s.unix_host
and s.os_username = 'weixb'
where s.id is null;

------��ʾ�ͻ��������
select real_name,birthdate,s.name
from account a join age_segment s
on round((sysdate - birthdate)/365) between lowage and hiage;

--��ʾ�ͻ�huangrong�������
select real_name,birthdate,s.name
from account a join age_segment s
on round((sysdate - birthdate)/365) between lowage and hiage
and real_name = 'huangrong';

--��ʾ��������εĿͻ�����û�пͻ�������β��ó����ڽ�����У�
select max(s.name),count(a.id)
from account a join age_segment s
on round((sysdate - birthdate)/365) between lowage and hiage
group by s.id;

-- ��ʾ��������εĿͻ�����û�пͻ�������εĿͻ���Ϊ0��
select max(s.name),count(a.id)
from account a right join age_segment s
on round((sysdate - birthdate)/365) between lowage and hiage
group by s.id;

--���°�����ʱ��Ϊ20Сʱ����λ������5�֣�Ϊ40Сʱ��3�֣��������䣨��UNION ALLʵ�֣�
select base_duration,base_cost,unit_cost + 0.05 new_unit_cost
from cost
where base_duration = 20
union all
select base_duration,base_cost,unit_cost + 0.03
from cost
where base_duration = 40
union all
select base_duration,base_cost,unit_cost
from cost
where base_duration not in (20,40)
union all
select base_duration,base_cost,unit_cost
from cost
where base_duration is null;

-- ��̨UNIX��������û�п�ͨԶ�̵�¼ҵ��
select id from host
minus--�ų�����Ľ����
select unix_host from service;

--�ҳ�������Ϣ���ǰ������¼
select rownum,id,real_name,create_date from account
where rownum <= 3;--ǰ3�еļ�¼

--�ҳ�������Ϣ��ĵ���������������¼��
select rn,id,real_name,create_date
from
    (select rownum rn,id,real_name,create_date from account
    where rownum <= 6)
where rn >= 4;

--���翪ͨNetCTOSSϵͳ��ǰ�����ͻ���
selectrownum,id,real_name,days
from
    (select id,real_name,round(sysdate - create_date) days 
from account
order by days desc)
where rownum <= 3;

--���翪ͨNetCTOSSϵͳ�ĵ��ĵ��������ͻ���
select rn,id,real_name,days
from (
        select rownum rn,id,real_name,days
        from
            (select id,real_name,round(sysdate - create_date) days 
            from account
        order by days desc)
            where rownum <= 6
     )
where rn >= 4;