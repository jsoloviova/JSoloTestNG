package Rozetka1_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComparisonProdsDataPage {
    WebDriver webDriver;

    public ComparisonProdsDataPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    By comparePriceOne = By.xpath("//li[1]//div[contains(@class,'product__price--red')]");
    By compareNameOne = By.xpath("//ul[@class='products-grid']/li[1]/rz-compare-tile/div/div/div/a");
    By comparePriceTwo = By.xpath("//li[2]//div[contains(@class,'product__price--red')]");
    By compareNameTwo = By.xpath("//ul[@class='products-grid']/li[2]/rz-compare-tile/div/div/div/a");

    public String firstComparisonPrice() {
        WebElement firstPrice = webDriver.findElement(comparePriceOne);
        return firstPrice.getText().substring(6, 12).replaceAll("\n", "");
    }

    public String firstComparisonName() {
        WebElement firstName = webDriver.findElement(compareNameOne);
        return firstName.getText();
    }

    public String secondComparisonPrice() {
        WebElement secondPrice = webDriver.findElement(comparePriceTwo);
        return secondPrice.getText().substring(6, 12).replaceAll("\n", "");
    }

    public String secondComparisonName() {
        WebElement secondName = webDriver.findElement(compareNameTwo);
        return secondName.getText();
    }

}
