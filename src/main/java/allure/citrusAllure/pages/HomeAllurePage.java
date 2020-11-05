package allure.citrusAllure.pages;

import allure.citrusAllure.fragments.HeaderAllureFragment;
import allure.citrusAllure.fragments.SearchAllureFragment;

import static com.codeborne.selenide.Selenide.$x;

public class HomeAllurePage extends BaseAllurePage {

    SearchAllureFragment searchFragment = new SearchAllureFragment();
    HeaderAllureFragment headerFragment = new HeaderAllureFragment();

    public HomeAllurePage hoverMenuLine(String menuText) {
        $x("//div[@class='menu--desktop__drop-list show']/ul[@class='menu-aim']/li/a/span[2][contains(text(),'" + menuText + "')]").hover();
        return this;
    }

    public HomeAllurePage clickLinkInMenu(String linkText) {
        $x("//a[contains(@href,'/smartfony/brand-')]/span[contains(text(),'" + linkText + "')]").click();
        return this;
    }

    public HomeAllurePage clickLaptopMenuLink(String linkText) {
        $x("//a[contains(@href,'/noutbuki-i-ultrabuki/brand-acer/')]/span[contains(text(),'" + linkText + "')]").click();
        return this;
    }

    public HomeAllurePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public HomeAllurePage closePopUp() {
        super.closePopUp();
        return this;
    }

    public SearchAllureFragment getSearchFragment() {
        return searchFragment;
    }

    public HeaderAllureFragment getHeaderFragment() {
        return headerFragment;
    }
}
