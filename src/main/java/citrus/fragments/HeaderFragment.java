package citrus.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderFragment {

    SelenideElement basketButton = $x("//div[@class='user-actions__cart h-icons__icon ctrs-basket-mini-icon']");
    SelenideElement comparisonPageButton = $x("//div[@class='user-actions__compare tips-parent']");

    public void openBasketFromHeader() {
        basketButton.click();
    }

    public void openComparisonFromHeader() {
        comparisonPageButton.click();
    }

}
