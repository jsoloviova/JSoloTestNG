package Rozetka1_FactoryPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeparateProductsDataFactoryPage {
    WebDriver webDriver;

    public SeparateProductsDataFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//p[@class='product-prices__big product-prices__big_color_red']")
    WebElement priceOfFirstProduct;
    @FindBy(xpath = "//h1[@class='product__title']")
    WebElement nameOfFirstProduct;
    @FindBy(xpath = "//p[@class='product-prices__big product-prices__big_color_red']")
    WebElement priceOfSecondProduct;
    @FindBy(xpath = "//h1[@class='product__title']")
    WebElement nameOfSecondProduct;

    public String firstProdPrice() {
        return priceOfFirstProduct.getText().substring(0, 5);
    }

    public String firstProdName() {
        return nameOfFirstProduct.getText();
    }

    public String secondProdPrice() {
        return priceOfSecondProduct.getText().substring(0, 5);
    }

    public String secondProdName() {
        return nameOfSecondProduct.getText();
    }

}
