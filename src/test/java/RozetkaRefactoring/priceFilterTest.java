package RozetkaRefactoring;

import Rozetka2_Pages.SearchByPricePage;
import TestNgTests.BaseUiTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class priceFilterTest extends BaseUiTests {
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
        SearchByPricePage searchByPricePage = new SearchByPricePage(driver);

        searchByPricePage.prodSearch(prodName);
        searchByPricePage.mobPhonesLinkClick();
        searchByPricePage.addBottomPriceFilter(bottomPriceValue);
        searchByPricePage.addTopPriceFilter(topPriceValue);

            for (WebElement we : searchByPricePage.getAllProdsOnPage()) {
                int price = Integer.parseInt(we.getText().replaceAll(" ", ""));
                assertTrue(price > bottomPrice && price < topPrice);
            }
            System.out.println("All products fit [5000-15000] price range");
    }
}
