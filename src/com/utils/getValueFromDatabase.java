package com.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.naming.NamingException;

import com.domain.userInfo;
import com.utils.JDBCUnit;

public class getValueFromDatabase {
	Map<String, String> basicAndExcelMap;
	String id;

	/**
	 * 构造函数,获取标准字段对照表&教师编号
	 * 
	 * @param basicAndExcelMap
	 * @param id
	 */
	public getValueFromDatabase(Map<String, String> basicAndExcelMap, String id) {
		this.basicAndExcelMap = basicAndExcelMap;
		// 临时传参,实际项目中会由request获取登陆账户的id,进而得到结果
		this.id = id;
	}

	/**
	 * 根据标准字段表&教师id查询数据库
	 * 
	 * @return 该id下的所有数据,封装成userInfo对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public userInfo getUserInfo() throws ClassNotFoundException, SQLException, NamingException {
		Connection conn = JDBCUnit.conn();
		Statement stmt = conn.createStatement();
		String sql;
		sql = "SELECT * from teacherinfotest where id='" + id + "'";
		ResultSet rs = stmt.executeQuery(sql);

		userInfo tempUserInfoFromDatabase = new userInfo();
		// 展开结果集数据库
		while (rs.next()) {
			// 通过字段检索
			String id = rs.getString("id");
			String name = rs.getString("name");
			String sex = rs.getString("sex");
			String age = rs.getString("age");
			String phoneNumber = rs.getString("phoneNumber");
			String address = rs.getString("address");
			String email = rs.getString("email");

			tempUserInfoFromDatabase.setId(id);
			tempUserInfoFromDatabase.setName(name);
			tempUserInfoFromDatabase.setSex(sex);
			tempUserInfoFromDatabase.setAge(age);
			tempUserInfoFromDatabase.setPhoneNumber(phoneNumber);
			tempUserInfoFromDatabase.setAddress(address);
			tempUserInfoFromDatabase.setEmail(email);
			// 输出数据
			System.out.print("ID: " + id);
			System.out.print(" 名称: " + name);
			System.out.print(" 性别: " + sex);
			System.out.print("\n");
		}
		JDBCUnit.close(rs, stmt, conn);
		return tempUserInfoFromDatabase;
	}
}
