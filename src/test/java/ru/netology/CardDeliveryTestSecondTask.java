package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTestSecondTask {

    private String generateDateSecondType(int targetDay, int targetMonth, int targetYear, String pattern) {
        return LocalDate.now().withDayOfMonth(targetDay).withMonth(targetMonth).withYear(targetYear).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void shouldTestSelectingFromCalendarOneWeek() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").sendKeys("ка");
        $$(".menu-item__control").findBy(text("Йошкар-Ола")).click();
        $(".icon-button__text").click();
        int addDays = 7;
        int currentDate = LocalDate.now().getDayOfMonth();
        int borderDate = LocalDate.now().lengthOfMonth();
        int targetDay = currentDate + addDays;
        int targetMonth = LocalDate.now().getMonthValue();
        int targetYear = LocalDate.now().getYear();
        for (int i = 0; i < 12; i++) {

            if (targetDay > borderDate) {

                targetDay = targetDay - borderDate;
                $("[data-step = '1']").click();
                LocalDate dateMonth = LocalDate.now().plusMonths(i);
                borderDate = dateMonth.plusMonths(i+1).lengthOfMonth();
                targetMonth = targetMonth + 1;
                if (targetMonth > 12) {
                    targetMonth = 1;
                    targetYear = targetYear + 1;
                }
                } else {
                    $$("[data-day]").findBy(text(String.valueOf(targetDay))).click();
                    $("[data-test-id='name'] input").setValue("Пушкин Александр");
                    $("[data-test-id='phone'] input").setValue("+79270000000");
                    $("[data-test-id='agreement']").click();
                    $("button.button").click();
                    $(".notification__title")
                            .shouldBe(Condition.visible, Duration.ofSeconds(15))
                            .shouldHave(Condition.exactText("Успешно!"));
                    String actualDate = generateDateSecondType(targetDay, targetMonth, targetYear, "dd.MM.yyyy");
                    $(".notification__content")
                            .shouldHave(Condition.text("Встреча успешно забронирована на " + actualDate));
                    return;
                }
            }

        }
}


