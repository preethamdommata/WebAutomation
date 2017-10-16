package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchPage {

    private WebDriver driver;
    String departureColumn = "#departure";
    String sorterWaitScreen = ".freeze_screen .sorter";
    String flightDetailRows = ".listing_row";
    String airlineName = ".pull-left>.logo_name.append_bottomSpace6.visible-stb";
    String flightNumber = ".pull-left>.block.logo_name.visible-stb";
    String flightFare = ".price_info>.num";
    String flightDuration = ".duratn>.block.timeCa";
    String departureTime = ".time_info_space>.block.timeCa";
//    String arrivalTime = "";
    String pageLoaderMover = "#progressBar .page_loader__mover";

    public FlightSearchPage(WebDriver driver){
        this.driver = driver;
        waitForElementNotVisible(pageLoaderMover);

    }


    public WebElement waitForClick(String locator){
        WebElement element = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        return element;
    }

    public WebElement waitForElementClick(WebElement givenElement){
        WebElement element = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.elementToBeClickable(givenElement));
        return element;
    }

    public void waitForElementNotVisible(String locator){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator)));
    }


    public void sortByDepartureTime(){
        WebElement departureColumnElement = waitForClick(departureColumn);
        departureColumnElement.click();
        waitForElementNotVisible(sorterWaitScreen);

    }

    public ArrayList<WebElement> getFlightsBetweenHours() throws InterruptedException {
        scrollUntilLastElementTimeGreaterThan("11:00");
        List<WebElement> flightRows = driver.findElements(By.cssSelector(flightDetailRows));

        ArrayList<WebElement> flights = new ArrayList<WebElement>();

        for(WebElement element: flightRows){
            String departureTimeOfFlight = element.findElement(By.cssSelector(departureTime)).getText();
            System.out.println(departureTimeOfFlight);
            if(verifyTimeInBetween("09:00", "10:00", departureTimeOfFlight)){
                System.out.println("time added: "+departureTimeOfFlight);
                flights.add(element);
            }
        }
        return flights;

    }

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

    public LocalTime getTimeFromString(String departureTime){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime departureTimeFinal = LocalTime.parse(departureTime, format);
        return departureTimeFinal;
    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }


    public void scrollUntilLastElementTimeGreaterThan(String time) throws InterruptedException {
        LocalTime givenTime = getTimeFromString(time);

        for(int i=0;i<10;i++){
            List<WebElement> flightRows = driver.findElements(By.cssSelector(flightDetailRows));
            int rowsSize = flightRows.size();
            System.out.println("rowSie: "+rowsSize);
            WebElement lastElement = flightRows.get(rowsSize-1);
            WebElement element = waitForElementClick(lastElement.findElement(By.cssSelector(departureTime)));
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

    public void printFlightDetails(ArrayList<WebElement> flightDetailsRows){
        int flightsNumber = flightDetailsRows.size();
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

        }

    }
}


