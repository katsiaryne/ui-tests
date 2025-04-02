package com.pizzeria.util;

import org.hamcrest.collection.IsEmptyCollection;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public final class TestPriceList {
    public static void testPriceList(List<Double> actualList, List<Double> expectedList) {
        assertAll(
                "Проверка порядка элементов по цене",
                () -> assertThat(actualList, not(IsEmptyCollection.empty())),
                () -> assertThat(actualList, is(expectedList))
        );
    }

    public static void testPriceLimit(List<Double> actualList, double leftBound, double rightBound) {
        assertAll(
                "Проверка ограничения элементов",
                () -> assertThat(actualList.get(0), is(greaterThanOrEqualTo(leftBound))),
                () -> assertThat(actualList.get(actualList.size() - 1), is(lessThanOrEqualTo(rightBound)))
        );
    }
}
