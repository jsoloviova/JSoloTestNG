package RozetkaRefactoring;

import Rozetka2_Pages.SearchByManufacturerPage;
import TestNgTests.BaseUiTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class manufacturerFilterTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";
    String prodNameOne = "Samsung";
    String prodNameTwo = "Apple";
    String prodNameThree = "Huawei";
    String prodLink = "https://rozetka.com.ua/mobile-phones/c80003/producer=apple,huawei,samsung/";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void manufacturerFilter() {
        SearchByManufacturerPage searchByManufacturerPage = new SearchByManufacturerPage(driver);

        searchByManufacturerPage.firstProdSearch(prodNameOne);
        searchByManufacturerPage.mobPhonesLinkClick();
        searchByManufacturerPage.addSecondProd();
        searchByManufacturerPage.addThirdProd();

        assertEquals(driver.getCurrentUrl(), prodLink);

            for (WebElement we : searchByManufacturerPage.getAllProdsOnPage()) {
                assertTrue(we.getText().contains(prodNameTwo) || we.getText().contains(prodNameThree) || we.getText().contains(prodNameOne));
            }
        System.out.println("All products are Apple, Huawei or Samsung made");
    }
}
