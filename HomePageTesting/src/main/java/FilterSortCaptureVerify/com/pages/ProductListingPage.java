package FilterSortCaptureVerify.com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import FilterSortCaptureVerify.com.utils.WaitUtil;

public class ProductListingPage {

	private WebDriver driver;

	public ProductListingPage(WebDriver driver) {
		this.driver = driver;
	}

	// Sort locators
	private By sortDropdown = By.xpath("//div[contains(@class,'sort-sortBy')]");
	private By lowToHigh = By.xpath("//label[normalize-space()='Price: Low to High']");
	private By highToLow = By.xpath("//label[normalize-space()='Price: High to Low']");
	private By productCards = By.xpath("//li[contains(@class,'product-base')]");

	public void sortLowToHigh() {
		WaitUtil.waitForClick(driver, sortDropdown).click();
		WaitUtil.waitForClick(driver, lowToHigh).click();
	}

	public void sortHighToLow() {
		WaitUtil.waitForClick(driver, sortDropdown).click();
		WaitUtil.waitForClick(driver, highToLow).click();
	}

	public void waitForPriceChange(List<Integer> oldPrices) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(d -> {
			List<Integer> newPrices = getFirstNVisibleProductPrices(5);
			return !newPrices.equals(oldPrices);
		});
	}

	public void scrollUntilPricesChange(List<Integer> oldPrices) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(d -> {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			List<Integer> newPrices = getFirstNVisibleProductPrices(5);
			return !newPrices.equals(oldPrices);
		});
	}
	
	public List<Integer> getFirstNVisibleProductPrices(int count) {

	    List<Integer> prices = new ArrayList<>();

	    try {

	        List<WebElement> products = driver.findElements(productCards);

	        for (WebElement product : products) {

	            if (!product.isDisplayed())
	                continue;

	            try {

	                String priceText;

	                List<WebElement> discounted =
	                        product.findElements(By.xpath(".//span[contains(@class,'product-discountedPrice')]"));

	                if (!discounted.isEmpty()) {
	                    priceText = discounted.get(0).getText();
	                } else {
	                    priceText = product.findElement(
	                            By.xpath(".//div[contains(@class,'product-price')]/span"))
	                            .getText();
	                }

	                priceText = priceText.replace("₹", "")
	                                     .replace("Rs.", "")
	                                     .replace(",", "")
	                                     .trim();

	                prices.add(Integer.parseInt(priceText));

	                if (prices.size() == count)
	                    break;

	            } catch (Exception e) {
	                continue;
	            }
	        }

	    } catch (org.openqa.selenium.StaleElementReferenceException e) {

	        // retry once if DOM refreshed
	        return getFirstNVisibleProductPrices(count);
	    }

	    return prices;
	}

	public void waitUntilPricesAreSortedDescending() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(d -> {
			List<Integer> prices = getFirstNVisibleProductPrices(5);

			if (prices.size() < 2) {
				return false;
			}

			for (int i = 0; i < prices.size() - 1; i++) {
				if (prices.get(i) < prices.get(i + 1)) {
					return false;
				}
			}

			return true;
		});
	}

	public void waitUntilPricesAreSortedAscending() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(d -> {
			List<Integer> prices = getFirstNVisibleProductPrices(5);

			// Must have at least 2 prices to compare
			if (prices.size() < 2) {
				return false;
			}

			// Compare adjacent elements safely
			for (int i = 0; i < prices.size() - 1; i++) {
				if (prices.get(i) > prices.get(i + 1)) {
					return false; // Not sorted yet
				}
			}

			return true; // Fully sorted
		});
	}

	public void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	}

}