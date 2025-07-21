package com.qa.crm2.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.crm2.exceptions.BrowserException;

public class DriverFactory {
	 
	WebDriver driver;
	Properties prop;
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		switch(browserName.toLowerCase().trim()) {
		
		case "chrome":
			driver = new ChromeDriver();
			
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
			
		case "edge":
			driver = new EdgeDriver();
			break;
		
		case "safari":
			driver = new SafariDriver();
			break;
			
			default:
				System.out.println("Please Pass the right browser..... " +browserName);
				throw new BrowserException("No browser Found.... " +browserName);
		}
		driver.manage().deleteAllCookies();
		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	public Properties initProp() {
		prop =new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;
	}

}
