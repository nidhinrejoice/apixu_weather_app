package com.nidhin.apixu_weather.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void getFormattedDate() {

        assertEquals("Friday, 23",Utils.getFormattedDate(1949524800));
    }
}