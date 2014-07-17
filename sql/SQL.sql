--数据库day01--
----------nvl函数-------------------------
--nvl(p1,p2)p1=null时返回p2,否则返回p1---
select id,nvl(base_cost,0) from cost;
select * from cost where 1=1 and id=nvl(id,7);
--distinct 函数，输出非重复--
select distinct unix_host from service;
-----组合不能重复-------
select distinct unix_host,create_date from service;

---************************   where子句查询   ***********************-----
------若每月的固定费用是5.9元,计算年包在线时长，年固定费用。------
select     id,
               base_duration * 12 ann_duration, 
            base_cost*12 ann_cost,
            unit_cost 
from cost 
where base_cost = 5.9;
----每年的固定费用是70.8元，---
select     id,
               base_duration * 12 ann_duration, 
            base_cost*12 ann_cost,
            unit_cost 
from cost 
where base_cost * 12 = 70.8;

------列出unix服务器ip地址，os帐号名，结果集中只包含os帐号：huangr----
select unix_host,os_username from service 
where os_username = 'huangr';
---upper,转化为大写---
select unix_host,os_username from service 
where upper(os_username) = 'HUANGR';
---lower,转化为小写---
select unix_host,os_username from service 
where lower(os_username) = 'huangr';
---initcap,首字母大写---
select unix_host,os_username from service 
where initcap(os_username) = 'Huangr';

--列出月固定费用在5元到10元之间的资费信息，显示资费ID，月包在线时长，月固定费用，单位费用。
select id,base_duration,base_cost,unit_cost 
from cost
where base_cost >= 5 and
          base_cost <= 10;
          --between--
select id,base_duration,base_cost,unit_cost 
from cost
where base_cost between 5 and 10;

--月固定费用是5.9元，8.5元，10.5元的含义是等于5.9元或者等于8.5元或者等于10.5元
--in的使用---
select id,base_duration,base_cost,unit_cost 
from cost
where base_cost in (5.9,8.5,10.5,null);
select id,base_duration,base_cost,unit_cost 
from cost
where base_cost in (5.9,8.5,10.5)
          or base_cost is null;
        
---like--d的使用----  以h开头的名字
select os_username from service where os_username like 'h%';

select os_username from service 
where os_username like 'h\_%' escape '\';

------------is  null 和  not null--------------
select base_cost,unit_cost from cost 
where unit_cost is null;

select base_cost,unit_cost from cost 
where unit_cost is not null;
--月固定费用不等于5.9元，8.5元，10.5元的含义是不等于5.9元并且不等于8.5元并且不等于10.5元。
select id,base_duration,base_cost,unit_cost from cost
where nvl(base_cost,0) <> 5.9 
and nvl(base_cost,0) <> 8.5 
and   nvl(base_cost,0) <> 10.5;

select id,base_duration,base_cost,unit_cost from cost
where nvl(base_cost,0) not in  (5.9,8.5,10.5);

select id,base_duration,base_cost,unit_cost from cost
where base_cost not in (5.9,8.5,10.5,null);

-----------升序和降序------------
----order by colname asc，表示升序排列，asc可以省略；
----order by colname desc，表示降序排列；
--按日期升序-----
select id,unix_host,os_username,create_date
from service
order by create_date;
--按月固定费用，升序
select id,base_cost,base_duration
from cost
order by base_cost desc;
----设置别名-----降序----
select id,base_cost*12 ann_cost,base_duration ann_duration
from cost
order by base_cost desc;

--order by子句后面可以跟多列，结果集先按第一列升序排列，若列值一样，再按第二列降序排列。
select id,unix_host,os_username,create_date
from service
order by unix_host,create_date desc;

--------round函数表示四舍五入，trunc函数表示截取，示例如下------------
select    base_cost,
            round(base_cost,1) round1,
            round(base_cost) round2,
            trunc(base_cost,1) trunc1,
            trunc(base_cost) trunc2
