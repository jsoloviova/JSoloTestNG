package citrus;

import citrus.pages.ComparisonPage;
import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class CitrusComparisonTest {

    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparisonPage comparisonPage;

    String productName = "Acer";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        open("/");
        homePage = new HomePage();
        productListPage = new ProductListPage();
        productPage = new ProductPage();
        comparisonPage = new ComparisonPage();
    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }

    @Test
    public void filterProductsByPrice() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine("Ноутбуки, планшеты, МФУ")
                .clickLaptopMenuLink(productName);
        String firstProductPrice = comparisonPage.waitForPageToLoad()
                .closePopUp()
                .getPriceList()
                .get(0).getText();
        String firstProductName = comparisonPage.getNameList()
                .get(0).getText();
        String secondProductPrice = comparisonPage.getPriceList()
                .get(1).getText();
        String secondProductName = comparisonPage.getNameList()
                .get(1).getText();

        comparisonPage.scrollToTitle()
                .addProductFromListToComparison(0)
                .waitForPageToLoad();
        comparisonPage.addProductFromListToComparison(1)
                .waitForPageToLoad();
        //Integer productPrice = Integer.parseInt(productPage.getProductPrice());

        comparisonPage.getHeaderFragment().openComparisonFromHeader();
        comparisonPage.getProductBlocksFromComparison().shouldHaveSize(2);
        comparisonPage.getProdNamesFromComparison().get(2).shouldHave(Condition.text(firstProductName));
        comparisonPage.getProdNamesFromComparison().get(0).shouldHave(Condition.text(secondProductName));
        comparisonPage.getProdPricesFromComparison().get(2).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getProdPricesFromComparison().get(0).shouldHave(Condition.text(secondProductPrice));

        comparisonPage.addNewProductToComparison()
                .waitForPageToLoad();

        String newProductName = comparisonPage.getNewProductNames().get(0).getText();
        String newProductPrice = comparisonPage.getNewProductPrices().get(0).getText();
        comparisonPage.clickAddNewProductButton()
                .waitForPageToLoad();

        comparisonPage.getProductBlocksFromComparison().shouldHaveSize(3);
        comparisonPage.getProdNamesFromComparison().get(2).shouldHave(Condition.text(newProductName));
        comparisonPage.getProdPricesFromComparison().get(2).shouldHave(Condition.text(newProductPrice));

    }
}
