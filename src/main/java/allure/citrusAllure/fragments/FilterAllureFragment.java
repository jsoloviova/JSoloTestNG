package allure.citrusAllure.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FilterAllureFragment {

    ElementsCollection priceFilterInput = $$x("//input[@class='el-input__inner']");
    SelenideElement filterTitle = $x("//p[@class='title']");
    ElementsCollection memoryCheckboxes = $$x("//div[contains(text(),'Объем встроенной памяти')]/../ul/li");
    SelenideElement memoryFilterTitle = $x("//div[contains(text(),'Объем встроенной памяти')]");
    SelenideElement materialMetalCheckbox = $x("//div[contains(text(),'Материалы корпуса')]/../ul/li//a[contains(text(),'Металл')]");
    SelenideElement materialFilterTitle = $x("//div[contains(text(),'Материалы корпуса')]");

    public void setPriceFilter(int index, String price) {
        priceFilterInput.get(index).clear();
        priceFilterInput.get(index).val(price);
        filterTitle.click();
    }

    public void setMemoryFilter(int index) {
        memoryFilterTitle.scrollTo();
        memoryCheckboxes.get(index).click();
    }

    public void setMaterialFilter() {
        materialFilterTitle.scrollTo();
        materialMetalCheckbox.click();
    }

}
