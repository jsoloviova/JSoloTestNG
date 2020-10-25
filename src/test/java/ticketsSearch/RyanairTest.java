package ticketsSearch;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class RyanairTest {

    @Test
    public void searchTicketsRyanair() {
        Configuration.baseUrl = "https://www.ryanair.com";
        Configuration.timeout = 60000;
        //Configuration.browser = "firefox";
        Configuration.startMaximized = true;

        open("/");

        $x("//button[@data-ref='language-selector-button__toggle-button']").click();
        $x("//a[@href='/gb/en']").click();

        $(By.id("input-button__departure")).click();
        $(By.id("input-button__departure")).clear();
        $(By.id("input-button__departure")).val("Vienna");
        $x("//span[contains(text(),'Vienna')]").click();
        $(By.id("input-button__destination")).val("Kyiv");
        $x("//span[contains(text(),'Kyiv')]").click();

        $x("//div[@data-id='2020-11-19']").click();
        $x("//div[@data-id='2020-11-24']").click();
        $$x("//div[@class='counter__button-wrapper--enabled']").first().click();
        $x(("//button[@aria-label='Search']")).click();

        $$x("//div[@class='journeys-wrapper']/div").shouldHaveSize(2);
        $$x("//div/span[contains(@class,'date-item__day-of-month')]").get(2).shouldHave(Condition.text("19"));
        $$x("//div/span[contains(@class,'date-item__month')]").get(2).shouldHave(Condition.text("Nov"));
        $$x("//div[contains(@class,'date-item__day-of-week')]").get(2).shouldHave(Condition.text(" Thursday "));

        $$x("//div[contains(@class,'date-item__day-of-week')]").get(7).scrollTo();
        $$x("//div/span[contains(@class,'date-item__day-of-month')]").get(7).shouldHave(Condition.text("24"));
        $$x("//div/span[contains(@class,'date-item__month')]").get(7).shouldHave(Condition.text("Nov"));
        $$x("//div[contains(@class,'date-item__day-of-week')]").get(7).shouldHave(Condition.text(" Tuesday "));

//        List lists = new LinkedList();
//        lists.add($$x("//div/span[contains(@class,'date-item__day-of-month')]").texts().get(2));
//        lists.add($$x("//div/span[contains(@class,'date-item__month')]").texts().get(2));
//        lists.add($$x("//div[contains(@class,'date-item__day-of-week')]").texts().get(2));
//        System.out.println(lists.toString());

    }
}
