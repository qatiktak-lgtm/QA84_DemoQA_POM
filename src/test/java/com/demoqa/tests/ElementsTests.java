package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.BrokenLinksImagesPage;
import com.demoqa.pages.elements.ButtonPage;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.pages.elements.UploadPage;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;


public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonPage buttons;
    TextBoxPage textBox;
    BrokenLinksImagesPage brokenLinks;
    UploadPage upload;


    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        buttons = new ButtonPage(driver);
        new HomePage(driver).getElements();
        textBox = new TextBoxPage(driver);
        brokenLinks =new BrokenLinksImagesPage(driver);
        upload = new UploadPage(driver);
    }

    @Test
    public void doubleClickTest() {
        sidePanel.getButtons();
        buttons.doubleClick()
                .verifyDoubleClick("double click"); // или "You have done a double click"
    }

    @Test
    public void rightClickTest() {
        sidePanel.getButtons();
        buttons.rightClick()
                .verifyRightClick("You have done a right click");
    }

    @Test
    public void copyPastTest() {
        sidePanel.getTextBox();
        textBox.copyPast("Berlin,12435")
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    public void textBoxWithParameterTest(String name, String email, String address) {
        sidePanel.getTextBox();
        textBox.enterPersonalData(name, email, address)
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Data84.csv")
    public void textBoxFromCsvFileParameterTest(
            String name,
            String email,
            String address) {
        sidePanel.getTextBox();
        textBox.enterPersonalData(name, email, address)
                .clickOnSubmitButton()
                .verifyAddress();
        logger.info("TEST FOR  '{}' TEST", name);
    }

    @Test
    public void javaScriptExecutorTest(){
        sidePanel.getTextBox();
        textBox.enterPersonalDataWithJS("Bob Marly", "bobm@gmail.com")
                .clickOnSubmitWithJS()
                .getInnerText()
                .verifyUrl()
                .refreshWithJS()
                .navigateWithJS("https://icarro-v1.netlify.app/let-car-work")
                .verifyFaveIconTitle();
    }

    @Test
    @Tag("smoky")
    public void getAllLinksTest(){
        sidePanel.getBrokenLinkImages();
        brokenLinks.getAllLinks();
    }

    @Test
    public void checkBrokenLinksTest(){
        sidePanel.getBrokenLinkImages();
        brokenLinks.checkBrokenLinks();
    }

    @Test
    public void checkBrokenImages(){
        sidePanel.getBrokenLinkImages();
        brokenLinks.checkBrokenImages();
    }

    @Test
    public void performKeyEventTest(){
        sidePanel.getUpload();
        upload.performKeyEvent()
                .verifyFilePath("C:\\Users\\7500308\\D1.txt");  //  Неправильно!!! передавать в тест путь скопированный с браузера в виде C:\fakepath\D1.txt
    }
}
