package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    public WebDriver driver;

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

    public WebElement waitForElementDisplay(WebElement givenElement){
        WebElement element = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(givenElement));
        return element;
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

}
