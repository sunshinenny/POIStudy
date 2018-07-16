package com.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class createAimExcelFile {
	// 表头字段和标准字段的映射关系
	Map<String, String> basicAndExcelMap;
	// 新建list对象,存储每一个字段对应的value
	ArrayList<String> headToValueArray;
	// 新建list对象,存储原始文件的头部字段
	ArrayList<String> headStringArray;
	// 开始遍历标准字段和头部字段对应表,并写入headToValue对象中,以便于确定位置
	Iterator<String> basicAndHeadStringMapIter;

	dynamicUserInfoFunction dy;
	private XSSFWorkbook wb;

	public createAimExcelFile(ArrayList<String> headToValueArray, ArrayList<String> headStringArray,
			 Map<String, String> basicAndExcelMap,
			dynamicUserInfoFunction dy) {
		this.headToValueArray = headToValueArray;
		this.headStringArray = headStringArray;
		this.basicAndExcelMap = basicAndExcelMap;
		this.dy = dy;
	}

	public void create() {
		Iterator<String> basicAndHeadStringMapIter = basicAndExcelMap.keySet().iterator();
		while (basicAndHeadStringMapIter.hasNext()) {
			String key = null;
			String value = null;
			key = basicAndHeadStringMapIter.next();
			value = basicAndExcelMap.get(key);
			headStringArray.add(value);
			// 通过key的对应值,动态调用userInfo的方法,获取对应的值,例如key为id,调用getId方法获取id值
			headToValueArray.add(dy.getValueFromUserInfo(key));
		}
		System.out.println(headStringArray);
		System.out.println(headToValueArray);
		wb = new XSSFWorkbook();

		// 添加 Worksheet（不添加 sheet 时生成的 xls 文件打开时会报错）
		@SuppressWarnings("unused")
		Sheet mainSheet = wb.createSheet("Main");
		// 创建首行
		Row mainRow = mainSheet.createRow(0);
		for (int i = 0; i < headStringArray.size(); i++) {
			mainRow.createCell(i).setCellValue(headStringArray.get(i).toString());
		}
		// 创建数据行
		Row valueRow = mainSheet.createRow(1);
		for (int i = 0; i < headStringArray.size(); i++) {
			valueRow.createCell(i).setCellValue(headToValueArray.get(i).toString());
		}
		// 保存为 Excel 文件
		FileOutputStream out = null;

		try {
			out = new FileOutputStream("D:\\Study System\\Code\\MyEclipse workspace\\POITest\\translated.xlsx");
			wb.write(out);
			System.out.println("Write Success");
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

	}
}
