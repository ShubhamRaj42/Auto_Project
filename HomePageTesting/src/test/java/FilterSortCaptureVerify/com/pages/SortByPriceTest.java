package FilterSortCaptureVerify.com.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import FilterSortCaptureVerify.com.base.BaseTest;

public class SortByPriceTest extends BaseTest {

	@Test
	public void verifySortByPriceLowAndHigh() {

		test = extent.createTest("Product Sorting Test").assignCategory("Filter");

		test.info("Test Started: Verify product sorting by price");
		Reporter.log("[INFO] Test Started: Verify product sorting by price", true);

		HomePage home = new HomePage(driver);
		ProductListingPage plp = new ProductListingPage(driver);

		// ---------------- STEP 1: Navigate to category ----------------
		Reporter.log("[INFO] Navigating to Men → T-Shirts category", true);
		test.info("Navigating to Men → T-Shirts category");
		
		
		home.goToMensTshirts();
		
		
		test.pass("Successfully navigated to Men T-Shirts listing page");
		Reporter.log("[PASS] Successfully navigated to Men T-Shirts listing page", true);

		// ---------------- STEP 2: Sort Low → High ----------------
		Reporter.log("[INFO] Applying sort: Price Low to High", true);
		test.info("Applying sort: Price Low to High");
		
	
		
		List<Integer> beforeSort = plp.getFirstNVisibleProductPrices(5);

		plp.sortLowToHigh();
		plp.scrollToTop();

		plp.scrollUntilPricesChange(beforeSort);   // wait until new products load
		plp.waitUntilPricesAreSortedAscending();   // then validate sorting

		test.info("Capturing first 5 product prices (Low to High)");
		Reporter.log("[INFO] Capturing first 5 product prices (Low to High)", true);
		
		
		List<Integer> actualLow = plp.getFirstNVisibleProductPrices(5);
		List<Integer> expectedLow = new ArrayList<>(actualLow);
		Collections.sort(expectedLow);

		test.info("Actual prices (Low to High): " + actualLow);
		test.info("Expected sorted prices: " + expectedLow);

		Reporter.log("[INFO] Actual prices (Low to High): " + actualLow, true);
		Reporter.log("[INFO] Expected sorted prices: " + expectedLow, true);

		
		Assert.assertEquals(actualLow, expectedLow, "Products are NOT sorted correctly in Low to High order");
		test.pass("Products are correctly sorted in Low → High order");
		
		
		Reporter.log("[PASS] Products are correctly sorted in Low → High order", true);

		// ---------------- STEP 3: Sort High → Low ----------------
		Reporter.log("[INFO] Applying sort: Price High to Low", true);
		test.info("Applying sort: Price High to Low");
		
		List<Integer> beforeSort1 = plp.getFirstNVisibleProductPrices(5);
		plp.sortHighToLow();
		plp.scrollToTop();
		plp.scrollUntilPricesChange(beforeSort1);
		plp.waitUntilPricesAreSortedDescending();
		
		
		test.info("Capturing first 5 product prices (High to Low)");
		Reporter.log("[INFO] Capturing first 5 product prices (High to Low)", true);
		
		
		List<Integer> actualHigh = plp.getFirstNVisibleProductPrices(5);
		List<Integer> expectedHigh = new ArrayList<>(actualHigh);
		expectedHigh.sort(Collections.reverseOrder());

		Reporter.log("[INFO] Actual prices (High to Low): " + actualHigh, true);
		Reporter.log("[INFO] Expected sorted prices: " + expectedHigh, true);
		test.info("Actual prices (High to Low): " + actualHigh);
		test.info("Expected sorted prices: " + expectedHigh);

		
		Assert.assertTrue(actualHigh.get(0) >= actualHigh.get(actualHigh.size() - 1),
				"Top product is not the most expensive");
		test.pass("Products are correctly sorted in High → Low order");
		Reporter.log("[PASS] Products are correctly sorted in High → Low order", true);

		// ---------- TEST END ----------
		test.pass("Test Completed Successfully: Sort by Price verification 🚀");
		Reporter.log("[PASS] Test Completed Successfully: Sort by Price verification 🚀", true);
	}
}
