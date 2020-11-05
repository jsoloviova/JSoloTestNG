package allure.citrusAllure.pages;

import allure.citrusAllure.fragments.BasketAllureFragment;
import allure.citrusAllure.fragments.FilterAllureFragment;
import allure.citrusAllure.fragments.HeaderAllureFragment;
import allure.citrusAllure.fragments.SearchAllureFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductListAllurePage extends BaseAllurePage {

    BasketAllureFragment basketFragment = new BasketAllureFragment();
    SearchAllureFragment searchFragment = new SearchAllureFragment();
    HeaderAllureFragment headerFragment = new HeaderAllureFragment();
    FilterAllureFragment filterFragment = new FilterAllureFragment();

    ElementsCollection productsNameList = $$x("//div[@class='product-card__name']/a/span");
    ElementsCollection productsPriceList = $$x("//div[@class='prices__price']/span[1]");
    ElementsCollection productsMaterialList = $$x("//ul[@class='properties__items']/li[1]/span[2]");
    ElementsCollection productsList = $$x("//div[@class='product-card__overview']");
    ElementsCollection itemValue = $$x("//ul[@class='properties__items']/li[1]/span[@class='item__value']");

    public ProductListAllurePage clickOnProductByName(String productName) {
        $x("//span[contains(text(),'" + productName + "')]").click();
        return this;
    }

    public ProductListAllurePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public ProductListAllurePage closePopUp() {
        super.closePopUp();
        return this;
    }

    public String getProductPriceByName(String productName) {
        return $x("//a[contains(@title,'" + productName + "')]/..//div[@class='base-price']/span")
                .getText();
    }

    public ProductListAllurePage addProductToBasket(String productName) {
        $x("//a[contains(@title,'" + productName + "')]/..//i[@class='icon-new-citrus-cart el-tooltip item']").click();
        return this;
    }

    public String getProductPriceFromSearchList(int index) {
        return $$x("//span[@class='price-number']").get(index).getText();
    }

    public String getProductNameFromSearchList(int index) {
        return $$x("//div[@class='title-itm']/h5").get(index).getText();
    }

    public ProductListAllurePage addProductFromListToBasket(int index) {
        $$x("//i[@class='icon-new-citrus-cart el-tooltip item']").get(index).click();
        return this;
    }

    public ProductListAllurePage addProductFromListToComparison(int index) {
        $$x("//i[@class='icon-comparison2 el-tooltip item']").get(index).click();
        return this;
    }

    public ElementsCollection getProductsNames() {
        return productsNameList;
    }

    public ElementsCollection getProductsPrices() {
        return productsPriceList;
    }

    public SelenideElement getProductsMaterial() {
        for (SelenideElement se : productsList) {
            se.hover();
        }
        return itemValue.get(0);
    }

    public BasketAllureFragment getBasketFragment() {
        return basketFragment;
    }

    public SearchAllureFragment getSearchFragment() {
        return searchFragment;
    }

    public HeaderAllureFragment getHeaderFragment() {
        return headerFragment;
    }

    public FilterAllureFragment getFilterFragment() {
        return filterFragment;
    }

}

//h5[contains(text(),'" + productName + "')]/../../..//div[@class='base-price']/span
//h5[contains(text(),'" + productName + "')]/../../..//i[@class='icon-new-citrus-cart el-tooltip item']