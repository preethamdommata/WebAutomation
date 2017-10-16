package common;

import Pages.FlightSearchPage;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseSpec {

    public String driverPath = "C:\\Users\\preethamr\\IdeaProjects\\WebAutomation\\src\\main\\resources\\";
    public WebDriver driver;
    public HomePage homePage;
    public FlightSearchPage searchPage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        searchPage = new FlightSearchPage(driver);


    }
}
