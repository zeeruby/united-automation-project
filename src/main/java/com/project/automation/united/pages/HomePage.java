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
	private By originOptionsLocator = By.xpath("//li[contains(@id,'autocomplete-item')]/button/span");
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
		return find(originLocator).getAttribute("value");
	}

	private void getOriginSelector(String originStr) {
		WebElement elem = find(originLocator);
		elem.clear();
		elem.sendKeys(originStr);
	}

	public void selectOrigin(String originStr, String origin) {
		getOriginSelector(originStr);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> originOptions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(originOptionsLocator));
		for (WebElement option : originOptions) {
			String optionText = option.getText().toLowerCase();
			//System.out.println(optionText + "this is option text");
			if (optionText.contains("origin")) {
				option.click();
				break;
			}
		}
	}

	private void getDestinationSelector(String destinationStr) {
		WebElement elem = find(destinationLocator);
		elem.clear();
		elem.sendKeys(destinationStr);
	}

	public void selectDestination(String destinationStr, String destination) {
		getDestinationSelector(destinationStr);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> destinationOptions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(destinationOptionsLocator));
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
