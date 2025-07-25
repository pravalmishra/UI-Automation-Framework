package com.qa.crm2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.crm2.base.BaseTest;

public class SearchResultsTest extends BaseTest {
	
	@BeforeClass
	public void searchResultsSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	
	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2}
		};
	}
	

	@Test(dataProvider = "getProductCountData")
	public void searchResultsCountTest(String searchKey, int productCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchProductCount(), productCount);
	}
	
	@Test
	public void searchResultsTest() {
		searchResultsPage = accPage.doSearch("macbook");
		Assert.assertEquals(searchResultsPage.getSearchProductCount(), 3);
	}

}
