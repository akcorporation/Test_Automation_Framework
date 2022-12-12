package Runner;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExcelUtils;
import Utilities.FileUtils;

public class Webdriver {
	public static WebDriver driver;
	
	public static WebDriver initializeDriver(String browser) {
		if(browser.equalsIgnoreCase("Chrome")) {
//			ChromeOptions option = new ChromeOptions();
//			option.addArguments("headless");
//		driver = new ChromeDriver(option);
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
	
	public static WebDriver getDriver() {
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
	
	public static void HandleALert() {
		WebDriverWait driver_wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		@SuppressWarnings("unused")
		Alert alert_box = driver_wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		captureWholeScreenshot("Handle_alert");
		System.out.println("Alert Message: " + alert.getText());
		alert.accept();
	}
	
	public static void captureWholeScreenshot(String FileName) {
		
		BufferedImage image = null;
			try {
				Thread.sleep(500);
				image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			} catch (HeadlessException | AWTException e) {
				System.out.println("Getting HeadlessException or AWTException while capturing screenshot  "+ e.getMessage());
			} catch (InterruptedException e) {
				System.out.println("Getting InterruptedException In thread sleep  "+ e.getMessage());
			}
		    try {
		    	String Name = BaseClassImplement.getEvidencePath() +"\\"+ FileName + ".png";
				ImageIO.write(image, "png", new File(Name));
				ExcelUtils.CaptureExcelEvidence(image);
			} catch (IOException e) {
				System.out.println("Getting IO Exception while writing whole screenshot image in Evidence folder "+ e.getMessage());
			}
		}
}
