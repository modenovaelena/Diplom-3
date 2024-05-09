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
import io.qameta.allure.Step;
import services.*;

public class LoginPage {
    
    private  WebDriver driver;
    
    private static final By LOGIN_BUTTON = By.xpath (".//button[text() = 'Войти']");
    private static final By PASSPORD_FIELD = By.xpath (".//label[text()='Пароль']/../input") ;
    private static final By EMAIL_FIELD = By.xpath (".//label[text()='Email']/../input");

   
    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open login page /login")
    public void open() {
        driver.get(BurgerService.BASE_URI + "/login");
    }
    
    @Step("Check if login page /login is opened")
    public boolean isOpened() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }
    
    
    @Step("Fill registration form and press 'Register' button")
    public HomePage fillLoginFormAndContinue(String email, String password) {

        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(email);

        driver.findElement(PASSPORD_FIELD).clear();
        driver.findElement(PASSPORD_FIELD).sendKeys(password);
        
        driver.findElement(LOGIN_BUTTON).click();
        
        return new HomePage(driver);
    }

}
