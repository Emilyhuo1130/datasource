package com.org.zhang.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.org.zhang.dao.face.SecurityTestInterface;
import com.org.zhang.pojo.Users;

@Repository("SecurityTestDao")
public class SecurityTestDao implements SecurityTestInterface{
	Logger log=Logger.getLogger(SecurityTestDao.class);
	
	@Resource
	private JdbcTemplate jdbcTamplate;
	


	public Users findbyUsername(String name) {
		final Users users = new Users();  
		jdbcTamplate.query("SELECT userName,userPassWord,power FROM usersinfo WHERE userName = ?",  
		                    new Object[] {name},  
		                    new RowCallbackHandler() {  
								@Override
								public void processRow(java.sql.ResultSet rs)
										throws SQLException {
								 users.setName(rs.getString("userName"));
								 users.setPassword(rs.getString("userPassWord"));
								 users.setRole(rs.getString("power"));
								}  
		                    });  
		log.info(users.getName()+"    "+users.getPassword()+"    "+users.getRole());
		return users;
	}



	@Override
	public boolean getinput() {
		// TODO Auto-generated method stub
		System.out.println("00000000000");
		
		
		try {
			Connection connection = jdbcTamplate.getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	
		
	

}
