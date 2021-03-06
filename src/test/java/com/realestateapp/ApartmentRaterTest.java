package com.realestateapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRaterTest {

    @ParameterizedTest
    @CsvFileSource(
            resources = "/ApartmentParameters.csv",
            numLinesToSkip = 1
    )
    void should_ReturnCorrectRating_When_CorrectApartment(double area, double price, int rating){
        // given
        Apartment apartment = new Apartment(area, BigDecimal.valueOf(price));
        int expected = rating;

        // when
        int actual = ApartmentRater.rateApartment(apartment);

        // then
        assertEquals(expected, actual);

    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment(){
        // given
        Apartment apartment = new Apartment(0, BigDecimal.valueOf(35000.0));
        int expected = -1;

        // when
        int actual = ApartmentRater.rateApartment(apartment);

        // then
        assertEquals(expected, actual);

    }
    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList(){
        // given

        // when

        // then

    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList(){
        // given

        // when

        // then

    }

}