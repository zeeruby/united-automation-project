package com.project.automation.united.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected Logger log;

	public BasePage(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	// Open page with given UR
	protected void openPage(String url) {
		driver.get(url);
	}

	// Find element using given locator
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}

	// find all element method to find list of elements using locator
	protected List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}

	// Click on element with given locator when its visible
	protected void clickOn(By locator) {
		waitForVisibilityOf(locator, Duration.ofSeconds(5));
		find(locator).click();
	}

	// Type given text into element with given locator
	protected void typeText(By locator, String text) {
		waitForVisibilityOf(locator, Duration.ofSeconds(5));
		find(locator).sendKeys(text);
	}

	protected String getActualtUrl() {
		return driver.getCurrentUrl();
	}

	protected Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert();
	}

	protected void switchToFrame(By frameLocator) {
		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.presenceOfElementLocated(frameLocator));
		driver.switchTo().frame(find(frameLocator));
	}

	// Wait for specific ExpectedCondition for the given amount of time in seconds
	private void waitFor(ExpectedCondition<WebElement> condition, Duration timeOut) {
		timeOut = timeOut != null ? timeOut : Duration.ofSeconds(30);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(condition);
	}

	// Wait for given number of seconds for element with given locator to be visible
	// on the page
	protected void waitForVisibilityOf(By locator, Duration... timeOut) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOut.length > 0 ? timeOut[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	
	protected void waitForClickability(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(elem));
	}

}
