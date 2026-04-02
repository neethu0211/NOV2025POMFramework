package com.opencart.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.opencart.qa.exceptions.BrowserException;
import com.opencart.qa.exceptions.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;

	public static String highlight;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	/**
	 * This method is used to initialise the driver on the basis of given browser
	 * name
	 * 
	 * @param browserName
	 * @return it returns driver value
	 */
	public WebDriver initDriver(Properties prop)// initialise driver
	{
		String browserName=prop.getProperty("browser");
		System.out.println("browsername :" + browserName);
		
		highlight=prop.getProperty("highlight");
		optionsManager=new OptionsManager(prop);
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;

		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;

		case "edge":
			tlDriver.set(driver = new EdgeDriver(optionsManager.getEdgeOptions()));
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;

		default:
			System.out.println("inavlid browser" + browserName);
			throw new BrowserException("----INVALID BROWSER----");

		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		
		/*driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.get(prop.getProperty("url"));*/
		
		return 	getDriver();
	}

	
	/**
	 * this will return one local copy of driver for a specific thread
	 * @return 
	 */
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	
	/**
	 * this method is used to initialize properties file 
	 * @return it return properties class  object which is having all properties(key-value )pair.
	 */
	public Properties initProperties() {
		FileInputStream ip = null;
		prop=new Properties();
		
		//mvn test -Denv="qa"
		//mvn clean install
		//mvn package
		//mvn deploy
		String envName=System.getProperty("env");
		System.out.println("Env name is "+envName);
		
		try
		{
			if(envName==null)
			{
				System.out.println("env name is null ");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			}
			else
			{
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
					
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
					
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
					
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
					
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
					
				default:
					System.out.println("******inavlid environment name*******");
					throw new FrameworkException("INVALID ENVIRONMENT NAME"+envName);
				}
		}}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		try
		{
			
			prop.load(ip);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		/*
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop; //return sll prop ie.key value pair in config.properies
		*/
	}
	
	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs((OutputType.FILE));// temp dir
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}


}
