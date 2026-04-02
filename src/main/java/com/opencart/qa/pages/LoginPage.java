package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	// 1.initial driver and element util
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2.page class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	
	}
	
	//3.private by locator page objects
			private final By emailId=By.id("input-email");
			private final By passwordId=By.id("input-password");
			private final By loginBtn=By.xpath("//input[@type='submit']");
			private final By forgotPwdLink=By.linkText("Forgotten Password");
			private final By registrationLink=By.linkText("Register");
			
	//4.public page action or methods
			@Step("getting login page title...")
			public String getLoginPageTitle()
			{
				String actualTitle=eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
				System.out.println("Login Page Title:"+actualTitle);
				//Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
				return actualTitle;
			}
			
			@Step("getting login page url")
			public String getLoginPageUrl()
			{
				String actualUrl=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL, AppConstants.SHORT_TIME_OUT);
				System.out.println("Login Page URL: "+actualUrl);
				return actualUrl;
			}
			
			@Step("checking forgot password exist on the login page")
			
			public boolean isForgotPwdLinkExist()
			{
				return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
			}
			
			@Step("user is logge in with user name {0} and password {1}")
			public HomePage doLogin(String username, String password)
			{// private locators are used in public method name ie encapsulation ie no one can change locators outside the class 
				System.out.println("App credentials are "+username+password);
				eleUtil.doSendKeys(emailId, username, AppConstants.SHORT_TIME_OUT);
				eleUtil.doSendKeys(passwordId, password);
				eleUtil.doClick(loginBtn);
				
				return new HomePage(driver);//Page chaining
				//return driver.getTitle();
			}
			
			@Step("navigating to the registration page")
			public RegisterPage navigateToRegisterPage()
			{
				eleUtil.waitForElementReadyAndClick(registrationLink,AppConstants.SHORT_TIME_OUT);
				return new RegisterPage(driver);
			}
}
