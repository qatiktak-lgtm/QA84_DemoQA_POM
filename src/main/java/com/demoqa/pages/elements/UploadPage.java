package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;

public class UploadPage extends BasePage {

    Robot robot;

    public UploadPage(WebDriver driver) {
        super(driver);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(id = "uploadFile")
    WebElement uploadFile;

    public UploadPage performKeyEvent() {
        //clickWithJS(uploadFile,0,300);
        clickWithRectangle(uploadFile);
        //press SHIFT
        pause(1000);
        robot.keyPress(KeyEvent.VK_SHIFT);
        //press d(upper case as SHIFT key is pressed)
        pause(1000);
        robot.keyPress(KeyEvent.VK_D);
        // release SHIFT
        robot.keyRelease(KeyEvent.VK_SHIFT);
        // press 1, ., t, x, t  I
        pause(1000);
        robot.keyPress(KeyEvent.VK_1);
        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyPress(KeyEvent.VK_T);
                // press ENTER
        pause(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        return this;
    }

    @FindBy(id = "uploadedFilePath")
    WebElement uploadedFilePath;
    public UploadPage verifyFilePath(String path) {

        String fileName = Paths.get(path).getFileName().toString();

        WebElement filePath = getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("uploadedFilePath")));

        String actualPath = filePath.getText();

        Assertions.assertTrue(actualPath.contains(fileName),
                "Expected file: " + fileName
                        + ", actual value: " + actualPath);
        return this;
    }
}
