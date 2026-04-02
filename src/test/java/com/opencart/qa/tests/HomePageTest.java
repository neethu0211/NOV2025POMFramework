package com.opencart.qa.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.utils.AppConstants;

public class HomePageTest extends BaseTest {

	HomePage homePage;
	
	@BeforeClass
	public void homePageSetup()
	{
		homePage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		 //homePage = new HomePage(driver);
	}
	@Test
	public void homePageTitleTest() {
//		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
//		HomePage homePage = new HomePage(driver);
		String homePageActualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(homePageActualTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test
	public void logoutLinkExistTest() {
//		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
//		HomePage homePage = new HomePage(driver);
		
		Assert.assertTrue(homePage.isLogoutLinkExist());

	}
	
	@Test
	public void headersTest() {
		List<String> actHeaderLiist=homePage.getHomePageHeader();
		Assert.assertEquals(actHeaderLiist, AppConstants.EXPECTED_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getSearchTestData()
	{
			return new Object[][] {
				{"macbook",3},
				{"imac",1},
				{"canon",1},
				{"samsung",2},
				{"airtel",0}
		};
	}
	
	@Test(dataProvider = "getSearchTestData")
	public void searchTest(String searchKey,int expectedResultsCount) {
		resultsPage= homePage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(),expectedResultsCount);
	}
}
