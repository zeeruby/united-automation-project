package com.project.automation.united.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PassengerSelectorModal extends BasePage {
	private By clearInputField = By.xpath("//*[@id='passengerMenuId']/div/div/div/div/input");
	private By closeModal = By.xpath("//button[@class='atm-c-btn atm-c-btn--bare']//*[name()='svg']");
	private By passengerListLocator = By
			.xpath("//div[@class= 'app-components-PassengerSelector-passengers__passengerRow--25vMz']");
	private By passengerTypeLocator = By.xpath("./child::p");
	private By passengerDivLocator = By.xpath("following-sibling:: div");
	private By subtractButtonLocator = By.xpath("./child:: button");
	private By passengerNumberInputLocator = By.xpath("following-sibling:: input");
	private By addButtonLocator = By.xpath("following-sibling:: button");

	public PassengerSelectorModal(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// clear input fields and remove default values
	private void clearInPutField() {
		List<WebElement> inPutList = findAll(clearInputField);
		for (WebElement inPut : inPutList) {
			if (Integer.parseInt(inPut.getAttribute("value")) > 0) {
				inPut.sendKeys(Keys.BACK_SPACE);
				break;
			}
		}
	}

	// remove, if the current number of passengers is more than the desired number
	private void executeRemoval(int currentNum, int desiredNum, WebElement subtractButton) {
		while (currentNum < desiredNum) {
			subtractButton.click();
			currentNum--;
		}
	}

	// add, if the current number of passengers is less than the desired number
	public void executeAddition(int currentNum, int desiredNum, WebElement addButton) {
		while (currentNum < desiredNum) {
			addButton.click();
			currentNum++;
		}
	}


	public Iterator<HashMap<String, String>> addPassengerControls(Map<String, String> passengerData) {
		clearInPutField();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<WebElement> passengerList = findAll(passengerListLocator);
		for (WebElement passenger : passengerList) {
			WebElement passengerTypeField = passenger.findElement(passengerTypeLocator);
			WebElement passengerDiv = passengerTypeField.findElement(passengerDivLocator);
			WebElement subtractButton = passengerDiv.findElement(subtractButtonLocator);
			WebElement passengerNumberInput = subtractButton.findElement(passengerNumberInputLocator);
			WebElement addButton = passengerNumberInput.findElement(addButtonLocator);
			String passengerType = passengerTypeField.getText();
			for (String passType : passengerData.keySet()) {
				if (passengerType.equals(passType)) {
					HashMap<String, String> myMap = new HashMap<String, String>();
					int desiredNumberOfPassengers = Integer.parseInt(passengerData.get(passType));
					int currentNumberOfPassengers = Integer.parseInt(passengerNumberInput.getDomAttribute("value"));
					executeAddition(currentNumberOfPassengers, desiredNumberOfPassengers, addButton);
					myMap.put(passengerType, passengerNumberInput.getDomAttribute("value"));
					list.add(myMap);
				}
			}
		}
		return list.iterator();
	}

	public ArrayList<String> getTestDataKeyset(Map<String, String> passengerData) {
		ArrayList<String> passengerKeyList = new ArrayList<String>();
		for (String key : passengerData.keySet()) {
			passengerKeyList.add(key);
		}
		return passengerKeyList;
	}

	
	public boolean verifyPassengerNumbers(Map<String, String> passengerData, Iterator<HashMap<String, String>> it, int x) {
		boolean flag = false;
		ArrayList<String> passengerKeyList = getTestDataKeyset(passengerData);
		//Iterator<HashMap<String, String>> it = addPassengerControls(passengerData);
		while (it.hasNext()) {
			HashMap<String, String> map = it.next();
			for (String key : map.keySet()) {
				if(key.equals(passengerKeyList.get(x))&&(map.get(key).equals
						(passengerData.get(passengerKeyList.get(x))))) {
					System.out.println(passengerKeyList.get(x)+" : the current number is correct " );
					flag= true;
				}
					
			}

		}
		return flag;
	}
}

// map.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] :
// " + value));
// Map.Entry entry = (Map.Entry) it.next();
//System.out.println("[Key] : " + entry.getKey() + " [Value] : " + entry.getValue());
