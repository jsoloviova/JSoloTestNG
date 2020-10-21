package RozetkaFactory;

import Rozetka1_FactoryPages.AddAndCheckComparisonFactoryPage;
import Rozetka1_FactoryPages.ComparisonProdsDataFactoryPage;
import Rozetka1_FactoryPages.MonitorsSearchFactoryPage;
import Rozetka1_FactoryPages.SeparateProductsDataFactoryPage;
import TestNgTests.BaseUiTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class monitorsComparisonFactoryTest extends BaseUiTests {
    String url = "https://rozetka.com.ua/";
    String topPriceValue = "2999";
    String prodNumber = "2";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void monitorsComparison() {
        MonitorsSearchFactoryPage monitorsSearchFactoryPage = new MonitorsSearchFactoryPage(driver);
        AddAndCheckComparisonFactoryPage addAndCheckComparisonFactoryPage = new AddAndCheckComparisonFactoryPage(driver);
        SeparateProductsDataFactoryPage separateProductsDataFactoryPage = new SeparateProductsDataFactoryPage(driver);
        ComparisonProdsDataFactoryPage comparisonProdsDataFactoryPage = new ComparisonProdsDataFactoryPage(driver);

        monitorsSearchFactoryPage.performHoverOnSideMenu();
        monitorsSearchFactoryPage.clickOnHoverMenuLink();
        monitorsSearchFactoryPage.filterProductsByPrice(topPriceValue);

        // click on first product with the price under 3000 + all the manipulations
        addAndCheckComparisonFactoryPage.findAndSelectProduct(0);
        addAndCheckComparisonFactoryPage.addToComparing();
        assertEquals(addAndCheckComparisonFactoryPage.compareIconCheck(), "1");

        monitorsSearchFactoryPage.navigateBack();

        // click on 2nd product with the price under 3000 + all the manipulations
        addAndCheckComparisonFactoryPage.findAndSelectProduct(1);
        addAndCheckComparisonFactoryPage.addToComparing();

        assertEquals(addAndCheckComparisonFactoryPage.compareIconCheck(), prodNumber);
        // compare pop-up + verifying (not the best way):
        assertEquals(addAndCheckComparisonFactoryPage.allProductsAddedToCompare(), prodNumber);

        addAndCheckComparisonFactoryPage.navigateToComparisonPage();

        assertEquals(comparisonProdsDataFactoryPage.firstComparisonPrice(), separateProductsDataFactoryPage.firstProdPrice());
        assertEquals(comparisonProdsDataFactoryPage.firstComparisonName(), separateProductsDataFactoryPage.firstProdName());
        assertEquals(comparisonProdsDataFactoryPage.secondComparisonPrice(), separateProductsDataFactoryPage.secondProdPrice());
        assertEquals(comparisonProdsDataFactoryPage.secondComparisonName(), separateProductsDataFactoryPage.secondProdName());

    }
}

// //span[@class='goods-tile__price-value']/ancestor::div[@data-goods-id='10592250']//a[@href='https://hard.rozetka.com.ua/samsung_c24f396f/p10592250/']