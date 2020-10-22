package Rozetka1_FactoryPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AddAndCheckComparisonFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public AddAndCheckComparisonFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 5);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "a.goods-tile__picture")
    List<WebElement> monitorsPrice;
    @FindBy(xpath = "//button[@class='compare-button']")
    WebElement compareButton;
    @FindBy(xpath = "//span[@class='header-actions__button-counter']")
    WebElement compareIconSearch;
    @FindBy(css = "i.header-actions__button-icon")
    WebElement comparePopupLink;
    @FindBy(xpath = "//a[@class='comparison-modal__link']//span[@class='comparison-modal__quantity']")
    WebElement prodNumberAdded;
    @FindBy(css = "a.comparison-modal__link")
    WebElement comparisonPageLink;
    @FindBy(xpath = "//li[2][@class='products-grid__cell']//img")
    WebElement priceTwoWaiter;

    public void findAndSelectProduct(int i) {
        monitorsPrice.get(i).click();
        wait.until(visibilityOf(compareButton));
    }

    public void addToComparing() {
        compareButton.click();
        wait.until(visibilityOf(compareIconSearch));
    }

    public String compareIconCheck() {
        return compareIconSearch.getText();
    }

    public String allProductsAddedToCompare() {
        comparePopupLink.click();
        return prodNumberAdded.getText();
    }

    public void navigateToComparisonPage() {
        comparisonPageLink.click();
        wait.until(visibilityOf(priceTwoWaiter));
    }

}
