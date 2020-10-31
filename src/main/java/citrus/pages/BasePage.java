package citrus.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.EventListener;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {

    SelenideElement popUpCloseButton = $x("//i[@class='el-dialog__close el-icon el-icon-close']");
    SelenideElement closeBasketButton = $x("//i[@class='el-dialog__close el-icon el-icon-close']");

    protected BasePage waitForPageToLoad() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public BasePage closePopUp() {
        if (popUpCloseButton.isDisplayed()) {
            popUpCloseButton.click();
        }
        return this;
    }

    public void closeBasketWidget() {
        closeBasketButton.click();
    }

}
