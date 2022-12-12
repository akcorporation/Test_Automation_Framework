package Pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Runner.BaseClass;
import Runner.BaseClassImplement;
import Runner.Webdriver;
import Utilities.ExcelUtils;

public class createProfile {
	static String Name = null;
	static String Password = null;
	static String ConfirmPassword = null;
	static String TestCaseName = null;

	public static void createprofile() {
		List<HashMap<String,String>> TestData =  ExcelUtils.getTestData("Create_Profile");
		
		for(HashMap<String, String> list : TestData)
		{
			if(list.get("Execution_Flag").equalsIgnoreCase("Yes")) {
				
				TestCaseDetails(list);
				BaseClassImplement.checkLogin();
				WebDriver driver = Webdriver.getDriver();
				WebDriverWait driver_wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				
				Webdriver.captureScreenshot("Pre_Create_Profile");

				String EmployeeMaster = BaseClass.readProperty("EmployyesMaster", "application");
				driver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmployeeMaster))).click();
//				driver.findElement(By.linkText("add_employee.php")).click();
				
				String EmployeeNamePath = BaseClass.readProperty("EmployeesName", "application");
				driver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmployeeNamePath))).sendKeys(Name);
				
				String EmployeePassPath = BaseClass.readProperty("EmployeesPassword", "application");
				driver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmployeePassPath))).sendKeys(Password);
				
				String EmployeeConfirmPass = BaseClass.readProperty("EmployeesConfirmPassword", "application");
				driver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmployeeConfirmPass))).sendKeys(ConfirmPassword);
				
				Webdriver.captureScreenshot("Post_Create_Profile");
				
				String EmployeeSubmitButton = BaseClass.readProperty("EmployeesSubmitButton", "application");
				driver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmployeeSubmitButton))).click();
				
				Webdriver.HandleALert();
				Webdriver.captureScreenshot("Before_Log_off");
				BaseClassImplement.checkRecordingRequired("Stop");
			}	
		}
		
	}
	
	public static void TestCaseDetails(HashMap<String, String> list) {
		Name = list.get("Name");
		Password = list.get("Password");
		ConfirmPassword = list.get("ConfirmPassword");
		TestCaseName = list.get("TestCaseName");
		
		BaseClassImplement.getTestCaseName(TestCaseName);
		BaseClassImplement.checkRecordingRequired("Start");
	}
}