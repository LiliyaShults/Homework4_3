import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class TestWithLogin {
    WebDriver driver;
    private final Logger logger = LogManager.getLogger(TestWithLogin.class);

    @Test
    public void Log(){
        logger.info("this is info");
    }

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void driverStart() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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

        WebElement elEnter = driver.findElement(By.xpath("//*[@id=\"__next\"]//section/div[1]/button"));
        elEnter.click();

        WebElement elFocus = driver.findElement(By.xpath("//input[@name]/.."));
        elFocus.click();
        WebElement elEmail = driver.findElement(By.xpath("//input[@name]"));
        elEmail.sendKeys("ineuxlm063@tempemail.ru");

        elFocus = driver.findElement(By.xpath("//input[@type=\"password\"]/.."));
        elFocus.click();
        WebElement elPassword = driver.findElement(By.xpath("//input[@type=\"password\"]"));
        elPassword.sendKeys("Qwerty123456!");
        elEnter = driver.findElement(By.xpath("//button[./*[text() = 'Войти']]"));
        elEnter.click();
        try {
            Thread.sleep(3000);
        }
        catch (Exception ignored){}


        String present;
        try {
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/section/div[2]/span"));
            present ="Вход осуществлен. Имя пользователя найдено";
            logger.info("Вход осуществлен. Имя пользователя найдено");

        } catch (NoSuchElementException e) {
            present = "Вход не осуществлен. Имя пользователя не найдено.";
            logger.info("Вход не осуществлен. Имя пользователя не найдено.");
        }
        System.out.println(present);
        Set<Cookie>cookies = driver.manage().getCookies();
        System.out.println("cookie=====>" + cookies);
        logger.info(cookies);
        logger.info("Драйвер закрыт");
    }

    }
