package allure.citrusAllure.steps;

import allure.citrusAllure.pages.HomeAllurePage;
import io.qameta.allure.Step;

public class HomeAllurePageStep {

    HomeAllurePage homePage = new HomeAllurePage();

    @Step("Click on link in menu")
    public void clickOnlinkInMenu(String text, String menuLink) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuLink)
                .clickLinkInMenu(text);
    }

    @Step("Search product with full name")
    public void searchProductWithFullName(String productName) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct(productName);
    }

    @Step("Search product with short name")
    public void searchProductWithShortName(String prodName) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct(prodName);
    }
}
