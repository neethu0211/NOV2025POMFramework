package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class ResultsPage {
	// 1.initial driver and element util
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2.page class constructor
	public ResultsPage(WebDriver driver) {
				this.driver = driver;
				eleUtil = new ElementUtil(driver);
				
				}
	
	//3.private by locator page objects
		private final By  searchResults=By.xpath("//div[@class='product-thumb']");
		
		
		//4.public page action or methods
		public int getSearchResultsCount()
		{
			int resultsCount=eleUtil.waitForAllElementsPresence(searchResults, AppConstants.MEDIUM_TIME_OUT).size();
			System.out.println("Total no: of products after search: "+resultsCount);
			return resultsCount;
		}
		
		public ProductInfoPage selectProduct(String productName)
		{
			System.out.println("select product name:"+productName);
			eleUtil.doActionsClick(By.linkText(productName));
			return new ProductInfoPage(driver);
		}
}
