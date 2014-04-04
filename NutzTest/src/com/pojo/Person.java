package com.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table(value = "userinfo")// 声明了Person对象的数据表
public class Person {
	@Id// 表示该字段为一个自增长的Id,注意,是数据库表中自增!!
	private int id;
	@Name // 表示该字段可以用来标识此对象，或者是字符型主键，或者是唯一性约束
	private String name;
	@Column("age") //   @Column("age")age 为数据库名字  对应java中的age   表示该对象属性可以映射到数据库里作为一个字段
	private int userage;
	
	@Column
	private int infoid;
	//一对一映射或者说是多对一映射
	@One(target=LogInfo.class,field="infoid")//对象Person中存在字段infoid 指向对象LogInfo的主键 
	private LogInfo info;
	
	
	
	public int getInfoid() {
		return infoid;
	}

	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}

	public LogInfo getInfo() {
		return info;
	}

	public void setInfo(LogInfo info) {
		this.info = info;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserage() {
		return userage;
	}
	public void setUserage(int userage) {
		this.userage = userage;
	}
	
	
	
}
