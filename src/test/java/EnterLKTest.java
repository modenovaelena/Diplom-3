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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class EnterLKTest {

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
    @DisplayName("Test enter LK")
    public void enterLKViaELKLinkTest () {
        
        HomePage hPage = new HomePage(driver);
        hPage.open();
        
        Assert.assertTrue(hPage.isOpened());
        
        LoginPage logPage = (LoginPage)hPage.clickLKLink();
        
        Assert.assertTrue(logPage.isOpened());
 
        hPage = logPage.fillLoginFormAndContinue(this.user.getEmail(), this.user.getPassword());

        Assert.assertTrue(hPage.isOpened());
        
        Page lkPage = hPage.clickLKLink();
        Assert.assertTrue(lkPage.isOpened());
        
        assertThat(lkPage, instanceOf(LKPage.class));
      
    }
    
   
    
    @After
    public void teardown() {
        
        this.service.deleteUser(this.accessToken);
        driver.quit();
    }

}
