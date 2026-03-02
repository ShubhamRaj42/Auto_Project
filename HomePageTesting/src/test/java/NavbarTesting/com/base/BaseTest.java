package NavbarTesting.com.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import NavbarTesting.com.config.ConfigReader;
import utils.ExtentManager;

public class BaseTest extends BasePage {
	protected static ExtentReports extent;
	protected ExtentTest test;

	@BeforeSuite
	public void startReport() {
		extent = ExtentManager.getExtentReport("Navbar_Report");
	}

	@BeforeMethod
	public void setUp() {
		launchBrowser(ConfigReader.getBaseUrl());

	}

	@AfterMethod
	public void tearDown() {
		closeBrowser();
	}

	@AfterSuite
	public void generateReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}
