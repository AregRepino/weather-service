package com.dbeaver.weather.service;

import org.junit.Test;

public class WeatherUtils {

    public static String findALocation(double latitude, double longitude) {
        if (latitude > 0 && longitude > 0) {
            return "location found";
        } else {
            return "location not found";
        }
    }

}
