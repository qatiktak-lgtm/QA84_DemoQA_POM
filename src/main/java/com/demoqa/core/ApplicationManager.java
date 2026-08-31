package com.demoqa.core;

import com.demoqa.utils.LoggerWriter;
import com.demoqa.utils.MyListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ApplicationManager {
    String browser;
    protected WebDriver driver;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public WebDriver start() {
        switch ((browser)){
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String startTime = LocalDateTime.now().format(formatter);
        LoggerWriter.logInfo("~~~~~~~~~~~~~~~~~ The test has started ~~~~~~~~~~~~~~~~~~ ");
        LoggerWriter.logInfo(startTime);

        WebDriverListener listener = new MyListener(driver);
        driver = new EventFiringDecorator<>(listener).decorate(driver);

        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait
                = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoggerWriter.logInfo("✅ Browser initialized successfully");
        return driver;
    }

    public WebDriver stop() {
        if (driver != null) {
            LoggerWriter.logInfo("Closing browser...");
            driver.quit();
            LoggerWriter.logInfo("✅ Browser closed");
        }
        return driver;
    }
}
