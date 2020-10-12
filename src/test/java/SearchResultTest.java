import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SearchResultTest extends BaseUiTests {
    String url = "https://www.google.com/";
    String linkText = "stylus.ua";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void searchLink() {
        driver.findElement(By.name("q")).sendKeys("iphone kyiv buy" + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));

        for (int i = 0; i < 5; i++) {
            List<WebElement> linksResult = driver.findElements(By.partialLinkText(linkText));
            int numberOfLinks = linksResult.size();
            if (numberOfLinks == 1) {
                System.out.println("STYLUS.UA found on " + (i + 1) + " page");
                break;
            }
            if (numberOfLinks == 0) {
                driver.findElement(By.id("pnnext")).click();
                wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));
            }
        }
    }
}
