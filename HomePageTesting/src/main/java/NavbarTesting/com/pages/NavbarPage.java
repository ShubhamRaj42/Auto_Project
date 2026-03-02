package NavbarTesting.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavbarPage {

	private WebDriver driver;

	public NavbarPage(WebDriver driver) {
		this.driver = driver;
	}

	// Navbar Locators
	private By men = By.xpath("//a[normalize-space()='Men']");
	private By women = By.xpath("//a[normalize-space()='Women']");
	private By kids = By.xpath("//a[normalize-space()='Kids']");
	private By home = By.xpath("//a[normalize-space()='Home']");
	private By beauty = By.xpath("//a[normalize-space()='Beauty']");
	private By genz = By.xpath("//a[normalize-space()='Genz']");
	private By studio = By.xpath("//a[normalize-space()='Studio']");
	private By wishlist = By.xpath("//span[contains(@class,'desktop-iconWishlist')]");

	// Actions
	public void clickMen() {
		driver.findElement(men).click();
	}

	public void clickWomen() {
		driver.findElement(women).click();
	}

	public void clickKids() {
		driver.findElement(kids).click();
	}

	public void clickHome() {
		driver.findElement(home).click();
	}

	public void clickBeauty() {
		driver.findElement(beauty).click();
	}

	public void clickGenz() {
		driver.findElement(genz).click();
	}

	public void clickStudio() {
		driver.findElement(studio).click();
	}

	public void clickWishlist() {
		driver.findElement(wishlist).click();
	}
}