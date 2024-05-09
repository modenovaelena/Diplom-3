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

public class ConstructorTest {

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
    @DisplayName("Check if section switch is working")
    public void sectionSwitchTest () throws Exception {
        HomePage page = new HomePage(driver);
        page.open();
        Assert.assertTrue(page.isOpened());
        
        page.switchToSoucesSection();
        Thread.sleep(2000);
        Assert.assertTrue(page.isSoucesSectionSelected());
        
        page.switchToBunsSection();
          Thread.sleep(2000);
        Assert.assertTrue(page.isBunsSectionSelected());
        
        page.isFillingsSectionSelected();
          Thread.sleep(2000);
        Assert.assertTrue(page.isSoucesSectionSelected());
    }
    
    
    @After
    public void teardown() {
        driver.quit();
    }

}
