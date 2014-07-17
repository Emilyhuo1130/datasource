

select s.id,s.account_id,
s.unix_host,
s.os_username,s.login_passwd,
s.status,s.create_date,
s.pause_date,s.close_date,cost_id,c.name 
from service s  join cost c
on s.cost_id=c.id;

SELECT * FROM
  (select * from 

      (select * from

      (select 
        rownum r,x.id,x.account_id,x.unix_host,x.real_name,x.os_username,x.login_passwd,x.status,x.create_date,x.pause_date,x.idcard_no,x.cost_id,c.name,c.descr 
      	from 
      	(select s.id,s.account_id,s.unix_host, a.real_name , s.os_username,s.login_passwd,s.status,s.create_date,s.pause_date,a.idcard_no,s.cost_id 
        from account a join service s 
      	on s.account_id=a.id) x join cost c 
      	on x.cost_id=c.id
         ) m
         where 1=1 
         
         and status=1
         
          
          
         )
       where r<9)
  
        f where f.r>0

;


select 
		id,real_Name,idCard_NO,login_Name,login_passwd,
			telephone,RECOMMENDER_ID,status,create_date,PAUSE_DATE,
			close_date,LAST_LOGIN_TIME,LAST_LOGIN_IP,birthdate,email,gender,
			MAILADDRESS,ZipCode,QQ
			from
      
     ( SELECT * FROM
     
      (select 
			rownum r, id,real_Name,idCard_NO,login_Name,login_passwd,
			telephone,RECOMMENDER_ID,status,create_date,PAUSE_DATE,
			close_date,LAST_LOGIN_TIME,LAST_LOGIN_IP,birthdate,email,gender,
			MAILADDRESS,ZipCode,QQ from account where 1=1 
      
     
      
      
      )
      
      where rownum  < 9)
      
      a where a.r > 0;




select * from service;
create sequence seq_service_id increment by 1 start with 2009;





create or replace view service_view as 
select rownum r, status from service ;






 
create table role (
id number(4),
name varchar2(50)
);



