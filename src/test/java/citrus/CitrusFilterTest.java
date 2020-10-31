package citrus;

import citrus.pages.ComparisonPage;
import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class CitrusFilterTest {

    HomePage homePage;
    ProductListPage productListPage;

    String menuLink = "Смартфоны";
    String productNamePriceFilter = "Samsung";
    String prodNameMemoryFilter = "Xiaomi";
    String prodNameMaterialFilter = "Oppo";
    String minPrice = "500";
    String maxPrice = "20000";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        open("/");
        homePage = new HomePage();
        productListPage = new ProductListPage();
    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }

    // Test 1
    @Test
    public void filterProductsByPrice() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuLink)
                .clickLinkInMenu(productNamePriceFilter);
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.getFilterFragment()
                .setPriceFilter(0, minPrice);
        productListPage.waitForPageToLoad()
                .getFilterFragment()
                .setPriceFilter(1, maxPrice);
        productListPage.waitForPageToLoad()
                .closePopUp();

        for (SelenideElement se : productListPage.getProductsPrices()) {
            int price = Integer.parseInt(se.getText().replaceAll(" ", ""));
            assertTrue(price >= Integer.parseInt(minPrice) && price <= Integer.parseInt(maxPrice));
        }
        System.out.println("All products fit [500-20000] price range");

        for (SelenideElement se : productListPage.getProductsNames()) {
            assertTrue(se.getText().contains(productNamePriceFilter));
        }
        System.out.println("All products are Samsung made");
    }

    // Test 2
    @Test
    public void filterProductsByMemorySize() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuLink)
                .clickLinkInMenu(prodNameMemoryFilter);
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.getFilterFragment()
                .setMemoryFilter(2);
        productListPage.getFilterFragment()
                .setMemoryFilter(3);
        productListPage.waitForPageToLoad()
                .closePopUp();

        for (SelenideElement se : productListPage.getProductsNames()) {
            assertTrue(se.getText().contains("32Gb") || se.getText().contains("64Gb"));
        }
        System.out.println("All products have 32Gb or 64Gb of memory size");

        for (SelenideElement se : productListPage.getProductsNames()) {
            assertTrue(se.getText().contains(prodNameMemoryFilter));
        }
        System.out.println("All products are Xiaomi made");
    }

    // Test 3
    @Test
    public void filterProductsByMaterial() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuLink)
                .clickLinkInMenu(prodNameMaterialFilter);
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.getFilterFragment()
                .setMaterialFilter();
        productListPage.waitForPageToLoad()
                .closePopUp();

        productListPage.getProductsMaterial().getText().contains("Металл");

        for (SelenideElement se : productListPage.getProductsNames()) {
            assertTrue(se.getText().contains(prodNameMaterialFilter));
        }
        System.out.println("All products are Oppo made");
    }
}
