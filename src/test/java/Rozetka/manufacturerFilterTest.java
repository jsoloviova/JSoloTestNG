package Rozetka;

import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class manufacturerFilterTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";
    String searchText = "samsung";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void manufacturerFilter() throws InterruptedException {
        driver.findElement(By.name("search")).sendKeys(searchText + Keys.ENTER);
        By productAppeared = By.xpath("//div[@class='layout layout_with_sidebar']/section/rz-grid/ul/li[1]/app-goods-tile-default/div/div/a[1]");
        wait.until(presenceOfElementLocated(productAppeared));

        driver.findElement(By.xpath("//aside//a[contains(@href,'mobile-phones')]")).click();
        wait.until(presenceOfElementLocated(productAppeared));

        driver.findElement(By.xpath("//label[@for='Apple']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//label[@for='Honor']")).click();

        assertEquals(driver.getCurrentUrl(), "https://rozetka.com.ua/mobile-phones/c80003/producer=apple,honor,samsung/");


        List<WebElement> searchTitlesResult = driver.findElements(By.cssSelector("span.goods-tile__title"));
        for (WebElement we : searchTitlesResult) {
            if (we.getText().contains("Apple") || we.getText().contains("Honor") || we.getText().contains("Samsung")) {
            } else {
                System.out.println("Some product is not Apple, Honor or Samsung made!");
            }
        }
        System.out.println("all products are Apple, Honor or Samsung made");
    }
}
