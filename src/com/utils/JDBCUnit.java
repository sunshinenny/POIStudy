package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 使用数据库连接池技术 进行数据库连接
 * 
 * @author sunshinenny
 *
 */

public class JDBCUnit {
	/**
	 * static的conn方法，在该类外调用，可以直接连接数据库
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hibernatetest?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT";

	static final String USER = "root";
	static final String PASS = "root";
	public static Connection conn() throws SQLException, NamingException, ClassNotFoundException {
		// 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
    
        // 打开链接
        System.out.println(" 连接数据库...");
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
		// 返回connection对象，以便其他类连接数据库
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param rs
	 * @param stm
	 * @param conn
	 */
	public static void close(java.sql.ResultSet rs, java.sql.Statement stm, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if (stm != null) {
			try {
				stm.close();// 可能存在错误
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				stm = null;
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}
