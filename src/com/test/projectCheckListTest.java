package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.NamingException;

import com.utils.createAimExcelFile;
import com.utils.dynamicUserInfoFunction;
import com.utils.getValueFromDatabase;
import com.utils.readOriginExcelFile;

public class projectCheckListTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
		// 如果以后使用公共模板,那直接传参为公共模板的地址即可
		String filePath = "D:\\Study System\\Code\\MyEclipse workspace\\POITest\\教师信息上传示例表.xlsx";
		// 获取表头字段和标准字段的映射关系
		readOriginExcelFile readOriginExcelFile = new readOriginExcelFile(filePath);
		Map<String, String> basicAndExcelMap = (Map<String, String>) readOriginExcelFile.readOriginExcelFile();
		// 匿名函数:根据教师id获取数据库中对应信息,并存入userInfo对象中
		// 新建userInfo的动态调用函数对象,构造函数传参为userInfo对象
		dynamicUserInfoFunction dy = new dynamicUserInfoFunction(
				new getValueFromDatabase(basicAndExcelMap, "1002").getUserInfo());
		// 新建list对象,存储每一个字段对应的value
		ArrayList<String> headToValueArray = new ArrayList<String>();
		// 新建list对象,存储原始文件的头部字段
		ArrayList<String> headStringArray = new ArrayList<String>();
		// 新建写入excel对象-名称为lastStep
		createAimExcelFile lastStep = new createAimExcelFile(headToValueArray, headStringArray, basicAndExcelMap, dy);
		lastStep.create();
	}
}
