update account set RECOMMENDER_ID=(select ID from account where idcard_no=320211199307310346 ) where id =1005;

insert into account(id,real_Name,idCard_NO,login_Name,login_passwd,telephone,RECOMMENDER_ID)values(
seq_account_id.nextval,'zhang','320683199008052032','zhang1','123456','15262516056','1005'
);

insert into account(id,real_Name,idCard_NO,login_Name,login_passwd,
			telephone,RECOMMENDER_ID,status,create_date,
			email,gender,MAILADDRESS,ZipCode,QQ,birthdate )values(seq_account_id.nextval,'zhang','320683199008052032','zhang1','123456','15262516056',
					null,
					'0',sysdate,null,null,null,null,null,null);