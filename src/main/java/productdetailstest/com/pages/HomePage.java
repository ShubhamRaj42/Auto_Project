package productdetailstest.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import productdetailstest.com.base.BasePage;
import productdetailstest.com.utils.WaitUtil;

public class HomePage extends BasePage {

    private final By searchInput = By.cssSelector("input[placeholder='Search for products, brands and more']");
    private final By searchIcon  = By.cssSelector("a.desktop-submit");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProductListingPage search(String keyword) {
        WaitUtil.visible(driver, searchInput, 20).sendKeys(keyword);
        WaitUtil.visible(driver, searchIcon, 10).click();
        return new ProductListingPage(driver);
    }
}