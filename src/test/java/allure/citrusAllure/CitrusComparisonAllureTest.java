package allure.citrusAllure;

import allure.citrusAllure.steps.ComparisonAllurePageStep;
import allure.citrusAllure.steps.HomeAllurePageStep;
import allure.citrusAllure.steps.ProductAllurePageStep;
import allure.citrusAllure.steps.ProductListAllurePageStep;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusComparisonAllureTest {
    HomeAllurePageStep homePageStep;
    ProductListAllurePageStep productListPageStep;
    ProductAllurePageStep productPageStep;
    ComparisonAllurePageStep comparisonPageStep;

    String brandName = "Acer";
    String laptopMenuLink = "Ноутбуки, планшеты, МФУ";

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

    @Test
    public void filterProductsByPrice() {
        homePageStep.clickOnlinkInMenu(brandName, laptopMenuLink);
        String firstProductPrice = comparisonPageStep.getProductPriceFromList(0);
        String firstProductName = comparisonPageStep.getProductNameFromList(0);
        String secondProductPrice = comparisonPageStep.getProductPriceFromList(1);
        String secondProductName = comparisonPageStep.getProductNameFromList(1);
        comparisonPageStep.addProductToComparison(0);
        comparisonPageStep.addProductToComparison(1);

        comparisonPageStep.verifyTwoProductsOnComparisonPage(firstProductName, secondProductName, firstProductPrice, secondProductPrice);

        comparisonPageStep.addThirdProductToComparison();
        String newProductName = comparisonPageStep.getThirdProductName(0);
        String newProductPrice = comparisonPageStep.getThirdProductPrice(0);
        comparisonPageStep.clickToAddThirdProductToCompare();

        comparisonPageStep.verifyThreeProductsToComparison(newProductName, newProductPrice);

    }
}
