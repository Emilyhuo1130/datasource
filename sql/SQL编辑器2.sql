update hisdata201308 set componentId='10.29.02.24.001' where pointcode='hzl_pwr_ai_35';
commit;
CREATE TABLE `server` (
   `id` int(11) not NULL auto_increment PRIMARY KEY ,
  `server_IP` varchar(100) NOT NULL ,
  `version` varchar(20) not NULL,
  `update_Time` varchar(20) not NULL,
  `update_Time_now` varchar(50) not NULL,
  `bisimmediateshutdown` boolean not NULL,
  `shutDown_Time` varchar(20) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `admin_info` (
   `id` int(11) not NULL auto_increment PRIMARY KEY ,
  `admin_Name` varchar(20) NOT NULL ,
  `admin_PassWord` varchar(20) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Config_client` (
   `id` int(11) not NULL auto_increment PRIMARY KEY ,
  `local_ip` varchar(20) NOT NULL ,
  `client_Id` varchar(20) not NULL,
  `pic_m_Number`  int(11) not NULL,
  `pic_s_Number`  int(11) not NULL,
   `videoConfig_s_Number`  int(11) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Pic_Multi` (
   `id` int(11) not NULL auto_increment PRIMARY KEY ,
  `sequence_number` int(11) NOT NULL ,
  `picShow` varchar(20) ,
  `resFolder`  varchar(300) not NULL,
  `random`  boolean not NULL,
   `time`  int(11) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Pic_Single` (
   `id` int(11) not NULL auto_increment PRIMARY KEY ,
  `sequence_number` int(11) NOT NULL ,
  `picIn` varchar(20)  ,
  `picout` varchar(20)  ,
  `resFolder`  varchar(300) not NULL,
  `random`  boolean not NULL,
   `time`  int(11) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `VideoConfig` (
   `id` int(11) not NULL auto_increment PRIMARY KEY ,
  `sequence_number` int(11) NOT NULL ,
  `VideoRelativePath`  varchar(300) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Pic_Linkage` (
   `id` int(11) not NULL auto_increment PRIMARY KEY ,
  `linkageCode` varchar(300) not NULL ,
  `multiScreenEffect` varchar(20) not null  ,
  `multiScreenEffect_Name` varchar(20) not null  ,
  `videoName` varchar(20) not null  ,
  `synchronizationPlay`  varchar(300) not NULL,
   `time`  int(11) not NULL
   
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `LinkageInfo_Single` (
   `id` int(11) not NULL auto_increment PRIMARY KEY ,
   `Pic_Linkage_id` int(11) NOT NULL ,
  `local_ip`  varchar(100) not NULL,
  `playOrder` int(11) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `effect` (
   `effect_id` int(11) not NULL PRIMARY KEY,
   `effect_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select uuid();
select * from hisdata201308;

UPDATE hisdata201308 set  pointcode = 'hzldb04' where pointcode = 'hzlzzj17';

select * from maintain_couple_back where 1=1 and LineNO= '10'
 and componentName like '%隧道风机通风系统%'  order by repairsDate desc  limit 0,2;
 
 select userinfo.id,userinfo.name,userinfo.age,userinfo.infoid,article.id,article.userid,article.title from userinfo,article
    	where userinfo.id=article.userid and userinfo.id=1;
 delete from userinfo where id in(15,16);
 
 
 
 create procedure proc1(out s int)
 begin
      select count(*) into s from uerinfo;
 end
drop procedure if exists hello
create procedure hello()
begin
     select * from userinfo;
end



call hello();

--------------------------------------------------------------
create PROCEDURE pom_name(in username varchar(10),in age int(11))
begin
select * from userinfo where userinfo.name=username or userinfo.age=age;
end;

call pom_name('test',42);

create PROCEDURE select_info(in username varchar(10),in age int(11))
begin
if age!=0 and username is null then
select * from userinfo where userinfo.age=age;
elseif username is not null then
select * from userinfo where userinfo.name=username;
else
select * from userinfo;
end if;
end;

call select_info(null,0);

