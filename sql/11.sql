CREATE TABLE user_INFO(
  ID NUMBER(4) CONSTRAINT user_INFO_ID_PK PRIMARY KEY,
  NAME VARCHAR2(30) NOT NULL,
  login_name VARCHAR2(30) UNIQUE¡¡NOT NULL,
  login_password  VARCHAR2(30) NOT NULL,
  id_cardno varchar2(50)
  );

CREATE table date_consumption(
 ID NUMBER(4) CONSTRAINT date_consumption_ID_PK PRIMARY KEY, 
 user_id number(4),
 dadys date,
 breaker number(2,1),
 lunch number(2,1),
 dinner number(2,1),
 cigarette number(2,1),
 wine number(2,1),
 stand_treat number(4,1),
 pin_money number(4,1),
 recreation  number(4,1),
 daily number(4,1),
 others number(6,1),
 fixed_day number(6,1),
 income_day number(6,1),
 out_day number(6,1)
);
insert into date_consumption (id,user_id,dadys) values(1,1001,sysdate);
create table fixed_change(
id number(4) CONSTRAINT fixed_change_ID_PK PRIMARY KEY,
user_id number(4) not null,
days date not null,
rent number(6,1),
broad number(4,1),
others number(4,1)
);
insert into fixed_change (id,user_id,dadys) values(1,1001,sysdate);
