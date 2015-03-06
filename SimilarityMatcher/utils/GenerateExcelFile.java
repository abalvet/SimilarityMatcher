package utils;
import java.io.*;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import tools.MyFileUtils;

/***
 * 
 * @author antonio
 * Utility class to print results to Excel file.
 * Takes as input a CSV text.
 */
public class GenerateExcelFile {
	
	public static void printCSVTextToExcel(String aCSVContent, String aFile){
		Workbook wb = new HSSFWorkbook();
        CreationHelper helper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("Patterns");
        String[] lines = aCSVContent.split("\n");
        for (int r = 0; r < lines.length; r++) {
        	Row row = sheet.createRow(r);
        	String[] cells = lines[r].split("\t");
        	for(int c = 0; c < cells.length; c++){
        		row.createCell(c).setCellValue(helper.createRichTextString(cells[c]));
        	}
        	
		}
        // Write the output to a file
        FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(aFile);
		} catch (FileNotFoundException e) {
			System.out.println("Problem generating spreadsheet!");
			e.printStackTrace();
		}
        try {
			wb.write(fileOut);
	        fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String text = "AAA\tBBB\tCCC\tDDD";
		
		GenerateExcelFile.printCSVTextToExcel(text, "/Users/antonio/Documents/Recherche/Tests/out.xls");
	}

}
