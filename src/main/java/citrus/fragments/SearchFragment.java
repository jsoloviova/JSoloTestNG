package citrus.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.*;

public class SearchFragment {

    SelenideElement searchInput = $("#search-input");
    SelenideElement backArrow = $x("//button[@class='menu__cancel-search']");
    SelenideElement searchButtonSubmit = $x("//button[@type='submit']");

    public void searchProduct(String productName) {
        searchInput.val(productName).pressTab();
//        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(
//                webDriver -> ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", searchButtonSubmit));
//        backArrow.click();
        actions().moveToElement(searchButtonSubmit).click();
        //searchButtonSubmit.click();
        //searchInput.val(productName).pressEnter();
    }
}
