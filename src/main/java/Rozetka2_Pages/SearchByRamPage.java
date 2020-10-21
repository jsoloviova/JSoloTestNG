package Rozetka2_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SearchByRamPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public SearchByRamPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 25);
    }

    By productAppeared = By.xpath("//div[@class='layout layout_with_sidebar']/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a[1]");
    By searchInput = By.name("search");
    By mobPhonesMenuLink = By.xpath("//aside//a[contains(@href,'mobile-phones')]");
    By nearElement = By.xpath("//aside[@class='sidebar']/rz-filter-stack/div[11]/button/span");
    By ramCheckbox = By.xpath("//a[@href='/mobile-phones/c80003/producer=samsung;38435=677049/']/label");
    By filterResult = By.cssSelector("span.goods-tile__title");

    public void prodSearch(String prodName) {
        webDriver.findElement(searchInput).sendKeys(prodName + Keys.ENTER);
        waitForProdAppearance();
    }

    public void mobPhonesLinkClick() {
        webDriver.findElement(mobPhonesMenuLink).click();
        waitForProdAppearance();
    }

    public void moveToNearElement() {
        WebElement element = webDriver.findElement(nearElement);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void clickOneRamFilter() {
        webDriver.findElement(ramCheckbox).click();
        waitForProdAppearance();
    }

    public List<WebElement> getAllProdsOnPage() {
        return webDriver.findElements(filterResult);
    }

    private void waitForProdAppearance() {
        wait.until(presenceOfElementLocated(productAppeared));
    }


}
