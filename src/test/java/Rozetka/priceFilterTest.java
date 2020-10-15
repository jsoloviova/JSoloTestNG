package Rozetka;

import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class priceFilterTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";
    String searchText = "samsung";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void manufacturerFilter() {
        driver.findElement(By.name("search")).sendKeys(searchText + Keys.ENTER);
        By productAppeared = By.xpath("//div[@class='layout layout_with_sidebar']/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a[1]");
        wait.until(presenceOfElementLocated(productAppeared));

        driver.findElement(By.xpath("//aside//a[contains(@href,'mobile-phones')]")).click();
        wait.until(presenceOfElementLocated(productAppeared));

        WebElement bottomPriceInput = driver.findElement(By.xpath("//div[@class='slider-filter__inner']/input[1]"));
        bottomPriceInput.clear();
        bottomPriceInput.sendKeys("5000");
        WebElement topPriceInput = driver.findElement(By.xpath("//div[@class='slider-filter__inner']/input[2]"));
        topPriceInput.clear();
        topPriceInput.sendKeys("15000" + Keys.ENTER);
        wait.until(presenceOfElementLocated(productAppeared));

        List<WebElement> searchPricesResult = driver.findElements(By.cssSelector("span.goods-tile__price-value"));
        try {
            for (WebElement we : searchPricesResult) {
                int price = Integer.parseInt(we.getText().replaceAll(" ", ""));
                assertTrue(price > 5000 && price < 15000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            System.out.println("All products are in [5000-15000] price range");
    }
}
