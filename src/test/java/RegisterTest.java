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

public class RegisterTest {

    public static final boolean USE_YANDEX = false;
    private  WebDriver driver;
    
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
    }
    @Test
    public void successfullRegistrationTest () throws Exception {
        RegistrationPage regPage= new RegistrationPage(driver);
        regPage.open();
        
        Assert.assertTrue(regPage.isOpened());
 
        LoginPage logPage = regPage.fillRegistrationFormAndContinue("Anna",
            "g" + System.currentTimeMillis() + "@gmail.com",
            "12345q");

        Assert.assertTrue(logPage.isOpened());
    }
    
    @After
    public void teardown() {
        driver.quit();
    }

}
