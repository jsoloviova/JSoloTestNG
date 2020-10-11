import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class GuruLoginTest extends BaseUiTests {
    String loginUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String login = "1303";
    String password = "Guru99";
    String loginBtn = "//input[@name='btnLogin']";
    String invalidCredentials = "User or Password is not valid";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(loginUrl);
    }

    @Test
    public void positiveLoginTest() {
        driver.findElement(By.name("uid")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath(loginBtn)).click();

        WebElement logoutButton = wait.until(presenceOfElementLocated(By.linkText("Log out")));
        logoutButton.click();

        assertEquals(driver.switchTo().alert().getText(), "You Have Succesfully Logged Out!!");
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), loginUrl);

    }

    @Test
    public void negativeWrongPasswordLoginTest() {
        driver.get(loginUrl);
        driver.findElement(By.name("uid")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys("qwerty");
        driver.findElement(By.xpath(loginBtn)).click();

        assertEquals(driver.switchTo().alert().getText(), invalidCredentials);
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), loginUrl);

    }

    @Test
    public void negativeWrongLoginTest() {
        driver.get(loginUrl);
        driver.findElement(By.name("uid")).sendKeys("qwerty");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath(loginBtn)).click();

        assertEquals(driver.switchTo().alert().getText(), invalidCredentials);
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), loginUrl);

    }

    @Test
    public void negativeWrongCredentialsTest() {
        driver.get(loginUrl);
        driver.findElement(By.name("uid")).sendKeys("qwerty");
        driver.findElement(By.name("password")).sendKeys("qwerty");
        driver.findElement(By.xpath(loginBtn)).click();

        assertEquals(driver.switchTo().alert().getText(), invalidCredentials);
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), loginUrl);

    }

    @Test
    public void negativeEmptyCredentialsTest() {
        driver.get(loginUrl);
        driver.findElement(By.xpath(loginBtn)).click();

        assertEquals(driver.switchTo().alert().getText(), invalidCredentials);
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), loginUrl);

    }

    @Test
    public void negativeEmptyPasswordTest() {
        driver.get(loginUrl);
        driver.findElement(By.name("uid")).sendKeys(login);
        driver.findElement(By.xpath(loginBtn)).click();

        assertEquals(driver.switchTo().alert().getText(), invalidCredentials);
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), loginUrl);

    }

    @Test
    public void negativeEmptyLoginTest() {
        driver.get(loginUrl);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath(loginBtn)).click();

        assertEquals(driver.switchTo().alert().getText(), invalidCredentials);
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), loginUrl);

    }

}
