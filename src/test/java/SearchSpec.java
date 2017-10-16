import common.BaseSpec;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SearchSpec extends BaseSpec{

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

        driver.get("https://www.makemytrip.com");
        homePage.enterFromToDetails(fromLocation, toLocation);
        homePage.selectDate(dayOfBooking, monthOfBooking);
        homePage.selectPassengers(numberOfAdults,numberOfChildren, numberOfInfant);
        homePage.search();
        searchPage.sortByDepartureTime();
        ArrayList<WebElement> flights = searchPage.getFlightsBetweenHours(departureStartTime, departureEndTime);
        searchPage.printFlightDetails(flights);

    }

    @AfterClass
    public void clean(){
            driver.quit();
        }

}
