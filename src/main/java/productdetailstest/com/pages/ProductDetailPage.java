package productdetailstest.com.pages;

import org.openqa.selenium.*;
import org.testng.Assert;
import productdetailstest.com.base.BasePage;
import productdetailstest.com.utils.WaitUtil;

import java.util.List;

public class ProductDetailPage extends BasePage {

    private final By title = By.cssSelector("h1.pdp-name");
    private final By price = By.cssSelector("span.pdp-price strong, span.pdp-price");

    // --- Common close buttons (modal + banners) ---
    private final By anyCloseBtn = By.xpath(
            "(//button[@aria-label='Close'] | //button[contains(@class,'close')] | //span[normalize-space(.)='×'] | //span[normalize-space(.)='X'] | //div[@role='button' and (normalize-space(.)='×' or normalize-space(.)='X')])[1]"
    );

    // --- PDP Size buttons (multiple variants) ---
    private final By sizeButtons = By.xpath(
            "//div[contains(@class,'size-buttons') or contains(@class,'sizes')]//button" +
            " | //button[contains(@class,'size') and (contains(.,'S') or contains(.,'M') or contains(.,'L') or contains(.,'XL') or contains(.,'XXL'))]"
    );

    // --- Add to Bag / Add to Cart (multi-variant, text + role) ---
    private final By addToBagAny = By.xpath(
            "//button[contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADD TO BAG')]" +
            " | //button[contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADD TO CART')]" +
            " | //div[@role='button' and contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADD TO BAG')]" +
            " | //div[@role='button' and contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADD TO CART')]" +
            " | //span[contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADD TO BAG')]/ancestor::*[self::button or @role='button'][1]" +
            " | //span[contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADD TO CART')]/ancestor::*[self::button or @role='button'][1]"
    );

    private final By notifyMeAny = By.xpath(
            "//button[contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'NOTIFY ME')]" +
            " | //div[@role='button' and contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'NOTIFY ME')]"
    );

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void assertPdpOpened() {
        WaitUtil.urlContains(driver, "myntra.com", 20);
        Assert.assertTrue(WaitUtil.visible(driver, title, 25).isDisplayed(), "Product title not visible");
        Assert.assertTrue(WaitUtil.visible(driver, price, 25).isDisplayed(), "Price not visible");
    }

    public void closeOverlaysIfAny() {
        // Try closing up to 3 times (sometimes multiple overlays)
        for (int i = 0; i < 3; i++) {
            try {
                List<WebElement> close = driver.findElements(anyCloseBtn);
                if (!close.isEmpty() && close.get(0).isDisplayed()) {
                    close.get(0).click();
                    WaitUtil.sleep(600);
                } else {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
    }

    public void selectSizeIfPresent() {

        closeOverlaysIfAny();           //close method
        WaitUtil.scrollBy(driver, 450);
        WaitUtil.sleep(700);

        // Myntra size buttons
        By sizeBtns = By.cssSelector("button.size-buttons-size-button");

        List<WebElement> sizes = driver.findElements(sizeBtns);

        // DEBUG print
        //System.out.println("DEBUG: Size buttons found = " + sizes.size());
        for (int i = 0; i < Math.min(8, sizes.size()); i++) {
            WebElement b = sizes.get(i);
            System.out.println("DEBUG size[" + i + "] text=" + b.getText()
                    + " enabled=" + b.isEnabled()
                    + " class=" + b.getAttribute("class"));
        }

        if (sizes.isEmpty()) {
            // if product has no sizes, skip
            return;
        }

        // click first enabled size
        for (WebElement b : sizes) {
            String cls = String.valueOf(b.getAttribute("class")).toLowerCase();
            boolean disabled = cls.contains("disabled");

            if (b.isEnabled() && !disabled) {
                WaitUtil.jsScrollIntoView(driver, b);

                try {
                    // 1) normal click
                    b.click();
                } catch (Exception e1) {
                    try {
                        // 2) Actions click
                        WaitUtil.actionClick(driver, b);
                    } catch (Exception e2) {
                        // 3) JS click
                        try { WaitUtil.jsClick(driver, b); }
                        catch (Exception e3) {
                            // 4) Dispatch click (strongest)
                            WaitUtil.dispatchClick(driver, b);
                        }
                    }
                }

                WaitUtil.sleep(800);
                System.out.println("DEBUG: Size clicked = " + b.getText());
                return;
            }
        }

        throw new RuntimeException("Size present but none selectable (all disabled/out of stock).");
    }
    
    public void clickAddToBagOrFailWithDebug() {

        closeOverlaysIfAny();
        WaitUtil.scrollBy(driver, 700);
        WaitUtil.sleep(700);

        By addAny = By.xpath(
                "//*[self::button or self::div][contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADD TO BAG')]" +
                " | //*[@role='button' and contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADD TO BAG')]" +
                " | //div[contains(@class,'pdp-add-to-bag')] | //button[contains(@class,'pdp-add-to-bag')]"
        );

        List<WebElement> addCandidates = driver.findElements(addAny);

        //System.out.println("DEBUG: Add-to-bag candidates found = " + addCandidates.size());
        for (int i = 0; i < Math.min(5, addCandidates.size()); i++) {
            WebElement el = addCandidates.get(i);
            /*System.out.println("DEBUG add[" + i + "] tag=" + el.getTagName()
                    + " text=" + el.getText()
                    + " displayed=" + safeDisplayed(el)
                    + " class=" + el.getAttribute("class"));*/
        }

        WebElement target = null;
        for (WebElement el : addCandidates) {
            if (safeDisplayed(el)) { target = el; break; }
        }

        if (target == null) {
            throw new RuntimeException("ADD TO BAG not found/displayed. Check console DEBUG counts + Extent screenshot.");
        }

        WaitUtil.jsScrollIntoView(driver, target);

        try { target.click(); }
        catch (Exception e1) {
            try { WaitUtil.actionClick(driver, target); }
            catch (Exception e2) {
                try { WaitUtil.jsClick(driver, target); }
                catch (Exception e3) { WaitUtil.dispatchClick(driver, target); }
            }
        }

        WaitUtil.sleep(800);
    }

    private boolean safeDisplayed(WebElement el) {
        try { return el.isDisplayed(); } catch (Exception e) { return false; }
    }
}