package pages;

import org.openqa.selenium.By;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    
    private  WebDriver driver;
    
    private static final By LOGIN_BUTTON = By.xpath (".//button[text() = 'Войти']");
   
    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }
    
    public boolean isOpened() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }

}
