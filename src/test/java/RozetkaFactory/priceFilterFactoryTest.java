package RozetkaFactory;

import Rozetka2_FactoryPages.SearchByPriceFactoryPage;
import TestNgTests.BaseUiTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class priceFilterFactoryTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";
    String prodName = "samsung";
    String bottomPriceValue = "5000";
    String topPriceValue = "15000";
    Integer bottomPrice = 5000;
    Integer topPrice = 15000;

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void manufacturerFilter() {
        SearchByPriceFactoryPage searchByPriceFactoryPage = new SearchByPriceFactoryPage(driver);

        searchByPriceFactoryPage.prodSearch(prodName);
        searchByPriceFactoryPage.mobPhonesLinkClick();
        searchByPriceFactoryPage.addBottomPriceFilter(bottomPriceValue);
        searchByPriceFactoryPage.addTopPriceFilter(topPriceValue);

            for (WebElement we : searchByPriceFactoryPage.getAllProdsOnPage()) {
                int price = Integer.parseInt(we.getText().replaceAll(" ", ""));
                assertTrue(price > bottomPrice && price < topPrice);
            }
            System.out.println("All products fit [5000-15000] price range");
    }
}
