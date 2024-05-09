package pages;

import org.openqa.selenium.By;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private  WebDriver driver;

    public final By privateOfficeSelectorXPath= By.xpath ( ".//p[text() = 'Личный Кабинет']/..");

    public final By waitForEntranceLabelSelectorXPath= By.xpath (".//h2[text() = 'Вход']");

    public final By entranceAccountLabelSelectorXPath= By.xpath (".//button[text()='Войти в аккаунт']");
    public final By passwordFieldSelectorXPath = By.xpath (".//label[text()='Пароль']/../input") ;
    public final By emailFieldSelectorXPath= By.xpath (".//label[text()='Email']/../input");

    public final By nameFieldSelectorXPath= By.xpath (".//label[text()='Имя']/../input");

    public final By entranceSelectorXPath= By.xpath (".//button[text()='Войти']");

    public final By exitSelectorXPath= By.xpath (".//button[text()='Выход']");
    public final By makeOrderSelectorXPath= By.xpath (".//button[text()='Оформить заказ']");
    public final By constructorLinkSelectorXPath= By.xpath (".//div/header/nav/ul/li/a/p[text() = 'Конструктор']/..");

    public final By logotipStellarBurgerLinkSelectorXPath= By.xpath (".//div/header/nav/div/a");

    public final By buttonRegister = By.xpath (".//a[text() = 'Зарегистрироваться']");

    public final By buttonRegisterAfterFillingForm = By.xpath(".//button[text()='Зарегистрироваться']");
    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    public void clickButtonEntranceAccount()
    {
        driver.findElement(entranceAccountLabelSelectorXPath).click();

    }
    public void clickButtonStellarAccount()
    {
        driver.findElement(logotipStellarBurgerLinkSelectorXPath).click();

    }
    public void clickCabinetLink()
    {
      //  new WebDriverWait(driver, 10)
        //        .until(ExpectedConditions.elementToBeClickable(privateOfficeSelectorXPath));

        driver.findElement(privateOfficeSelectorXPath).click();


    }
    public void clickbuttonRegister()
    {
        driver.findElement(buttonRegister).click();


    }
    public void clickExitButton()
    {
        driver.findElement(exitSelectorXPath).click();


    }
    public void clickButtonRegisterAfterForm()
    {
        driver.findElement(buttonRegisterAfterFillingForm).click();

    }
    public void clickButtonConstructor()
    {
        driver.findElement(constructorLinkSelectorXPath).click();

    }

    public void clickButtonEntranceAfterForm()
    {
        driver.findElement(entranceSelectorXPath).click();

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(makeOrderSelectorXPath));

    }

    public void fillRegisterTheFormAndContinue(String name, String email, String password) {

        driver.findElement(nameFieldSelectorXPath).clear();
        driver.findElement(nameFieldSelectorXPath).sendKeys(name);

        driver.findElement(emailFieldSelectorXPath).clear();
        driver.findElement(emailFieldSelectorXPath).sendKeys(email);

        driver.findElement(passwordFieldSelectorXPath).clear();
        driver.findElement(passwordFieldSelectorXPath).sendKeys(password);

    }

    public void fillEntranceTheFormAndContinue(String email, String password) {

        driver.findElement(emailFieldSelectorXPath).clear();
        driver.findElement(emailFieldSelectorXPath).sendKeys(email);

        driver.findElement(passwordFieldSelectorXPath).clear();
        driver.findElement(passwordFieldSelectorXPath).sendKeys(password);

    }
}
