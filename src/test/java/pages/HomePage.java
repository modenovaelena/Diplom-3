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
import java.lang.*;
import java.time.Duration;

public class HomePage implements Page {
    
    private  WebDriver driver;
    
    private static final By BURGER_CONSTRUCT_MESSAGE = By.xpath (".//h1[text()='Соберите бургер']");
    private static final By ENTER_ACCOUNT_BUTTON = By.xpath (".//button[text() = 'Войти в аккаунт']");
    private static final By LK_LINK = By.xpath (".//p[text() = 'Личный Кабинет']/..");
    
    private static final By SOUCE_LINK = By.xpath (".//span[text() = 'Соусы']");
    private static final By SOUCE_DIV = By.xpath (".//span[text() = 'Соусы']/..");
    private static final By FILLING_LINK = By.xpath (".//span[text() = 'Начинки']");
    private static final By FILLING_DIV = By.xpath (".//span[text() = 'Начинки']/..");
    private static final By BUN_LINK = By.xpath (".//span[text() = 'Булки']");
    private static final By BUN_DIV = By.xpath (".//span[text() = 'Булки']/..");
    private static final String SECTION_CURRENT_CLASS = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    private static final String SECTION_NOT_SELECTED_CLASS = "tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect";

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
        new WebDriverWait(driver, Duration.ofSeconds(10))
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
               
        new WebDriverWait(driver, Duration.ofSeconds(10))
               .until(ExpectedConditions.attributeContains(SOUCE_DIV, "class", SECTION_NOT_SELECTED_CLASS));
            
        driver.findElement(SOUCE_DIV).click();   
        return this;
    }
    
    @Step("Check if 'Souces' section is selected")
    public boolean isSoucesSectionSelected() {
        
      WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
      return wait.until(ExpectedConditions.attributeContains(SOUCE_DIV, "class", SECTION_CURRENT_CLASS));
    }
    
    @Step("Click 'Buns' link")
    public HomePage switchToBunsSection() {

       // WebElement el = driver.findElement(BUN_DIV);
        new WebDriverWait(driver, Duration.ofSeconds(10))
               .until(ExpectedConditions.attributeContains(BUN_DIV, "class", SECTION_NOT_SELECTED_CLASS));

        driver.findElement(BUN_DIV).click();
        return this;
    }
    
    @Step("Check if 'Buns' section is selected")
    public boolean isBunsSectionSelected() {
        
        WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.attributeContains(BUN_DIV, "class", SECTION_CURRENT_CLASS));
    }
    
    @Step("Click 'Fillings' link")
    public HomePage switchToFillingsSection() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(FILLING_DIV, "class", SECTION_NOT_SELECTED_CLASS));

        driver.findElement(FILLING_DIV).click();
        return this;
    }
    
    @Step("Check if 'Fillings' section is selected")
    public boolean isFillingsSectionSelected() {
    
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.attributeContains(FILLING_DIV, "class", SECTION_CURRENT_CLASS));
    }
    
}
