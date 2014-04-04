
package com.ucs.rcm.db;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.apache.log4j.Logger;


public class KSKDBUtil {
	private static KSKDBParams dbParams;
	static Logger log = Logger.getLogger(KSKDBUtil.class);
	/*
	 * static { LoadProperties(); }
	 */
	public static KSKDBParams LoadProperties() {
		
		Properties p = new Properties();
		try {
			//p.load(new FileInputStream("E:/Workspaces/MyEclipse 9/Rcm/src/ksk.properties"));
			//p.load(new FileInputStream("d:/jdbc/jdbc.properties"));
			p.load(new FileInputStream(System.getProperty("user.dir")+"/WebRoot/WEB-INF/ksk.properties"));
			KSKDBParams dbParams = new KSKDBParams();
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
		Connection  conn = null;
		try {
			dbParams = KSKDBUtil.LoadProperties();
			Class.forName(dbParams.getDriverClass());
			
			conn =  (Connection) DriverManager.getConnection(
					dbParams.getJdbcUrl(), dbParams.getUsername(),
					dbParams.getPassword());
			

		} catch (Exception e) {
			e.printStackTrace();
			log.warn("数据库链接异常！");
		}
		return conn;
	}

	

	

}