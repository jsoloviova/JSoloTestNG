package RozetkaRefactoring;

import Rozetka1_Pages.AddAndCheckComparisonPage;
import Rozetka1_Pages.ComparisonProdsDataPage;
import Rozetka1_Pages.MonitorsSearchPage;
import Rozetka1_Pages.SeparateProductsDataPage;
import TestNgTests.BaseUiTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class monitorsComparisonTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";
    String topPriceValue = "2999";
    String prodNumber = "2";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void monitorsComparison() {
        MonitorsSearchPage monitorsSearchPage = new MonitorsSearchPage(driver);
        AddAndCheckComparisonPage addAndCheckComparisonPage = new AddAndCheckComparisonPage(driver);
        SeparateProductsDataPage separateProductsDataPage = new SeparateProductsDataPage(driver);
        ComparisonProdsDataPage comparisonProdsDataPage = new ComparisonProdsDataPage(driver);

        monitorsSearchPage.performHoverOnSideMenu();
        monitorsSearchPage.clickOnHoverMenuLink();
        monitorsSearchPage.filterProductsByPrice(topPriceValue);

        // click on first product with the price under 3000 + all the manipulations
        addAndCheckComparisonPage.findAndSelectProduct(0);
        addAndCheckComparisonPage.addToComparing();
        assertEquals(addAndCheckComparisonPage.compareIconCheck(), "1");

        monitorsSearchPage.navigateBack();

        // click on 2nd product with the price under 3000 + all the manipulations
        addAndCheckComparisonPage.findAndSelectProduct(1);
        addAndCheckComparisonPage.addToComparing();

        assertEquals(addAndCheckComparisonPage.compareIconCheck(), prodNumber);
        // compare pop-up + verifying (not the best way):
        assertEquals(addAndCheckComparisonPage.allProductsAddedToCompare(), prodNumber);

        addAndCheckComparisonPage.navigateToComparisonPage();

        assertEquals(comparisonProdsDataPage.firstComparisonPrice(), separateProductsDataPage.firstProdPrice());
        assertEquals(comparisonProdsDataPage.firstComparisonName(), separateProductsDataPage.firstProdName());
        assertEquals(comparisonProdsDataPage.secondComparisonPrice(), separateProductsDataPage.secondProdPrice());
        assertEquals(comparisonProdsDataPage.secondComparisonName(), separateProductsDataPage.secondProdName());

    }
}

// //span[@class='goods-tile__price-value']/ancestor::div[@data-goods-id='10592250']//a[@href='https://hard.rozetka.com.ua/samsung_c24f396f/p10592250/']