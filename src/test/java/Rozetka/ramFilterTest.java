package Rozetka;

import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ramFilterTest extends BaseUiTests {
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

        WebElement element = driver.findElement(By.xpath("//aside[@class='sidebar']/rz-filter-stack/div[11]/button/span"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        driver.findElement(By.xpath("//a[@href='/mobile-phones/c80003/producer=samsung;38435=677049/']/label")).click();
        Thread.sleep(3000);

        List<WebElement> searchTitlesResult = driver.findElements(By.cssSelector("span.goods-tile__title"));
        for (WebElement we : searchTitlesResult) {
            if (we.getText().contains("6/")) {
            } else {
                System.out.println("Some product has not 6Gb RAM!");
            }
        }
        System.out.println("All products have 6Gb RAM");

    }

    private void scrollToElement(WebElement el) {
        WebElement element = driver.findElement(By.id(""));
        ((JavascriptExecutor) driver).executeScript("", el);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
