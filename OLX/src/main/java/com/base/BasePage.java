package com.base;

import org.openqa.selenium.WebDriver;

import com.utils.WaitUtils;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver); // ✅ initialize WaitUtils
    }
}
