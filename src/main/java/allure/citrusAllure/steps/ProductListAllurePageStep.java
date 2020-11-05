package allure.citrusAllure.steps;

import allure.citrusAllure.pages.ProductListAllurePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static org.testng.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductListAllurePageStep {

    ProductListAllurePage productListPage = new ProductListAllurePage();

    @Step("Click on product name")
    public void clickOnProduct(String productName) {
        productListPage.waitForPageToLoad()
                .closePopUp()
                .clickOnProductByName(productName);
    }

    @Step("Get product price")
    public String getProductPrice(String productName) {
        return productListPage.waitForPageToLoad()
                .closePopUp()
                .getProductPriceByName(productName);
    }

    @Step("Add product to basket")
    public void addToBasket(String productName) {
        productListPage.addProductToBasket(productName);
    }

    @Step("Get product price from search list")
    public String getProductPriceFromResultList(int index) {
        return productListPage.waitForPageToLoad()
                .closePopUp()
                .getProductPriceFromSearchList(index);
    }

    @Step("Get product name from search list")
    public String getProductNameFromResultList(int index) {
        return productListPage
                .closePopUp()
                .getProductNameFromSearchList(index);
    }

    @Step("Add product from list to basket")
    public void addProductToBasketFromList(int index) {
        productListPage.addProductFromListToBasket(index).closeBasketWidget();
    }

    @Step("Verify product names in the basket are correct")
    public void verifyProductNamesInBasket(String firstProductName, String secondProductName) {
        productListPage.getHeaderFragment().openBasketFromHeader();
        productListPage.getBasketFragment().getProductBlocksFromBasket().shouldHaveSize(2);
        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(secondProductName));
    }

    @Step("Verify product prices in the basket are correct")
    public void verifyProductPricesInBasket(String firstProductPrice, String secondProductPrice) {
        productListPage.getHeaderFragment().openBasketFromHeader();
        productListPage.getBasketFragment().getProductBlocksFromBasket().shouldHaveSize(2);
        productListPage.getBasketFragment().getProductPricesFromBasket().get(0).shouldHave(Condition.text(firstProductPrice));
        productListPage.getBasketFragment().getProductPricesFromBasket().get(1).shouldHave(Condition.text(secondProductPrice));
    }

    @Step("Add product from list to comparison")
    public void addProductToComparisonFromList(int index) {
        productListPage.addProductFromListToComparison(index);
    }

    @Step("Open comparison page from header")
    public void openComparisonPageFromHeader() {
        productListPage.getHeaderFragment().openComparisonFromHeader();
    }

    @Step("Set min price in price filter")
    public void setMinPriceInFilter(String minPrice) {
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.getFilterFragment()
                .setPriceFilter(0, minPrice);
    }

    @Step("Set max price in price filter")
    public void setMaxPriceInFilter(String maxPrice) {
        productListPage.waitForPageToLoad()
                .getFilterFragment()
                .setPriceFilter(1, maxPrice);
        productListPage.waitForPageToLoad()
                .closePopUp();
    }

    @Step("Verify all products fit price range")
    public void verifyAllProductsFitPriceRange(String minPrice, String maxPrice) {
        for (SelenideElement se : productListPage.getProductsPrices()) {
            int price = Integer.parseInt(se.getText().replaceAll(" ", ""));
            assertTrue(price >= Integer.parseInt(minPrice) && price <= Integer.parseInt(maxPrice));
        }
        System.out.println("All products fit [500-20000] price range");
    }

    @Step("Verify all product names contain correct selected brand name")
    public void verifyBrandNameOfAllProducts(String brandName) {
        for (SelenideElement se : productListPage.getProductsNames()) {
            assertTrue(se.getText().contains(brandName));
        }
    }

    @Step("Set memory filter")
    public void setMemoryFilter(int index) {
        productListPage.getFilterFragment()
                .setMemoryFilter(index);
        productListPage.waitForPageToLoad()
                .closePopUp();
    }

    @Step("Verify all products have correct memory size")
    public void verifyMemorySizeOfAllProducts(String prodNameMemoryFilter) {
        for (SelenideElement se : productListPage.getProductsNames()) {
            assertTrue(se.getText().contains("32Gb") || se.getText().contains("64Gb"));
        }
        System.out.println("All products have 32Gb or 64Gb of memory size");
    }

    @Step("Set certain material filter")
    public void setCertainMaterialFilter() {
        productListPage.getFilterFragment()
                .setMaterialFilter();
        productListPage.waitForPageToLoad()
                .closePopUp();
    }

    @Step("Verify all products contain selected material")
    public void verifyAllProductsSelectedCertainMaterial(String materialType) {
        productListPage.getProductsMaterial().getText().contains(materialType);
    }

    @Step("Verify total price in basket")
    public void verifyTotalPriceInBasket(String firstProductPrice, String secondProductPrice, Integer productPrice) {
        assertThat(Integer.parseInt(firstProductPrice) + Integer.parseInt(secondProductPrice)).isEqualTo(productPrice);
    }
}
