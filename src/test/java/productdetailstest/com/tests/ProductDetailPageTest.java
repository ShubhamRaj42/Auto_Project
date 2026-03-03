package productdetailstest.com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import productdetailstest.com.config.ConfigReader;
import productdetailstest.com.pages.HomePage;
import productdetailstest.com.pages.ProductDetailPage;
import productdetailstest.com.pages.ProductListingPage;
import productdetailstest.com.utils.DriverFactory;

public class ProductDetailPageTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        DriverFactory.initDriver(ConfigReader.getBoolean("headless"));
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @Test
    public void productDetailPageFlow() {
        HomePage home = new HomePage(driver);

        //keyword for size products
        ProductListingPage listing = home.search("men tshirt");

        ProductDetailPage pdp = listing.openFirstProduct();

        pdp.assertPdpOpened();
        pdp.closeOverlaysIfAny();
        pdp.selectSizeIfPresent();
        pdp.clickAddToBagOrFailWithDebug();
       /* pdp.assertBrandAndRatingIfPresent();
        pdp.assertMrpDiscountIfPresent();

        pdp.closeSizeChartIfOpen();
        pdp.selectSizeMandatoryOrFail();

        pdp.clickAddToBag();

        pdp.clickWishlistIfPresent();
        pdp.assertDescriptionIfPresent();
        pdp.assertProductCodeAndSellerIfPresent();*/
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}