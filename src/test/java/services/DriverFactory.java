package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    
    public static final String USE_BROWSER = "chrome";
    
    public static WebDriver getDriver() {
        
        WebDriver result;
        
        switch(USE_BROWSER) {
            case "firefox": 
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("-headless");
                result = new FirefoxDriver(ffOptions);
                break;
            case "yandex":
                ChromeOptions yOptions = new ChromeOptions();
                yOptions.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                yOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                result = new ChromeDriver(yOptions);
                break;
            default:
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                result = new ChromeDriver(cOptions);
        }
        
        return result;
    }
    
}