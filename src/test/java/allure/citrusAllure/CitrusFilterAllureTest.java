package allure.citrusAllure;

import allure.citrusAllure.steps.HomeAllurePageStep;
import allure.citrusAllure.steps.ProductListAllurePageStep;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusFilterAllureTest {
    HomeAllurePageStep homePageStep;
    ProductListAllurePageStep productListPageStep;

    String menuLink = "Смартфоны";
    String productNamePriceFilter = "Samsung";
    String prodNameMemoryFilter = "Xiaomi";
    String prodNameMaterialFilter = "Oppo";
    String minPrice = "500";
    String maxPrice = "20000";
    String materialType = "Металл";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        open("/");
        homePageStep = new HomeAllurePageStep();
        productListPageStep = new ProductListAllurePageStep();
    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }

    // Test 1
    @Test
    public void filterProductsByPrice() {
        homePageStep.clickOnlinkInMenu(productNamePriceFilter, menuLink);
        productListPageStep.setMinPriceInFilter(minPrice);
        productListPageStep.setMaxPriceInFilter(maxPrice);
        productListPageStep.verifyAllProductsFitPriceRange(minPrice, maxPrice);
        productListPageStep.verifyBrandNameOfAllProducts(productNamePriceFilter);
    }


    // Test 2
    @Test
    public void filterProductsByMemorySize() {
        homePageStep.clickOnlinkInMenu(prodNameMemoryFilter, menuLink);
        productListPageStep.setMemoryFilter(2);
        productListPageStep.setMemoryFilter(3);
        productListPageStep.verifyMemorySizeOfAllProducts(prodNameMemoryFilter);
        productListPageStep.verifyBrandNameOfAllProducts(prodNameMemoryFilter);
    }

    // Test 3
    @Test
    public void filterProductsByMaterial() {
        homePageStep.clickOnlinkInMenu(prodNameMaterialFilter, menuLink);
        productListPageStep.setCertainMaterialFilter();
        productListPageStep.verifyAllProductsSelectedCertainMaterial(materialType);
        productListPageStep.verifyBrandNameOfAllProducts(prodNameMaterialFilter);
    }
}
