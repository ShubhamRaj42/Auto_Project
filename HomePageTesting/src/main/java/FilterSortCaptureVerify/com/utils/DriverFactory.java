package FilterSortCaptureVerify.com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static WebDriver driver;
	
	
    public static WebDriver initDriver() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();   //  assign to static variable
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
