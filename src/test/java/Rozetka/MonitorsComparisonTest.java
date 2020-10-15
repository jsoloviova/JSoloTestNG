package Rozetka;

import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class MonitorsComparisonTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void monitorsComparison() throws InterruptedException {
        By laptopsMenuLink = By.xpath("//aside//a[contains(@href,'computers-notebooks')]");
        wait.until(presenceOfElementLocated(laptopsMenuLink)); // waiting for the link appearance
        WebElement onHover = driver.findElement(laptopsMenuLink);

        Actions actions = new Actions(driver);
        actions.moveToElement(onHover).perform(); // hover the link

        By monitorsLink = By.xpath("//ul/li/ul/li/a[@href='https://hard.rozetka.com.ua/monitors/c80089/']");
        wait.until(presenceOfElementLocated(monitorsLink));
        Thread.sleep(3000);

        driver.findElement(monitorsLink).click(); // clicking on monitors link
        By productsLink = By.xpath("//div/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a");
        wait.until(presenceOfElementLocated(productsLink));

        WebElement okButton = driver.findElement(By.cssSelector("button.slider-filter__button"));
//        scrollToElement(okButton);
        WebElement topPriceInput = driver.findElement(By.xpath("//input[2][@class='slider-filter__input ng-untouched ng-pristine ng-valid']"));
        topPriceInput.clear();
        topPriceInput.sendKeys("2999");
        okButton.click(); // OK price filter button
        wait.until(presenceOfElementLocated(productsLink));

        // click on first product with the price under 3000 + all the manipulations
        List<WebElement> monitorsPrice1 = driver.findElements(By.cssSelector("a.goods-tile__picture"));
        monitorsPrice1.get(0).click();
        Thread.sleep(5000);
        By compareButton = By.xpath("//button[@class='compare-button']");
        wait.until(presenceOfElementLocated(compareButton));
        driver.findElement(compareButton).click();
        Thread.sleep(5000);

        By compareIconSearch = By.xpath("//span[@class='header-actions__button-counter']");
        WebElement compareIcon = driver.findElement(compareIconSearch);
        wait.until(presenceOfElementLocated(compareIconSearch));
        assertEquals(compareIcon.getText(), "1");
        WebElement priceOne = driver.findElement(By.xpath("//p[@class='product-prices__big product-prices__big_color_red']"));
        WebElement nameOne = driver.findElement(By.xpath("//h1[@class='product__title']"));
        String price1 = priceOne.getText().substring(0, 5);
        String name1 = nameOne.getText();

        driver.navigate().back();
        wait.until(presenceOfElementLocated(productsLink));

        // click on 2nd product with the price under 3000 + all the manipulations
        List<WebElement> monitorsPrice2 = driver.findElements(By.cssSelector("a.goods-tile__picture"));
        monitorsPrice2.get(1).click();
        wait.until(presenceOfElementLocated(compareButton));

        driver.findElement(By.xpath("//button[@class='compare-button']")).click();
        Thread.sleep(3000);

        assertEquals(compareIcon.getText(), "2");
        WebElement priceTwo = driver.findElement(By.xpath("//p[@class='product-prices__big product-prices__big_color_red']"));
        WebElement nameTwo = driver.findElement(By.xpath("//h1[@class='product__title']"));
        String price2 = priceTwo.getText().substring(0, 5);
        String name2 = nameTwo.getText();

        System.out.println(name1);
        System.out.println(price1);
        System.out.println(name2);
        System.out.println(price2);

        driver.findElement(By.cssSelector("i.header-actions__button-icon")).click();
        WebElement verifyBothAdded = driver.findElement(By.xpath("//a[@class='comparison-modal__link']//span[@class='comparison-modal__quantity']"));
        assertEquals(verifyBothAdded.getText(), "2");
        driver.findElement(By.cssSelector("a.comparison-modal__link")).click();
        By priceTwoWaiter = By.xpath("//li[2][@class='products-grid__cell']//img");
        wait.until(presenceOfElementLocated(priceTwoWaiter));

        WebElement comparePriceOne = driver.findElement(By.xpath("//li[1]//div[contains(@class,'product__price--red')]"));
        WebElement compareNameOne = driver.findElement(By.xpath("//ul[@class='products-grid']/li[1]/rz-compare-tile/div/div/div/a"));
        WebElement comparePriceTwo = driver.findElement(By.xpath("//li[2]//div[contains(@class,'product__price--red')]"));
        WebElement compareNameTwo = driver.findElement(By.xpath("//ul[@class='products-grid']/li[2]/rz-compare-tile/div/div/div/a"));
        assertEquals(comparePriceOne.getText().substring(6, 12).replaceAll("\n", ""), price1);
        assertEquals(compareNameOne.getText(), name1);
        assertEquals(comparePriceTwo.getText().substring(6, 12).replaceAll("\n", ""), price2);
        assertEquals(compareNameTwo.getText(), name2);

    }

    private void scrollToElement(WebElement el) {
        WebElement element = driver.findElement(By.id(""));
        ((JavascriptExecutor) driver).executeScript("", el);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

// //span[@class='goods-tile__price-value']/ancestor::div[@data-goods-id='10592250']//a[@href='https://hard.rozetka.com.ua/samsung_c24f396f/p10592250/']