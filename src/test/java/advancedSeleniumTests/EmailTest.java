package advancedSeleniumTests;

import TestNgTests.BaseUiTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class EmailTest extends BaseUiTests {
// - Login to mail.google.com with existing account
// - Create new email (with TO=*the same email*, subject, email text), attach file from your local machine, click send
// - Go to inbox, verify email came, verify subject, content of email, verify file is attached

    String url = "https://mail.google.com";
    String email = "jsolotest@gmail.com";
    String password = "aqa230720";
    String subjectText = "Subject Text";
    String bodyText = "Body Text";
    String fileName = "99little_bugs_in_the_code.jpg";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void emailTest() throws AWTException {
        By nextBtn = By.xpath("//button[contains(@class,'VfPpkd-LgbsSe')]");
        wait.until(presenceOfElementLocated(nextBtn));

        // enter email
        driver.findElement(By.cssSelector("input[type=email]")).sendKeys(email);
        driver.findElement(nextBtn).click();
        WebElement progressBar = driver.findElement(By.xpath("//div[@role='progressbar']"));
        wait.until(invisibilityOf(progressBar));

        // enter password
        driver.findElement(By.cssSelector("input[type=password]")).sendKeys(password);
        driver.findElement(By.xpath("//div[@id='passwordNext']//button[contains(@class,'VfPpkd-LgbsSe')]")).click();
        By composeEmailBtn = By.xpath("//div[@class='T-I T-I-KE L3']");
        wait.until(presenceOfElementLocated(composeEmailBtn));

        // open new message window
        driver.findElement(composeEmailBtn).click();
        By enterRecepient = By.xpath("//div[@class='AD']//table//form//table//tr[1]/td[2]//textarea");
        wait.until(presenceOfElementLocated(enterRecepient));

        // fill all the input fields, add an image and send the message
        driver.findElement(enterRecepient).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectText);
        driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys(bodyText);

        driver.findElement(By.xpath("//div[@command='Files']/div")).click();
        StringSelection strSel = new StringSelection("D:\\AQA\\99little_bugs_in_the_code.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSel, null);
        //wait.until(presenceOfElementLocated(By.xpath("//div[text()='Attach files']")));
        Robot robot = new Robot();
        robot.delay(1000); // Andrey said it's ok here :)
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        wait.until(presenceOfElementLocated(By.xpath("//div[text()='99little_bugs_in_the_code.jpg']")));
        driver.findElement(By.xpath("//table[@role='group']/tbody/tr/td[1]/div/div/div[1]")).click();
        driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div[1]/div[contains(@class,'TO')]")).click();
        By subjectPreview = By.xpath("//td[5]/div/div/div/span/span");
        wait.until(presenceOfElementLocated(subjectPreview));
        assertEquals(driver.findElement(subjectPreview).getText(), subjectText);

        // open the email
        driver.findElement(subjectPreview).click();
        wait.until(presenceOfElementLocated(subjectPreview));
        WebElement emailSubject = driver.findElement(By.xpath("//table[@role='presentation']//div/h2"));
        assertEquals(emailSubject.getText(), subjectText);
        WebElement emailBody = driver.findElement(By.xpath("//div[text()='Body Text']"));
        assertEquals(emailBody.getText(), bodyText);
        WebElement emailFile = driver.findElement(By.xpath("//a[@role='link']/div/div[3]/div[2]/div[2]/div/div[1]/span"));
        // this line almost always works:
        assertEquals(emailFile.getText(), fileName);

        // i wanted to delete the email here, but failed it :( but i'll try again!

//        driver.navigate().back();
//        driver.findElement(By.xpath("//div[@role='checkbox']")).click();
//        //wait.until(presenceOfElementLocated(By.xpath("//div[@data-tooltip='Delete']")));
//        driver.findElement(By.xpath("//div[@data-tooltip='Delete']/div")).click();
//        WebElement subjText = driver.findElement(By.xpath("//span[text()='Subject Text']"));
//        assertFalse(subjText.isDisplayed());



    }
}


//        driver.findElement(By.xpath("//table/tbody/tr/td[4]/div[2]/img")).click();
//        By deleteCTA = By.xpath("//div[text()='Delete this message']");
//        wait.until(presenceOfElementLocated(deleteCTA));
//        driver.findElement(deleteCTA).click();