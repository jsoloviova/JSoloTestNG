package citrus.pages;

import citrus.fragments.HeaderFragment;
import citrus.fragments.SearchFragment;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {

    SearchFragment searchFragment = new SearchFragment();
    HeaderFragment headerFragment = new HeaderFragment();

    public HomePage hoverMenuLine(String menuText) {
        $x("//div[@class='menu--desktop__drop-list show']/ul[@class='menu-aim']/li/a/span[2][contains(text(),'" + menuText + "')]").hover();
        return this;
    }

    public HomePage clickLinkInMenu(String linkText) {
        $x("//a[contains(@href,'/smartfony/brand-')]/span[contains(text(),'" + linkText + "')]").click();
        return this;
    }

    public HomePage clickLaptopMenuLink(String linkText) {
        $x("//a[contains(@href,'/noutbuki-i-ultrabuki/brand-acer/')]/span[contains(text(),'" + linkText + "')]").click();
        return this;
    }

    public HomePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public HomePage closePopUp() {
        super.closePopUp();
        return this;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }
}
