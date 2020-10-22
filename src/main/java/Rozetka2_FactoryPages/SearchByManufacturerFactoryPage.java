package Rozetka2_FactoryPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SearchByManufacturerFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public SearchByManufacturerFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 15);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//div[@class='layout layout_with_sidebar']/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a[1]")
    WebElement productAppeared;
    @FindBy(name = "search")
    WebElement searchInput;
    @FindBy(xpath = "//aside//a[contains(@href,'mobile-phones')]")
    WebElement mobPhonesMenuLink;
    @FindBy(xpath = "//label[@for='Apple']")
    WebElement appleCheckBox;
    @FindBy(xpath = "//label[@for='Huawei']")
    WebElement huaweiCheckBox;
    @FindBy(css = "span.goods-tile__title")
    List<WebElement> filterResult;

    public void firstProdSearch(String prodNameOne) {
        searchInput.sendKeys(prodNameOne + Keys.ENTER);
        waitForProdAppearance();
    }

    public void mobPhonesLinkClick() {
        mobPhonesMenuLink.click();
        waitForProdAppearance();
    }

    public void addSecondProd() {
        appleCheckBox.click();
        waitForProdAppearance();
    }

    public void addThirdProd() {
        huaweiCheckBox.click();
        waitForProdAppearance();
    }

    public List<WebElement> getAllProdsOnPage() {
        return filterResult;
    }

    private void waitForProdAppearance() {
        wait.until(visibilityOf(productAppeared));
    }

}
