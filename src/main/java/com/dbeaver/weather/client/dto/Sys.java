package com.dbeaver.weather.client.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Sys {

    private double type;
    private double id;
    private String country;
    private double sunrise;
    private double sunset;
}
