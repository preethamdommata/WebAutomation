import Pages.FlightSearchPage;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SearchSpec {

    String driverPath = "C:\\Users\\preethamr\\IdeaProjects\\WebAutomation\\src\\main\\resources\\";
    WebDriver driver;
    HomePage homePage;
    FlightSearchPage searchPage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");


//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        searchPage = new FlightSearchPage(driver);


    }

    @Test
    public void firstScript() throws InterruptedException {
        driver.get("https://www.makemytrip.com");
        Thread.sleep(5000);
        homePage.enterFromToDetails();
        homePage.selectDate("25", "DECEMBER", "2017");
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
