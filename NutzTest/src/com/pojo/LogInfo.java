package com.pojo;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

@Table(value="loginfo")
public class LogInfo {
	@Id
	private int id;
	@Column("username")
	private String userName;
	@Column("password")
	private String passWord;
	@Many(target=Person.class ,field="infoid")//用person对象中的infoid去对应logingo的主键值
	private List<Person>users;
	

	
	
	
	
	public List<Person> getUsers() {
		return users;
	}
	public void setUsers(List<Person> users) {
		this.users = users;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