from cost;
---------------------------显示当前日期和时间-----------------------
--设置日期格式
alter session set nls_date_format = 'yyyy mm dd hh24:mi:ss';
select sysdate from dual;
--日期的处理----
create table test(c1 date);
insert into test values ('01-JAN-08');
insert into test values ('2008-08-08');--（报错）--
insert into test values (to_date('2008-08-08','yyyy-mm-dd'));
select c1 from test;
select to_char(c1,'yyyy-mm-dd') from test;


insert into test values (to_date('2008-08-08 08:08:08','yyyy-mm-dd hh24:mi:ss'));
select to_char(c1,'yyyy-mm-dd hh24:mi:ss') from test;


----os帐号的创建信息在create_date列，
--to_char函数通过通过格式说明可以获取年、月、日、时、分、秒中的任意一部分，需要用月份信息格式'mm'。
select os_username,create_date from service 
where to_char(create_date,'mm') = '03';--输出3月份的

select os_username,create_date from service 
where to_char(create_date,'fmmm') = '3';

select os_username,create_date from service 
where to_char(create_date,'mm') = 3;

select user,create_date from service 
where to_number(to_char(create_date,'mm')) = 3;


-----------------------------to_number函数的注意事项---------------------
--若to_number函数处理的字符串为'ab'，则系统报错，若转换后的值是十进制的，则要求字符串必须是数字字符。
select to_number('ab') from dual;--（报错invalid number）

--显示月固定费用，单位费用，单位费用为null，显示no unit cost--
select base_cost,'   ',nvl(to_char(unit_cost),'no unit cost') unit_cost
from cost;



------------------------------day 03---   日期问题---------------------------
--显示出昨天，今天，明天，精度到时，分，秒。
alter session set nls_date_format = 'yyyy mm dd hh24:mi:ss';
select sysdate-1,sysdate,sysdate+1 from dual;--sysdate+1表示日期加一输出
--显示出十分钟之后的时间，精度到时，分，秒。 十分钟为 1/144天
alter session set nls_date_format = 'yyyy mm dd hh24:mi:ss';
select sysdate,sysdate + 1/144 from dual;
--列出unix服务器ip地址，os帐号名，开通日期，开通天数
select unix_host,os_username,create_date,
        round(sysdate - create_date) days
from service;

--列出业务帐号ID，unix服务器ip地址，os帐号，开通时间，开通天数，结果集按开通天数从长到短的顺序显示
select id,unix_host,os_username,create_date,round(sysdate - create_date) days
from service
order by sysdate - create_date desc;
select id,unix_host,os_username,create_date, round(sysdate - create_date) days
from service
order by 5 desc;  --第五列按降序
select id,unix_host,os_username,create_date, round(sysdate - create_date) days
from service
order by create_date;

--列出上个月的今天，今天，下个月的今天，精度到时、分、秒
alter session set nls_date_format = 'yyyy mm dd hh24:mi:ss';
select add_months(sysdate,-1),sysdate,add_months(sysdate,1) from dual;
--列出当前月的最后一天，精确到时、分、秒。
select last_day(sysdate) from dual;
--当月包在线时长为20小时，单位费用涨5分，为40小时涨3分，其他不变（用CASE WHEN实现）
select     base_duration,base_cost,
            case when  base_duration = 20 then unit_cost + 0.05
                 when  base_duration = 40 then unit_cost + 0.03
            else
                 unit_cost
            end new_unit_cost
from cost;
--根据cost中的信息,计算若使用了50小时,每种资费标准下该付多少费用
select     base_cost,base_duration,
            case when nvl(base_duration,0) < 50
                 then nvl(base_cost,0) + 
                          (50 - nvl(base_duration,0)) * nvl(unit_cost,0)
            else
                 base_cost
            end  new_cost
from cost;
--月包在线时长在20到40小时之间，单位费用涨5分，月包在线时长大于40小时，单位费用涨3分
select base_duration,base_cost,
        case when base_duration between 20 and 40 
             then unit_cost + 0.05
             when base_duration > 40
             then unit_cost + 0.03
        else
             unit_cost
        end  new_unit_cost
