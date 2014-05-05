
package servlet.db;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;


public class DBUtil {
	private static DBParams dbParams;
	static Logger log = Logger.getLogger(DBUtil.class);
	/*
	 * static { LoadProperties(); }
	 */
	public static DBParams LoadProperties() {
		
		Properties p = new Properties();
		try {
			//p.load(new FileInputStream("E:/Workspaces/MyEclipse 9/Rcm/src/jdbc.properties"));
			//p.load(new FileInputStream("d:/jdbc/jdbc.properties"));
			p.load(new FileInputStream("F:/GitHub/datasource/TestServlet3.0/src/jdbc.properties"));
			DBParams dbParams = new DBParams();
			dbParams.setDriverClass(p.getProperty("jdbc.driverClass"));
			dbParams.setJdbcUrl(p.getProperty("jdbc.jdbcUrl"));
			dbParams.setUsername(p.getProperty("jdbc.username"));
			dbParams.setPassword(p.getProperty("jdbc.password"));
			
			return dbParams;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	public static Connection getConn() {
		Connection conn = null;
		try {
			dbParams = DBUtil.LoadProperties();
			Class.forName(dbParams.getDriverClass());
			
			conn = (Connection) DriverManager.getConnection(
					dbParams.getJdbcUrl(), dbParams.getUsername(),
					dbParams.getPassword());
			

		} catch (Exception e) {
			e.printStackTrace();
			log.warn("数据库链接异常！");
		}
		return conn;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			Connection conn = DBUtil.getConn();
			PreparedStatement ps;
			ResultSet rs;

			ps = conn.prepareStatement("select count(*) as rowCount from test");
			rs = ps.executeQuery();
			rs.absolute(11);
			rs.next();
			System.out.println(rs.getInt("rowCount"));
			rs.close();

			ps = conn.prepareStatement("select * from test where name like ? ");
			ps.setString(1, "%gao%");
			rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("name"));
			}

			rs.close();

		
			ps = conn.prepareStatement("insert into test  (name) values(?)");
			ps.setString(1, "gao11");
			ps.executeUpdate();
			
			
			ps = conn.prepareStatement("update  test  set name = ? where id = 1 ");
			ps.setString(1,"gao100");
			ps.executeUpdate();
			
			ps = conn.prepareStatement("delete from test  where id = ? ");
			ps.setString(1,"3");
		
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}