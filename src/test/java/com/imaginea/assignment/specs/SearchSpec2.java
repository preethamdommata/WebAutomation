package com.imaginea.assignment.specs;

import com.imaginea.assignment.common.BaseSpec;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static com.imaginea.assignment.data.SearchSpecData.*;

public class SearchSpec2 extends BaseSpec{


    @Test
    public void ParameterizationUsingDataClass() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        homePage.enterFromToDetails(fromLocation, toLocation);
        homePage.selectDate(departureDay, departureMonth);
        homePage.selectPassengers(numberOfAdults,numberOfChildren, numberOfInfant);
        homePage.search();
        searchPage.sortByDepartureTime();
        ArrayList<WebElement> flights = searchPage.getFlightsBetweenHours(departureStartTime, departureEndTime);
        searchPage.printFlightDetails(flights);
        //Sample soft assert - is not verifying anything
        softAssert.assertEquals(true, true);
        softAssert.assertAll();

    }

    @AfterMethod
    public void clean(){
        driver.quit();
    }


}
