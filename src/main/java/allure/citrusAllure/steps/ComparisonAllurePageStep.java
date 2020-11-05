package allure.citrusAllure.steps;

import allure.citrusAllure.pages.ComparisonAllurePage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class ComparisonAllurePageStep {

    ComparisonAllurePage comparisonPage = new ComparisonAllurePage();

    @Step("Verify products on comparison page have correct names and prices")
    public void verifyProductsOnComparisonPage(String firstProductName, String secondProductName, String firstProductPrice, String secondProductPrice) {
        comparisonPage.getProductBlocksFromComparison().shouldHaveSize(2);
        comparisonPage.getHeaderFragment().openBasketFromHeader();
        comparisonPage.getBasketFragment().getProductBlocksFromBasket().shouldHaveSize(2);
        comparisonPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(firstProductName));
        comparisonPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(secondProductName));
        comparisonPage.getBasketFragment().getProductPricesFromBasket().get(0).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getBasketFragment().getProductPricesFromBasket().get(1).shouldHave(Condition.text(secondProductPrice));
    }

    @Step("Get product price from result list")
    public String getProductPriceFromList(int index) {
        return comparisonPage.waitForPageToLoad()
                .closePopUp()
                .getPriceList()
                .get(index).getText();
    }

    @Step("Get product name from result list")
    public String getProductNameFromList(int index) {
        return comparisonPage.getNameList()
                .get(index).getText();
    }

    @Step("Add product from list to Comparison")
    public void addProductToComparison(int index) {
        comparisonPage.scrollToTitle()
                .addProductFromListToComparison(index)
                .waitForPageToLoad();
    }

    @Step("Verify 2 products added to comparison page are correct")
    public void verifyTwoProductsOnComparisonPage(String firstProductName, String secondProductName, String firstProductPrice, String secondProductPrice) {
        comparisonPage.getHeaderFragment().openComparisonFromHeader();
        comparisonPage.getProductBlocksFromComparison().shouldHaveSize(2);
        comparisonPage.getProdNamesFromComparison().get(2).shouldHave(Condition.text(firstProductName));
        comparisonPage.getProdNamesFromComparison().get(0).shouldHave(Condition.text(secondProductName));
        comparisonPage.getProdPricesFromComparison().get(2).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getProdPricesFromComparison().get(0).shouldHave(Condition.text(secondProductPrice));
    }

    @Step("Add 3rd product to comparison")
    public void addThirdProductToComparison() {
        comparisonPage.addNewProductToComparison()
                .waitForPageToLoad();
    }

    @Step("Get 3rd product name")
    public String getThirdProductName(int index) {
        return comparisonPage.getNewProductNames().get(index).getText();
    }

    @Step("Get 3rd product to price")
    public String getThirdProductPrice(int index) {
        return comparisonPage.getNewProductPrices().get(0).getText();
    }

    @Step("Click add button for 3rd partner")
    public void clickToAddThirdProductToCompare() {
        comparisonPage.clickAddNewProductButton()
                .waitForPageToLoad();
    }

    @Step("Verify 3rd product added to comparison page is correct")
    public void verifyThreeProductsToComparison(String newProductName, String newProductPrice) {
        comparisonPage.getProductBlocksFromComparison().shouldHaveSize(3);
        comparisonPage.getProdNamesFromComparison().get(2).shouldHave(Condition.text(newProductName));
        comparisonPage.getProdPricesFromComparison().get(2).shouldHave(Condition.text(newProductPrice));
    }
}
