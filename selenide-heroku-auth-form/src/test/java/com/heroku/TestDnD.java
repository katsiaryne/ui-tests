package com.heroku;

import com.heroku.page.draganddrop.DragAndDropPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.heroku.config.ConfigProvider.DND_URL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestDnD extends BaseTest {
    @BeforeEach
    public void setUp() {
        open(DND_URL);
    }

    @Test
    public void testDragAndDrop() {
        DragAndDropPage page = new DragAndDropPage();
        List<String> baseColumns = page.getListOfColumns();
        page.dragAndDrop();
        assertAll(
                "Проверка перемещения колонок",
                () -> assertNotEquals(baseColumns, page.getListOfColumns())
        );
    }
}
