package com.qa.crm2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.crm2.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {
	
	    //AAA -->UTs
		//TC -- only one hard assertion or can have multiple soft assertions
		
		@BeforeClass
		public void infoPageSetup() {
			accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}
		
		
		@Test()
		public void productHeaderTest() {
			searchResultsPage = accPage.doSearch("macbook");
			productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
			Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
		}
		
		
		

}
