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
import model.*;
import services.*;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginTest {

    private  WebDriver driver;
    
    private User user = new User("Anna","g" + System.currentTimeMillis() +"@gmail.com",
             "p12345");
    private String accessToken;
    private BurgerService service = new BurgerService();
    
    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        
        RestAssured.baseURI = BurgerService.BASE_URI;
        this.accessToken = this.service.createUser(this.user);
    }
    
    @Test
    @DisplayName("Test login via 'Enter Account' button on home page")
    public void loginViaEnterAccountButtonTest () {
        
        HomePage hPage = new HomePage(driver);
        hPage.open();
        
        Assert.assertTrue(hPage.isOpened());
        
        LoginPage logPage = hPage.pressEnterAccountButton();
        
        Assert.assertTrue(logPage.isOpened());
 
        hPage = logPage.fillLoginFormAndContinue(this.user.getEmail(), this.user.getPassword());

        Assert.assertTrue(hPage.isOpened());
    }
    
    @Test
    @DisplayName("Test login via 'Personal Space' link on home page")
    public void loginViaLkLinkTest () {
        
        HomePage hPage = new HomePage(driver);
        hPage.open();
        
        Assert.assertTrue(hPage.isOpened());
        
        LoginPage logPage = (LoginPage)hPage.clickLKLink();
        
        Assert.assertTrue(logPage.isOpened());
 
        hPage = logPage.fillLoginFormAndContinue(this.user.getEmail(), this.user.getPassword());

        Assert.assertTrue(hPage.isOpened());
    }
    
    
    @Test
    @DisplayName("Test login via registration page")
    public void loginViaRegistrationPageTest () {
        
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.open();
        
        Assert.assertTrue(regPage.isOpened());
        
        LoginPage logPage = regPage.clickLoginLink();
        
        Assert.assertTrue(logPage.isOpened());
 
        HomePage hPage = logPage.fillLoginFormAndContinue(this.user.getEmail(), this.user.getPassword());

        Assert.assertTrue(hPage.isOpened());
    }
    
    @Test
    @DisplayName("Test login via password recovery")
    public void loginViaPasswordRecoveryTest () {
        
        PasswordRecoveryPage prPag = new PasswordRecoveryPage(driver);
        prPag.open();
        
        Assert.assertTrue(prPag.isOpened());
        
        LoginPage logPage = prPag.clickLoginLink();
        
        Assert.assertTrue(logPage.isOpened());
 
        HomePage hPage = logPage.fillLoginFormAndContinue(this.user.getEmail(), this.user.getPassword());

        Assert.assertTrue(hPage.isOpened());
    }
    
    @After
    public void teardown() {
        
        this.service.deleteUser(this.accessToken);
        driver.quit();
    }

}
