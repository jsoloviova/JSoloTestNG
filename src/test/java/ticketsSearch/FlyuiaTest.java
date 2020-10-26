package ticketsSearch;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class FlyuiaTest {

    @Test
    public void searchTicketsFlyuia() {
        Configuration.baseUrl = "https://www.flyuia.com";
        Configuration.timeout = 120000;
        //Configuration.browser = "firefox";
        Configuration.startMaximized = true;

        open("/");

        $("li.country-chooser").click();
        $(By.name("lang_chooser_input")).click();
        $x("//a[@href='/ua/en/home']").click();
        $x("//button[contains(@class,'country-chooser__btn')]").click();

        $$x(("//div[contains(@class,'input-infix')]")).get(0).click();
        $$x(("//div[contains(@class,'input-infix')]/input")).get(0).val("Vienna").pressEnter();
        $$x(("//div[contains(@class,'input-infix')]")).get(1).click();
        $$x(("//div[contains(@class,'input-infix')]/input")).get(1).val("Kyiv").pressEnter();

        $x("//sw-form-control-datepicker[@formcontrolname='departureDate']").click();
        $$x("//span[@class='prev-next-btn']").get(1).click();
        $("span.checkmark").click();
        $x("//button[contains(text(),'19')]").click();
        $x("//sw-form-control-datepicker[@formcontrolname='returnDate']").click();
        $("span.checkmark").click();
        $x("//button[contains(text(),'22')]").click();

        $x("//sw-form-control-passengers[@formcontrolname='passengers']").click();
        $$x("//button[contains(@class,'set-val-btn')]").get(1).click();
        $(By.id("SEARCH_WIDGET_FORM_BUTTONS_SEARCH_FLIGHTS")).click();

        switchTo().window("Search results");
        $("i.icon-close").click();

        $$x("//div[contains(@class,'flights-section')]/div").shouldHaveSize(2);
        $$x("//div[@class='product__title']").get(0).shouldHave(Condition.text("Departure Options: Vienna - Kyiv"));
        $$x("//div[@class='product__title']").get(1).shouldHave(Condition.text("Return Options: Kyiv - Vienna"));
        $$x("//button/div[@class='date-title']").get(3).shouldHave(Condition.text("Thu, 19 Nov"));
        $$x("//button/div[@class='date-title']").get(10).shouldHave(Condition.text("Sun, 22 Nov"));

    }
}
