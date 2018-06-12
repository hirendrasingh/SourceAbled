package com.sa.utility;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public String path=null;
	public FileInputStream fis=null;
	public XSSFWorkbook workbook=null;
	public XSSFSheet sheet=null;
	public XSSFRow row=null;
	public XSSFCell cell=null;
	
	// Constructer....
	public ExcelReader(String path)	{
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	// To get data from excel file USING SHEETNAME & COLUMNNAME any specified cell...
	public String getCellData(String sheetName, String colName, int rowNum)
	{
		try {
			int colNum=0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for(int i=0; i<row.getLastCellNum(); i++){
				if(row.getCell(i).getStringCellValue().equals(colName)){
					colNum=i;
				}
			}
			row=sheet.getRow(rowNum-1);
			
			XSSFCell cell = row.getCell(colNum);
			
			if(cell.getCellType()==cell.CELL_TYPE_STRING){
				return cell.getStringCellValue();
				
			} else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
				return String.valueOf(cell.getNumericCellValue());
				
			} else if(cell.getCellType()==cell.CELL_TYPE_BOOLEAN){
				return String.valueOf(cell.getBooleanCellValue());
				
			} else if(cell.getCellType()==cell.CELL_TYPE_BLANK){
				return "";				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	// This is the overloaded method...
	// To get data from all cell USING SHEETNAME & INDEX(row and column no.) in excel file.....
	public String getCellData(String sheetName, int colNum, int rowNum)
	{
		try {
			
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			// Used row-1 so that it gives exact count.....
			row=sheet.getRow(rowNum-1);
			
			XSSFCell cell = row.getCell(colNum);
			
			if(cell.getCellType()==cell.CELL_TYPE_STRING){
				
				return cell.getStringCellValue();
				
			} /*else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
				return String.valueOf(cell.getNumericCellValue());*/
			
		// From this else block numeric cell value is converted to string and will get all the numbers...
			else if (cell.getCellType()==cell.CELL_TYPE_NUMERIC) {
				cell.setCellType(cell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
									
			} else if(cell.getCellType()==cell.CELL_TYPE_BOOLEAN){
				return String.valueOf(cell.getBooleanCellValue());
				
			} else if(cell.getCellType()==cell.CELL_TYPE_BLANK){
				return "";				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; 
	}
	
	// Gives total no. of rows in the sheet....
	public int getRowCount(String sheetName){
		try {
			int index = workbook.getSheetIndex(sheetName);
			if(index == -1){
				return 0;
			}
			else{
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum()+1;
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// Gives total no. of columns in the sheet....
		public int getColumnCount(String sheetName){
			try {
				int index = workbook.getSheetIndex(sheetName);
				if(index == -1){
					return 0;
				}
				else{
					sheet = workbook.getSheetAt(index);
					row = sheet.getRow(0);
					return row.getLastCellNum();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		// Create Object to get all cell data from any Excel using path....
		public static Object[][] getData(String ExcelName, String sheetName) {
			ExcelReader Data = new ExcelReader(System.getProperty("user.dir") + "\\ExcelFiles\\"+ExcelName);
			int rowNum = Data.getRowCount(sheetName);
			//System.out.println(rowNum);
			int colNum = Data.getColumnCount(sheetName);
			
			// Created 2d array of type Object to get cell data....
			Object testData[][] = new Object[rowNum - 1][colNum];
			for (int i = 2; i <=rowNum; i++) {
				for (int j = 0; j < colNum; j++) {
					testData[i - 2][j] = Data.getCellData(sheetName, j, i);
					// Data.getCellData(sheetName, j, i).toString();
					System.out.println("Excel Data="+Data.getCellData(sheetName, j, i).toString());
					System.out.println("row="+ i+" and "+"col="+ j);
				}
			}
			return testData;
		}
		
		/*public static void main(String[] args) {
			String path = System.getProperty("user.dir")+"\\ExcelFiles\\Logins.xlsx";
			ExcelReader obj = new ExcelReader(path);
			//System.out.println(obj.getCellData("JsLogins", "JsEmailAdresses", 2));
			//System.out.println(obj.getRowCount("SALogins"));
			System.out.println(obj.getCellData("JSlogins", 1, 3));
			
		}*/

}
