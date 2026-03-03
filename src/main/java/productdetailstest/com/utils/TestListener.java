package productdetailstest.com.utils;

import com.aventstack.extentreports.*;
import org.openqa.selenium.*;
import org.testng.*;

import java.io.File;
import java.nio.file.Files;

public class TestListener implements ITestListener {
    private static final ExtentReports extent = ExtentManager.getExtent();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        try {
            Object obj = result.getInstance();
            WebDriver driver = (WebDriver) obj.getClass().getDeclaredField("driver").get(obj);

            String path = takeScreenshot(driver, result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(path);
        } catch (Exception ignored) {}
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String takeScreenshot(WebDriver driver, String name) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = "test-output/screenshots/" + name + ".png";
        File d = new File(dest);
        d.getParentFile().mkdirs();
        Files.copy(src.toPath(), d.toPath());
        return dest;
    }
}