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

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    private String generateDateSecondType(int targetDay, int targetMonth, int targetYear, String pattern) {
        return LocalDate.now().withDayOfMonth(targetDay).withMonth(targetMonth).withYear(targetYear).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    public void shouldTestSelectingFromCitiesBySymbols() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("ка");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        $$(".menu-item__control").findBy(text("Йошкар-Ола")).click();
        try {
            Thread.sleep(2000);
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

    @Test
    public void shouldTestSelectingFromCalendarOneWeek() {
        open("http://localhost:9999");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        $("[data-test-id='city'] input").sendKeys("ка");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        $$(".menu-item__control").findBy(text("Йошкар-Ола")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        $(".icon-button__text").click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
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
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
                LocalDate dateMonth = LocalDate.now().plusMonths(i);
                borderDate = dateMonth.plusMonths(i+1).lengthOfMonth();
                targetMonth = targetMonth + 1;
                if (targetMonth > 12) {
                    targetMonth = 1;
                    targetYear = targetYear + 1;
                }

                }
                else {
                    $$("[data-day]").findBy(text(String.valueOf(targetDay))).click();
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
                    $(".notification__title")
                            .shouldBe(Condition.visible, Duration.ofSeconds(15))
                            .shouldHave(Condition.exactText("Успешно!"));
                    String actualDate = generateDateSecondType(targetDay, targetMonth, targetYear, "dd.MM.yyyy");
                    $(".notification__content")
                            .shouldHave(Condition.text("Встреча успешно забронирована на " + actualDate));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                    }
                    return;

                }
            }

        }



    @Test
    public void shouldTestSelectingFromCalendarAnyDateWithinPeriodOfYear() {
        open("http://localhost:9999");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        $("[data-test-id='city'] input").sendKeys("ка");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        $$(".menu-item__control").findBy(text("Йошкар-Ола")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        $(".icon-button__text").click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        int addDays = 109;
        int currentDate = LocalDate.now().getDayOfMonth();
        int borderDate = LocalDate.now().lengthOfMonth();
        int targetDay = currentDate + addDays;
        int targetMonth = LocalDate.now().getMonthValue();
        int targetYear = LocalDate.now().getYear();
        for (int i = 0; i < 12; i++) {

//            LocalDate dateMonth = LocalDate.now();

            if (targetDay > borderDate) {

                $("[data-step = '1']").click();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }

                targetDay = targetDay - borderDate;
                targetMonth = targetMonth + 1;

                if (targetMonth != 12) {
                    borderDate = LocalDate.now().plusMonths(1).lengthOfMonth();
                } else borderDate = 31;
                }
                if (targetMonth > 12) {
                    targetMonth = 1;
                    targetYear = targetYear + 1;
                }
                }
                if (targetDay <= borderDate) {
                    $$("[data-day]").findBy(text(String.valueOf(targetDay))).click();
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
                    $(".notification__title")
                            .shouldBe(Condition.visible, Duration.ofSeconds(15))
                            .shouldHave(Condition.exactText("Успешно!"));
                    String actualDate = generateDateSecondType(targetDay, targetMonth, targetYear, "dd.MM.yyyy");
                    $(".notification__content")
                            .shouldHave(Condition.text("Встреча успешно забронирована на " + actualDate));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                    }
                    return;

                }
            }

        }


