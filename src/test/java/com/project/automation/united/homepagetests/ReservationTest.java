package com.project.automation.united.homepagetests;

import java.util.Map;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.project.automation.united.base.CsvDataProvider;
import com.project.automation.united.base.TestUtils;
import com.project.automation.united.pages.HomePage;

public class ReservationTest extends TestUtils {

	@Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProvider.class)
	public void flightReservationTest(Map<String, String> testData) throws InterruptedException {
		String desiredOriginStr = testData.get("desiredOriginStr");
		String desiredOrigin = testData.get("desiredOrigin");
		String desiredDestinationStr = testData.get("desiredDestinationStr");
		String desiredDestination = testData.get("desiredDestination");
		// String waitForClickability= testData.get("waitForClickability");
		// String totalTravellers= testData.get("totalTravellers");
		log.info("Starting flight reservation test");
		SoftAssert softAssert = new SoftAssert();

		// opening homepage
		HomePage homePage = new HomePage(driver, log);
		homePage.openPageUrl();

		homePage.selectOrigin(desiredOriginStr, desiredOrigin);
		homePage.selectDestination(desiredDestinationStr, desiredDestination);

		softAssert.assertTrue((homePage.getOrigin().contains(desiredOrigin)),
				" origin is not as expected. Desired origin: " + desiredOrigin + " Actual origin: "
						+ homePage.getOrigin());
		softAssert.assertTrue((homePage.getDestination().contains(desiredDestination)),
				" Destination in not as expected. Desired destination: " + desiredDestination + " Actual destination: "
						+ homePage.getDestination());
		
		softAssert.assertAll();
	}

}
