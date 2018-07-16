package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.domain.ticket;

public class projectCheckListTest {
	public static void main(String[] args) {
		String filePath = "D:\\Study System\\Code\\MyEclipse workspace\\POITest\\教师信息上传示例表.xlsx";
		readOriginExcelFile(filePath);// 读取Excel2007-即xls文件的示例方法 （单个sheet）
	}

	/**
	 * 读取源文件(originExcelFile),模拟老师上传后的空文件
	 * 
	 * @param filePath
	 */

	static void readOriginExcelFile(String filePath) {
		/**
		 * 建立基本对照表basicCheckTable
		 */
		ArrayList<String[]> basicCheckTable = new ArrayList<String[]>();
		String[] idArray = { "id", "ID", "编号", "教师编号", "教师ID", "教师id" };
		String[] nameArray = { "name", "Name", "姓名", "教师姓名", "教师Name", "教师name" };
		String[] sexArray = { "sex", "Sex", "性别", "教师性别" };
		String[] ageArray = { "age", "Age", "年龄" };
		basicCheckTable.add(idArray);
		basicCheckTable.add(nameArray);
		basicCheckTable.add(sexArray);
		basicCheckTable.add(ageArray);

		/**
		 * 新建存储对象
		 */
		// originExcelHeadString->原始excel文件的表头字段的列表
		ArrayList<String> originExcelHeadString = new ArrayList<String>();

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
			Workbook workbook2007 = WorkbookFactory.create(is);
			Sheet sheet = workbook2007.getSheetAt(0);
			/**
			 * 开始获取表头各个字段的内容,并添加到List-originExcelHeadString中
			 */
			Row headRow = sheet.getRow(0);// 获取表头行
			for (int i = 0; i < headRow.getLastCellNum(); i++) {
				// 只有不为空的字段才会被加入匹配列表,实现去除空字段
				if (headRow.getCell(i)!=null)
					originExcelHeadString.add(headRow.getCell(i).toString());
				// 循环结束后,表头字段存储完毕
			}

			// 实现表头字段的简单去重(即两个字段名是一样的可以去重,但是两个字段名不一样,表达同一个意思时无法实现)
			// originExcelHeadString = new ArrayList<String>(new
			// HashSet<String>(originExcelHeadString));

			// 使用 HashSet 去重后的 List 元素顺序可能与原 List 不一样，可以使用 TreeSet 代替 HashSet
			// 实现同样的功能，且保证顺序一致性。
			originExcelHeadString = new ArrayList<String>(new TreeSet<String>(originExcelHeadString));

			// feature->高级去重功能,可以先将字段名和判断后的类别组成字典,通过字典自己去重,即键值对的形式
			
			// 输出查看获取的表头元素
			System.out.println(originExcelHeadString);

			// 开始在自定义的库中进行匹对关键词(basicCheckTable)
			// 从表头字段list开始外循环
			for (int i = 0; i < originExcelHeadString.size(); i++) {
				// 开始和基础检查表的各个数组进行匹对
				begin: for (int j = 0; j < basicCheckTable.size(); j++) {
					// 获取数组对象
					String[] tempArray = (String[]) basicCheckTable.get(j);
					// 从获取的数组对象中开始遍历
					for (int k = 0; k < tempArray.length; k++) {
						// 如果匹配成功,就显示其对应的最简化字符串
						if (tempArray[k].equals(originExcelHeadString.get(i))) {
							System.out.println(
									"存在' " + originExcelHeadString.get(i) + " ' ,对应结果为," + tempArray[0] + "字段");
							// 匹配到对应字段后,回到起点,以便于下一次便利
							j = 0;
							// 可以直接跳出循环了,暂时不考虑有多个重复表头的情况
							// 跳出循环到开始检查基础表的时候,便于i++
							break begin;
						} else {
							// 否则显示不存在此基础字段
							// 当表内的数组对象 走到最后一项之后显示未查找到,开始走基础检查表的下一项
							if (k == tempArray.length - 1) {
								// 如果不等于最终长度,就跳转回检查表的遍历起点
								if (j != basicCheckTable.size() - 1) {
									// 如果加完后等于基础检查表的长度,说明可以结束了,显示未查询到的结果
									continue begin;
								}
								if (j == basicCheckTable.size() - 1) {
									// 如果等于的话,最终就会执行下一句话
									System.out.println("不存在 " + originExcelHeadString.get(i) + " 请检查表格");
								}
							}

							// 暂时不处理,以后可能可以让用户自己选择列名
						}
					}
				}
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
