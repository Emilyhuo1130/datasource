select * from equipmentinfo where 1=1 ；

select max(componentid) as max_id from equipmentinfo;

select count(equipment) coun from equipmenttree;

select DISTINCT equipment from equipmenttree where systemname='信号系统';

select * from warning where 1=1  and equipmentname= '道岔'  
and WarningDate>='1990-1-1' and WarningDate<='2099-1-1' order by warningDate  ;

select distinct lineNo  from warning  order by CAST(lineNo as UNSIGNED  );

select statments,warningDate from warning ORDER by statments,warningDate DESC;

select distinct lineNo  from warning  GROUP by lineNo ;
select * from warning where warningdate >= '2013-10-19 00:00:00' and warningdate<='2013-10-19 24:00:00';
 
select * from warning where lineNo ='10' and statments in(0,1,2,3,4);
DELETE from hisdata201308 where id >864;

select warningDate,statments from warning order by statments and warningDate;
select warningDate from warning where equipmentId='10.29.08.01.001' and statments='1';
select warningDate,statments from warning  GROUP BY statments   ;
select * from pdrdata where componentId= '10.29.02.24.001' order by pointCode;
insert into`station` message (info) VALUES('4444444444');

delete from warning where  id>=444;

select count(*) as count from facilityindex ;
update warning_plan set statments='1' where id=1
select * from maintainstatistics where 1=1 order by updateday  ;

SELECT * FROM warning LIMIT 1,10 ;
SELECT * FROM warning where equipmentname LIKE '%转辙机%'  ;
select * from warning where id>=(select id from warning limit 0,1)  
 and equipmentname LIKE '%转辙机%' 
  and WarningDate>='1980-1-1' and WarningDate<='2014-1-1'
   and statments in(0,1,2,3,4) ORDER by statments,warningDate DESC  limit 10;



select * from reportforms_operateindex where id>=(select id from reportforms_operateindex limit 0,1) 
order by updatetime desc
 limit 10 ;

select * from pdrdata where componentId= '10.29.08.01.001' order by id;

select count(*) from Weektask where weekNo=47 and userAccount = 'chenhao' and updateTime <= '2014'

insert into test values(auto_increment,'hhhhhh');
CREATE DATABASE `jxc`；


UPDATE facilityindex set state='良好',woringCount='0',healthIndex='4' where woringCount!='0';


UPDATE equipmenttreeinfo set stationName='紫藤路' where stationName='紫藤路站';

select distinct pointcode from hisdata201308 where 1=1 and componentId='10.29.08.01.001' and savetime>=1379347200;

select * from warning order by statments desc ;

select * from pdrdata where componentId= '10.29.08.02.001' order by pointCode


select distinct equipmentname from warning where id>=(select id from warning limit 0,1)  
 and WarningDate>='1990-1-1' and WarningDate<='2099-1-1'  ORDER by statments,warningDate DESC  limit 5;

select distinct equipmentname from warning_plan where 
id>=(select id from warning_plan  where statments='1' limit 0,1) and statments='1'  limit 5;

select distinct equipmentname from warning_plan 
where id>=(select id from warning_plan where statments='1' limit 0,1) and statments='1' limit 5;

(select * from warning where id>=(select id from warning limit 5,1) 
and WarningDate>='1990-1-1' and WarningDate<='2099-1-1' )  limit 5 ;

select * from historywarning where 
id>=(select id from historywarning  where WarningType='故障预警'  limit 0,1)
 and WarningType='故障预警' limit 10
 
select * from pdrdata where 1=1
 and componentid='10.29.02.100.001' and pointcode like '%_contact%_di_%' order by pointcode;

select * from task_particulars where 1=1 and commit_Dare>'2013' 
and week_number>=3 and week_number<9 ORDER by week_number;

select distinct project_Name from project_info where 1=1 and project_Name= '主动维保';

select * from task_particulars ORDER by  commit_Dare DESC;

select * from task_particulars where project_Name like '%主动%' ;


select uuid();
SELECT replace(uuid(), '-', '');
insert into test2(id,name) values(uuid(),'22');

