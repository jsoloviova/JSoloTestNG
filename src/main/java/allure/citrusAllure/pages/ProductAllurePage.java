package allure.citrusAllure.pages;

import allure.citrusAllure.fragments.BasketAllureFragment;
import allure.citrusAllure.fragments.HeaderAllureFragment;
import allure.citrusAllure.fragments.SearchAllureFragment;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProductAllurePage extends BaseAllurePage {

    BasketAllureFragment basketFragment = new BasketAllureFragment();
    SearchAllureFragment searchFragment = new SearchAllureFragment();

    HeaderAllureFragment headerFragment = new HeaderAllureFragment();

    SelenideElement productPrice = $x("//div[@class='price']/span/span");
    SelenideElement buyButton = $x("//button[@class='btn orange full'][contains(text(),'Купить')]");

    public String getProductPrice() {
        return productPrice.getText();
    }

    public ProductAllurePage clickBuyButton() {
        buyButton.shouldBe(Condition.enabled).click();
        return this;
    }

    public ProductAllurePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public BasketAllureFragment getBasketFragment() {
        return basketFragment;
    }

    public SearchAllureFragment getSearchFragment() {
        return searchFragment;
    }

    public HeaderAllureFragment getHeaderFragment() {
        return headerFragment;
    }
}
