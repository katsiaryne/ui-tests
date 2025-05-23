package com.pizzeria;

import com.pizzeria.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;
import java.util.List;

import static com.pizzeria.util.TestPriceList.testPriceLimit;
import static com.pizzeria.util.TestPriceList.testPriceList;

@DisplayName("Тестирование применения фильтров к меню")
public class TestMenuFilters extends BaseTest {

    @DisplayName("Применение фильтра по возрастанию цены")
    @ParameterizedTest
    @CsvSource(value = {
            "ДЕСЕРТЫ, По возрастанию цены",
            "НАПИТКИ, По возрастанию цены",
            "ПИЦЦА, По возрастанию цены",
    })
    public void useFiltersAscOnMenu(String pageName, String sortName) {
        List<Double> resultPriceList = new MainPage()
                .getHeader()
                .openMenuList()
                .openMenuPage(pageName)
                .setSorting(sortName)
                .getProductsPriceList();
        List<Double> expectedPriceList = resultPriceList.stream().sorted().toList();
        testPriceList(resultPriceList, expectedPriceList);
    }

    @DisplayName("Применение фильтра по убыванию цены")
    @ParameterizedTest
    @CsvSource(value = {
            "ДЕСЕРТЫ, По убыванию цены",
            "НАПИТКИ, По убыванию цены",
            "ПИЦЦА, По убыванию цены"
    })
    public void useFiltersDescOnMenu(String pageName, String sortName) {
        List<Double> resultPriceList = new MainPage()
                .getHeader()
                .openMenuList()
                .openMenuPage(pageName)
                .setSorting(sortName)
                .getProductsPriceList();
        List<Double> expectedPriceList = resultPriceList.stream().sorted(Comparator.reverseOrder()).toList();
        testPriceList(resultPriceList, expectedPriceList);
    }

    @DisplayName("Применение слайдера сортирвоки цены")
    @ParameterizedTest
    @CsvSource(value = {
            "200, 300",
            "110, 300",
            "270, 520",
            "300, 300"
    })
    public void testSliderPriceFilter(int leftSliderPercents, int rightSliderPercents) {
        List<Double> resultPriceList = new MainPage()
                .getHeader()
                .openMenu()
                .moveLeftSlider(leftSliderPercents)
                .moveRightSlider(rightSliderPercents)
                .submitPriceSlider()
                .getProductsPriceList();
        List<Double> sortedList = resultPriceList.stream().sorted().toList();
        testPriceLimit(sortedList, leftSliderPercents, rightSliderPercents);
    }
}
