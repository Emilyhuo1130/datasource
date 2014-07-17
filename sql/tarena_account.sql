

ALTER SESSION SET NLS_DATE_FORMAT = 'yyyy mm dd hh24:mi:ss';
--帐务信息表
drop table account cascade constraints purge;
create table account(
id number(9)constraint account_id_pk primary key,
recommender_id number(9) constraint 
  account_recommender_id_fk references account(id),
login_name varchar2(30) unique not null,
login_passwd varchar2(8) not null,
status char(1) not null constraint account_status_nu check(status in(0,1,2)),
create_date date default sysdate,
pause_date date null,
close_date date null,
real_name varchar2(20) not null,
idcard_no char(18) not null unique,
birthdate date null,
gender char(1)  constraint account_gender check(gender in (0,1)),
occupation varchar2(50),
telephone varchar2(15) not null,
email varchar2(50),
mailaddress varchar2(50),
zipcode char(6),
qq varchar2(15),
last_login_time date,
lasr_login_ip varchar2(15)
);
commit;

--资费表
drop table cost purge;
create table cost(
id number(4) constraint cost_id primary key,
name varchar2(50) not null,
base_duration number(11),
base_cost number(7,2),
unix_cost number(7,4),
status char(1) not null constraint cost_status check(status in(0,1)),
descr varchar2(100),
creatime date default sysdate,
startime date
);
commit;
--unix服务器信息表
drop table host purge;
create table host(
id varchar2(15) constraint host_pk primary key,
name varchar2(20) not null,
location varchar2(20)
);
commit;
--业务信息表
drop table service purge;
create table service (
id number(10) constraint service_id_pk primary key,
account_id number(9) references account(id) not null,
unix_host varchar2(15) not null references host(id),
os_username varchar2(8) not null,
constraint host_user_uk unique(unix_host,os_username),
login_passed varchar2(8) not null,
status char(1)  constraint service_status check(status in(0,1,2)),
create_date date default sysdate,
pause_date date,
close_date date,
cost_id number(4) references cost(id) not null
);

ALTER SESSION SET NLS_DATE_FORMAT = 'yyyy mm dd hh24:mi:ss';

--时长信息表
drop table month_duration purge;
create table month_duration(
service_id number(10),
month_id char(6),
service_detail_id number(11),
sofar_duration number(11) null
);
commit;
--账单信息表
drop table bill purge;
create table bill(
id number(11) constraint bill_id_pk primary key,
account_id number(9) references account(id),
bill_month char(6) not null,
cost number(13) not null,
payment_mode char(1) default null 
  constraint bill_payment_mode check(payment_mode in(0,1,2)),
pay_state char(1) default 0 
  constraint bill_pay_state check(pay_state in (0,1))
  
);
commit;
--帐目条目表
drop table bill_item purge;
create table bill_item(
item_id number(11) constraint item_id_pk primary key,
bill_id number(11) not null references bill(id),
service_id number(10) not null references service(id),
cost number(13,2) null
);
commit;
--业务详单表
drop table service_detail purge;
create table service_detail(
id number(11) constraint service_detail_id_pk primary key,
service_id number(11) references service(id),
client_host varchar2(15) null,
os_username varchar2(8) null,
pid number(11) null,
login_time date null,
logout_time date null,
duration number(20,9) null,
cost number(20,6) null
);
commit;
--角色表
drop table role_info purge;
create table role_info(
id number(11) constraint role_info_id_pk primary key,
name varchar2(20) not null
);
commit;
--角色权限表
drop table role_privilege purge;
create table role_privilege(
role_id number(4) ,
privilege_id number(4) ,
constraint rol_privilege_id_pk primary key(role_id,privilege_id)
);
commit;
--管理员表
drop table admin_info purge;
create table admin_info(
id number(4) constraint admin_info_id primary key,
admin_code varchar2(30) unique not null,
password varchar2(8) not null,
mame varchar2(20) not null,
telephone varchar2(15) null,
email varchar2(50) null,
enrolldate date not null
);
commit;
--管理员角色
drop table admin_role purge;
create table admin_role(
admin_id number(4) not null references admin_info(id),
role_id number(4) not null references role_info(id),
constraint admin_role_pk primary key(admin_id,role_id) 
);
commit;
--年龄段信息表
drop table age_segment purge;
create table age_segment(
id number(1) constraint age_agement_id_pk primary key,
name varchar2(20) not null,
lowage number(2),
hiage number(2)
);
commit;





