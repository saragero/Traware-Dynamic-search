package Initialization;

import io.cucumber.java.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverInit {
    protected static WebDriver driver = new ChromeDriver();

    @AfterAll
    protected static void quitDriver(){
        driver.quit();
    }
}

