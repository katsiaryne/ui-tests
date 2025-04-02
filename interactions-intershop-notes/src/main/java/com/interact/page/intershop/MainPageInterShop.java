package com.interact.page.intershop;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.interact.config.ConfigProvider.URL_SHOP;

@Getter
public class MainPageInterShop extends BasePageShop<MainPageInterShop> {
    @FindBy(xpath = "//ul[@id='menu-primary-menu']//a[text()='Каталог']")
    private WebElement catalogButton;
    private final String catalogFirstSubMenuXpath = "//ul[@id='menu-primary-menu']//a[text()='%s']//following-sibling::*/li[1]";
    private final String catalogMenuButtonXpath = "//ul[@id='menu-primary-menu']//a[text()='%s']";

    public MainPageInterShop() {
        driver.get(URL_SHOP);
    }

    public CatalogPage moveToSubMenuOfCatalogButtonAndClick(String menuName, String subMenuName) {
        WebElement menuButton = driver.findElement(By.xpath(String.format(catalogMenuButtonXpath, menuName)));
        WebElement subMenuFirstItem = driver.findElement(By.xpath(String.format(catalogFirstSubMenuXpath, menuName)));
        WebElement subMenuItemButton = driver.findElement(By.xpath(String.format(catalogMenuButtonXpath, subMenuName)));

        actions.moveToElement(catalogButton)
                .moveToElement(menuButton)
                .moveToElement(subMenuFirstItem)
                .moveToElement(subMenuItemButton)
                .click()
                .perform();
        return new CatalogPage();
    }
}
