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
import pages.HomePage;
public class ExitTest {

    public static final boolean USE_FIREFOX = true;
    private  WebDriver driver;
    @Before
    public void setup() {
        if (USE_FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            //  options.addArguments("-headless");
            driver = new FirefoxDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            //    options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }
    }
    @Test
    public void checkExitTest () throws Exception {
        // переход на страницу тестового приложения

        HomePage homePage= new HomePage(driver);
        homePage.open();
        Thread.sleep(2000);

        homePage.clickButtonEntranceAccount();

        Assert.assertTrue(driver.findElement(homePage.entranceSelectorXPath).isDisplayed());

        homePage.fillEntranceTheFormAndContinue("ert123@mail.ru", "12345a");
        Thread.sleep(1000);
        homePage.clickButtonEntranceAfterForm();

        homePage.clickCabinetLink();

        homePage.clickExitButton();

        Assert.assertTrue(driver.findElement(homePage.waitForEntranceLabelSelectorXPath).isDisplayed());
        // надо доделать с wait когда зайдет - не работает тест
    }

}
