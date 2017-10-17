package common;

import Pages.FlightSearchPage;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

public class BaseSpec {

    private String BASEURL = System.getProperty("baseUrl");
    private String BROWSER = System.getProperty("browser");

    public WebDriver driver;
    public HomePage homePage;
    public FlightSearchPage searchPage;

    @BeforeClass
    public void setUp(){
        intilizeBrowser();
        homePage = new HomePage(driver);
        searchPage = new FlightSearchPage(driver);

    }

    /**
     * Intilizes the driver accordingly
     */
    public void intilizeBrowser(){

        if(BROWSER.equalsIgnoreCase("chrome")){
            driver  = new ChromeDriver();
        }
        else if(BROWSER.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.get(BASEURL);

    }
}
