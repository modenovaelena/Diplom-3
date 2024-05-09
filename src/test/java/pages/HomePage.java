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

public class HomePage {
    
    private  WebDriver driver;
    
    private static final By BURGER_CONSTRUCT_MESSAGE = By.xpath (".//h1[text()='Соберите бургер']");
    private static final By ENTER_ACCOUNT_BUTTON = By.xpath (".//button[text() = 'Войти в аккаунт']");
    private static final By LK_LINK = By.xpath (".//p[text() = 'Личный Кабинет']/..");
   
    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open home page /")
    public void open() {
        driver.get(BurgerService.BASE_URI);
    }
    
    @Step("Check if home page / is opened")
    public boolean isOpened() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_CONSTRUCT_MESSAGE));
        return driver.findElement(BURGER_CONSTRUCT_MESSAGE).isDisplayed();
    }
    
    @Step("Press 'Enter Account' button")
    public LoginPage pressEnterAccountButton() {

        driver.findElement(ENTER_ACCOUNT_BUTTON).click();
        
        return new LoginPage(driver);
    }
    
    @Step("Click 'LK' link")
    public LoginPage clickLKLink() {

        driver.findElement(LK_LINK).click();
        
        return new LoginPage(driver);
    }
    
    
    
}
