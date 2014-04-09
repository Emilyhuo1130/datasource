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
	 * ��ɾ�Ĳ�Ĳ���
	 * ����list�Ĳ���
	 * һ�Զ�Ĳ���
	 */
	public static void main(String[] args) {
		SqlSession session = sqlsessionFactory.openSession();
		try{
			IUserInfoOperation operation = session.getMapper(IUserInfoOperation.class);
			List<UserInfo> list = operation.selectALLKileName("%zh%");
			for(UserInfo user:list){
				System.out.println(user.getName());
			}
			
			//����û�
			/**
			UserInfo user=new UserInfo();
			user.setName("myibatisuser");
			user.setAge(22);
			user.setInfoid(2);
			operation.addUser(user);
			//�����ύ  �����޷�д�����ݿ�
			session.commit();
			**/
			
			//�����û�
			UserInfo user = operation.selectUserByID(4);
			user.setInfoid(99);
			operation.updateUserInfo(user);
			session.commit();
			//ɾ��ĳ��id���û�
			operation.deleteUserinfo(12);
			session.commit();
			
			//һ�Զ�Ĳ�ѯ
			//operation.getUserArticles(1);
			System.out.println("-------------------------");
			//��ҳ
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
