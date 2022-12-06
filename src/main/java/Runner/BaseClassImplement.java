package Runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Pages.Login;
import Utilities.ExcelUtils;

public class BaseClassImplement {
	static String TestcaseName;
	
//	Intializing all properties files
	public static void PropImplementation() {
		BaseClass.initializeGlobalProp();
		BaseClass.connectionDetailsProp();
		BaseClass.initializeapplicationProp();
		BaseClass.initializelog4jProp();
	}
	
//	Getting Evidence Path
	public static String getEvidencePath() {
		String temp_Evidence_path = BaseClass.readProperty("EvidencePath", "global");
		String Evidence_path = System.getProperty("user.dir")+temp_Evidence_path+"\\"+TestcaseName;
		return Evidence_path;
	}
	
//	Append test case name with Date Time to make random
	public static void getTestCaseName(String TestName) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
		TestcaseName = TestName + "_" + formatter.format(date);
	}
	
//	Getting sheet Name which we need to execute
	public static List<String> TestSheetHandler(){
		List<HashMap<String,String>> TestData =  ExcelUtils.getTestData("Index");
		List<String> TestDataList=new ArrayList<String>();
		for(HashMap<String, String> list : TestData)
		{
			if(list.get("Execution_Flag").equalsIgnoreCase("Yes")) {
				TestDataList.add(list.get("Sheet_Name"));
			}	
		}
		return TestDataList;
	}
	
//	Check Login count if it's 0 then it login otherwise UI already launched
	public static void checkLogin() {
		if(TestScript.Login_count == 0) {
			try {
				Login.login();
				TestScript.Login_count++;
			} catch (InterruptedException e) {
				System.out.println(" Getting Interrupted Exception while Login " + e.getMessage());
			}
		}
	}
}