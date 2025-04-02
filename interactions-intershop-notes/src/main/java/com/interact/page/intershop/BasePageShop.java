package com.interact.page.intershop;

import com.interact.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public abstract class BasePageShop<T extends BasePageShop<T>> extends BasePage<BasePageShop<T>> {
    @FindBy(id = "ak-top")
    private WebElement toTopButton;
    @FindBy(id = "top-footer")
    private WebElement footerContactForm;

    public T scrollPageUpWithButton() {
        toTopButton.click();
        wait.until(ExpectedConditions.invisibilityOf(toTopButton));
        return (T) this;
    }

    public T scrollPageDownToFooter() {
        js.executeScript("arguments[0].scrollIntoView();", footerContactForm);
        wait.until(ExpectedConditions.visibilityOf(toTopButton));
        return (T) this;
    }
}
