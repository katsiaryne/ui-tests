package com.pizzeria.page.base;

import com.pizzeria.page.component.Footer;
import com.pizzeria.page.component.Header;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public abstract class BasePage {
    @Setter
    protected static WebDriver driver;
    protected final WebDriverWait wait;
    protected final Header header;
    protected final Footer footer;

    public BasePage() {
        this.header = new Header(driver);
        this.footer = new Footer(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public String getTotalCartPriceFromHeader() {
        return header.getCartTotal();
    }
}
