package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextBoxPage extends BasePage {
    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    public TextBoxPage copyPast(String address) {
        typeWithJS(currentAddress, address, 0, 400);
        //type current address text
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
//copy current address text
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
//press TAB to switch focus to permanent address
        actions.keyDown(Keys.TAB).perform();
//past current address text to  permanent address
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        return this;
    }

    @FindBy(id = "submit")
    WebElement submit;

    public TextBoxPage clickOnSubmitButton() {
        clickWithJS(submit, 0, 200);
        return this;
    }

    @FindBy(css = ".border #currentAddress")
    WebElement currentAddressResult;
    @FindBy(css = ".border #permanentAddress")
    WebElement permanentAddressResult;

    public TextBoxPage verifyAddress() {
        String[] current = currentAddressResult.getText().split(":");
        String[] permanent = permanentAddressResult.getText().split(":");
        Assertions.assertEquals(current[1], permanent[1]);
        return this;
    }

    @FindBy(id = "userName")
    WebElement userName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "permanentAddress")
    WebElement permanentAddress;

    public TextBoxPage enterPersonalData(String name, String email, String address) {
        typeWithJS(userName, name, 0, 0);
        type(userEmail, email);
        type(currentAddress, address);
        type(permanentAddress, address);
        return this;
    }

    public TextBoxPage enterPersonalDataWithJS(String name, String email) {
        js.executeScript("document.getElementById('userName').value='" + name + "';");
        js.executeScript("document.getElementById('userEmail').value='" + email + "';");
        return this;
    }

    public TextBoxPage clickOnSubmitWithJS() {
        js.executeScript("document.querySelector('#submit').click();");
        js.executeScript("document.querySelector('#submit').style.backgroundColor='red';");
        return this;
    }

    // Этого не должно быть в итоговой документауии это только для отладки
    public TextBoxPage getInnerText() {
        String innetText = js.executeScript("return document.documentElement.innerText;").toString();
        System.out.println(innetText);
        return this;
    }

    public TextBoxPage verifyUrl() {
        String url = js.executeScript("return document.URL").toString();
        System.out.println("URL = : " + url);
        return this;
    }

    public TextBoxPage refreshWithJS() {
        js.executeScript("history.go(0);");
        return this;
    }

    public TextBoxPage navigateWithJS(String url) {
        js.executeScript("window.location='" + url + "';");
        return this;
    }

    public TextBoxPage verifyFaveIconTitle() {
        String title = js.executeScript("return document.title;").toString();
        System.out.println(title);
        return this;
    }
}
