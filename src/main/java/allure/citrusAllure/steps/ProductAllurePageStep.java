package allure.citrusAllure.steps;

import allure.citrusAllure.pages.ProductAllurePage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class ProductAllurePageStep {

    ProductAllurePage productPage = new ProductAllurePage();

    @Step("Add product to basket")
    public String addProductToBasket() {
        productPage.clickBuyButton();
        return productPage.getProductPrice();
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName, String productPrice) {
        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    @Step("Get total price from basket and parse to Integer")
    public Integer getTotalPriceAsInteger() {
        return Integer.parseInt(productPage.getProductPrice());
    }
}
