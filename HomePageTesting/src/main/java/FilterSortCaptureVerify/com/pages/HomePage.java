package FilterSortCaptureVerify.com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FilterSortCaptureVerify.com.utils.WaitUtil;

public class HomePage {

	private WebDriver driver;
	private WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	private By menMenu = By.xpath("//a[@href='/shop/men']");
	private By tshirts = By.xpath("//a[@href='/men-tshirts']");

	/**
	 * Navigate to Men → T-Shirts
	 */
	public void goToMensTshirts() {
		// STEP 1: Hover on MEN menu
		// STEP 1: Hover on MEN menu (wait until visible)
		Actions actions = new Actions(driver);
		actions.moveToElement(WaitUtil.waitForVisibility(driver, menMenu)).perform();

		// STEP 2: Wait until T-Shirts is clickable
		WaitUtil.waitForClick(driver, tshirts).click();
	}
}
