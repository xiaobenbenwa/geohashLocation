package momo.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class DataService {

	private DBSingleton dbs = DBSingleton.getInstance();
	private Connection conn =  dbs.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	

	public static void main(String[] args) {

		DataService ds = new DataService();

		// insert操作
		
		String[] para = {"29","程亦可","女","2011-07-21","106.98" };
		String tablename = "STUDENT";
		ds.insert("STUDENT", para,ds.conn);
		//ds.closeConnection();
		
		
		// find操作
		
		ds.rs = ds.find("select * from STUDENT", ds.conn);

		try {
			while (ds.rs.next()) {
				System.out.println(ds.rs.getString("SNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			ds.closeConnection();
		}

	}



	public void insert(String tablename, String[] para,Connection conn) {
		

		int paraLength = para.length;
		String insertSql = "insert into " + tablename + " values (";

		for (int i = 0; i < para.length - 1; i++) {
			insertSql += "?,";

		}
		insertSql += "?)";

		//System.out.println(insertSql);
		// "insert into STUDENT values (?,?,?,?,?)"
		try {
			pstmt = conn.prepareStatement(insertSql);
			for (int i = 1; i <= para.length; i++) {
				pstmt.setObject(i, para[i - 1]);
			}
			pstmt.executeUpdate();
			pstmt = null;
			System.out.println("=======================");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete() {

	}

	public ResultSet find(String sql,Connection conn) {
	    

		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * 关闭数据库方法1, 需要三个参数 resultset statement connection
	 * 
	 * 
	 */

	public void closeConnection() {
		try {
			if (rs != null) {
				rs.close();
				rs=null;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
						conn = null;
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

	