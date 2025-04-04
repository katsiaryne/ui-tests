package com.interact;

import com.interact.page.webinar.WebinarPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.interact.util.TestConstantsWebinar.DEFAULT_ACTIVE_DIRECTION;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//https://docs.google.com/spreadsheets/d/1R2i8tKGKz7aTY-iA_joQGGQzVeKP26g_4HpGNqzmy10/edit?gid=269149191#gid=269149191
public class TestWebinarDirections extends WebinarBaseTest {
    @Test
    @DisplayName("Проверка открытия выпадающего списка")
    public void checkDirections() {
        WebinarPage page = new WebinarPage()
                .openDirections();

        assertAll(
                "Проверка открытия выпадающего списка",
                () -> assertFalse(page.getDirectionsList().isEmpty(), "Список не отобразился"),
                () -> assertEquals(15, page.getDirectionsList().size(), "Не все элементы доступны"),
                () -> assertEquals("#ebebeb", page.getActiveDirectionBackgroundColor(), "Текущий элемент не выделен")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Инженерия", "Психология", "Нейросети и no-code", "Все направления"})
    public void openAnotherDirection(String directionName) {
        WebinarPage page = new WebinarPage()
                .openDirections()
                .openDirection(directionName);
        assertAll(
                "Проверка перехода на другое направление",
                () -> assertEquals(directionName, page.getDirectionsButton().getText(), "Направление не изменилось")
        );
    }

    @Test
    public void openAndCloseDirections() {
        WebinarPage page = new WebinarPage()
                .openDirections()
                .closeDirections();
        assertAll(
                "Проверка активого элемента",
                () -> assertEquals(DEFAULT_ACTIVE_DIRECTION, page.getDirectionsButton().getText(), "Активное направление не совпадает")
        );
    }
}
