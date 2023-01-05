package com.dbeaver.weather.service;




import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WeatherUtilsTest {

    @Test
    public void creatingALocationRequestTrue() {
        String actual = WeatherUtils.findALocation(60.016033, 30.475637);
        assertEquals("location found", actual);
    }


    @Test
    public void creatingALocationRequestFalse() {
        String actual = WeatherUtils.findALocation(0, 0);
        assertEquals("location not found", actual);
    }

}
