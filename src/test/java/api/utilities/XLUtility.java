package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
// THis excel utility can be used for both Selenium web automation and api automation	
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public XLUtility(String path)
	{
		this.path = path;
	}
	
	public int getRowCount(String SheetName) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(SheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
		
	}
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
		
	}
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data ;
		try
		{
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data = "";
		}
		workbook.close();
		fi.close();
		
		return data;
		
	}
	
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{// Note we dont use this method in api testing
		File xlfile = new File(path);
		if(!xlfile.exists()) // if file doesnt exists then create new file
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		fi=new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1) // if sheet doesnt exists then create new sheet
				workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
						
		if(sheet.getRow(rownum)==null) // if row doesnt exists then create new row
				sheet.createRow(rownum);
			row=sheet.getRow(rownum);
			
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
		
		
	}
	
	public void fillGreenColor(String sheetName,int rownnum,int colnum) throws IOException
	{// Note we dont use this method in api testing
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownnum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	
	
	public void fillRedColor(String sheetName,int rownnum,int colnum) throws IOException
	{// Note we dont use this method in api testing
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownnum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}


}
