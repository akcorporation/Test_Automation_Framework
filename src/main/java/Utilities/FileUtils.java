package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Runner.BaseClass;
import Runner.TestScript;

public class FileUtils {
	
	public static void copyInEvidenceFolder(File srcFile,String Filne_name) throws FileNotFoundException{
		String Evidence_Folder_Name = TestScript.testcase_id;
		String temp_Evidence_path = BaseClass.readProperty("EvidencePath", "global");
		String Evidence_path = System.getProperty("user.dir")+temp_Evidence_path+"\\"+Evidence_Folder_Name;
		String File_path = Evidence_path+"\\"+Filne_name+".png";
		
		File file = new File(Evidence_path);
		Boolean dir = file.isDirectory();
		if(!(dir)) {
			File directory = new File(Evidence_path);
			directory.mkdir();
		}
		
		@SuppressWarnings("resource")
		FileInputStream src_file = new FileInputStream(srcFile);
		@SuppressWarnings("resource")
		FileOutputStream dest_file = new FileOutputStream(File_path);

		
		int b = 0;
		while(b!=-1) {
			try {
			b=src_file.read();
			dest_file.write(b);
			}
			catch(IOException e) {
				System.out.println("Getting IO Exception in File: "+e.getMessage() );
			}
		}
//		Calling Excel utils to capture scrennshot in excel format as well
		try {
			ExcelUtils.CaptureExcelEvidence(srcFile);
		} catch (IOException e) {
			System.out.println("Getting IOException :" + e.getMessage());
		}
		
	}

}
