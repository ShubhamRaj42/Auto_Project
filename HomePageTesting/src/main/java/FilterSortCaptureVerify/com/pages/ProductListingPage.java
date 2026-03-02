package FilterSortCaptureVerify.com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
	//private By sortLabel = By.xpath("//span[contains(@class,'sort-selected')]");
	// Price locator (generic – derived from single product)
	private By productPrices = By
			.xpath("//li[contains(@class,'product-base')]//span[contains(@class,'product-discountedPrice')]");

	public void sortLowToHigh() {
		WaitUtil.waitForClick(driver, sortDropdown);
		driver.findElement(sortDropdown).click();
		driver.findElement(lowToHigh).click();
	}

	public void sortHighToLow() {
		WaitUtil.waitForClick(driver, sortDropdown);
		driver.findElement(sortDropdown).click();
		driver.findElement(highToLow).click();
	}

	/**
	 * Capture first N product prices
	 */
	public List<Integer> getFirstNVisibleProductPrices(int count) {

		List<Integer> prices = new ArrayList<>();

		List<WebElement> products = driver.findElements(By.xpath("//li[contains(@class,'product-base')]"));

		int collected = 0;

		for (WebElement product : products) {

			// Skip invisible / recycled cards
			if (!product.isDisplayed())
				continue;

			try {
				WebElement priceElement = product
						.findElement(By.xpath(".//span[contains(@class,'product-discountedPrice')]"));

				String priceText = priceElement.getText().replace("Rs.", "").trim();

				prices.add(Integer.parseInt(priceText));
				collected++;

				if (collected == count)
					break;

			} catch (Exception e) {
				// Product without discounted price → skip
				continue;
			}
		}
		return prices;
	}

	public void waitUntilPricesAreSortedDescending() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(d -> {
			List<Integer> prices = getFirstNVisibleProductPrices(2);
			return prices.size() == 2 && prices.get(0) >= prices.get(1);
		});
	}

	public void waitUntilPricesAreSortedAscending() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(driver -> {
			List<WebElement> prices = driver.findElements(productPrices);

			if (prices.size() < 2)
				return false;

			int first = Integer.parseInt(prices.get(0).getText().replace("Rs.", "").trim());
			int second = Integer.parseInt(prices.get(1).getText().replace("Rs.", "").trim());

			// Key condition: first price must be <= second price
			return first <= second;
		});
	}
}