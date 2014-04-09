package com.yihaomen.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yihaomen.mybatis.inter.IUserInfoOperation;
import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.model.Page_;
import com.yihaomen.mybatis.model.UserInfo;

public class Find_Delete_Update_Add extends MyIbatisBase{

	/**
	 * @param args
	 * 增删改查的测试
	 * 返回list的测试
	 * 一对多的测试
	 */
	public static void main(String[] args) {
		SqlSession session = sqlsessionFactory.openSession();
		try{
			IUserInfoOperation operation = session.getMapper(IUserInfoOperation.class);
			List<UserInfo> list = operation.selectALLKileName("%zh%");
			for(UserInfo user:list){
				System.out.println(user.getName());
			}
			
			//添加用户
			/**
			UserInfo user=new UserInfo();
			user.setName("myibatisuser");
			user.setAge(22);
			user.setInfoid(2);
			operation.addUser(user);
			//必须提交  否则无法写入数据库
			session.commit();
			**/
			
			//更新用户
			UserInfo user = operation.selectUserByID(4);
			user.setInfoid(99);
			operation.updateUserInfo(user);
			session.commit();
			//删除某个id的用户
			operation.deleteUserinfo(12);
			session.commit();
			
			//一对多的查询
			//operation.getUserArticles(1);
			System.out.println("-------------------------");
			//分页
			Page_ page=new Page_();
			int pageCount=2;
			int pageSize=2;
			page.setOffset((pageCount-1)*pageSize);
			page.setPageSize(pageSize);
			List<UserInfo> userList = operation.getAllusers(page);
			for(UserInfo info:userList){
				System.out.println(info.getName()+"   "+info.getId());
			}
			
			
		}finally{
			session.close();
		}
	}

}
