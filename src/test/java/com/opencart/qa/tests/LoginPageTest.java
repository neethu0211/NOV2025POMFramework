package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.opencart.qa.base.BaseTest;
import com.opencart.qa.utils.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design login page for Open Cart Application")
@Story("Login User story  add login page feature with title , url, user login etc")
public class LoginPageTest extends BaseTest {
@Description("checking login page title")
@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageTitleTest() {
		ChainTestListener.log("starting loginPageTitleTest");
		Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE);
	}

@Description("checking login page url")
@Severity(SeverityLevel.CRITICAL)
	@Test
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl().contains(AppConstants.LOGIN_PAGE_URL));
	}
@Description("checking forgot password link exist on the login page")
@Severity(SeverityLevel.BLOCKER)	
@Issue("BUG 9013 forgot password link is missing on the login page")
	@Test
	public void forgotPwdLinkExistTest(){
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest() {
		homePage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		ChainTestListener.log("Home Page Title: " + homePage.getHomePageTitle());
		Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);
	}
}
