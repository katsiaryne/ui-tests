package com.pizzeria.page.component;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class Footer {
    private final WebDriver driver;

    @FindBy(xpath = "//footer//div[@class='cta-desc_simple']//p")
    private List<WebElement> contactsList;
    @FindBy(css = "div.site-info")
    private WebElement siteInfo;

    public Footer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
