package allure.citrusAllure.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BasketAllureFragment {

    SelenideElement basketWidget = $x("//div[@class='el-dialog el-dialog--medium']");
    ElementsCollection productNames = $$x("//a[contains(@class,'ctrs-basket-product__name')]");
    ElementsCollection productPrices = $$x("//br[@class='ctrs-mixed-price__string-transfer']");
    SelenideElement basketTotalPrice = $x("//span[@class='ctrs-main-price ctrs-basket-footer__new-price']");
    ElementsCollection productsBlocks = $$x("//div[@class='ctrs-basket-item ctrs-basket-product-list__item']");

    public SelenideElement getBasket() {
        return basketWidget;
    }

    public ElementsCollection getProductNamesFromBasket() {
        return productNames;
    }

    public ElementsCollection getProductPricesFromBasket() {
        return productPrices;
    }

    public SelenideElement getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public ElementsCollection getProductBlocksFromBasket() {
        return productsBlocks;
    }

}
