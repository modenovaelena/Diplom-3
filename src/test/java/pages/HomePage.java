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
import java.lang.Boolean;

public class HomePage implements Page {
    
    private  WebDriver driver;
    
    private static final By BURGER_CONSTRUCT_MESSAGE = By.xpath (".//h1[text()='Соберите бургер']");
    private static final By ENTER_ACCOUNT_BUTTON = By.xpath (".//button[text() = 'Войти в аккаунт']");
    private static final By LK_LINK = By.xpath (".//p[text() = 'Личный Кабинет']/..");
    
    private static final By SOUCE_LINK = By.xpath (".//span[text() = 'Соусы']");
    private static final By SOUCE_TITLE = By.xpath (".//h2[text() = 'Соусы']");
    private static final By FILLING_LINK = By.xpath (".//span[text() = 'Начинки']");
    private static final By FILLING_TITLE = By.xpath (".//h2[text() = 'Начинки']");
    private static final By BUN_LINK = By.xpath (".//span[text() = 'Булки']");
    private static final By BUN_TITLE = By.xpath (".//h2[text() = 'Булки']");

    private boolean auth = false;
   
    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open home page /")
    public void open() {
        driver.get(BurgerService.BASE_URI);
    }
    
    public void setAuth(boolean auth) {
        this.auth = auth;
    }
    
    public boolean isAuth() {
        return this.auth;
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
    public Page clickLKLink() {

        driver.findElement(LK_LINK).click();
        
        if (!auth)
            return new LoginPage(driver);
        else 
            return new LKPage(driver);
    }
    
    @Step("Click 'Souces' link")
    public HomePage switchToSoucesSection() {

        driver.findElement(SOUCE_LINK).click();
        return this;
    }
    
    @Step("Check if 'Souces' section is selected")
    public boolean isSoucesSectionSelected() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(SOUCE_TITLE));
        return driver.findElement(SOUCE_TITLE).isDisplayed();
    }
    
    @Step("Click 'Buns' link")
    public HomePage switchToBunsSection() {

        driver.findElement(BUN_LINK).click();
        return this;
    }
    
    @Step("Check if 'Buns' section is selected")
    public boolean isBunsSectionSelected() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(BUN_TITLE));
        return driver.findElement(BUN_TITLE).isDisplayed();
    }
    
    @Step("Click 'Fillings' link")
    public HomePage switchToFillingsSection() {

        driver.findElement(FILLING_LINK).click();
        return this;
    }
    
    @Step("Check if 'Fillings' section is selected")
    public boolean isFillingsSectionSelected() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(FILLING_TITLE));
        return driver.findElement(FILLING_TITLE).isDisplayed();
    }
    
}
