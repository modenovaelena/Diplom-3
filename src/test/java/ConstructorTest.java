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
import org.assertj.core.api.SoftAssertions;

public class ConstructorTest {

    private  WebDriver driver;
    
    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
    }
    
    @Test
    @DisplayName("Check if we can switch to Souces")
    public void sectionSwitchToSoucesTest () {
        HomePage page = new HomePage(driver);
        page.open();
        Assert.assertTrue(page.isOpened());
        Assert.assertTrue(page.isBunsSectionSelected());
        
        page.switchToSoucesSection();
        Assert.assertTrue(page.isSoucesSectionSelected());
    }
    
    @Test
    @DisplayName("Check if we can switch to Fillings")
    public void sectionSwitchToFillingsTest () {
        HomePage page = new HomePage(driver);
        page.open();
        Assert.assertTrue(page.isOpened());
        Assert.assertTrue(page.isBunsSectionSelected());
        
        page.switchToFillingsSection();
        Assert.assertTrue(page.isFillingsSectionSelected());
    }
    
    
    @Test
    @DisplayName("Check if we can switch to Fillings and back to Bulky")
    public void sectionSwitchToBulkyTest () {
        HomePage page = new HomePage(driver);
        page.open();
        Assert.assertTrue(page.isOpened());
        Assert.assertTrue(page.isBunsSectionSelected());
        
        page.switchToFillingsSection();
        Assert.assertTrue(page.isFillingsSectionSelected());
        
        page.switchToBunsSection();
        Assert.assertTrue(page.isBunsSectionSelected());
    }
    
    
    @After
    public void teardown() {
        driver.quit();
    }

}
