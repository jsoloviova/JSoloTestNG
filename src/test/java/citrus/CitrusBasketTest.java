package citrus;

import citrus.pages.ComparisonPage;
import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CitrusBasketTest {
    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparisonPage comparisonPage;

    String productName = "Apple iPhone 11 128Gb Black";
    String prodName = "Apple iPhone";

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

    // Test 1
    @Test
    public void addProductToBasketViaMenu() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine("Смартфоны")
                .clickLinkInMenu("Apple");
        productListPage.waitForPageToLoad()
                .closePopUp()
                .clickOnProductByName(productName);
        String productPrice = productPage.getProductPrice();
        productPage.clickBuyButton();

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    // Test 2
    @Test
    public void addProductToBasketViaSearchResults() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct(productName);
        String productPrice = productListPage.waitForPageToLoad()
                .closePopUp()
                .getProductPriceByName(productName);
        productListPage
                .addProductToBasket(productName);

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    // Test 3
    @Test
    public void addTwoProductsToBasketViaSearchResults() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct(prodName);
        String firstProductPrice = productListPage.waitForPageToLoad()
                .closePopUp()
                .getProductPriceFromSearchList(0);
        String firstProductName = productListPage.getProductNameFromSearchList(0);
        String secondProductPrice = productListPage.getProductPriceFromSearchList(1);
        String secondProductName = productListPage.getProductNameFromSearchList(1);

        productListPage.addProductFromListToBasket(0).closeBasketWidget();
        productListPage.addProductFromListToBasket(1).closeBasketWidget();
        Integer productPrice = Integer.parseInt(productPage.getProductPrice());

        productListPage.getHeaderFragment().openBasketFromHeader();
        productListPage.getBasketFragment().getProductBlocksFromBasket().shouldHaveSize(2);
        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(secondProductName));

        productListPage.getBasketFragment().getProductPricesFromBasket().get(0).shouldHave(Condition.text(firstProductPrice));
        productListPage.getBasketFragment().getProductPricesFromBasket().get(1).shouldHave(Condition.text(secondProductPrice));
        assertThat(Integer.parseInt(firstProductPrice) + Integer.parseInt(secondProductPrice)).isEqualTo(productPrice);
    }

    // Test 4
    @Test
    public void addTwoProductsToBasketComparison() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct(prodName);
        String firstProductPrice = productListPage.waitForPageToLoad()
                .closePopUp()
                .getProductPriceFromSearchList(0);
        String firstProductName = productListPage.getProductNameFromSearchList(0);
        String secondProductPrice = productListPage.getProductPriceFromSearchList(1);
        String secondProductName = productListPage.getProductNameFromSearchList(1);

        productListPage.addProductFromListToComparison(0);
        productListPage.addProductFromListToComparison(1);
        Integer productPrice = Integer.parseInt(productPage.getProductPrice());

        productListPage.getHeaderFragment().openComparisonFromHeader();
        comparisonPage.getProductBlocksFromComparison().shouldHaveSize(2);

        comparisonPage.getHeaderFragment().openBasketFromHeader();
        comparisonPage.getBasketFragment().getProductBlocksFromBasket().shouldHaveSize(2);
        comparisonPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(firstProductName));
        comparisonPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(secondProductName));
        comparisonPage.getBasketFragment().getProductPricesFromBasket().get(0).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getBasketFragment().getProductPricesFromBasket().get(1).shouldHave(Condition.text(secondProductPrice));
        assertThat(Integer.parseInt(firstProductPrice) + Integer.parseInt(secondProductPrice)).isEqualTo(productPrice);
    }



}
