package allure.citrusAllure.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchAllureFragment {

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
