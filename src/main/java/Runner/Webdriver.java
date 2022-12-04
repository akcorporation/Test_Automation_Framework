package Runner;

import java.io.File;
import java.io.FileNotFoundException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.FileUtils;

public class Webdriver {
	public static WebDriver driver;
	
	public static WebDriver initializeDriver(String browser) {
		if(browser.equalsIgnoreCase("Chrome")) {
		driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			}
		else {
			System.out.println("Browser not supported");
			return null;
		}
		driver.get(BaseClass.readProperty("applicationUrl","global"));
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void captureScreenshot(String FileName) {
		TakesScreenshot srcShot = ((TakesScreenshot)driver);
		File srcFile= srcShot.getScreenshotAs(OutputType.FILE);	
		try {
			FileUtils.copyInEvidenceFolder(srcFile, FileName);
		} catch (FileNotFoundException e) {
			System.out.println("Getting FileNotFound Exception: "+ e.getMessage());
		}
	}
	
	public static void captureElementScreenshot(WebElement element,String element_Name) {
		TakesScreenshot srcShot = ((TakesScreenshot)element);
		File srcFile= srcShot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyInEvidenceFolder(srcFile, element_Name);
		} catch (FileNotFoundException e) {
			System.out.println("Getting FileNotFound Exception: "+ e.getMessage());
		}
	}

	
}
