package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ButtonPage extends BasePage {
    public ButtonPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "doubleClickBtn")
    WebElement doubleClickBtn;

    public ButtonPage doubleClick() {
        scrollWithJS(0,100); //nen можно играться с координатой у или попробовать метод Евгении
        actions.doubleClick(doubleClickBtn).perform();


        return this;
    }

    @FindBy(id="doubleClickMessage")
    WebElement doubleClickMessage;

    public ButtonPage verifyDoubleClick(String text) {
        Assertions.assertTrue(isContainsText(text,doubleClickMessage));
        return this;
    }

    @FindBy(id="rightClickBtn")
    WebElement rightClickBtn;
    public ButtonPage rightClick() {
        scrollWithJS(0,-100);
        actions.contextClick(rightClickBtn).perform();
        return this;
    }

    @FindBy(id = "rightClickMessage")
    WebElement rightClickMessage;
    public ButtonPage verifyRightClick(String text) {
        Assertions.assertTrue(isContainsText(text,rightClickMessage));
        return this;
    }
}
