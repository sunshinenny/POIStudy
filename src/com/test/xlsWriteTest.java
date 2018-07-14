package com.test;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlsWriteTest {
	public static void main(String[] args) {
		// 生成 Workbook
		//XSSFWorkbook wb = new XSSFWorkbook();->生成xlsx文件
		//HSSFWorkbook wb = new HSSFWorkbook();->生成xls文件		
		XSSFWorkbook wb = new XSSFWorkbook();

		// 添加 Worksheet（不添加 sheet 时生成的 xls 文件打开时会报错）
		@SuppressWarnings("unused")
		Sheet sheet1 = wb.createSheet("First");
		@SuppressWarnings("unused")
		Sheet sheet2 = wb.createSheet("Second");
		@SuppressWarnings("unused")
		Sheet sheet3 = wb.createSheet("Third");
		// 保存为 Excel 文件
		FileOutputStream out = null;

		try {
			out = new FileOutputStream("D:\\Study System\\Code\\MyEclipse workspace\\POITest\\writeTestFile.xlsx");
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
