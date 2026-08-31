package com.demoqa.utils;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Random;

public class MyListener implements WebDriverListener {
    Logger logger = LoggerFactory.getLogger(MyListener.class);

    private final WebDriver driver;

    public MyListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        logger.error("The test has a problem!!!");
        logger.error("**************************");
        logger.error("Method --> {}",method.getName());
        logger.error("Target exception --> {}",e.getTargetException());

        int i = new Random().nextInt(1000);
        String link = "screenshots/screen_"+ i + ".png";
        logger.error("Screen with error -->", link);

        File tmp = ((TakesScreenshot) driver).getScreenshotAs((OutputType.FILE));
        try {
            Files.copy(tmp, new File(link));
        } catch (IOException ex) {
            logger.error("Failed to save screenshot",ex);
        }

    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logger.info("We opened the site {}",url);
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        WebDriverListener.super.afterAnyCall(target, method, args, result);
    }

    @Override
    public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
        logger.info("{}",actions);
        logger.info("*********************************************************");
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("We enter {} to element {}",keysToSend,element);
        logger.info("*********************************************************");

    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("We cliked on {}",element);
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        logger.info("{} contains {}", element,result);
        logger.info("*********************************************************");
    }
}
