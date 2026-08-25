package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NestedFramesPage extends BasePage {
    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "frame1")
    WebElement frame1;
    @FindBy(css = "body")
    WebElement body;
    @FindBy(css = "iframe")
    List<WebElement> iframes;

    public NestedFramesPage verifyNestedFrames() {
        //switch to parent frame  by id  нужно добавить новую библиотеку с Maven cental для softAssert
        driver.switchTo().frame(frame1);
//assert by text
        softly.assertThat(isContainsText("Parent frame", body));
//assert by total numbers of frames
        softly.assertThat(iframes.size()).isEqualTo(1);
//switch to child frame by index
        driver.switchTo().frame(0);
        softly.assertThat(isContainsText("", body));

//assert by text
        softly.assertThat(isContainsText("Child Iframe",body));

//return to parent
        driver.switchTo().parentFrame();

//assert by  text
        softly.assertThat(isContainsText("Parent frame", body));
        softly.assertAll();
        return this;
    }
}
