package Rozetka2_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SearchByManufacturerPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public SearchByManufacturerPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 15);
    }

    By productAppeared = By.xpath("//div[@class='layout layout_with_sidebar']/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a[1]");
    By searchInput = By.name("search");
    By mobPhonesMenuLink = By.xpath("//aside//a[contains(@href,'mobile-phones')]");
    By appleCheckBox = By.xpath("//label[@for='Apple']");
    By huaweiCheckBox = By.xpath("//label[@for='Huawei']");
    By filterResult = By.cssSelector("span.goods-tile__title");

    public void firstProdSearch(String prodNameOne) {
        webDriver.findElement(searchInput).sendKeys(prodNameOne + Keys.ENTER);
        waitForProdAppearance();
    }

    public void mobPhonesLinkClick() {
        webDriver.findElement(mobPhonesMenuLink).click();
        waitForProdAppearance();
    }

    public void addSecondProd() {
        webDriver.findElement(appleCheckBox).click();
        waitForProdAppearance();
    }

    public void addThirdProd() {
        webDriver.findElement(huaweiCheckBox).click();
        waitForProdAppearance();
    }

    public List<WebElement> getAllProdsOnPage() {
        return webDriver.findElements(filterResult);
    }

    private void waitForProdAppearance() {
        wait.until(presenceOfElementLocated(productAppeared));
    }

}
