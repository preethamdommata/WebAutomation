import common.BaseSpec;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SearchSpec extends BaseSpec{

    @Test
    public void firstScript() throws InterruptedException {
        driver.get("https://www.makemytrip.com");
        Thread.sleep(5000);
        homePage.enterFromToDetails();
        homePage.selectDate("25", "DECEMBER");
        homePage.selectPassengers("1","1", "0");
        homePage.search();
        Thread.sleep(5000);
        searchPage.sortByDepartureTime();
        searchPage.getFlightsBetweenHours();
        ArrayList<WebElement> flights = searchPage.getFlightsBetweenHours();
        searchPage.printFlightDetails(flights);
        Thread.sleep(5000);

    }

        @AfterClass
    public void clean(){
            driver.quit();
        }

}
