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

public class PasswordRecoveryPage implements Page {
    
    private  WebDriver driver;
    
    private static final By PASSWORD_RECOVERY_MESSAGE = By.xpath (".//h2[text()='Восстановление пароля']");
    private static final By LOGIN_LINK = By.xpath (".//a[text() = 'Войти']");

   
    public PasswordRecoveryPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open password recovery page /forgot-password")
    public void open() {
        driver.get(BurgerService.BASE_URI + "/forgot-password");
    }
    
    @Step("Check if password recovery page /forgot-password is opened")
    public boolean isOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_RECOVERY_MESSAGE));
        return driver.findElement(PASSWORD_RECOVERY_MESSAGE).isDisplayed();
    }
    
    
    @Step("Click 'Login' link")
    public LoginPage clickLoginLink() {

        driver.findElement(LOGIN_LINK).click();
        
        return new LoginPage(driver);
    }
    
    

}
