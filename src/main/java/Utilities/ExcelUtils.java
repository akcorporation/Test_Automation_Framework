package Utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;

import Runner.BaseClass;
import Runner.BaseClassImplement;

public class ExcelUtils {
	
	static int row1 = 1;
	static int row2 = 30;
	static int col1 = 1;
	static int col2 = 30;
	static String oldEvidencePath= "Initialize_variable";

//	Use Method overloading concept with different parameter in below function we give  parameter type as file
	public static void CaptureExcelEvidence(File srcFile) throws IOException {
		XSSFClientAnchor anchor = null;
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		File file = null;
		
		String Evidence_path = BaseClassImplement.getEvidencePath();
		String outputFile = Evidence_path+"\\Evidence.xlsx";
		
		if(!oldEvidencePath.equalsIgnoreCase(Evidence_path)) {
			row1 = 1;
			  row2 = 30;
			  col1 = 1;
			  col2 = 30;
			  oldEvidencePath = Evidence_path;
		}
		
		file = new File(Evidence_path);
		if(!(file.isDirectory())) {
			File directory = new File(Evidence_path);
			directory.mkdir();
		}
        
		file = new File(outputFile);
		if(file.exists()) {
			workbook = new XSSFWorkbook(new FileInputStream(outputFile));
//	        sheet = workbook.getSheet("Screenshots");
			sheet = workbook.getSheetAt(0);
		}
		else {
			workbook = new XSSFWorkbook();
	        sheet = workbook.createSheet("Screenshots");
//	        System.out.println("First TIme---");	
	        }
		
			anchor = new XSSFClientAnchor();
        
        anchor.setCol1(col1); // Sets the column (0 based) of the first cell.
        anchor.setCol2(col2); // Sets the column (0 based) of the Second cell.
        anchor.setRow1(row1); // Sets the row (0 based) of the first cell.
        anchor.setRow2(row2); // Sets the row (0 based) of the Second cell.

        anchor.setAnchorType(AnchorType.DONT_MOVE_AND_RESIZE);
        int index = sheet.getWorkbook().addPicture(imageToBytes(srcFile), XSSFWorkbook.PICTURE_TYPE_PNG);
        
        double scale = 0.9;
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        XSSFPicture picture = patriarch.createPicture(anchor, index);
        picture.resize(scale);
       
        // Writing Image in Excel file
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        workbook.close();
       
        row1 = row1 + 32;
        row2 = row2 + 32;
}
	
//	Use Method overloading concept with different parameter in below function we give  parameter type as file
	private static byte[] imageToBytes(File srcFile) throws IOException {
        FileInputStream fis = null;
        ByteArrayOutputStream bos;
        int read;
        try {
            fis = new FileInputStream(srcFile);
            bos = new ByteArrayOutputStream();
            while ((read = fis.read()) != -1) {
                bos.write(read);
            }
        } finally {
            if (fis != null) {
                    fis.close();
                    fis = null;
            }
        }
        return (bos.toByteArray());
    }
	
//	Use Method overloading concept with different parameter in below function we give  parameter type as BufferedImage
	public static void CaptureExcelEvidence(BufferedImage srcFile) throws IOException {
		XSSFClientAnchor anchor = null;
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		File file = null;
		
		String Evidence_path = BaseClassImplement.getEvidencePath();
		String outputFile = Evidence_path+"\\Evidence.xlsx";
		
		if(!oldEvidencePath.equalsIgnoreCase(Evidence_path)) {
			row1 = 1;
			  row2 = 30;
			  col1 = 1;
			  col2 = 30;
			  oldEvidencePath = Evidence_path;
		}
		
		file = new File(Evidence_path);
		if(!(file.isDirectory())) {
			File directory = new File(Evidence_path);
			directory.mkdir();
		}
        
		file = new File(outputFile);
		if(file.exists()) {
			workbook = new XSSFWorkbook(new FileInputStream(outputFile));
//	        sheet = workbook.getSheet("Screenshots");
			sheet = workbook.getSheetAt(0);
		}
		else {
			workbook = new XSSFWorkbook();
	        sheet = workbook.createSheet("Screenshots");
//	        System.out.println("First TIme---");	
	        }
		
			anchor = new XSSFClientAnchor();
        
        anchor.setCol1(col1); // Sets the column (0 based) of the first cell.
        anchor.setCol2(col2); // Sets the column (0 based) of the Second cell.
        anchor.setRow1(row1); // Sets the row (0 based) of the first cell.
        anchor.setRow2(row2); // Sets the row (0 based) of the Second cell.

        anchor.setAnchorType(AnchorType.DONT_MOVE_AND_RESIZE);
        int index = sheet.getWorkbook().addPicture(imageToBytes(srcFile), XSSFWorkbook.PICTURE_TYPE_PNG);
        
        double scale = 0.9;
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        XSSFPicture picture = patriarch.createPicture(anchor, index);
        picture.resize(scale);
       
        // Writing Image in Excel file
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        workbook.close();
       
        row1 = row1 + 32;
        row2 = row2 + 32;
}
//	Use Method overloading concept with different parameter in below function we give  parameter type as BufferedImage
	 private static byte[] imageToBytes(BufferedImage srcFile) throws IOException {
	        ByteArrayOutputStream bos;
	            bos = new ByteArrayOutputStream();
	            ImageIO.write(srcFile, "png", bos);
	        return (bos.toByteArray());

	    }
	 
	 public static List<HashMap<String,String>> getTestData(String sheetName) {
		 //Creating List of Hash map
		 List<HashMap<String,String>> ListofHash = new ArrayList<HashMap<String, String>>();
//		 Creating Hash Map
		 HashMap<String, String> map = new HashMap<String, String>();
//		 Getting Test data
		 String TestDataPath = System.getProperty("user.dir") + BaseClass.readProperty("TestdataFilePath","global");
		 FileInputStream fis = null;
			 try  
			 {  
			 File file = new File(TestDataPath);   //creating a new file instance  
			 fis = new FileInputStream(file);   //obtaining bytes from the file  
			 //creating Workbook instance that refers to Test Data File  
			 XSSFWorkbook wb = new XSSFWorkbook(fis);   
//			 XSSFSheet sheet = wb.getSheetAt(1);     //creating a Sheet object to retrieve object  
			 XSSFSheet sheet = wb.getSheet(sheetName);
			 int RowCount = sheet.getLastRowNum();
			 XSSFRow row;
			 
//			 Executing For loop where RowCount is total no. rows in that sheet
			 for(int i=1; i <= RowCount; i++) {
				 row = sheet.getRow(i);
//				 Fetching total no of column
				 int ColumnCount= row.getLastCellNum();
				 map = new HashMap<String, String>();
				 for(int j=0; j < ColumnCount; j++) {
//					 Fetching data from excel and placing in hash map in key, value pair
					 map.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i).getCell(j).toString());
				 }
//				 adding hash map in list
				 ListofHash.add(map);
			 }
//			 closing workbook
			 wb.close();
			 }  
			 catch(FileNotFoundException e)  
			 {  
			 System.out.println("Not able to find file: " + TestDataPath + " \nErrormessage: " + e.getMessage());
			 }
			 catch(IOException e)  
			 {  
			 System.out.println("Getting IO Exception in FileInputStream: " + fis + " \nErrormessage: " + e.getMessage());
			 } 
			  
		 return ListofHash;
	 }
}