package com.opencart.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class HomePage {
	// 1.initial driver and element util
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2.page class constructor
	public HomePage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
			
			}

	//3.private by locator page objects
	/*private final By  logoutLink=By.linkText("Logout");
	private final By header=By.xpath("//div[@id='content']/h2");
	private final By searchTextField=By.name("search");
	private final By searchIcon=By.xpath("//div[@id='search']//button");*/
	
	private final By logoutLink = By.linkText("Logout");
	private final By header = By.cssSelector("div#content h2");
	private final By searchTextField = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");
	
	
	//4.public page action or methods
	
	public String getHomePageTitle()
	{
		String actualTitle=eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Homen Page Title:"+actualTitle);
		//Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
		return actualTitle;
	}
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.isElementDisplayed(logoutLink);
	}
	
	public List<String> getHomePageHeader()
	{
		List<WebElement> headersList = eleUtil.waitForAllElementsPresence(header, AppConstants.SHORT_TIME_OUT);
		List<String>  headerValueList=new ArrayList<String>();
		for(WebElement e:headersList)
		{
			String text=e.getText();
			headerValueList.add(text);
			//System.out.println(text);
		}
		return headerValueList;
	}
	
	public ResultsPage doSearch(String searchKey) {
		System.out.println("search key: "+searchKey);
		eleUtil.doSendKeys(searchTextField, searchKey, AppConstants.SHORT_TIME_OUT);
		eleUtil.doActionsClick(searchIcon);
		return new ResultsPage(driver);
	}
}