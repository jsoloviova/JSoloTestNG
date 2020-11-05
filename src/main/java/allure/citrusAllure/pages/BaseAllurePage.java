package allure.citrusAllure.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$x;

public class BaseAllurePage {

    SelenideElement popUpCloseButton = $x("//i[@class='el-dialog__close el-icon el-icon-close']");
    SelenideElement closeBasketButton = $x("//i[@class='el-dialog__close el-icon el-icon-close']");

    protected BaseAllurePage waitForPageToLoad() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public BaseAllurePage closePopUp() {
        if (popUpCloseButton.isDisplayed()) {
            popUpCloseButton.click();
        }
        return this;
    }

    public void closeBasketWidget() {
        closeBasketButton.click();
    }

}
