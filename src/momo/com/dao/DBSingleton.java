package momo.com.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class DBSingleton {

	public static void main(String[] args) {
		Connection conn = DBSingleton.getInstance().getConn();
		if (!conn.isClosed())

			System.out.println("Succeeded connecting to the Database!");

	}

	private String url = "jdbc:mysql://localhost:3306/zhoulu?useUnicode=true&characterEncoding=utf8";
	private String uid = "root";
	private String pwd = "123456";
	private static DBSingleton instance = null;// 创建数据库实例 必须是静态变量

	public static DBSingleton getInstance() {
		if (instance == null) {
			synchronized (DBSingleton.class) {// 加锁
				if (instance == null) {
					instance = new DBSingleton();// 预处理数据库实例，即调用该方法时才创建该实例，而不是一开始就
													// 在虚拟机里创建实例
				}
			}
		}
		return instance;
	}

	/**
	 * 连接数据库方法
	 * 
	 * @return
	 */
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, uid, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	
}
