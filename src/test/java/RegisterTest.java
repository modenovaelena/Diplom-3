import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import io.qameta.allure.junit4.DisplayName;
import services.*;

public class RegisterTest {

    private  WebDriver driver;
    
    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
    }
    
    @Test
    @DisplayName("Positive registration scenario")
    public void successfullRegistrationTest () {
        RegistrationPage regPage= new RegistrationPage(driver);
        regPage.open();
        
        Assert.assertTrue(regPage.isOpened());
 
        LoginPage logPage = regPage.fillRegistrationFormAndContinue("Anna",
            "g" + System.currentTimeMillis() + "@gmail.com",
            "12345q");

        Assert.assertTrue(logPage.isOpened());
    }
    
    @Test
    @DisplayName("Attempt to register with invalid password")
    public void incorrectPasswordRegistrationTest () {
        RegistrationPage regPage= new RegistrationPage(driver);
        regPage.open();
        
        Assert.assertTrue(regPage.isOpened());
 
        regPage.fillRegistrationFormAndContinue("Anna",
            "g" + System.currentTimeMillis() + "@gmail.com",
            "1");

        Assert.assertTrue(regPage.isPasswordError());
    }
    
    @After
    public void teardown() {
        driver.quit();
    }

}
