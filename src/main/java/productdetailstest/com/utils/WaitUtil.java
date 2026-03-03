package productdetailstest.com.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class WaitUtil {

    public static WebElement visible(WebDriver driver, By by, int sec) {
        return new WebDriverWait(driver, Duration.ofSeconds(sec))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void urlContains(WebDriver driver, String partial, int sec) {
        new WebDriverWait(driver, Duration.ofSeconds(sec))
                .until(ExpectedConditions.urlContains(partial));
    }

    public static List<WebElement> presenceOfAll(WebDriver driver, By by, int sec) {
        return new WebDriverWait(driver, Duration.ofSeconds(sec))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public static void jsScrollIntoView(WebDriver driver, WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    public static void jsClick(WebDriver driver, WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public static void scrollBy(WebDriver driver, int px) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", px);
    }

    public static void actionClick(WebDriver driver, WebElement el) {
        new org.openqa.selenium.interactions.Actions(driver)
                .moveToElement(el)
                .pause(java.time.Duration.ofMillis(200))
                .click(el)
                .perform();
    }

    public static void dispatchClick(WebDriver driver, WebElement el) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('click',{bubbles:true,cancelable:true,view:window}));",
                el
        );
    }
    public static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}