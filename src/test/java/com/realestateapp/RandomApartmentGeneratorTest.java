package com.realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomApartmentGeneratorTest {

    private static final double MAX_MULTIPLIER = 4.0;

    @Nested
    class DefaultApartmentGeneratorTest {

        RandomApartmentGenerator defaultRAG;

        @BeforeEach
        void setUp() {
            defaultRAG = new RandomApartmentGenerator();
        }

        @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
        void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
            // given
            double minArea = 30;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            // when
            Apartment defaultApartment = defaultRAG.generate();

            // then
            BigDecimal minApartmentPrice = new BigDecimal(defaultApartment.getArea()).multiply(minPricePerSquareMeter);
            BigDecimal maxApartmentPrice = new BigDecimal(defaultApartment.getArea()).multiply(maxPricePerSquareMeter);

            assertAll(
                    () -> assertTrue(defaultApartment.getArea() >= minArea),
                    () -> assertTrue(defaultApartment.getArea() <= maxArea),
                    () -> assertTrue(defaultApartment.getPrice().compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(defaultApartment.getPrice().compareTo(maxApartmentPrice) <= 0)
            );
        }
    }

    @Nested
    class CustomApartmentGeneratorTest {

        private double minArea = 20.0;
        private BigDecimal minPricePerSquareMeter = new BigDecimal(25000.0);
        RandomApartmentGenerator customRAG;

        @BeforeEach
        void setUp() {
            customRAG = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
        }

        @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
        void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
            // given
            double minArea = this.minArea;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(BigDecimal.valueOf(MAX_MULTIPLIER));

            // when
            Apartment customApartment = customRAG.generate();

            // then
            BigDecimal minApartmentPrice = new BigDecimal(customApartment.getArea()).multiply(minPricePerSquareMeter);
            BigDecimal maxApartmentPrice = new BigDecimal(customApartment.getArea()).multiply(maxPricePerSquareMeter);

            assertAll(
                    () -> assertTrue(customApartment.getArea() >= minArea),
                    () -> assertTrue(customApartment.getArea() <= maxArea),
                    () -> assertTrue(customApartment.getPrice().compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(customApartment.getPrice().compareTo(maxApartmentPrice) <= 0)
            );

        }

    }

}