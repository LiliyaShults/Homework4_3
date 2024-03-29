import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;


public class TestWithLogin {
    private WebDriver driver;
    private final Logger logger = LogManager.getLogger(TestWithLogin.class);


    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void driverStart() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        logger.info("Драйвер поднят");
    }

    @AfterEach
    public void driverStop() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLogin() {
        driver.get(" https://otus.ru");
        logger.info("Открыта страница отус");

        WebElement elEnter = driver.findElement(By.xpath("//./*[text() = 'Войти']"));
        elEnter.click();
        WebElement elFocus = driver.findElement(By.xpath("//input[@name]/.."));
        elFocus.click();
        //WebElement elFocus = driver.findElement(By.xpath("//input[@name='email']"));
        //elFocus.click();
        WebElement elEmail = driver.findElement(By.xpath("//input[@name]"));
        elEmail.sendKeys("ineuxlm063@tempemail.ru");

        elFocus = driver.findElement(By.xpath("//input[@type=\"password\"]/.."));
        elFocus.click();
        WebElement elPassword = driver.findElement(By.xpath("//input[@type=\"password\"]"));
        elPassword.sendKeys("Qwerty123456!");
        elEnter = driver.findElement(By.xpath("//button[./*[text() = 'Войти']]"));
        elEnter.click();
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//./*[text() = 'Войти']")));

        String present;
        try {
            driver.findElement(By.xpath("//./*[text() = 'Войти']"));
            present ="Вход осуществлен. Кнопка Выйти найдена";
            logger.info("Вход осуществлен. Кнопка Выйти найдена");

        } catch (NoSuchElementException e) {
            present = "Вход не осуществлен. Кнопка Выйти не найдена.";
            logger.info("Вход не осуществлен. Кнопка Выйти не найдена.");
        }
        System.out.println(present);
        Set<Cookie>cookies = driver.manage().getCookies();
        System.out.println("cookie=====>" + cookies);
        logger.info(cookies);
        logger.info("Драйвер закрыт");
    }

    }
