package com.demoqa.core;

import com.demoqa.utils.LoggerWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@ExtendWith(LoggerWriter.class)

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ApplicationManager app = new ApplicationManager(System.getProperty("browser","chrome"));

    @BeforeEach
    public void init(){
        driver = app.start();
    }

    @AfterEach
    public void tearDown() {
        driver = app.stop();
    }
}
