package gosn.test;

import com.google.gson.Gson;

public class GsonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		UserInfo user =new UserInfo();
		String Str = gson.toJson(user);
		System.out.println(Str);
		user=gson.fromJson(Str, UserInfo.class);
		System.out.println(user.getDate());
		System.out.println(user.getDate2());
	}

}
