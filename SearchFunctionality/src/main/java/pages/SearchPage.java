package pages;

import base.BasePage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@placeholder='Search for products, brands and more']")
    WebElement searchBox;

    public void searchProduct(String product) {

        searchBox.clear();

        searchBox.sendKeys(product);

        searchBox.sendKeys(Keys.ENTER);
    }
}