package Rozetka2_FactoryPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SearchByPriceFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public SearchByPriceFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 20);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//div[@class='layout layout_with_sidebar']/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a[1]")
    WebElement productAppeared;
    @FindBy(name = "search")
    WebElement searchInput;
    @FindBy(xpath = "//aside//a[contains(@href,'mobile-phones')]")
    WebElement mobPhonesMenuLink;
    @FindBy(xpath = "//div[@class='slider-filter__inner']/input[1]")
    WebElement bottomPriceField;
    @FindBy(xpath = "//div[@class='slider-filter__inner']/input[2]")
    WebElement topPriceField;
    @FindBy(css = "span.goods-tile__price-value")
    List<WebElement> filterResult;

    public void prodSearch(String prodName) {
        searchInput.sendKeys(prodName + Keys.ENTER);
        waitForProdAppearance();
    }

    public void mobPhonesLinkClick() {
        mobPhonesMenuLink.click();
        waitForProdAppearance();
    }

    public void addBottomPriceFilter(String bottomPriceValue) {
        bottomPriceField.clear();
        bottomPriceField.sendKeys(bottomPriceValue);
    }

    public void addTopPriceFilter(String topPriceValue) {
        topPriceField.clear();
        topPriceField.sendKeys(topPriceValue + Keys.ENTER);
        waitForProdAppearance();
    }

    public List<WebElement> getAllProdsOnPage() {
        return filterResult;
    }

    private void waitForProdAppearance() {
        wait.until(visibilityOf(productAppeared));
    }

}
