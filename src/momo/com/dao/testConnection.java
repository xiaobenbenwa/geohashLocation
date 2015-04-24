package momo.com.dao;

import java.sql.*;

public class testConnection {


    
	
	public static void main(String[] args) {
		
		String driver = "com.mysql.jdbc.Driver";
		
		 String url = "jdbc:mysql://127.0.0.1:3306/zhoulu?useUnicode=true&characterEncoding=utf8";

		String user = "root";

		String password = "123456";

		try {
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())

				System.out.println("Succeeded connecting to the Database!");

			//使用Statement查询
			
			
			Statement statement = conn.createStatement();
			String sql = "select * from STUDENT";
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("-----------------");  
			System.out.println("执行结果如下所示:");  
			System.out.println("-----------------");  
			System.out.println(" 学号" + "\t" + " 姓名");  
			System.out.println("-----------------");  
			String name = null;  
			while(rs.next()) {  
				
				System.out.println(rs.getString("sno") + "\t" + rs.getString("sname"));  
			}  
			rs.close();  
			conn.close();   
			} catch(ClassNotFoundException e) {   
			System.out.println("Sorry,can`t find the Driver!");   
			e.printStackTrace();   
			} catch(SQLException e) {   
			e.printStackTrace();   
			} catch(Exception e) {   
			e.printStackTrace();   
			}   
			}   
			}
		
		
		    /*
			// 使用preparedstatement插入数据
			PreparedStatement pstmt = conn
					.prepareStatement("insert into STUDENT values (?,?,?,?,?)");
			pstmt.setString(1, "13");
			pstmt.setString(2, "陈天佑");
			pstmt.setString(3, "男");
			pstmt.setObject(4, "2011-08-24");
			pstmt.setDouble(5, 98); // 如果不知道占位符的数据类型，可以使用setObject()
			// 提及插入操作
			pstmt.executeUpdate();
			System.out.println("=======================");
			// 使用preparedstatement查询数据
			PreparedStatement pstmt1 = conn.prepareStatement("select * from  STUDENT");
			ResultSet rs=pstmt1.executeQuery();
			String name="";
			while (rs.next()) {
				name = rs.getString("HEIGHT");
				System.out.println(name);
			}
			
            
			rs.close();
			//printResultSet(pstmt1.executeQuery());
			System.out.println("=======================");

			// ResultSetMetaData:ResultSet 读取结果
			ResultSetMetaData rsmd = pstmt1.getMetaData();
			System.out.println("" + rsmd.getColumnCount());
			System.out.println("" + rsmd.getTableName(1));

			
			
		
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
   
	
	
	
	
	
	
	
	
	/*
	public static testConnection getTestConnection() {
		if (testConn == null) {
			// 给类加锁 防止线程并发
			synchronized (testConnection.class) {
				if (testConn == null) {
					testConn = new testConnection();
				}
			}
		}
		return testConn;
	}

	private testConnection() {
	}
	
	
	// 获得连接
	public Connection getConnection() {
		 Connection conn=null;
		 try {
			   Class.forName(driver);
			   conn=DriverManager.getConnection(url,user,password);
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			  return conn;
	}
	
	 public void closeConnection(ResultSet rs, Statement statement, Connection con) {  
		           try {  
		              if (rs != null) {  
		                  rs.close();  
		              }  
		          } catch (SQLException e) {  
		              e.printStackTrace();  
		           } finally {  
		              try {  
		                  if (statement != null) {  
		                    statement.close();  
		                  }  
		             } catch (Exception e) {  
		                 e.printStackTrace();  
		             } finally {  
		                  try {  
		                      if (con != null) {  
		                          con.close();  
		                     }  
		                 } catch (SQLException e) {  
		                    e.printStackTrace();  
		               }  
		              }  
		          }  
		     }  
		     
	

	
    
	
        
    public static void printResultSet(ResultSet rs2) {
		try {
			while (rs2.next()) {
				System.out.println(rs2.getString(1) + "\t" + rs2.getString(2)
						+ "\t" + rs2.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void connectDB (String driver, String url,String user,String password) 
			throws ClassNotFoundException,SQLException,Exception{
		
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())

				System.out.println("Succeeded connecting to the Database!");

			//使用Statement查询
			
			/*
			Statement statement = conn.createStatement();
			String sql = "select * from STUDENT";
			ResultSet rs = statement.executeQuery(sql);
			
		
			// 使用preparedstatement插入数据
			PreparedStatement pstmt = conn
					.prepareStatement("insert into STUDENT values (?,?,?,?,?)");
			pstmt.setString(1, "13");
			pstmt.setString(2, "陈天佑");
			pstmt.setString(3, "男");
			pstmt.setObject(4, "2011-08-24");
			pstmt.setDouble(5, 98); // 如果不知道占位符的数据类型，可以使用setObject()
			// 提及插入操作
			pstmt.executeUpdate();
			System.out.println("=======================");
			// 使用preparedstatement查询数据
			PreparedStatement pstmt1 = conn.prepareStatement("select * from  STUDENT");
			ResultSet rs=pstmt1.executeQuery();
			String name="";
			while (rs.next()) {
				name = rs.getString("HEIGHT");
				System.out.println(name);
			}
			
            
			rs.close();
			//printResultSet(pstmt1.executeQuery());
			System.out.println("=======================");

			// ResultSetMetaData:ResultSet 读取结果
			ResultSetMetaData rsmd = pstmt1.getMetaData();
			System.out.println("" + rsmd.getColumnCount());
			System.out.println("" + rsmd.getTableName(1));

			
			
		
			conn.close();
			
		}
      */

