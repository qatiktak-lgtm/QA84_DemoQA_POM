package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

public class BrokenLinksImagesPage extends BasePage {


    public BrokenLinksImagesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='col-12 mt-4 col-md-6 col-xl-7']//a")    //@FindBy(css= "div.col-md-6 a")
    List<WebElement> allLinks;

    public BrokenLinksImagesPage getAllLinks() {
        //size
        System.out.println("Total links on the page = " + allLinks.size());
        //list
        String url; //= ""
        Iterator<WebElement> iterator = allLinks.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
        }

        return this;
    }


    public BrokenLinksImagesPage checkBrokenLinks() {
        for (int i = 0; i < allLinks.size(); i++) {
            WebElement element = allLinks.get(i);
            String url = element.getAttribute("href");
            verifyLinks(url);
        }
        softly.assertAll();
        return this;
    }

    @FindBy(css = "img")
    List<WebElement> images;

    public BrokenLinksImagesPage checkBrokenImages() {
        System.out.println("Total images on the page = " + images.size());
        for (int i = 0; i < images.size(); i++) {
            WebElement image = images.get(i);
            String imageUrl = image.getAttribute("src");
            verifyLinks(imageUrl);
            try {
                boolean imageDisplayed = (Boolean)js.executeScript
                        ("return (typeof arguments[0].naturalWidth!=undefined && arguments[0].naturalWidth>0);",image);
                if(imageDisplayed){
                    // System.out.println("Display -> OK");
                    softly.assertThat(imageDisplayed);

                }else {
                    // System.out.println("Display - > Broken");
                    softly.fail("Broken image ->" + imageUrl);
                }
            } catch (Exception e) {
                // System.out.println("Error occurred");
                softly.fail("Error occurred");
            }
        }
        softly.assertAll();
        return this;
    }

}
