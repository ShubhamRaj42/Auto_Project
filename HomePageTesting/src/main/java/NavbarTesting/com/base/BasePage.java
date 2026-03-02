package NavbarTesting.com.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;


import NavbarTesting.com.utils.DriverFactory;



public class BasePage {

	protected WebDriver driver;

	

	public void launchBrowser(String url) {
		driver = DriverFactory.initDriver();
		driver.get(url);

		// Explicit Wait -> declaration
		//WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void closeBrowser() {
		DriverFactory.quitDriver();
	}
}
