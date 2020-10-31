package citrus.pages;

import citrus.fragments.BasketFragment;
import citrus.fragments.HeaderFragment;
import citrus.fragments.SearchFragment;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    BasketFragment basketFragment = new BasketFragment();
    SearchFragment searchFragment = new SearchFragment();

    HeaderFragment headerFragment = new HeaderFragment();

    SelenideElement productPrice = $x("//div[@class='price']/span/span");
    SelenideElement buyButton = $x("//button[@class='btn orange full'][contains(text(),'Купить')]");

    public String getProductPrice() {
        return productPrice.getText();
    }

    public ProductPage clickBuyButton() {
        buyButton.shouldBe(Condition.enabled).click();
        return this;
    }

    public ProductPage waitForPageToLoad() {
        super.waitForPageToLoad();
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
