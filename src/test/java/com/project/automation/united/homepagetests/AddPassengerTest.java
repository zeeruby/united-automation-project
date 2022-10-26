package com.project.automation.united.homepagetests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.project.automation.united.base.CsvDataProvider;
import com.project.automation.united.base.TestUtils;
import com.project.automation.united.pages.HomePage;
import com.project.automation.united.pages.PassengerSelectorModal;

public class AddPassengerTest extends TestUtils {

	@Test(priority=1, dataProvider="csvReader", dataProviderClass= CsvDataProvider.class)
	public void addPassengerTest(Map<String, String> testData) {
	//	SoftAssert softAssert= new SoftAssert();
		
		//opening homepage
	    HomePage homePage = new HomePage(driver, log);
	    homePage.openPageUrl();
	    
	    
	    //opening Passenger Selector Model
	    PassengerSelectorModal passengerSelectorModal = homePage.getPassengerSelectorModal();
	    
	    //get testdata keyset
	   ArrayList<String> testDataKeySet=  passengerSelectorModal.getTestDataKeyset(testData);
	    
	    //Getting iterator to iterate through the verious tyes of passengers and add the right number of each
	    Iterator<HashMap<String, String>> iterator= passengerSelectorModal.addPassengerControls(testData);
	    
	   boolean numberOfPassengersAt_0_IsCorrect= passengerSelectorModal.verifyPassengerNumbers(testData, iterator, 0);
	   boolean numberOfPassengersAt_1_IsCorrect= passengerSelectorModal.verifyPassengerNumbers(testData, iterator, 1);
	   boolean numberOfPassengersAt_2_IsCorrect= passengerSelectorModal.verifyPassengerNumbers(testData, iterator, 2);
	   boolean numberOfPassengersAt_3_IsCorrect=passengerSelectorModal.verifyPassengerNumbers(testData, iterator, 3);
	   boolean numberOfPassengersAt_4_IsCorrect=passengerSelectorModal.verifyPassengerNumbers(testData, iterator, 4);
	   boolean numberOfPassengersAt_5_IsCorrect=passengerSelectorModal.verifyPassengerNumbers(testData, iterator, 5);
	   boolean numberOfPassengersAt_6_IsCorrect=passengerSelectorModal.verifyPassengerNumbers(testData, iterator, 6);
	   boolean numberOfPassengersAt_7_IsCorrect=passengerSelectorModal.verifyPassengerNumbers(testData, iterator, 7);
	    
		/*
		 * //Assertions for variuos types of passengers
		 * Assert.assertTrue(numberOfPassengersAt_0_IsCorrect,
		 * testDataKeySet.get(0)+" number is incorrect");
		 * Assert.assertTrue(numberOfPassengersAt_1_IsCorrect,
		 * testDataKeySet.get(1)+" number is incorrect");
		 * Assert.assertTrue(numberOfPassengersAt_2_IsCorrect,
		 * testDataKeySet.get(2)+" number is incorrect");
		 * Assert.assertTrue(numberOfPassengersAt_3_IsCorrect,
		 * testDataKeySet.get(3)+" number is incorrect");
		 * Assert.assertTrue(numberOfPassengersAt_4_IsCorrect,
		 * testDataKeySet.get(4)+" number is incorrect");
		 * Assert.assertTrue(numberOfPassengersAt_5_IsCorrect,
		 * testDataKeySet.get(5)+" number is incorrect");
		 * Assert.assertTrue(numberOfPassengersAt_6_IsCorrect,
		 * testDataKeySet.get(6)+" number is incorrect");
		 * Assert.assertTrue(numberOfPassengersAt_7_IsCorrect,
		 * testDataKeySet.get(7)+" number is incorrect");
		 */
	}

}
