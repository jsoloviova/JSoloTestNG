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

        try {
            for (int i = 0; i < 5; i++) {
                List<WebElement> linksResult = driver.findElements(By.className("iUh30 gBIQub bc tjvcx"));
                int numberOfLinks = linksResult.size();
                for (int k = 0; k < numberOfLinks; k++) {
                    if (linksResult.contains(driver.findElement(By.partialLinkText(linkText)))) {
                        System.out.println("STYLUS.UA found on " + (i + 1) + " page");
                        //break;
                    } else {
                        driver.findElement(By.id("pnnext")).click();
                        wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));
                    }
                }
                break;
            }
        } catch (Exception e) {
            System.out.println("STYLUS.UA not found on first 5 pages");
        }


//        try {
//            for (int i = 0; i < 5; i++) {
//                if (driver.findElement(By.partialLinkText("mrfix.ua")) != null) {
//                    assertEquals(driver.findElement(By.partialLinkText("mrfix.ua")), "www.mrfix.ua");
//                    System.out.println("STYLUS.UA found on " + i+1 + " page");
//                    break;
//                } else {
//                    driver.findElement(By.id("pnnext")).click();
//                    wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("STYLUS.UA not found on first 5 pages");
//        }
//        driver.quit();
    }
}
