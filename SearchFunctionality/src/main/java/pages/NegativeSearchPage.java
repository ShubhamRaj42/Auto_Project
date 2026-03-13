package pages;

import base.BasePage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class NegativeSearchPage extends BasePage {

    public NegativeSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@placeholder='Search for products, brands and more']")
    WebElement searchBox;

    public void invalidSearch(String text) {

        searchBox.clear();

        searchBox.sendKeys(text);

        searchBox.sendKeys(Keys.ENTER);
    }
}