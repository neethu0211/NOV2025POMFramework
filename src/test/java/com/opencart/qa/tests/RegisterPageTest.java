package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;
import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.CsvUtil;
import com.opencart.qa.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registerPage=loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserRegTestData()
	{
		return new Object[][]
				{
					{"neethu", "c", "1234567890", "test@1", "yes"},
					{"niya", "y", "789123450", "testing@1", "no"},
					{"nilayu", "m", "1234569014", "tester@1", "yes"}
				};
	}
	@DataProvider
	public Object[][] getUserRegExcelTestData()
	{
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][]getUserRegCSVTestData()
	{
		return CsvUtil.csvData(AppConstants.REGISTER_SHEET_NAME);
	}
	@Test(dataProvider = "getUserRegCSVTestData")
	public void userRegisterTest(String firstName, String lastName,String phone,String password,String subscribe) 
	{
		//Assert.assertTrue(registerPage.userRegisteration("neethu", "c", "1234567890", "test@1", "yes"));
		Assert.assertTrue(registerPage.userRegisteration(firstName,lastName,phone,password,subscribe));
		
	}

}