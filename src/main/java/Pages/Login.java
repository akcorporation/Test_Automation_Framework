package Pages;

import java.time.Duration;
import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Runner.BaseClass;
import Runner.Webdriver;

public class Login{
	static WebDriver login_driver = Webdriver.initializeDriver("chrome");
	static WebDriverWait driver_wait = new WebDriverWait(login_driver, Duration.ofSeconds(20));

	public static void login() throws InterruptedException {
		
//		Webdriver.captureScreenshot("Launch_UI");
		String email_path = BaseClass.readProperty("Email_test_box","application").trim();
		String email_content = BaseClass.readProperty("user_email","global");
		driver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(email_path))).sendKeys(email_content);
//		WebElement text_email = login_driver.findElement(By.xpath(email_path));
//		text_email.sendKeys(email_content);
//		Webdriver.captureElementScreenshot(text_email,"Only_email");
		
		String pass_path = BaseClass.readProperty("Pass_test_box","application");
		String pass_content = BaseClass.readProperty("user_pass","global");
		// Decrypt Pass
		byte[] actualByte= Base64.getDecoder().decode(pass_content); 
		String actualString= new String(actualByte);
		
		driver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pass_path))).sendKeys(actualString);
//		WebElement text_pass = login_driver.findElement(By.xpath(pass_path));
//		text_pass.sendKeys(actualString);
//		Webdriver.captureElementScreenshot(text_pass,"Password_element");
//		
		Webdriver.captureScreenshot("Enter_Login_Details");
		
		String button_path = BaseClass.readProperty("Submit_button","application");
		driver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(button_path))).click();
//		WebElement button = login_driver.findElement(By.xpath(button_path));
//		button.click();
		Thread.sleep(1000);
	}
	
	public static void logout() {
		Webdriver.captureScreenshot("Before_Log_off");
		String Logout_text = BaseClass.readProperty("Log_out_test","application");
		driver_wait.until(ExpectedConditions.elementToBeClickable(login_driver.findElement(By.partialLinkText(Logout_text)))).click();
		Webdriver.captureScreenshot("After_Log_off");
		login_driver.close();
		login_driver.quit();
	}
}
