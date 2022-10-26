package com.project.automation.united.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.automation.united.base.TestUtils;

public class HomePage extends BasePage {
	public static String pageUrl = "https://www.united.com/en/us";
	// identify parent element with ./.. expression in xpath
	// parent.findElements(By.xpath("./child::*"))
	private By originLocator = By.id("bookFlightOriginInput");
	private By destinationLocator = By.id("bookFlightDestinationInput");
	private By originOptionsLocator = By.xpath("//li[contains(@id,'autocomplete-item')]//span");
	private By destinationOptionsLocator = By.xpath("//ul[@id= 'bookFlightDestinationInput-menu']/li/button/span"); 
	// path=//tagname[contains(@Attribute,‘Value’)]
	private By passengerSelectorModalLocator = By.xpath("//*[@id='passengerSelector']/button");

	public HomePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void openPageUrl() {
		openPage(pageUrl);
	}

	public String getOrigin() {
		return find(originLocator).getAttribute("value").toLowerCase();
	}

	private void getOriginSelector(String originStr) throws InterruptedException {
		WebElement elem = find(originLocator);
		elem.clear();
		elem.sendKeys(originStr);
		//Thread.sleep(2000);
	}

	public void selectOrigin(String originStr, String origin) throws InterruptedException {
		getOriginSelector(originStr);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> originOptions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(originOptionsLocator));
		//List<WebElement> originOptions= findAll(originOptionsLocator);
		for (WebElement option : originOptions) {
			String optionText = option.getText().toLowerCase();
			if (optionText.contains(origin)) {
				System.out.println(option.getText() + "contains "+ origin);
				option.click();
				break;
			}
		}
	}

	public String getDestination() {
		return find(destinationLocator).getAttribute("value").toLowerCase();
	}
	private void getDestinationSelector(String destinationStr) throws InterruptedException {
		Thread.sleep(3000);
		WebElement elem = find(destinationLocator);
		elem.clear();
		elem.sendKeys(destinationStr);
	}

	public void selectDestination(String destinationStr, String destination) throws InterruptedException {
		getDestinationSelector(destinationStr);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> destinationOptions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(destinationOptionsLocator));
		//List<WebElement> destinationOptions = findAll(destinationOptionsLocator);
		System.out.println(destinationOptions+"dest options array");
		for (WebElement option : destinationOptions) {
			if (option.getText().toLowerCase().contains(destination)) {
				option.click();
				break;
			}
		}

	}

	public PassengerSelectorModal getPassengerSelectorModal() {
		find(passengerSelectorModalLocator).click();
		return new PassengerSelectorModal(driver, log);
	}

}