from cost;
--当月包在线时长为20小时，单位费用涨5分，为40小时涨3分，其他不变（用decode实现）
--decode函数用于解决不同记录需要不同处理方式的问题。
--若base_duration= 20,那么函数返回值为unit_cost + 0.05;若base_duration= 40,
--那么函数返回值为unit_cost + 0.03;否则返回unit_cost值。
--若没有最后一个参数unit_cost，函数返回值为null。
select base_duration,base_cost,
           decode(base_duration,20,unit_cost + 0.05,
                                 40,unit_cost + 0.03,
                                unit_cost) new_unit_cost
from cost;

--单位费用的总和，平均值，最大值，最小值，个数
---------------------------sum求和  avg求平均值 max求最大值 min最小值 count个数----------------------------
select sum(unit_cost) sum1,avg(unit_cost) avg1,
max(unit_cost) max1,min(unit_cost) min1,
count(unit_cost) cntfrom cost;

select avg(nvl(unit_cost,0)),sum(unit_cost)/count(*) 
from cost;

select avg(unit_cost),count(unit_cost) 
from cost 
where unit_cost is null;

--列出unix服务器的ip地址和该机器上开通的os帐号数。
--group 相同的分一组
select unix_host,count(os_username) from service
group by unix_host;

--练习一、列出192.168.0.26机器上开通的os帐号数，结果集中只显示开通的os帐号数；
--练习二、列出192.168.0.26机器上开通的os帐号数，结果集中显示unix服务器ip地址，开通的os帐号数。
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

--列出unix服务器ip地址，开通时间，开通的os帐号数。
select unix_host,to_char(create_date,'yyyymmdd') create_date,
        count(os_username) cnt
from service
group by unix_host,to_char(create_date,'yyyymmdd');
--列出unix服务器ip地址，开通的os帐号数。结果集中只包含开通的os帐号数多于2个的。
select unix_host,count(os_username) cnt
from service
group by unix_host
having count(os_username) > 2;--符合条件的组进行最后的计算。having子句执行在select子句之前。
--列出unix服务器ip地址，开通时间，开通的os帐号数。结果集中只包含开通的os帐号数多于1个的。
select unix_host,to_char(create_date,'yyyymmdd') create_date,
        count(os_username) cnt
from service
group by unix_host,to_char(create_date,'yyyymmdd')
having count(os_username) > 1;

--显示unix服务器ip地址，os帐号名，开通时间，结果集中只包含开通时间最小的os帐号。
select unix_host,os_username,create_date from service
where create_date = (select min(create_date) from service);
--显示unix服务器ip地址,开通日期,os帐号名,结果集中只包含开通日期比unix服务器192.168.0.26上的huangrong早的。
select unix_host, create_date,os_username from service
where create_date >
                (select create_date from service
                 where os_username = 'huangr'
                 and unix_host = '192.168.0.26');
                 
--列出推荐人的客户姓名
select real_name from account
where id in (select recommender_id from account);
--显示unix服务器ip地址,开通日期,os帐号名,结果集中只包含每台unix服务器上最早开通的os帐号。
select unix_host,os_username,create_date from service
where (unix_host,create_date) in 
(select unix_host,min(create_date) from service
group by unix_host);

--显示unix服务器ip地址,开通日期,os帐号名,结果集中只包含开通日期比所在unix服务器上最早开通日期晚九天的os帐号。
select unix_host,os_username,create_date from service
where (unix_host,to_char(create_date,'yyyymmdd')) in 
(select unix_host,
            to_char(min(create_date) + 9,'yyyymmdd')
from service
group by unix_host);



--********************************    day 04******************************88
--显示unix服务器ip地址,开通日期,os帐号名,结果集中只包含os帐号的开通天数比平均开通天数长的那些记录。
select unix_host os_username ,create_date,
        round(sysdate - create_date) open_age
