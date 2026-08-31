package com.demoqa.core;

import com.demoqa.utils.LoggerWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;
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

    @BeforeEach
    public void init(){
        driver = app.start();
    }

    @AfterEach
    public void tearDown() {
        driver = app.stop();
    }
}
