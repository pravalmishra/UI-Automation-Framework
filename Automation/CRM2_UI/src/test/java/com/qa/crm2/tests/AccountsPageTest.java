package com.qa.crm2.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.crm2.base.BaseTest;
import com.qa.crm2.constants.AppConstants;
import com.qa.crm2.pages.AccountsPage;



public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		 accPage =loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void isMyAccountLinkExistTest() {
		Assert.assertTrue(accPage.myAccountLinkExist());
	}

	@Test
	public void accPageHeadersTest() {
		List<String> actHeadersList = accPage.getAccountsPageHeadersList();
		System.out.println(actHeadersList);
	}

	@Test
	public void searchTest() {
		accPage.doSearch("macbook");
	}


	
}
