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

public class RegistrationPage {
    
    private  WebDriver driver;
    
    private static final By PASSPORD_FIELD = By.xpath (".//label[text()='Пароль']/../input") ;
    private static final By EMAIL_FIELD = By.xpath (".//label[text()='Email']/../input");
    private static final By NAME_FIELD = By.xpath (".//label[text()='Имя']/../input");
    private static final By REGISTER_BUTTON = By.xpath (".//button[text() = 'Зарегистрироваться']");

   
    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }
    
    public boolean isOpened() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
        return driver.findElement(REGISTER_BUTTON).isDisplayed();
    }

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

}
