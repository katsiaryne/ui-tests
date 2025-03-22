package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import com.pizzeria.page.component.AccountPageNavigation;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

@Getter
public class EditAccountPage extends BasePage {
    private final AccountPageNavigation navigation;
    private final File uploadFile = new File("../test/resources/file.jpg");

    @FindBy(xpath = "//input[@value='Файл...']")
    private WebElement addFileButton;
    @FindBy(xpath = "//button[@value='Save changes']")
    private WebElement saveChangesButton;

    public EditAccountPage() {
        this.navigation = new AccountPageNavigation(driver);
        PageFactory.initElements(driver, this);
    }

    public EditAccountPage uploadNewFile() {
        addFileButton.sendKeys(uploadFile.getAbsolutePath());
        return this;
    }
}
