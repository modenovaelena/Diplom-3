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

    public static final boolean USE_YANDEX = false;
    private  WebDriver driver;
    
    private User user = new User("Anna","g" + System.currentTimeMillis() +"@gmail.com",
             "p12345");
    private String accessToken;
    private BurgerService service = new BurgerService();
    
    @Before
    public void setup() throws Exception {
        if (USE_YANDEX) {
           throw new Exception("Unsupported driver");
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            //options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }
        
        RestAssured.baseURI = BurgerService.BASE_URI;
        this.accessToken = this.service.createUser(this.user);
    }
    
    @Test
    @DisplayName("Positive registration scenario")
    public void successfullRegistrationTest () {
        LoginPage logPage = new LoginPage(driver);
        logPage.open();
        
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
