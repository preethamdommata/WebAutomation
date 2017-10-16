package Pages;

import common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightSearchPage extends BaseClass{

    String departureColumn = "#departure";
    String sorterWaitScreen = ".freeze_screen .sorter";
    String flightDetailRows = ".listing_row";
    String airlineName = ".pull-left>.logo_name.append_bottomSpace6.visible-stb";
    String flightNumber = ".pull-left>.block.logo_name.visible-stb";
    String flightFare = ".price_info>.num";
    String flightDuration = ".duratn>.block.timeCa";
    String departureTime = ".time_info_space>.block.timeCa";
//    String pageLoaderMover = "#progressBar .page_loader__mover";
    String pageLoaderMover = "span.page_loader__text:contains(Getting Flights...)";

    /**
     * constructor to handle the driver
     * @param driver
     */
    public FlightSearchPage(WebDriver driver){
        this.driver = driver;
//        waitForElementNotVisible(pageLoaderMover);

        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    /**
     * Clicks on the Departure Column header for sorting
     */
    public void sortByDepartureTime(){
        WebElement departureColumnElement = waitForClick(departureColumn);
        departureColumnElement.click();
        waitForElementNotVisible(sorterWaitScreen);

    }

    /**
     * Gets the flights that are in between the specified Departure timings
     * @return Arraylist of searched flight row elements
     * @throws InterruptedException
     */
    public ArrayList<WebElement> getFlightsBetweenHours(String departurestartTime, String departureEndTime) throws InterruptedException {
        scrollUntilLastElementTimeGreaterThan(departureEndTime);
        List<WebElement> flightRows = driver.findElements(By.cssSelector(flightDetailRows));
        ArrayList<WebElement> flights = new ArrayList<WebElement>();
        for(WebElement element: flightRows){
            String departureTimeOfFlight = element.findElement(By.cssSelector(departureTime)).getText();
            if(verifyTimeInBetween(departurestartTime, departureEndTime, departureTimeOfFlight)){
                flights.add(element);
            }
        }
        return flights;

    }

    /**
     * Verifies if the given time is in between the given start and end times
     * @param startTime - Start time to check if the given time is in between
     * @param endTime - end time to check if the given time is in between
     * @param time - Given time - to verify if this falls in between start and end time
     * @return boolean value (true or false)
     */
    public boolean verifyTimeInBetween(String startTime, String endTime, String time){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        boolean defaultResult = false;
        LocalTime start = LocalTime.parse(startTime, format);
        LocalTime end = LocalTime.parse(endTime, format);
        LocalTime targetTime = LocalTime.parse(time, format);

        if(targetTime.isAfter(start) && targetTime.isBefore(end)){
            defaultResult = true;
        }
        return defaultResult;
    }

    /**
     * Converts the string to time
     * @param departureTime - time given in string
     * @return - returns time in LocalTime format
     */
    public LocalTime getTimeFromString(String departureTime){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime departureTimeFinal = LocalTime.parse(departureTime, format);
        return departureTimeFinal;
    }

    /**
     * Scrolls the page to the given element
     * @param element - WedElement where the page should be scrolled to.
     */
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Scrolls the page until the departure time of the last element becomes more than the end departure time given
     * @param time - Departure time
     * @throws InterruptedException
     */
    public void scrollUntilLastElementTimeGreaterThan(String time) throws InterruptedException {
        LocalTime givenTime = getTimeFromString(time);

        for(int i=0;i<10;i++){
            List<WebElement> flightRows = driver.findElements(By.cssSelector(flightDetailRows));
            int rowsSize = flightRows.size();
            System.out.println("rowsSize: "+rowsSize);
            WebElement lastElement = flightRows.get(rowsSize-1);
            waitForPresenceOfElements(flightDetailRows);
            waitForAllElementsToBeVisible(flightRows);
            WebElement element = waitForElementDisplay(lastElement.findElement(By.cssSelector(departureTime)));
            String departureTimeStamp = element.getText();
            LocalTime lastElementTime = getTimeFromString(departureTimeStamp);

            if(lastElementTime.isBefore(givenTime)){
                scrollToElement(lastElement);
                Thread.sleep(5000);
            }else{
                break;
            }
        }

    }

    /**
     * Prints the flight details like Ariline Name, Flight Number, Fare, Flight Duration, Departure time of the Flight
     * @param flightDetailsRows - Arraylist of the searched results flight row elements
     */
    public void printFlightDetails(ArrayList<WebElement> flightDetailsRows){
        for(WebElement element:flightDetailsRows){
            WebElement airlineNameElement = element.findElement(By.cssSelector(airlineName));
            WebElement flightNumberElement = element.findElement(By.cssSelector(flightNumber));
            WebElement flightFareElement = element.findElement(By.cssSelector(flightFare));
            WebElement flightDurationElement = element.findElement(By.cssSelector(flightDuration));
            WebElement departureTimeElement = element.findElement(By.cssSelector(departureTime));

            System.out.println("Airline Name: "+airlineNameElement.getText());
            System.out.println("Flight Number: "+flightNumberElement.getText());
            System.out.println("Flight Fare: "+flightFareElement.getText());
            System.out.println("Flight Duation: "+flightDurationElement.getText());
            System.out.println("Departure Time: "+departureTimeElement.getText());
            System.out.println("\n");

        }

    }
}


