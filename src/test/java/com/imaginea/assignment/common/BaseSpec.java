package com.imaginea.assignment.common;

import com.imaginea.assignment.pages.FlightSearchPage;
import com.imaginea.assignment.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

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

    @BeforeMethod
    public void set(){
        driver.manage().window().maximize();
        driver.get(BASEURL);
    }

    @AfterMethod
    public void clean(){
        driver.manage().deleteAllCookies();
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
