package com.dbeaver.weather.service;

import com.dbeaver.weather.client.OpenWeatherMapClient;
import com.dbeaver.weather.client.dto.OpenWeatherMapResult;
import com.dbeaver.weather.config.AppConfig;
import com.dbeaver.weather.repository.WeatherRepository;
import com.dbeaver.weather.repository.entity.WeatherHistory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Data
@Service
@RequiredArgsConstructor

public class WeatherService {
    private final OpenWeatherMapClient openWeatherMapClient;
    private final AppConfig appConfig;
    private final WeatherRepository weatherRepository;


    public WeatherHistory queryCurrentWeather() {
        WeatherHistory weatherHistory = weatherRepository.findByDate(LocalDate.now());
        if (weatherHistory != null) {
            return weatherHistory;
        }

        OpenWeatherMapResult openWeatherMapResult = openWeatherMapClient.getCurrentWeather(appConfig.getLat(), appConfig.getLon(), appConfig.getUnits());
        double temp = openWeatherMapResult.getMain().getTemp();

        weatherHistory = weatherRepository.save(new WeatherHistory(temp, LocalDate.now()));


        return weatherHistory;


    }


}
