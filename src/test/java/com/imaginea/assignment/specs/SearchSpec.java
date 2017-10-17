package com.imaginea.assignment.specs;

import com.imaginea.assignment.common.BaseSpec;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class SearchSpec extends BaseSpec{

    @DataProvider(name = "DepartureDetails")
    public static Object[][] details(){
        return new Object[][] {{"10:00", "11:00", "30", "OCTOBER", "1", "1", "1", "Bangalore", "Hyderabad"},
                {"09:30","10:30","15", "NOVEMBER", "2", "2", "1", "Mumbai", "Hyderabad"}};
    }

    @Test
    public void firstScript() throws InterruptedException {
        String departureStartTime = "09:00";
        String departureEndTime = "10:00";
        String dayOfBooking = "25";
        String monthOfBooking = "DECEMBER";
        String numberOfAdults = "1";
        String numberOfChildren = "1";
        String numberOfInfant = "0";
        String fromLocation = "Hyderabad";
        String toLocation = "Bangalore";

        homePage.enterFromToDetails(fromLocation, toLocation);
        homePage.selectDate(dayOfBooking, monthOfBooking);
        homePage.selectPassengers(numberOfAdults,numberOfChildren, numberOfInfant);
        homePage.search();
        searchPage.sortByDepartureTime();
        ArrayList<WebElement> flights = searchPage.getFlightsBetweenHours(departureStartTime, departureEndTime);
        searchPage.printFlightDetails(flights);
        //Sample Hard assert - is not verifying anything
        Assert.assertEquals(true, true);
    }

    @Test(dataProvider = "DepartureDetails")
    public void parameterizationUsingDataProvider(String dStartTime, String dEndTime, String day, String month, String noA, String noC, String noI, String fromLoc, String toLoc) throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        homePage.enterFromToDetails(fromLoc, toLoc);
        homePage.selectDate(day, month);
        homePage.selectPassengers(noA,noC, noI);
        homePage.search();
        searchPage.sortByDepartureTime();
        ArrayList<WebElement> flights = searchPage.getFlightsBetweenHours(dStartTime, dEndTime);
        searchPage.printFlightDetails(flights);
        //Sample soft assert - is not verifying anything
        softAssert.assertEquals(true, true);
        softAssert.assertAll();

    }

    @AfterClass
    public void clean(){
            driver.quit();
        }

}
