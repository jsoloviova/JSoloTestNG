package Rozetka1_FactoryPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComparisonProdsDataFactoryPage {
    WebDriver webDriver;

    public ComparisonProdsDataFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//li[1]//div[contains(@class,'product__price--red')]")
    WebElement comparePriceOne;
    @FindBy(xpath = "//ul[@class='products-grid']/li[1]/rz-compare-tile/div/div/div/a")
    WebElement compareNameOne;
    @FindBy(xpath = "//li[2]//div[contains(@class,'product__price--red')]")
    WebElement comparePriceTwo;
    @FindBy(xpath = "//ul[@class='products-grid']/li[2]/rz-compare-tile/div/div/div/a")
    WebElement compareNameTwo;

    public String firstComparisonPrice() {
        return comparePriceOne.getText().substring(6, 12).replaceAll("\n", "");
    }

    public String firstComparisonName() {
        return compareNameOne.getText();
    }

    public String secondComparisonPrice() {
        return comparePriceTwo.getText().substring(6, 12).replaceAll("\n", "");
    }

    public String secondComparisonName() {
        return compareNameTwo.getText();
    }

}
