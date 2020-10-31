package citrus.pages;

import citrus.fragments.BasketFragment;
import citrus.fragments.FilterFragment;
import citrus.fragments.HeaderFragment;
import citrus.fragments.SearchFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductListPage extends BasePage {

    BasketFragment basketFragment = new BasketFragment();
    SearchFragment searchFragment = new SearchFragment();
    HeaderFragment headerFragment = new HeaderFragment();
    FilterFragment filterFragment = new FilterFragment();

    ElementsCollection productsNameList = $$x("//div[@class='product-card__name']/a/span");
    ElementsCollection productsPriceList = $$x("//div[@class='prices__price']/span[1]");
    ElementsCollection productsMaterialList = $$x("//ul[@class='properties__items']/li[1]/span[2]");
    ElementsCollection productsList = $$x("//div[@class='product-card__overview']");
    ElementsCollection itemValue = $$x("//ul[@class='properties__items']/li[1]/span[@class='item__value']");

    public ProductListPage clickOnProductByName(String productName) {
        $x("//span[contains(text(),'" + productName + "')]").click();
        return this;
    }

    public ProductListPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public ProductListPage closePopUp() {
        super.closePopUp();
        return this;
    }

    public String getProductPriceByName(String productName) {
        return $x("//a[contains(@title,'" + productName + "')]/..//div[@class='base-price']/span")
                .getText();
    }

    public ProductListPage addProductToBasket(String productName) {
        $x("//a[contains(@title,'" + productName + "')]/..//i[@class='icon-new-citrus-cart el-tooltip item']").click();
        return this;
    }

    public String getProductPriceFromSearchList(int index) {
        return $$x("//span[@class='price-number']").get(index).getText();
    }

    public String getProductNameFromSearchList(int index) {
        return $$x("//div[@class='title-itm']/h5").get(index).getText();
    }

    public ProductListPage addProductFromListToBasket(int index) {
        $$x("//i[@class='icon-new-citrus-cart el-tooltip item']").get(index).click();
        return this;
    }

    public ProductListPage addProductFromListToComparison(int index) {
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

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }

    public FilterFragment getFilterFragment() {
        return filterFragment;
    }

}

//h5[contains(text(),'" + productName + "')]/../../..//div[@class='base-price']/span
//h5[contains(text(),'" + productName + "')]/../../..//i[@class='icon-new-citrus-cart el-tooltip item']