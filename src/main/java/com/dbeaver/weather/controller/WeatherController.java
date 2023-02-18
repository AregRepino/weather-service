package com.dbeaver.weather.controller;


import com.dbeaver.weather.dto.WeatherHistoryDto;
import com.dbeaver.weather.mapper.WeatherMapper;
import com.dbeaver.weather.repository.entity.WeatherHistory;
import com.dbeaver.weather.service.WeatherService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;

    @GetMapping(value = "/weather", produces = "application/json")
    public WeatherHistoryDto getCurrentWeather() {

        WeatherHistory weatherHistory = weatherService.queryCurrentWeather();
        WeatherHistoryDto weatherHistoryDto = weatherMapper.toWeatherHistoryDto(weatherHistory);

        return weatherHistoryDto;
    }

}
