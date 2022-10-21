package com.project.automation.united.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {
private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
private String browser ;
private Logger log;
	public BrowserDriverFactory(String browser, Logger log) {
		this.browser = browser;
		this.log= log;
	}
	public WebDriver createDriver() {
		switch(browser) {
    	case "chrome":
    		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    		driver.set(new ChromeDriver());
    		log.info("chrome dirver loaded");
    		
    		break;
    	case "firefox":
    		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
    		driver.set(new FirefoxDriver());
    		log.info("Firefox dirver loaded");
    		break;
    	case "edge":
    		System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
    		driver.set(new EdgeDriver());
    		log.info("Edge dirver loaded");
    		break;
    	default:
    		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    		driver.set(new ChromeDriver());
    		log.info("default dirver loaded");
    		break;
    	}
    	return driver.get();
    	
    }
	}


