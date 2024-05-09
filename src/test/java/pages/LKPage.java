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

public class LKPage implements Page {
    
    private  WebDriver driver;
    
    private static final By PROFILE_LINK = By.xpath (".//a[text()='Профиль']");
   
    public LKPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open account profile page /account/profile")
    public void open() {
        driver.get(BurgerService.BASE_URI + "/account/profile");
    }
    
    @Step("Check if account profile page /account/profile is opened")
    public boolean isOpened() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_LINK));
        return driver.findElement(PROFILE_LINK).isDisplayed();
    }
    

}
