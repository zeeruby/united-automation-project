package com.project.automation.united.pages;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PassengerSelectorModal extends BasePage{
	private By clearInputField = By.xpath("//*[@id='passengerMenuId']/div/div/div/div/input");
	private By closeModal = By.xpath("//button[@class='atm-c-btn atm-c-btn--bare']//*[name()='svg']");
	private By passengerListLocator = By.xpath("//div[@class= 'app-components-PassengerSelector-passengers__passengerRow--25vMz']");
	private By passengerTypeLocator = By.xpath("./child::p");
	private By passengerDivLocator = By.xpath("following-sibling:: div");
	private By subtractButtonLocator = By.xpath("./child:: button");
	private By passengerNumberInputLocator= By.xpath("following-sibling:: input");
	private By addButtonLocator = By.xpath("following-sibling:: button");
	public PassengerSelectorModal(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	// clear input fields and remove default values
	private void clearInPutField(){
		List<WebElement> inPutList = findAll(clearInputField);
		for (WebElement inPut: inPutList) {
			if(Integer.parseInt(inPut.getAttribute("value"))> 0) {
				inPut.sendKeys(Keys.BACK_SPACE);
				break;
			}
		}
	}
	
	
	//remove, if the current number of passengers is more than the desired number
	private void executeRemoval(int currentNum, int desiredNum, WebElement subtractButton) {
		while(currentNum<desiredNum) {
			subtractButton.click();
			currentNum--;
		}
	}
	
	//add, if the current number of passengers is less than the desired number
	public void executeAddition(int currentNum, int desiredNum, WebElement addButton) {
		while(currentNum<desiredNum) {
			addButton.click();
			currentNum++;
		}
	}
	
	//create passenger controls and add and remove passengers as required
	public void addPassenger(Map<String, String> passengerData) {
		clearInPutField();
		List<WebElement> passengerList = findAll(passengerListLocator);
		for(WebElement passenger: passengerList) {
			WebElement passengerTypeField = passenger.findElement(passengerTypeLocator);
			WebElement passengerDiv = passengerTypeField.findElement(passengerDivLocator);
			WebElement subtractButton = passengerDiv.findElement(subtractButtonLocator);
			WebElement passengerNumberInput = subtractButton.findElement(passengerNumberInputLocator);
			WebElement addButton = passengerNumberInput.findElement(addButtonLocator);
			String passengerType = passengerTypeField.getText();
			for(String passType:passengerData.keySet()) {
				if(passengerType.equals(passType)){
					int desiredNumberOfPassengers = Integer.parseInt(passengerData.get(passType));
					int currentNumberOfPassengers = Integer.parseInt(passengerNumberInput.getDomAttribute("value"));
					executeAddition(currentNumberOfPassengers, desiredNumberOfPassengers, addButton);
					//executeRemoval(currentNumberOfPassengers, desiredNumberOfPassengers, subtractButton);
				}
				System.out.println(passengerType+ " : " + passengerNumberInput.getDomAttribute("value"));
			}
			
		}
	}
	
}
