package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public WebDriver driver;

    String fromField = "#hp-widget__sfrom";
    String toField = "#hp-widget__sTo";
    String fromMenu = ".ui-widget-content.hp-widget__sfrom";
    String hyderLoca = "[aria-label = 'Top Cities : Hyderabad, India ']";
    String bangLoca = "[aria-label = 'Top Cities : Hyderabad, India ']";
    String toMenu = ".ui-widget-content.hp-widget__sTo";

    public WebElement waitForPresence(String locator){
        WebElement element = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        return element;
    }

    public WebElement waitForDisplay(String locator){
        WebElement element = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
        return element;
    }

    public WebElement waitForClick(String locator){
        WebElement element = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        return element;
    }

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void enterFromToDetails() throws InterruptedException {
        WebElement fromFieldE = waitForDisplay(fromField);
        fromFieldE.click();
        waitForDisplay(fromMenu);
        selectFromLocation("Hyderabad");
        waitForDisplay(toMenu);
        selectToLocation("Bangalore");
    }


    public void selectFromLocation(String locationName){
        waitForDisplay(fromMenu);
        List<WebElement> fromElements = new ArrayList<WebElement>();
        fromElements = driver.findElements(By.cssSelector(".hp-widget__sfrom .ui-menu-item"));
        for(WebElement element:fromElements){
            System.out.println(element.getText());
            if(element.getText().contains(locationName)){
                element.findElement(By.cssSelector("span")).click();
                break;
            }

        }

    }

    public void selectToLocation(String locationName){
        waitForDisplay(toMenu);
        List<WebElement> fromElements = new ArrayList<WebElement>();
        fromElements = driver.findElements(By.cssSelector(".hp-widget__sTo .ui-menu-item"));
        for(WebElement element:fromElements){
            System.out.println(element.getText());
            if(element.getText().contains(locationName)){
                element.findElement(By.cssSelector("span")).click();
                break;
            }
        }
    }

    String departDate = ".dateFilter .ui-datepicker-group-first";
    String departPickerMonth = ".dateFilter .ui-datepicker-group-first .ui-datepicker-month";
    String departPickerYear = ".dateFilter .ui-datepicker-group-first .ui-datepicker-year";
    String departPickerDate = ".dateFilter .ui-datepicker-group-first .ui-datepicker-calendar";
    String firstTableDates = ".dateFilter .ui-datepicker-group-first .ui-datepicker-calendar a.ui-state-default";
    String nextButton = ".dateFilter .ui-datepicker-next.ui-corner-all";
    String passengerField = "#hp-widget__paxCounter";
    String paxFilter = ".paxFilter";
    String adultCounterLoc = "#js-adult_counter>li";
    String childrenCounterLoc = "#js-child_counter>li";
    String infantCounterLoc = "#js-infant_counter>li";
    String pasClose = ".close_pax";
    String searchButton = "#searchBtn";


    public void selectDate(String day, String month, String year){
        waitForDisplay(departDate);
        selectMonth(month);
        selectDay(day);

    }


    public void selectMonth(String month){

        waitForDisplay(departDate);
        for(int i=0;i<12;i++){
            WebElement monthElement = driver.findElement(By.cssSelector(departPickerMonth));
            WebElement nextElement = driver.findElement(By.cssSelector(nextButton));
        if(monthElement.getText().equals(month)){
            System.out.println(monthElement.getText());
            break;
        }else{
                nextElement.click();
            }
                }
    }

    public void selectDay(String day){
        waitForDisplay(departDate);
//        List<WebElement> dayElements = new ArrayList<WebElement>();
        List<WebElement> dayElements = driver.findElements(By.cssSelector(firstTableDates));
//        dayElements = driver.findElements(By.cssSelector(firstTableDates));
        for(WebElement element: dayElements){
            if(element.getText().contentEquals(day)){
                element.click();
                break;
            }
        }
    }





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

    public void search(){
        WebElement searchElement = waitForClick(searchButton);
        searchElement.click();
    }

}
