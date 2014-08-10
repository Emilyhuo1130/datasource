package gosn.test;

import java.sql.Timestamp;
import java.util.Date;

public class UserInfo {
	private Timestamp date;
	private Date date2;
	
	public UserInfo(){
		date=new Timestamp(System.currentTimeMillis());
		date2=new Date();
	}
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	
	
}
