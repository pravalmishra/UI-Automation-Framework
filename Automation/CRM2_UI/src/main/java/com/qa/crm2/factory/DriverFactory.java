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
import com.qa.crm2.errors.AppError;
import com.qa.crm2.exceptions.FrameworkException;

public class DriverFactory {
	 
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		//String browserName = System.getProperty("browser"); /If pass from command line
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		switch(browserName.toLowerCase().trim()) {
		
		case "chrome":
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			
			break;
		case "firefox":
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
			
		case "edge":
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
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
		// envName = qa, stage , prod ,uat, dev
		// mvn clean install -Denv="qa"
        // ls -alt
		// mvn --version
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("Running tests on Env : " + envName);

		try {
			if (envName == null) {
				System.out.println("No env is given....hence running it on QA env...");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");

			} else {

				switch (envName.toLowerCase().trim()) {

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

					ip = new FileInputStream("./src/test/resources/config/config.config.properties");
					break;

				default:
					System.out.println("Please pass the right env name..... " + envName);
					throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : " + envName);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
	}
