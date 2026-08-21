package com.demoqa.pages.bookStore;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName")
    WebElement userNameInput;
    @FindBy(id = "password")
    WebElement userPasswordInput;

    public LoginPage enterUserDate(String username, String password) {
        type(userNameInput, username);
        type(userPasswordInput, password);
        return this;
    }

    @FindBy(id = "login")
    WebElement loginButton;

    public LoginPage clickOnLoginButton() {
        click(loginButton);
        return this;
    }

    @FindBy(id = "userName-value")
    WebElement userNameValue;

    public LoginPage verifyUserName(String name) {
        Assertions.assertTrue(userNameValue.getText().contains(name));
        return this;
    }
}
