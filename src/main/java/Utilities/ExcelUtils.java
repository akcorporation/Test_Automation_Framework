package Utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;

import Runner.BaseClass;
import Runner.TestScript;

public class ExcelUtils {
	static int row1 = 1;
	static int row2 = 4;
	static int col1 = 1;
	static int col2 = 4;

	public static void CaptureExcelEvidence(File srcFile) throws IOException {
		XSSFClientAnchor anchor = null;
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		File file = null;
		
		String Evidence_Folder_Name = TestScript.testcase_id;
		String temp_Evidence_path = BaseClass.readProperty("EvidencePath", "global");
		String Evidence_path = System.getProperty("user.dir")+temp_Evidence_path+"\\"+Evidence_Folder_Name;
		String outputFile = Evidence_path+"\\Evidence.xlsx";
		
		file = new File(Evidence_path);
		Boolean dir = file.isDirectory();
		if(!(dir)) {
			File directory = new File(Evidence_path);
			directory.mkdir();
		}
        
		
		file = new File(outputFile);
		if(file.exists()) {
//			workbook = new XSSFWorkbook(outputFile);
			workbook = new XSSFWorkbook(new FileInputStream(outputFile));
//	        sheet = workbook.getSheet("Screenshots");
			sheet = workbook.getSheetAt(0);
		}
		else {
			workbook = new XSSFWorkbook();
	        sheet = workbook.createSheet("Screenshots");
//	        System.out.println("First TIme---");	
	        }
		
        
//        anchor = new XSSFClientAnchor(10, 10, 10, 10, col1, row1, col2, row2);
			anchor = new XSSFClientAnchor();
        
        anchor.setCol1(col1); // Sets the column (0 based) of the first cell.
        anchor.setCol2(col2); // Sets the column (0 based) of the Second cell.
        anchor.setRow1(row1); // Sets the row (0 based) of the first cell.
        anchor.setRow2(row2); // Sets the row (0 based) of the Second cell.

        anchor.setAnchorType(AnchorType.DONT_MOVE_AND_RESIZE);
        int index = sheet.getWorkbook().addPicture(imageToBytes(srcFile), XSSFWorkbook.PICTURE_TYPE_PNG);
        
        double scale = 10;
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        XSSFPicture picture = patriarch.createPicture(anchor, index);
        picture.resize(scale);
        
//        Picture pict = drawing.createPicture(anchor, index);
       
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        workbook.close();
       
        row1 = row1 + 32;
        row2 = row2 + 32;
}
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
}