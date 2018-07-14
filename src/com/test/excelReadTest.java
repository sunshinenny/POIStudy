package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.domain.ticket;

public class excelReadTest {
	public static void main(String[] args) {
		String filePath = "D:\\Study System\\Code\\MyEclipse workspace\\POITest\\火车票列表.xlsx";
		readOneSheet(filePath);// 读取Excel2007-即xls文件的示例方法 （单个sheet）
	}

	/**
	 * 读取Excel2007-即xls文件的示例方法 （单个sheet）
	 * 
	 * @param filePath
	 */
	static void readOneSheet(String filePath) {
		/**
		 * 新建对象,并赋值为null
		 */
		File excelFile = null;// Excel文件对象
		InputStream is = null;// 输入流对象
		String cellStr = null;// 单元格，最终按字符串处理
		List<ticket> ticketList = new ArrayList<ticket>();// 返回封装数据的List
		ticket tic = null;// 每一张票的信息对象
		/**
		 * 开始读取文件
		 */
		try {
			excelFile = new File(filePath);// File excelFile=new File(filePath);
			is = new FileInputStream(excelFile);// 获取文件输入流
			// XSSFWorkbook workbook2007 = new XSSFWorkbook(is);// 创建Excel2007文件对象
			Workbook workbook2007 = WorkbookFactory.create(is);
			// XSSFSheet sheet = workbook2007.getSheetAt(0);// 取出第一个工作表，索引是0
			Sheet sheet = workbook2007.getSheetAt(0);

			/**
			 * 开始获取表头信息
			 */
			Row headRow = sheet.getRow(0);// 获取表头行
			for (int i = 0; i < headRow.getLastCellNum(); i++) {
				if (i > 0 && i < 4)
					System.out.print(headRow.getCell(i) + "\t");
			}

			// 开始循环遍历**行**，表头不处理，故从1开始
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				tic = new ticket();// 实例化ticket对象
				// HSSFRow row = sheet.getRow(i);// 获取行对象
				Row row = sheet.getRow(i);// 获取行对象
				if (row == null) {// 如果为空，不处理
					continue;
				}
				// 循环遍历单元格
				for (int j = 0; j < row.getLastCellNum(); j++) {
					// XSSFCell cell = row.getCell(j);// 获取单元格对象
					Cell cell = row.getCell(j);// 获取单元格对象
					if (cell == null) {// 单元格为空设置cellStr为空串
						cellStr = "";
					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
						cellStr = String.valueOf(cell.getBooleanCellValue());
					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
						cellStr = cell.getNumericCellValue() + "";
					} else {// 其余按照字符串处理
						cellStr = cell.getStringCellValue();
					}
					// 下面按照数据出现位置封装到bean中
					if (j == 0) {
						tic.setId(cellStr);
					} else if (j == 1) {
						tic.setDate(cellStr);
					} else if (j == 2) {
						tic.setFrom(cellStr);
					} else if (j == 3) {
						tic.setEnd(cellStr);
					} else if (j == 4) {
						tic.setNumber(cellStr);
					} else if (j == 5) {
						tic.setPrice(new Float(cellStr).floatValue());
					} else {
						tic.setNote(cellStr);
					}
				}
				ticketList.add(tic);// 数据装入List
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
		} finally {// 关闭文件流
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for (Object i : ticketList) {
			System.out
					.println(((ticket) i).getDate() + "\t" + ((ticket) i).getFrom() + "\t->\t" + ((ticket) i).getEnd());
		}
	}
}
