package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.BasePage;

public class HomePage extends BasePage {
	
	By searchBox=By.xpath("//input[@type='text']");
	By searchBtn=By.xpath("//div[@class='_3wPu_']//span");

	
	 public HomePage(WebDriver driver) {
	        super(driver);
	    }
	 
	 public void search() {
		  waitUtils.waitForVisibility(searchBox).sendKeys("Used Mobile Phone");
		  waitUtils.waitForClickable(searchBtn).click();

	 }
}
