package Rozetka2_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SearchByPricePage {
    WebDriver webDriver;
    WebDriverWait wait;

    public SearchByPricePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 20);
    }

    By productAppeared = By.xpath("//div[@class='layout layout_with_sidebar']/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a[1]");
    By searchInput = By.name("search");
    By mobPhonesMenuLink = By.xpath("//aside//a[contains(@href,'mobile-phones')]");
    By bottomPriceField = By.xpath("//div[@class='slider-filter__inner']/input[1]");
    By topPriceField = By.xpath("//div[@class='slider-filter__inner']/input[2]");
    By filterResult = By.cssSelector("span.goods-tile__price-value");

    public void prodSearch(String prodName) {
        webDriver.findElement(searchInput).sendKeys(prodName + Keys.ENTER);
        waitForProdAppearance();
    }

    public void mobPhonesLinkClick() {
        webDriver.findElement(mobPhonesMenuLink).click();
        waitForProdAppearance();
    }

    public void addBottomPriceFilter(String bottomPriceValue) {
        WebElement bottomPriceInput = webDriver.findElement(bottomPriceField);
        bottomPriceInput.clear();
        bottomPriceInput.sendKeys(bottomPriceValue);
    }

    public void addTopPriceFilter(String topPriceValue) {
        WebElement topPriceInput = webDriver.findElement(topPriceField);
        topPriceInput.clear();
        topPriceInput.sendKeys(topPriceValue + Keys.ENTER);
        waitForProdAppearance();
    }

    public List<WebElement> getAllProdsOnPage() {
        return webDriver.findElements(filterResult);
    }

    private void waitForProdAppearance() {
        wait.until(presenceOfElementLocated(productAppeared));
    }

}
