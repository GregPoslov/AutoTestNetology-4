package ru.netology.webselenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class AppCardTest {
    private String generateDate (int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void completedSuccessfully (){
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Рязань");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Послов Григорий Вадимович");
        $("[data-test-id='phone'] input").setValue("+79504596752");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
}
