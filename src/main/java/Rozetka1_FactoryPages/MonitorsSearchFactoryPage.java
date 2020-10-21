package Rozetka1_FactoryPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MonitorsSearchFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public MonitorsSearchFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    @FindBy(xpath = "//aside//a[contains(@href,'computers-notebooks')]")
    WebElement laptopsMenuLink;
    @FindBy(xpath = "//ul/li/ul/li/a[@href='https://hard.rozetka.com.ua/monitors/c80089/']")
    WebElement monitorsLink;
    @FindBy(xpath = "//div/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a")
    WebElement productsLink;
    @FindBy(css = "button.slider-filter__button")
    WebElement okPriceFilterButton;
    @FindBy(xpath = "//input[2][@class='slider-filter__input ng-untouched ng-pristine ng-valid']")
    WebElement topPriceFilterInput;

    public void performHoverOnSideMenu() {
        wait.until(visibilityOf(laptopsMenuLink)); // waiting for the link appearance
        Actions actions = new Actions(webDriver);
        actions.moveToElement(laptopsMenuLink).perform();
        wait.until(visibilityOf(monitorsLink));
    }

    public void clickOnHoverMenuLink() {
        monitorsLink.click();
        waitForProdAppearance();
    }

    public void filterProductsByPrice(String topPriceValue) {
        topPriceFilterInput.clear();
        topPriceFilterInput.sendKeys(topPriceValue);
        okPriceFilterButton.click();
        waitForProdAppearance();
    }

    public void navigateBack() {
        webDriver.navigate().back();
        waitForProdAppearance();
    }

    private void waitForProdAppearance() {
        wait.until(visibilityOf(productsLink));
    }

}
