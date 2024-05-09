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

public class RegisterTest {

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
    public void checkRegisterTest () throws Exception {
        // переход на страницу тестового приложения

        HomePage homePage= new HomePage(driver);
        homePage.open();
        Thread.sleep(2000);

        homePage.clickCabinetLink();

        Assert.assertTrue(driver.findElement(homePage.buttonRegister).isDisplayed());
        homePage.clickbuttonRegister();
        homePage.fillRegisterTheFormAndContinue("Anna","gfhdsfh" + System.currentTimeMillis() + "@gmail.com", "12345q");
        homePage.clickButtonRegisterAfterForm();
    }

    @Test
    public void checkRegisterPasswordTest () throws Exception {
        // переход на страницу тестового приложения

        HomePage homePage= new HomePage(driver);
        homePage.open();
        Thread.sleep(2000);

        homePage.clickCabinetLink();

        Assert.assertTrue(driver.findElement(homePage.buttonRegister).isDisplayed());
        homePage.clickbuttonRegister();
        homePage.fillRegisterTheFormAndContinue("Anna","gfhdsfh" + System.currentTimeMillis() + "@gmail.com", "123");
        homePage.clickButtonRegisterAfterForm();
// нужно ли как-то обрабатывать ошибку
    }

   // @After
    //public void teardown() {
        // Закрой браузер
      //  driver.quit ();
    //}



}
