package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.HomePage;
import com.pages.productPage;

public class searchProductTest extends BaseTest{
	HomePage hp;
	productPage pp;

    // TC_OLX_001
    @Test(description = "TC_OLX_001 - Search with valid keyword")
    public void searchWithValidKeyword() {
        hp = new HomePage(driver);

        // Perform search
        hp.search();
         

      
    }
 // TC_OLX_002
    @Test(description = "TC_OLX_002 - Apply location filter")
    public void applyLocation() {
        pp=new productPage(driver);
     //   hp.search();
        pp.selectLocation(); 

      
    }
    
	 
}
