package RozetkaFactory;

import Rozetka2_FactoryPages.SearchByRamFactoryPage;
import TestNgTests.BaseUiTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ramFilterFactoryTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";
    String prodName = "samsung";
    String partialProdName = "6/";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void manufacturerFilter() {
        SearchByRamFactoryPage searchByRamFactoryPage = new SearchByRamFactoryPage(driver);

        searchByRamFactoryPage.prodSearch(prodName);
        searchByRamFactoryPage.mobPhonesLinkClick();
        searchByRamFactoryPage.moveToNearElement();
        searchByRamFactoryPage.clickOneRamFilter();

            for (WebElement we : searchByRamFactoryPage.getAllProdsOnPage()) {
                assertTrue(we.getText().contains(partialProdName));
            }
        System.out.println("All products have 6Gb RAM");

    }
}