from service o
where round(sysdate - create_date) >
            (select avg(round(sysdate - create_date))
             from service i
             where o.unix_host = i.unix_host);
             
---显示客户姓名,结果集中只包含申请了远程登录业务的客户。
--exists 关联子查询
select real_name from account o
where exists
(select 1 from service i
where o.id = i.account_id);

--显示客户姓名,unix服务器ip地址，os帐号名,开通日期(结果集中只包含开通了远程登录业务的客户)。
--jion
column real_name format a12
column os_username format a12
column unix_host format a16

select a.real_name,s.unix_host,s.os_username,s.create_date
from account a join service s
on a.id = s.account_id;

--客户huangrong在哪些unix服务器上申请了远程登录业务
select a.real_name,s.unix_host,s.os_username,s.create_date
from account a join service s
on a.id = s.account_id
and a.real_name = 'huangrong';

--列出客户姓名以及开通的远程登录业务的数量
select a.id,max(a.real_name),count(a.id)
from account a join service s
on a.id = s.account_id
group by a.id;

--列出客户姓名以及他的推荐人
select a1.real_name customer,
            a2.real_name recommender
from account a1 join account a2
on a1.recommender_id = a2.id;


--哪些客户是推荐人
select distinct a2.id,a2.real_name
from account a1 join account a2
on  a1.recommender_id = a2.id;
------

------************************day 05 多表查询**************************88-------------
--列出客户姓名以及他的推荐人
select a1.real_name customer,
        nvl(a2.real_name,'No Recommender') recommender
from account a1 left join account a2
on a1.recommender_id = a2.id;

--列出客户姓名以及所开通的远程登录业务的信息 
select a.id,a.real_name,s.unix_host,s.os_username 
from account a left join service s
on a.id = s.account_id;

--列出客户姓名以及开通的远程登录业务的数量
select a.id,max(a.real_name),count(s.id)
from account a left join service s
on a.id = s.account_id
group by a.id;

--哪些客户不是推荐人
select a1.real_name recommender
from account a1 left join account a2
on a1.id = a2.recommender_id
where a2.id is null;

--哪些UNIX服务器上没有os帐号weixb
select h.id from host h left join service s
on h.id = s.unix_host
and s.os_username = 'weixb'
where s.id is null;

------显示客户的年龄段
select real_name,birthdate,s.name
from account a join age_segment s
on round((sysdate - birthdate)/365) between lowage and hiage;

--显示客户huangrong的年龄段
select real_name,birthdate,s.name
from account a join age_segment s
on round((sysdate - birthdate)/365) between lowage and hiage
and real_name = 'huangrong';

--显示各个年龄段的客户数（没有客户的年龄段不用出现在结果集中）
select max(s.name),count(a.id)
from account a join age_segment s
on round((sysdate - birthdate)/365) between lowage and hiage
group by s.id;

-- 显示各个年龄段的客户数（没有客户的年龄段的客户数为0）
select max(s.name),count(a.id)
from account a right join age_segment s
on round((sysdate - birthdate)/365) between lowage and hiage
group by s.id;

--当月包在线时长为20小时，单位费用涨5分，为40小时涨3分，其他不变（用UNION ALL实现）
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

-- 哪台UNIX服务器上没有开通远程登录业务
select id from host
minus--排除下面的结果集
select unix_host from service;

--找出帐务信息表的前三条记录
select rownum,id,real_name,create_date from account
where rownum <= 3;--前3行的记录

--找出帐务信息表的第四条到第六条记录？
select rn,id,real_name,create_date
from
    (select rownum rn,id,real_name,create_date from account
    where rownum <= 6)
where rn >= 4;

--最早开通NetCTOSS系统的前三个客户？
selectrownum,id,real_name,days
from
    (select id,real_name,round(sysdate - create_date) days 
from account
order by days desc)
where rownum <= 3;

--最早开通NetCTOSS系统的第四到第六名客户？
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