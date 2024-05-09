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

public class ProfileTest {

    public static final boolean USE_YANDEX = false;
    private  WebDriver driver;
    
    private User user = new User("Anna","g" + System.currentTimeMillis() +"@gmail.com",
             "p12345");
    private String accessToken;
    private BurgerService service = new BurgerService();
    
    private LKPage lkPage;
    
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
        
        LoginPage logPage = new LoginPage(driver);
        logPage.open();
        logPage.isOpened();
 
        HomePage hPage = logPage.fillLoginFormAndContinue(this.user.getEmail(), this.user.getPassword());
        hPage.isOpened();
        
        LKPage lkPage = (LKPage)hPage.clickLKLink();
        lkPage.isOpened();
        
        this.lkPage = lkPage;
    }
    
    @Test
    @DisplayName("Open constructor from LK")
    public void openConstructorTest () {
        Assert.assertTrue(this.lkPage.isOpened());
        assertThat(this.lkPage, instanceOf(LKPage.class));
        
        HomePage hPage = this.lkPage.clickConstructorLink();
        Assert.assertTrue(hPage.isOpened());
    }
    
    @Test
    @DisplayName("Exit from authenticated mode")
    public void exitFromAuthModeTest () {
        Assert.assertTrue(this.lkPage.isOpened());
        assertThat(this.lkPage, instanceOf(LKPage.class));
       
        LoginPage logPage = this.lkPage.exit();
        Assert.assertTrue(logPage.isOpened());
    }
    
   
    
    @After
    public void teardown() {
        
        this.service.deleteUser(this.accessToken);
        driver.quit();
    }

}
