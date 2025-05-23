package com.pizzeria.page.base;

import com.pizzeria.page.component.Footer;
import com.pizzeria.page.component.Header;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public abstract class BasePage<T extends BasePage<T>> {
    @Setter
    protected static WebDriver driver;
    protected final WebDriverWait wait;
    protected final Header header;
    protected final Footer footer;
    protected final Actions actions;

    @FindBy(id = "ak-top")
    private WebElement toTopButton;

    public BasePage() {
        this.header = new Header(driver);
        this.footer = new Footer(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTotalCartPriceFromHeader() {
        return header.getCartTotal();
    }

    public T openSocialMediaInNewTab(String name) {
        actions.moveToElement(footer.getContactsList()
                        .stream()
                        .filter(contact -> name.equals(contact.getText()))
                        .findFirst()
                        .map(contact -> contact.findElement(By.tagName("a")))
                        .orElseThrow()
                ).click()
                .perform();
        return (T) this;
    }

    public T scrollPageDown() {
        actions.scrollToElement(footer.getSiteInfo())
                .perform();
        return (T) this;
    }

    public T scrollPageUpWithToTopButton() {
        toTopButton.click();
        wait.until(ExpectedConditions.invisibilityOf(toTopButton));
        return (T) this;
    }
}
