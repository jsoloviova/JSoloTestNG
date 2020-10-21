package RozetkaRefactoring;

import Rozetka2_Pages.SearchByRamPage;
import TestNgTests.BaseUiTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ramFilterTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";
    String prodName = "samsung";
    String partialProdName = "6/";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void manufacturerFilter() {
        SearchByRamPage searchByRamPage = new SearchByRamPage(driver);

        searchByRamPage.prodSearch(prodName);
        searchByRamPage.mobPhonesLinkClick();
        searchByRamPage.moveToNearElement();
        searchByRamPage.clickOneRamFilter();

            for (WebElement we : searchByRamPage.getAllProdsOnPage()) {
                assertTrue(we.getText().contains(partialProdName));
            }
        System.out.println("All products have 6Gb RAM");

    }
}
