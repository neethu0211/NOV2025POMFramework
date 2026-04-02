package com.opencart.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.opencart.qa.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{

	@BeforeClass
	public void productInfoSetup()
	{
		homePage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] 
				{
					{"macbook","MacBook Pro"},
					{"macbook","MacBook Air"},
					{"imac","iMac"},
					{"samsung","Samsung SyncMaster 941BW"},
					{"samsung","Samsung Galaxy Tab 10.1"},
					{"canon","Canon EOS 5D"}
					
				};
	}
	
	@Test(dataProvider ="getProductData" )
	public void productHeaderTest(String searchKey,String productName)
	{
		resultsPage=homePage.doSearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
		
	}
	@DataProvider
	public Object[][]  getProductImageData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3}
			
		};
	}
	
	@Test(dataProvider = "getProductImageData")
	public void productImagesCountTest(String searchKey, String productName,int imagesCount) {
		resultsPage=homePage.doSearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(),imagesCount);
	}
	
	@Test
	public void productInfoTest()
	{
		resultsPage=homePage.doSearch("macbook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoDataMap = productInfoPage.getProductInfoData();
		
		SoftAssert softAssert=new SoftAssert();
		

		softAssert.assertEquals(productInfoDataMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfoDataMap.get("imagescount"), "4");
		softAssert.assertEquals(productInfoDataMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoDataMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoDataMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoDataMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productInfoDataMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productInfoDataMap.get("extaxprice"), "$2,000.00");
		
		softAssert.assertAll();
		/*
		Assert.assertEquals(productInfoDataMap.get("productname"), "MacBook Pro");
		Assert.assertEquals(productInfoDataMap.get("imagescount"), "4");
		Assert.assertEquals(productInfoDataMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productInfoDataMap.get("Reward Points"), "800");
		Assert.assertEquals(productInfoDataMap.get("Availability"), "Out Of Stock");
		Assert.assertEquals(productInfoDataMap.get("productprice"), "$2,000.00");
		Assert.assertEquals(productInfoDataMap.get("extaxprice"), "$2,000.00");*/
		
		
	}
}
