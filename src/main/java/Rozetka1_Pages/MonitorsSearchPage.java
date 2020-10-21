package Rozetka1_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MonitorsSearchPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public MonitorsSearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    By laptopsMenuLink = By.xpath("//aside//a[contains(@href,'computers-notebooks')]");
    By monitorsLink = By.xpath("//ul/li/ul/li/a[@href='https://hard.rozetka.com.ua/monitors/c80089/']");
    By productsLink = By.xpath("//div/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a");
    By okPriceFilterButton = By.cssSelector("button.slider-filter__button");
    By topPriceFilterInput = By.xpath("//input[2][@class='slider-filter__input ng-untouched ng-pristine ng-valid']");

    public void performHoverOnSideMenu() {
        wait.until(presenceOfElementLocated(laptopsMenuLink)); // waiting for the link appearance
        WebElement onHover = webDriver.findElement(laptopsMenuLink);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(onHover).perform();
        wait.until(presenceOfElementLocated(monitorsLink));
    }

    public void clickOnHoverMenuLink() {
        webDriver.findElement(monitorsLink).click();
        waitForProdAppearance();
    }

    public void filterProductsByPrice(String topPriceValue) {
        WebElement topPriceInput = webDriver.findElement(topPriceFilterInput);
        topPriceInput.clear();
        topPriceInput.sendKeys(topPriceValue);
        webDriver.findElement(okPriceFilterButton).click();
        waitForProdAppearance();
    }

    public void navigateBack() {
        webDriver.navigate().back();
        waitForProdAppearance();
    }

    private void waitForProdAppearance() {
        wait.until(presenceOfElementLocated(productsLink));
    }

}
