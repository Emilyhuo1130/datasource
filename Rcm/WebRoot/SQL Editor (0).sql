select * from tana_data t  order by savetime,groupcode,recordno;

select * from vw1205_hispara t;

select SAVETIME,VAL0 from tana_data where recordno='0' and groupcode='HZL' and savetime >= '1386308026';

select * from tpdr_description;

select * from tpdr_groupdata order by starttime desc;

select * from tpdr_groupdata  order by pointcode;

select * from tpdr_groupdata  where starttime <=1386308236  and starttime >=1386308200 order by pointcode;