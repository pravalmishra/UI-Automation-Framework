package com.qa.crm2.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.crm2.factory.DriverFactory;
import com.qa.crm2.pages.AccountsPage;
import com.qa.crm2.pages.LoginPage;
import com.qa.crm2.pages.ProductInfoPage;
import com.qa.crm2.pages.SearchResultsPage;

public class BaseTest {
	
	WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginpage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	
	@BeforeTest
	public void setup(){
		df = new DriverFactory();
	  prop = df.initProp();
    driver = df.initDriver(prop);
    loginpage = new LoginPage(driver);
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
