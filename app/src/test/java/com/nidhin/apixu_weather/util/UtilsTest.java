package com.nidhin.apixu_weather.util;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void getFormattedDate() {

        try {
            assertEquals("Friday, 23",Utils.getFormattedDate("2019-02-02"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getDayOfMonthSuffix() {

        assertEquals("nd",Utils.getDayOfMonthSuffix(2));
        assertEquals("rd",Utils.getDayOfMonthSuffix(3));
        assertEquals("th",Utils.getDayOfMonthSuffix(4));
        assertEquals("th",Utils.getDayOfMonthSuffix(9));
        assertEquals("st",Utils.getDayOfMonthSuffix(21));
        assertEquals("nd",Utils.getDayOfMonthSuffix(22));
    }
}