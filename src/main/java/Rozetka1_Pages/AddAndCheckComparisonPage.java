package Rozetka1_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class AddAndCheckComparisonPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public AddAndCheckComparisonPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 5);
    }

    By monitorsPrice = By.cssSelector("a.goods-tile__picture");
    By compareButton = By.xpath("//button[@class='compare-button']");
    By compareIconSearch = By.xpath("//span[@class='header-actions__button-counter']");
    By comparePopupLink = By.cssSelector("i.header-actions__button-icon");
    By prodNumberAdded = By.xpath("//a[@class='comparison-modal__link']//span[@class='comparison-modal__quantity']");
    By comparisonPageLink = By.cssSelector("a.comparison-modal__link");
    By priceTwoWaiter = By.xpath("//li[2][@class='products-grid__cell']//img");

    public void findAndSelectProduct(int i) {
        List<WebElement> monitorsByPrice = webDriver.findElements(monitorsPrice);
        monitorsByPrice.get(i).click();
        wait.until(presenceOfElementLocated(compareButton));
    }

    public void addToComparing() {
        webDriver.findElement(compareButton).click();
        wait.until(presenceOfElementLocated(compareIconSearch));
    }

    public String compareIconCheck() {
        WebElement compareIcon = webDriver.findElement(compareIconSearch);
        return compareIcon.getText();
    }

    public String allProductsAddedToCompare() {
        webDriver.findElement(comparePopupLink).click();
        WebElement verifyProductsAdded = webDriver.findElement(prodNumberAdded);
        return verifyProductsAdded.getText();
    }

    public void navigateToComparisonPage() {
        webDriver.findElement(comparisonPageLink).click();
        wait.until(presenceOfElementLocated(priceTwoWaiter));
    }

}
