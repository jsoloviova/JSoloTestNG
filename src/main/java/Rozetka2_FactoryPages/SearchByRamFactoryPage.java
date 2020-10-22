package Rozetka2_FactoryPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SearchByRamFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public SearchByRamFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 25);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//div[@class='layout layout_with_sidebar']/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a[1]")
    WebElement productAppeared;
    @FindBy(name = "search")
    WebElement searchInput;
    @FindBy(xpath = "//aside//a[contains(@href,'mobile-phones')]")
    WebElement mobPhonesMenuLink;
    @FindBy(xpath = "//aside[@class='sidebar']/rz-filter-stack/div[11]/button/span")
    WebElement nearElement;
    @FindBy(xpath = "//a[@href='/mobile-phones/c80003/producer=samsung;38435=677049/']/label")
    WebElement ramCheckbox;
    @FindBy(css = "span.goods-tile__title")
    List<WebElement>  filterResult;

    public void prodSearch(String prodName) {
        searchInput.sendKeys(prodName + Keys.ENTER);
        waitForProdAppearance();
    }

    public void mobPhonesLinkClick() {
        mobPhonesMenuLink.click();
        waitForProdAppearance();
    }

    public void moveToNearElement() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(nearElement);
        actions.perform();
    }

    public void clickOneRamFilter() {
        ramCheckbox.click();
        waitForProdAppearance();
    }

    public List<WebElement> getAllProdsOnPage() {
        return filterResult;
    }

    private void waitForProdAppearance() {
        wait.until(visibilityOf(productAppeared));
    }

}
