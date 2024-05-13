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
import java.time.Duration;

public class RegistrationPage implements Page {
    
    private  WebDriver driver;
    
    private static final By PASSPORD_FIELD = By.xpath (".//label[text()='Пароль']/../input");
    private static final By EMAIL_FIELD = By.xpath (".//label[text()='Email']/../input");
    private static final By NAME_FIELD = By.xpath (".//label[text()='Имя']/../input");
    private static final By REGISTER_BUTTON = By.xpath (".//button[text() = 'Зарегистрироваться']");
    private static final By PSWD_ERROR_MESSAGE = By.xpath (".//p[text() = 'Некорректный пароль']");
    private static final By LOGIN_LINK = By.xpath (".//a[text() = 'Войти']");
    
   
    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open registration page /register")
    public void open() {
        driver.get(BurgerService.BASE_URI + "/register");
    }
    
    @Step("Check if registration page /register is opened")
    public boolean isOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
        return driver.findElement(REGISTER_BUTTON).isDisplayed();
    }
    
    @Step("Check if we have password error on the page")
    public boolean isPasswordError() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(PSWD_ERROR_MESSAGE));
        return driver.findElement(PSWD_ERROR_MESSAGE).isDisplayed();
    }

    @Step("Fill registration form and press 'Register' button")
    public LoginPage fillRegistrationFormAndContinue(String name, String email, String password) {

        driver.findElement(NAME_FIELD).clear();
        driver.findElement(NAME_FIELD).sendKeys(name);

        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(email);

        driver.findElement(PASSPORD_FIELD).clear();
        driver.findElement(PASSPORD_FIELD).sendKeys(password);
        
        driver.findElement(REGISTER_BUTTON).click();
        
        return new LoginPage(driver);
    }
    
    @Step("Click 'Login' link")
    public LoginPage clickLoginLink() {

        driver.findElement(LOGIN_LINK).click();
        
        return new LoginPage(driver);
    }
    

}
