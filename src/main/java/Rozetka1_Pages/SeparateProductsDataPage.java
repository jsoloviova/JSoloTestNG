package Rozetka1_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeparateProductsDataPage {
    WebDriver webDriver;

    public SeparateProductsDataPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    By priceOfFirstProduct = By.xpath("//p[@class='product-prices__big product-prices__big_color_red']");
    By nameOfFirstProduct = By.xpath("//h1[@class='product__title']");
    By priceOfSecondProduct = By.xpath("//p[@class='product-prices__big product-prices__big_color_red']");
    By nameOfSecondProduct = By.xpath("//h1[@class='product__title']");

    public String firstProdPrice() {
        WebElement firstProductPrice = webDriver.findElement(priceOfFirstProduct);
        return firstProductPrice.getText().substring(0, 5);
    }

    public String firstProdName() {
        WebElement firstProductName = webDriver.findElement(nameOfFirstProduct);
        return firstProductName.getText();
    }

    public String secondProdPrice() {
        WebElement secondProductPrice = webDriver.findElement(priceOfSecondProduct);
        return secondProductPrice.getText().substring(0, 5);
    }

    public String secondProdName() {
        WebElement secondProductName = webDriver.findElement(nameOfSecondProduct);
        return secondProductName.getText();
    }

}
