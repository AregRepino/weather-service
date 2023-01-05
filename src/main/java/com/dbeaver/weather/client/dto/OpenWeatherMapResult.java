package com.dbeaver.weather.client.dto;

import com.dbeaver.weather.client.dto.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class OpenWeatherMapResult {

    private Coord coord;
    private List<Weather> weather;
    //private Weather[] weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private String base;
    private double visibility;
    private double dt;
    private double timezone;
    private double id;
    private String name;
    private int cod;




}
