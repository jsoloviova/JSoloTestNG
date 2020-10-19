package advancedSeleniumTests;

import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class IframeTest extends BaseUiTests {
// - Navigate to http://demo.guru99.com/Agile_Project/Agi_V1/index.php
// - Find iframe with id=primis_playerSekindoSPlayer...
// - Click on play button
// - While video is playing move cursor in and out iframe
// - Verify that when cursor is hovering iframe then stop button is visible, when cursor is not hovering - non visible

    String loginUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(loginUrl);
    }

    @Test
    public void iframeTest() throws AWTException {
//        driver.manage().window().maximize();

        wait.until(visibilityOfElementLocated(By.xpath("//iframe[@id='flow_close_btn_iframe']")));
        driver.switchTo().frame("flow_close_btn_iframe");
        driver.switchTo().defaultContent();

        WebElement playBtnElement = driver.findElement(By.cssSelector("#playBtn"));
        playBtnElement.click();
        By pauseButton = By.id("videoContainerDiv");
        WebElement onHover = driver.findElement(pauseButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(onHover).perform();
        assertTrue(driver.findElement(pauseButton).isDisplayed());

        Robot robot = new Robot();
        robot.delay(5000);
        Point coordinates = driver.findElement(pauseButton).getLocation();
//        Point coordinatesNew = new Point(coordinates.x, coordinates.y);
//
//        robot.mouseMove(coordinates.getX(), coordinates.getY());
//        wait.until(presenceOfElementLocated(pauseButton));
//        assertTrue(driver.findElement(pauseButton).isDisplayed());

        robot.mouseMove(coordinates.getX(), coordinates.getY());
        wait.until(invisibilityOf(onHover));
        assertFalse(driver.findElement(pauseButton).isDisplayed());

    }
}

//        By iframeWindow = By.tagName("iframe");
//        wait.until(presenceOfElementLocated(iframeWindow));
//        int size = driver.findElements(iframeWindow).size();
//        driver.switchTo().frame(3);
