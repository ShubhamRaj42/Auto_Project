package NavbarTesting.com.pages;

import org.testng.Reporter;
import org.testng.annotations.Test;

import NavbarTesting.com.base.BaseTest;

/*
 * Test Class: NavbarNavigationTest
 * Purpose   : Validate Myntra navbar navigation by clicking
 *             each top-level menu item one by one.
 * Tooling   : Selenium + Java + TestNG
 */
public class NavbarNavigationTest extends BaseTest {

	/**
	 * Test Case: Verify that all navbar items are clickable without errors
	 */
	@Test
	public void navbarNavigationTest() throws InterruptedException {

		test = extent.createTest("Navbar Navigation Test").assignCategory("Navbar");
		// Initialize Navbar Page Object
		NavbarPage navbar = new NavbarPage(driver);

		// -------- MEN --------
		Reporter.log("[PASS] Clicking MEN navbar", true);
		test.info("STEP 1: Clicking MEN navbar");
		navbar.clickMen();
		test.pass("PASS: MEN navbar clicked successfully");
		// Thread.sleep(2000);

		// -------- WOMEN --------
		Reporter.log("[PASS] Clicking WOMEN navbar", true);
		test.info("STEP 2: Clicking WOMEN navbar");
		navbar.clickWomen();
		test.pass("PASS: WOMEN navbar clicked successfully");
		// Thread.sleep(2000);

		// -------- KIDS --------
		Reporter.log("[PASS] Clicking KIDS navbar", true);
		test.info("STEP 3: Clicking KIDS navbar");
		navbar.clickKids();
		test.pass("PASS: KIDS navbar clicked successfully");
		// Thread.sleep(2000);

		// -------- HOME --------
		Reporter.log("[PASS] Clicking HOME navbar", true);
		test.info("STEP 4: Clicking HOME navbar");
		navbar.clickHome();
		test.pass("PASS: HOME navbar clicked successfully");
		// Thread.sleep(2000);

		// -------- BEAUTY --------
		Reporter.log("[PASS] Clicking BEAUTY navbar", true);
		test.info("STEP 5: Clicking BEAUTY navbar");
		navbar.clickBeauty();
		test.pass("PASS: BEAUTY navbar clicked successfully");
		// Thread.sleep(2000);

		// -------- GENZ --------
		Reporter.log("[PASS] Clicking GENZ navbar", true);
		test.info("STEP 6: Clicking GENZ navbar");
		navbar.clickGenz();
		test.pass("PASS: GENZ navbar clicked successfully");
		// Thread.sleep(2000);

		// -------- STUDIO --------
		Reporter.log("[PASS] Clicking STUDIO navbar", true);
		test.info("STEP 7: Clicking STUDIO navbar");
		navbar.clickStudio();
		test.pass("PASS: STUDIO navbar clicked successfully");
		// Thread.sleep(2000);

		// -------- WISHLIST --------
		Reporter.log("[PASS] Clicking WISHLIST icon", true);
		test.info("STEP 8: Clicking WISHLIST icon");
		navbar.clickWishlist();
		test.pass("PASS: WISHLIST icon clicked successfully");
		// Thread.sleep(2000);

		// -------- FINAL STATUS --------
		Reporter.log("[PASS] Navbar navigation test completed successfully ", true);
	}
}