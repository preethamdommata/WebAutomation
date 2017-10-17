package Pages;

import common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage extends BaseClass{

    String fromField = "#hp-widget__sfrom";
    String toField = "#hp-widget__sTo";
    String fromMenu = ".ui-widget-content.hp-widget__sfrom";
    String toMenu = ".ui-widget-content.hp-widget__sTo";
    String departDate = ".dateFilter .ui-datepicker-group-first";
    String departPickerMonth = ".dateFilter .ui-datepicker-group-first .ui-datepicker-month";
    String firstTableDates = ".dateFilter .ui-datepicker-group-first .ui-datepicker-calendar a.ui-state-default";
    String nextButton = ".dateFilter .ui-datepicker-next.ui-corner-all";
    String passengerField = "#hp-widget__paxCounter";
    String paxFilter = ".paxFilter";
    String adultCounterLoc = "#js-adult_counter>li";
    String childrenCounterLoc = "#js-child_counter>li";
    String infantCounterLoc = "#js-infant_counter>li";
    String pasClose = ".close_pax";
    String searchButton = "#searchBtn";
    String fromDayLocator = ".hp-widget__sfrom .ui-menu-item";
    String toDayLocator = ".hp-widget__sTo .ui-menu-item";

    /**
     * Constructor to handle the driver
     * @param driver
     */
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Enters the From and To place details
     * @param fromLocation - From Location name
     * @throws InterruptedException - To location Name
     */
    public void enterFromToDetails(String fromLocation, String toLocation) throws InterruptedException {
        WebElement fromFieldE = waitForDisplay(fromField);
        fromFieldE.click();
        waitForDisplay(fromMenu);
        selectFromLocation(fromLocation);
        waitForDisplay(toMenu);
        selectToLocation(toLocation);
    }

    /**
     * Selects from location from the Location dropdown provided
     * @param locationName - Name of the From location
     */
    public void selectFromLocation(String locationName){
        waitForDisplay(fromMenu);
        List<WebElement> fromElements = driver.findElements(By.cssSelector(fromDayLocator));
        for(WebElement element:fromElements){
            if(element.getText().contains(locationName)){
                element.findElement(By.cssSelector("span")).click();
                break;
            }
        }
    }

    /**
     * Selects TO location from the Location dropdown provided
     * @param locationName - Name of the To location
     */
    public void selectToLocation(String locationName){
        waitForDisplay(toMenu);
        List<WebElement> fromElements = driver.findElements(By.cssSelector(toDayLocator));
        for(WebElement element:fromElements){
            if(element.getText().contains(locationName)){
                element.findElement(By.cssSelector("span")).click();
                break;
            }
        }
    }

    /**
     * Selects the Date and Month from the Calender
     * @param day - Date details of a particular month
     * @param month - Details of the Month
     */
    public void selectDate(String day, String month){
        waitForDisplay(departDate);
        selectMonth(month);
        selectDay(day);
    }

    /**
     * Selects the Month in calender as per the given Month details
     * @param month - Given Month name
     */
    public void selectMonth(String month){

        waitForDisplay(departDate);
        for(int i=0;i<12;i++){
            WebElement monthElement = driver.findElement(By.cssSelector(departPickerMonth));
            WebElement nextElement = driver.findElement(By.cssSelector(nextButton));
        if(monthElement.getText().equals(month)){
            break;
        }else{
                nextElement.click();
            }
                }
    }

    /**
     * Selects the day of a month in calender as per the day details provided
     * @param day - Day of a Month (Date)
     */
    public void selectDay(String day){
        waitForDisplay(departDate);
        List<WebElement> dayElements = driver.findElements(By.cssSelector(firstTableDates));
        for(WebElement element: dayElements){
            if(element.getText().contentEquals(day)){
                element.click();
                break;
            }
        }
    }

    /**
     * Selects the number of passengers (considering Adults, Children and Infants)
     * @param noAdults - Number of adults
     * @param noChildren - Number of children
     * @param noInfant - Number of Infants
     */
    public void selectPassengers(String noAdults, String noChildren, String noInfant){
        WebElement paxFieldElement = waitForClick(passengerField);
        paxFieldElement.click();
        waitForDisplay(paxFilter);
        List<WebElement> adultCounter = driver.findElements(By.cssSelector(adultCounterLoc));
        for(WebElement element: adultCounter){
            if(element.getText().contentEquals(noAdults)){
                element.click();
                break;
            }
        }

        if(!noChildren.contentEquals("0")){
            List<WebElement> childrenCounter = driver.findElements(By.cssSelector(childrenCounterLoc));
            for(WebElement element: childrenCounter){
                if(element.getText().contentEquals(noChildren)){
                    element.click();
                    break;
                }
            }
        }

        if(!noInfant.contentEquals("0")){
            List<WebElement> infantCounter = driver.findElements(By.cssSelector(infantCounterLoc));
            for(WebElement element: infantCounter){
                if(element.getText().contentEquals(noInfant)){
                    element.click();
                    break;
                }
            }}

        WebElement closeButton = waitForDisplay(pasClose);
        closeButton.click();

    }

    /**
     * Clicks on the search Button and perfrom the Search operation for the details provided
     */
    public void search(){
        WebElement searchElement = waitForClick(searchButton);
        searchElement.click();
    }

}
