package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    public static WebDriver driver = null;

    public static WebDriver getDriver() {
        if(Objects.nonNull(driver)) {
            return driver;
        } else {
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver;
        }
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

    public WebDriverWait getWaitInSeconds(long sec) {
        return new WebDriverWait(driver, 10);
    }
}
