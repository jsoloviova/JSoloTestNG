package citrus.pages;

import citrus.fragments.BasketFragment;
import citrus.fragments.HeaderFragment;
import citrus.fragments.SearchFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonPage extends BasePage {

    BasketFragment basketFragment = new BasketFragment();
    SearchFragment searchFragment = new SearchFragment();
    HeaderFragment headerFragment = new HeaderFragment();

    ElementsCollection productsNumberToCompare = $$x("//div[@class='relative']");
    ElementsCollection prodNames = $$x("//div[@class='title-itm']/h5");
    ElementsCollection prodPrices = $$x("//div[@class='price-itm']//span[@class='price-number']");
    SelenideElement addNewProductButton = $x("//span[@class='img-container flex-column']");
    ElementsCollection newProductNames = $$x("//p[@class='product-name']");
    ElementsCollection newProductPrices = $$x("//span[@class='price-new']/span[@class='price-number']");
    SelenideElement addButton = $x("//button[@class='el-button el-button--primary']");
    ElementsCollection priceList = $$x("//span[@class='price']");
    ElementsCollection nameList = $$x("//div[@class='product-card__name']//span");
    SelenideElement resultPageTitle = $x("//h1[@class='main-content__promotion-title']");

    public ElementsCollection getPriceList() {
        return priceList;
    }

    public ElementsCollection getNameList() {
        return nameList;
    }

    public ElementsCollection getProductBlocksFromComparison() {
        return productsNumberToCompare;
    }

    public ElementsCollection getProdNamesFromComparison() {
        return prodNames;
    }

    public ElementsCollection getProdPricesFromComparison() {
        return prodPrices;
    }

    public ComparisonPage addNewProductToComparison() {
        addNewProductButton.click();
        return this;
    }



    public ElementsCollection getNewProductNames() {
        return newProductNames;
    }

    public ElementsCollection getNewProductPrices() {
        return newProductPrices;
    }

    public ComparisonPage clickAddNewProductButton() {
        addButton.click();
        return this;
    }

    public ComparisonPage closePopUp() {
        super.closePopUp();
        return this;
    }

    public ComparisonPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public ComparisonPage addProductFromListToComparison(int index) {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", $$x("//button[@class='product-card__to-compare']").get(index)));
        //$$x("//button[@class='product-card__to-compare']").get(index).click();
        return this;
    }

    public ComparisonPage scrollToTitle() {
        resultPageTitle.scrollTo();
        return this;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }
}
