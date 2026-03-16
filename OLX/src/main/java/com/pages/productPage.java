package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.BasePage;

public class productPage extends BasePage {
	By locationFilter = By.xpath("//span[text()='Delhi']");
	public productPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void selectLocation() {
		waitUtils.waitForPresence(locationFilter).click();
	}

}
