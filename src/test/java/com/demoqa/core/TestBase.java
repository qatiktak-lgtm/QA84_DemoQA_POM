package com.demoqa.core;

import com.demoqa.utils.DriverManager;
import com.demoqa.utils.LoggerWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(LoggerWriter.class)

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ApplicationManager app = new ApplicationManager(System.getProperty("browser","chrome"));
    public static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    @BeforeEach
    public void init(){
        driver = app.start();
        DriverManager.setDriver(driver);
        logger.info("✅ WebDriver initialized and stored in ThreadLocal");
    }

    @AfterEach
    public void tearDown() {
        WebDriver tempDriver = driver;
        driver = app.stop();
        DriverManager.removeDriver();
        logger.info("✅ WebDriver cleaned up");
    }
}
