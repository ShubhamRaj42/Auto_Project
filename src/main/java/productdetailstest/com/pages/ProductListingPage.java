package productdetailstest.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import productdetailstest.com.base.BasePage;
import productdetailstest.com.utils.WaitUtil;

import java.util.ArrayList;

public class ProductListingPage extends BasePage {

    private final By firstProduct = By.cssSelector("li.product-base a");

    public ProductListingPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailPage openFirstProduct() {
        String parent = driver.getWindowHandle();

        WaitUtil.visible(driver, firstProduct, 25).click();

        // if new tab opened, switch
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            for (String t : tabs) {
                if (!t.equals(parent)) {
                    driver.switchTo().window(t);
                    break;
                }
            }
        }
        return new ProductDetailPage(driver);
    }
}