package com.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import com.drivers.DriverFactory;
import com.pages.HomePage;
import com.utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;
    HomePage hp;

    @BeforeClass
    public void setup() throws IOException {

        ConfigReader.loadConfig();

        driver = DriverFactory.initDriver(
                ConfigReader.getProperty("browser"));

        driver.manage().window().maximize();

        driver.get(ConfigReader.getProperty("url"));
    }
    
    

 /*   @AfterMethod
    public void tearDown(){

        if(driver!=null)
            driver.quit();
    }*/
   
    
}