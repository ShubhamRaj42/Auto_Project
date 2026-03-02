package FilterSortCaptureVerify.com.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import FilterSortCaptureVerify.com.config.ConfigReader;
import FilterSortCaptureVerify.com.utils.DriverFactory;
import utils.ExtentManager;

public class BaseTest extends BasePage {
	
	protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManager.getExtentReport("FilterSort_Report");
    }

	@BeforeMethod
	public void setup() {
		driver = DriverFactory.initDriver();
		driver.get(ConfigReader.getBaseUrl());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

    @AfterSuite
    public void generateReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
