package com.qa.crm2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.crm2.base.BaseTest;
import com.qa.crm2.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String accTitle = loginpage.getLoginPageTitle();
		//Assert.assertEquals(accTitle, "Account Login");
		Assert.assertEquals(accTitle, AppConstants.LOGIN_PAGE_TITLE);

	}

	@Test
	public void loginPageUrlTest() {
		String accUrl = loginpage.getLoginPageURL();
		Assert.assertTrue(accUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));

	}

	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}

	@Test
	public void loginTest() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}

}
