package advancedSeleniumTests;

import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class CookiesHandleTest extends BaseUiTests {
// - Login to http://demo.guru99.com/Agile_Project/Agi_V1/index.php
// - Print out all cookies (all data)
// - Clear all cookies
// - Refresh page, verify session of authorization still exists

    String loginUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String login = "1303";
    String password = "Guru99";
    String loginBtn = "//input[@name='btnLogin']";
    String logoutBtn = "Log out";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(loginUrl);
    }

    @Test
    public void cookiesTest() {
        driver.findElement(By.name("uid")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath(loginBtn)).click();
        wait.until(presenceOfElementLocated(By.linkText(logoutBtn)));

        // 1st way:
//        Set<Cookie> cookies = driver.manage().getCookies();
//        assertThat(cookies).isNotEmpty();
//        System.out.println(cookies + "\n");

        // 2nd way:
        Cookie guruCookieOne = driver.manage().getCookieNamed("_gid");
        assertThat(guruCookieOne).isNotNull();
        System.out.println("1. " + guruCookieOne);

        assertThat(guruCookieOne.getName()).isEqualTo("_gid");
        assertThat(guruCookieOne.getValue()).isNotNull();
        assertThat(guruCookieOne.getDomain()).isEqualTo(".guru99.com");
        assertThat(guruCookieOne.getPath()).isEqualTo("/");
        assertThat(guruCookieOne.getExpiry()).isNotNull();
        assertThat(guruCookieOne.isHttpOnly()).isEqualTo(false);
        assertThat(guruCookieOne.isSecure()).isEqualTo(false);

        Cookie guruCookieTwo = driver.manage().getCookieNamed("_ga");
        assertThat(guruCookieTwo).isNotNull();
        System.out.println("2. " + guruCookieTwo);

        assertThat(guruCookieTwo.getName()).isEqualTo("_ga");
        assertThat(guruCookieTwo.getValue()).isNotNull();
        assertThat(guruCookieTwo.getDomain()).isEqualTo(".guru99.com");
        assertThat(guruCookieTwo.getPath()).isEqualTo("/");
        assertThat(guruCookieTwo.getExpiry()).isNotNull();
        assertThat(guruCookieTwo.isHttpOnly()).isEqualTo(false);
        assertThat(guruCookieTwo.isSecure()).isEqualTo(false);

        Cookie guruCookieThree = driver.manage().getCookieNamed("PHPSESSID");
        assertThat(guruCookieThree).isNotNull();
        System.out.println("3. " + guruCookieThree);

        assertThat(guruCookieThree.getName()).isEqualTo("PHPSESSID");
        assertThat(guruCookieThree.getValue()).isNotNull();
        assertThat(guruCookieThree.getDomain()).isEqualTo("demo.guru99.com");
        assertThat(guruCookieThree.getPath()).isEqualTo("/");
        assertThat(guruCookieThree.isHttpOnly()).isEqualTo(false);
        assertThat(guruCookieThree.isSecure()).isEqualTo(false);

        driver.manage().deleteCookie(guruCookieOne);
        Cookie deletedCookieOne = driver.manage().getCookieNamed("_gid");
        assertThat(deletedCookieOne).isNull();

        driver.manage().deleteCookie(guruCookieTwo);
        Cookie deletedCookieTwo = driver.manage().getCookieNamed("_ga");
        assertThat(deletedCookieTwo).isNull();

        driver.manage().deleteCookie(guruCookieThree);
        Cookie deletedCookieThree = driver.manage().getCookieNamed("PHPSESSID");
        assertThat(deletedCookieThree).isNull();

        driver.navigate().refresh();
        wait.until(presenceOfElementLocated(By.linkText(logoutBtn)));
        assertEquals(driver.findElement(By.xpath("//ul[@class='menusubnav']/li[3]")).getText(), logoutBtn);

    }

}
