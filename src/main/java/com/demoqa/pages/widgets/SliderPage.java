package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SliderPage extends BasePage {
    public SliderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".range-slider")
    WebElement rangeSlider;
    public SliderPage moveSlider() {
        scrollWithJS(0,30);
        actions.dragAndDropBy(rangeSlider,450,0).perform();
        return this;
    }
    //oT Zolotarenko
    // public SliderPage moveSlider() {
    // js.executeScript("arguments[0].scrollIntoView(true);", rangeSlider);
    // scrollWithJS(0,30);
    // OT Stupachenkp
    // actions.dragAndDropBy(rangeSlider,450,0).perform();
    // rangeSlider.sendKeys(Keys.END);
    // return this;
    //


    @FindBy(id = "sliderValue")
    WebElement sliderValue;
    public SliderPage verifySliderValue(String number) {
        Assertions.assertEquals(number, getValue(sliderValue, "value"));
        return this;
    }

    public SliderPage moveSliderTo(int value) {
        rangeSlider.sendKeys(Keys.HOME);
        for (int i = 0; i < value; i++) {
            rangeSlider.sendKeys(Keys.ARROW_RIGHT);
        }
        return this;
    }
}
