package allure.citrusAllure;

import allure.citrusAllure.steps.ComparisonAllurePageStep;
import allure.citrusAllure.steps.HomeAllurePageStep;
import allure.citrusAllure.steps.ProductAllurePageStep;
import allure.citrusAllure.steps.ProductListAllurePageStep;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusBasketAllureTest {

    HomeAllurePageStep homePageStep;
    ProductListAllurePageStep productListPageStep;
    ProductAllurePageStep productPageStep;
    ComparisonAllurePageStep comparisonPageStep;

    String productName = "Apple iPhone 11 128Gb Black";
    String prodName = "Apple iPhone";
    String phonesMenuLink = "Смартфоны";
    String brandName = "Apple";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        open("/");
        homePageStep = new HomeAllurePageStep();
        productListPageStep = new ProductListAllurePageStep();
        productPageStep = new ProductAllurePageStep();
        comparisonPageStep = new ComparisonAllurePageStep();
    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }

    // Test 1
    @Test
    public void addProductToBasketViaMenu() {
        homePageStep.clickOnlinkInMenu(brandName, phonesMenuLink);
        productListPageStep.clickOnProduct(productName);
        String productPrice = productPageStep.addProductToBasket();
        productPageStep.verifyBasketContent(productName, productPrice);
    }

    // Test 2
    @Test
    public void addProductToBasketViaSearchResults() {
        homePageStep.searchProductWithFullName(productName);
        String productPrice = productListPageStep.getProductPrice(productName);
        productListPageStep.addToBasket(productName);
        productPageStep.verifyBasketContent(productName, productPrice);
    }

    // Test 3
    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void addTwoProductsToBasketViaSearchResults() {
        homePageStep.searchProductWithShortName(prodName);
        String firstProductPrice = productListPageStep.getProductPriceFromResultList(0);
        String firstProductName = productListPageStep.getProductNameFromResultList(0);
        String secondProductPrice = productListPageStep.getProductPriceFromResultList(1);
        String secondProductName = productListPageStep.getProductNameFromResultList(1);

        productListPageStep.addProductToBasketFromList(0);
        productListPageStep.addProductToBasketFromList(1);
        Integer productPrice = productPageStep.getTotalPriceAsInteger();

        productListPageStep.verifyProductNamesInBasket(firstProductName, secondProductName);
        productListPageStep.verifyProductPricesInBasket(firstProductPrice, secondProductPrice);
        productListPageStep.verifyTotalPriceInBasket(firstProductPrice, secondProductPrice, productPrice);
    }

    // Test 4
    @Test
    public void addTwoProductsToBasketComparison() {
        homePageStep.searchProductWithShortName(prodName);
        String firstProductPrice = productListPageStep.getProductPriceFromResultList(0);
        String firstProductName = productListPageStep.getProductNameFromResultList(0);
        String secondProductPrice = productListPageStep.getProductPriceFromResultList(1);
        String secondProductName = productListPageStep.getProductNameFromResultList(1);

        productListPageStep.addProductToComparisonFromList(0);
        productListPageStep.addProductToComparisonFromList(1);
        Integer productPrice = productPageStep.getTotalPriceAsInteger();
        productListPageStep.openComparisonPageFromHeader();

        comparisonPageStep.verifyProductsOnComparisonPage(firstProductName, secondProductName, firstProductPrice, secondProductPrice);
        productListPageStep.verifyTotalPriceInBasket(firstProductPrice, secondProductPrice, productPrice);

    }
}
