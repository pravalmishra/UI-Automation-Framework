package com.qa.crm2.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.crm2.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {
	
	    //AAA --> UTs Arrange Act Assert
		//TC  --> only one hard assertion or can have multiple soft assertions
		
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
		
		@Test()
		public void productImagesCountTest() {
			searchResultsPage = accPage.doSearch("macbook");
			productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
			Assert.assertEquals(productInfoPage.getProductImagesCount(), 4);
		}
		
		@Test
		public void productInfoTest() {
			searchResultsPage = accPage.doSearch("macbook");
			productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
			Map<String, String> productActDetailsMap = productInfoPage.getProductDetailsMap();
			softAssert.assertEquals(productActDetailsMap.get("Brand"), "Apple");
			softAssert.assertEquals(productActDetailsMap.get("Product Code"), "Product 18");
			softAssert.assertEquals(productActDetailsMap.get("Availability"), "In Stock");
			softAssert.assertEquals(productActDetailsMap.get("productprice"), "$2,000.00");
			softAssert.assertEquals(productActDetailsMap.get("extaxprice"), "$2,000.00");
			softAssert.assertAll();
			
		}
		
		
		

}
