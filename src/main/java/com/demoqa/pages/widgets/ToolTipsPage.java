package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ToolTipsPage extends BasePage {
    public ToolTipsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "toolTipButton")
    WebElement toolTipButton;
    public ToolTipsPage hoversOnToolTips() {
        //scrollWithJS(0,200);
        actions.moveToElement(toolTipButton).perform();
        return this;
    }


    public ToolTipsPage verifyToolTips(String value) {
       // waitIsElementVisibility(buttonToolTip,2);
        Assertions.assertEquals(value, getValue(toolTipButton,"aria-describedby"));
        return this;
    }

}
