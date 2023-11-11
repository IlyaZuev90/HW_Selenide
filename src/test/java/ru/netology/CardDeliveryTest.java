package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBePositiveTestCard() {
        open("http://localhost:9999");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        $("[data-test-id='city'] input").setValue("Самара");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        String currentDate = generateDate(3, "dd.MM.yyyy");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        $("[data-test-id='date'] input").sendKeys(currentDate);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        $("[data-test-id='name'] input").setValue("Пушкин Александр");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        $("[data-test-id='phone'] input").setValue("+79270000000");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        $("[data-test-id='agreement']").click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        $("button.button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + currentDate));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
    }
}