-----------------------------------------------权限控制设计字段-----------------------------------------------------------
CREATE TABLE `action` (
  `actionid` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `actionname` varchar(255) not NULL,
  `actioncolumnid` int(11) not NULL,
  `action` varchar(255) not NULL,
  `viewmode` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `actioncolumn` (
  `actioncolumnid` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `actioncolumnname` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `actiongroup` (
  `id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `action` int(11) not NULL,
  `groupid` int(11) not NULL,
  `masterid` int(11) not NULL,
  `mastername` varchar(255) not NULL,
  `creatdate` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `groupmanager` (
  `groupid` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `groupname` int(11) not NULL,
  `groupinfo`  varchar(255) not NULL,
  `masterid` int(11) not NULL,
  `mastername` varchar(255) not NULL,
  `creatdate` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mastergroup` (
  `id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `masterid` int(11) not NULL,
  `name`  varchar(255) not NULL,
  `groupid` int(11) not NULL,
  `masterid2` int(11) not NULL,
  `mastername` varchar(255) not NULL,
  `creatdate` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `master` (
  `id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `name`  varchar(255) not NULL,
  `password`  varchar(255) not NULL,
  `truename` varchar(255) not NULL,
  `phone` varchar(255) not NULL,
  `masterid` int(11) not NULL,
  `mastername` varchar(255) not NULL,
  `creatdate` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-----------------------------------------------UCS项目管理系统------------------------------------------------------------
CREATE TABLE `task_particulars` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `project_Name` varchar(255) not NULL,
  `finish_Date` varchar(255) not NULL,
  `this_week_task` varchar(800) not NULL,
  `nest_week_task` varchar(800) not NULL,
  `week_number` int(11) not NULL,
  `commit_Dare` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `project_info` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `project_Name` varchar(255) not NULL,
  `project_State` varchar(255) not NULL,
  `project_Remark` varchar(800) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_info` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `user_Name` varchar(50) not NULL,
  `user_account` varchar(50) not NULL,
  `user_pw` varchar(50) not NULL,
  `phone_number` varchar(20) not NULL
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `admin_info` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `user_Name` varchar(50) not NULL,
  `user_pw` varchar(50) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


----------------------------------------------十二五主动维保-----------------------------------------------------------------------------
--每小时统计每条线的健康状况
CREATE TABLE `Line_health` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `lineNo` varchar(255) not NULL,
  `saveTime` timestamp NULL default NULL,
  `value` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--点表信息


CREATE TABLE `PointTable_B` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `pointCode` varchar(255) not NULL,
  `pointDesc` varchar(255) not NULL,
  `groupCode` varchar(255) not NULL,
  `deviceCode` varchar(255) not NULL,
  `appSysID` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;







--首页告警记录信息
DROP TABLE IF EXISTS `warning`;
CREATE TABLE `warning` (
  `Id` int(11) NOT NULL auto_increment,
  `warningId` varchar(255) default NULL,
  `equipmentId` varchar(255) default NULL,
  `equipmentname` varchar(255) default NULL,
  `systemName` varchar(255) default NULL,
  `equipmentDescription` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `warningType` varchar(255) default NULL,
  `warningTypeLevel` varchar(255) default NULL,
  `warninginfo` varchar(255) default NULL,
  `warningDate` timestamp NULL default NULL,
  `statments` varchar(255) default NULL,
  `fromuser` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--计划修预警信息

CREATE TABLE `warning_plan` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY  ,
  `equipmentId` varchar(255) default NULL,
  `equipmentname` varchar(255) default NULL,
  `systemName` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `warningType` varchar(255) default NULL,
  `warninginfo` varchar(255) default NULL,
  `warningDate` timestamp NULL default NULL,
  `statments` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--计划修规程信息

CREATE TABLE `maintain_plan_table` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY  ,
  `equipmentId` varchar(255) default NULL,
  `equipmentname` varchar(255) default NULL,
  `systemName` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `warningType` varchar(255) default NULL,
  `warningTypeLevel` varchar(255) default NULL,
  `maintaininfo` varchar(255) default NULL,
  `lastTime_maintainDate` timestamp NULL default NULL,
  `productDate` timestamp  NULL default NULL,
  `next_maintainDate` timestamp NULL default NULL,
  `statments` varchar(255) default NULL,
  `maintain_period` varchar(10) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--计划通知单信息

CREATE TABLE `inform_plan` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY  ,
  `equipmentId` varchar(255) default NULL,
  `equipmentname` varchar(255) default NULL,
  `systemName` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `warningType` varchar(255) default NULL,
  `warningTypeLevel` varchar(255) default NULL,
  `maintaininfo` varchar(255) default NULL,
  `next_maintainDate` timestamp NULL default NULL,
  `statments` varchar(255) default NULL,
  `maintain_period` varchar(10) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--车站对应表
CREATE TABLE `station` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `lineNo` varchar(255) not NULL,
  `stationid` varchar(255) not NULL,
  `stationNme` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--全站设备树
CREATE TABLE `EquipmentTreeinfo` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `stationName` varchar(255) not NULL,
  `systemname` varchar(255) not NULL,
  `subsystemname` varchar(255) not NULL,
  `component` varchar(255) not NULL,
  `subcomponent` varchar(255) not NULL,
  `subcomponentid` varchar(255) not NULL,
  `Level` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--原因，策略
CREATE TABLE `Strategy` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `componentid` varchar(255) not NULL,
  `fultDescription` varchar(255) not NULL,
  `faultCause` varchar(255) not NULL,
  `maintenancePolicy` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--影响
CREATE TABLE `influence` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `componentid` varchar(255) not NULL,
  `influenceInfo` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





--故障树
CREATE TABLE `Faultinfo` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `componentid` varchar(255) not NULL,
  `equipmentname` varchar(255) not NULL,
  `equipmentid` varchar(255) not NULL,
  `systemname` varchar(255) not NULL,
  `equipmentDescription` varchar(255) not NULL,
  `faulttype` varchar(255) not NULL,
  `fultDescription` varchar(255) not NULL,
  `faultCause` varchar(255) not NULL,
  `influence` varchar(255) not NULL,
  `maintenancePolicy` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


select DISTINCT equipmentName from faultinfo where componentid=4;
insert into message (info,infodate) values('xxxok','1990-08-04 12:24:22')


select DISTINCT   equipment from equipmenttree where systemname='信号系统';

select count(DISTINCT   systemname) as namenumber from   equipmenttree;

select * from warning where 1=1 and systemname='4' and (statments='2' or statments='3') and lineno='6' ;



--warning表的副表，用户操作中辅助作用
CREATE TABLE `Faultinfo_user2` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `equipmentid` varchar(255) not NULL,
  `equipmentname` varchar(255) not NULL,
  `systemname` varchar(255) not NULL,
  `warningType` varchar(255) not NULL,
  `warningTypeLevel` varchar(255) not NULL,
  `fultDescription` varchar(255) not NULL,
  `LineNo` varchar(255) not NULL,
  `stationName` varchar(255) not NULL,
  `warningDate` timestamp not NULL,
  `faultCause` varchar(255) not NULL,
  `influence` varchar(255) not NULL,
  `maintenancePolicy` varchar(255) not NULL,
  `equipmentDescription` varchar(255) not NULL,
  `opinion` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--实件分析-趋势分析
CREATE TABLE `hisdata201308` (
  `pointcode` varchar(255) DEFAULT NULL,
  `savetime` int(11) NOT NULL DEFAULT '0',
  `flag` tinyint(1) NOT NULL DEFAULT '0',
  `status` tinyint(3) DEFAULT '0',
  `value` double(16,2) NOT NULL DEFAULT '0.00',
  `opertime` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--指标分析-运营统计指标
DROP TABLE IF EXISTS `operatestatistics`;
CREATE TABLE `operatestatistics` (
  `Id` int(11) NOT NULL auto_increment,
  `lineNo` varchar(255) NOT NULL,
  `safetyDays` varchar(255) NOT NULL,
  `offstreamDays` varchar(255) NOT NULL,
  `noPlanoffstreamDays` varchar(255) NOT NULL,
  `month` varchar(255) NOT NULL,
  `updateDay` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--指标分析-维修统计指标
CREATE TABLE `MaintainStatistics` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `systemName` varchar(255) not NULL,
  `equipmentName`  varchar(255) not NULL,
  `noFaultDays` varchar(255) not NULL,
  `statisticsDays` varchar(255) not NULL,
  `statisticsInterval` varchar(255) not NULL,
  `realityMistiming`  varchar(255) not NULL,
  `month`  varchar(255) not NULL,
  `updateDay`  timestamp not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--通知单表-维保通知单
CREATE TABLE `inform_user4` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY ,
  `jobNumber` varchar(255) not NULL,
  `starTime` timestamp not NULL,
  `LineNo` varchar(255) not NULL,
  `stationName` varchar(255) not NULL,
  `systemname` varchar(255) not NULL,
  `subSystemname` varchar(255) not NULL,
  `equipmentname` varchar(255) not NULL,
  `equipmentId` varchar(255) not NULL,
  `warningTypeLevel` varchar(255) not NULL,
  `healthLevel` varchar(255) not NULL,
  `fultDescription` varchar(255) not NULL,
  `faultCause` varchar(255) not NULL,
  `influence` varchar(255) not NULL,
  `maintenancePolicy` varchar(255) not NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--通知单-维修反馈记录
CREATE TABLE `maintain_couple_back` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY,
  `componentId` varchar(255) default NULL,
  `componentName` varchar(255) default NULL,
  `systemName` varchar(255) default NULL,
  `equipmentDescription` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `repairsDate`  timestamp NULL default NULL,
  `changeEquipment` varchar(255) default NULL,
  `maintainInfo` varchar(255) default NULL,
  `maintainResult` varchar(255) default NULL,
  `maintainPerson` varchar(255) default NULL,
  `checkPerson` varchar(255) default NULL,
  `remark` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--报表管理 历史告警/预警统计
CREATE TABLE `historywarning` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY,
  `systemName` varchar(255) default NULL,
  `subsystemName` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `component` varchar(255) default NULL,
  `warninginfo` varchar(255) default NULL,
  `warningTypeLevel` varchar(255) default NULL,
  `warningstatments` varchar(255) default NULL,
  `warningDate` timestamp NULL default NULL
 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--报表管理-健康指数分析表-设施指数 reportforms_equipment_index
CREATE TABLE `reportforms_equipment_index` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY,
  `systemName` varchar(255) default NULL,
  `subsystemName` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `component` varchar(255) default NULL,
  `locationId` varchar(255) default NULL,
  `componentDescription` varchar(255) default NULL,
  `healthLevel` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--报表管理-健康指数分析-运营指数
CREATE TABLE `reportforms_operateindex` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY,
  `lineNo` varchar(255) NOT NULL,
  `healthindex` varchar(255) NOT NULL,
  `leveloneCount` varchar(255) NOT NULL,
  `leveltwoCount` varchar(255) NOT NULL,
  `levelthreeCount` varchar(255) NOT NULL,
  `levelforeCount` varchar(255) NOT NULL,
  `updatetime` timestamp NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--报表管理 -KPI分析表 --Reportforms_KPI
CREATE TABLE `Reportforms_KPI` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY,
  `systemName` varchar(255) default NULL,
  `subsystemName` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `component` varchar(255) default NULL,
  `safety_production_days` varchar(255) default NULL,
  `force_stop_days` varchar(255) default NULL,
  `noplan_stop_days` varchar(255) default NULL,
  `no_fault_days` varchar(255) default NULL,
  `key_equipment_repair_time` varchar(255) default NULL,
  `key_technology_repair_time` varchar(255) default NULL,
  `physical_variance` varchar(255) default NULL,
  `updateTime` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--报表管理-主动维保通知单reportforms_requisition 
CREATE TABLE `reportforms_requisition` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY,
  `componentId` varchar(255) default NULL,
  `systemName` varchar(255) default NULL,
  `subsystemName` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `component` varchar(255) default NULL,
  `requisitionNo` varchar(255) default NULL,
  `requisitionstatments` varchar(255) default NULL,
  `sendTime` timestamp NOT NULL,
  `maintainTime` timestamp NOT NULL,
  `maintainDepartment` varchar(255) default NULL,
  `maintainPerson` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--报表管理-设备履历表reportforms_equipment_record
CREATE TABLE `reportforms_equipment_record` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY,
  `systemName` varchar(255) default NULL,
  `subsystemName` varchar(255) default NULL,
  `lineNo` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `component` varchar(255) default NULL,
  `componentId` varchar(255) default NULL,
  `locationId` varchar(255) default NULL,
  `modelstandard` varchar(255) default NULL,
  `outTime` timestamp NOT NULL,
  `buyTime` timestamp NOT NULL,
  `Production_house` varchar(255) default NULL,
  `Production_coding` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;







--点代码的35秒的数据
CREATE TABLE `pdrdata` (
  `Id` int(11) NOT NULL auto_increment PRIMARY KEY,
  `componentId` varchar(255) default NULL,
  `pointCode` varchar(255) default NULL,
  `dateType` varchar(255) default NULL,
  `value1` varchar(255) default NULL,
  `value2` varchar(255) default NULL,
  `value3` varchar(255) default NULL,
  `value4` varchar(255) default NULL,
  `value5` varchar(255) default NULL,
  `value6` varchar(255) default NULL,
  `value7` varchar(255) default NULL,
  `value8` varchar(255) default NULL,
  `value9` varchar(255) default NULL,
  `value10` varchar(255) default NULL,
  `value11` varchar(255) default NULL,
  `value12` varchar(255) default NULL,
  `value13` varchar(255) default NULL,
  `value14` varchar(255) default NULL,
  `value15` varchar(255) default NULL,
  `value16` varchar(255) default NULL,
  `value17` varchar(255) default NULL,
  `value18` varchar(255) default NULL,
  `value19` varchar(255) default NULL,
  `value20` varchar(255) default NULL,
  `value21` varchar(255) default NULL,
  `value22` varchar(255) default NULL,
  `value23` varchar(255) default NULL,
  `value24` varchar(255) default NULL,
  `value25` varchar(255) default NULL,
  `value26` varchar(255) default NULL,
  `value27` varchar(255) default NULL,
  `value28` varchar(255) default NULL,
  `value29` varchar(255) default NULL,
  `value30` varchar(255) default NULL,
  `value31` varchar(255) default NULL,
  `value32` varchar(255) default NULL,
  `value33` varchar(255) default NULL,
  `value34` varchar(255) default NULL,
  `value35` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

