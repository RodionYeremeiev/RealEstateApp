package com.realestateapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApartmentRaterTest {

    @ParameterizedTest
    @CsvFileSource(
            resources = "/ApartmentParameters.csv",
            numLinesToSkip = 1
    )
    void should_ReturnCorrectRating_When_CorrectApartment(double area, double price, int rating) {
        // given
        Apartment apartment = new Apartment(area, BigDecimal.valueOf(price));
        int expected = rating;

        // when
        int actual = ApartmentRater.rateApartment(apartment);

        // then
        assertEquals(expected, actual);

    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment() {
        // given
        Apartment apartment = new Apartment(0, BigDecimal.valueOf(35000.0));
        int expected = -1;

        // when
        int actual = ApartmentRater.rateApartment(apartment);

        // then
        assertEquals(expected, actual);

    }

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList(double area, double price) {
        // given
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(new Apartment(50.0, BigDecimal.valueOf(35000.0)));
        apartments.add(new Apartment(100.0, BigDecimal.valueOf(750000.0)));
        apartments.add(new Apartment(60.0, BigDecimal.valueOf(600000.0)));
        double expected = 1;

        // when
        double actual = ApartmentRater.calculateAverageRating(apartments);

        // then
        assertEquals(expected, actual);


    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
        // given
        List<Apartment> apartments = new ArrayList<>();

        // when
        Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);

        // then
        assertThrows(RuntimeException.class, executable);
    }

}