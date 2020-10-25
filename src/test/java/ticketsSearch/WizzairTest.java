package ticketsSearch;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class WizzairTest {

    @Test
    public void searchTicketsWizzair() {
        Configuration.baseUrl = "https://wizzair.com";
        Configuration.timeout = 60000;
        //Configuration.browser = "firefox";
        Configuration.startMaximized = true;

        open("/");

        $(By.id("search-departure-station")).val("Vienna");
        $x("//mark[contains(text(),'Vienna')]").click();
        $(By.id("search-arrival-station")).val("Kyiv");
        $x("//mark[contains(text(),'Kyiv')]").click();

        $(By.id("search-departure-date")).click();
        $x("//button[@data-pika-year='2020'][@data-pika-month='10'][@data-pika-day='19']").click();
        $(By.id("search-return-date")).click();
        $x("//button[@data-pika-year='2020'][@data-pika-month='10'][@data-pika-day='22']").click();
        $$x("//button[@class='base-button base-button--medium base-button--primary']").first().click();

        $(By.id("search-passenger")).click();
        $$x("//button[@data-test='services-flight-search-increment']").first().click();
        $$x("//button[@class='base-button base-button--medium base-button--primary']").get(1).click();

        $(".flight-search__panel__cta-btn").click();

        switchTo().window(1);

        $$(".flight-select__fare-selector").shouldHaveSize(2);
        $$(".flight-select__flight-info__day").get(0).shouldHave(Condition.text(" Thu, 19 Nov 2020 "));
        $$(".flight-select__flight-info__day").get(1).scrollTo();
        //or $x("//button[@type='submit']").scrollTo();
        $$(".flight-select__flight-info__day").get(1).shouldHave(Condition.text(" Sun, 22 Nov 2020 "));

    }
}
