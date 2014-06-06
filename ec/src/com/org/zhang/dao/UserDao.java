package com.org.zhang.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.org.zhang.context.EC;
import com.org.zhang.dao.face.UserDaoFace;
import com.org.zhang.pojo.UsersInfo;
@Repository("UserDao")
public class UserDao implements UserDaoFace{
	Logger log=Logger.getLogger(UserDao.class);
	
	@Resource
	private JdbcTemplate jdbcTamplate;
	@Override
	public boolean verifyUserName(String userName) {
		@SuppressWarnings("deprecation")
		int count = jdbcTamplate.queryForInt("select count(*) from usersInfo where userName=?",new Object[]{userName});
		return count==0?false:true;
	}
	@Override
	public boolean addUserInfo(UsersInfo user) {
		jdbcTamplate.update("insert into usersInfo(userName,userPassWord,power,userAge,inProvince,inCity,nowJob)" +
				"values(?,?,?,?,?,?,?)",new Object[]{user.getUserName(),user.getUserPassWord(),EC.Variable.userRole,
				user.getUserAge(),user.getInProvince(),user.getInCity(),user.getNowJob()});
		
		return true;
	}

}
