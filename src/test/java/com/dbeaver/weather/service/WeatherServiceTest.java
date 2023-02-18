package com.dbeaver.weather.service;


import com.dbeaver.weather.client.OpenWeatherMapClient;
import com.dbeaver.weather.client.dto.Main;
import com.dbeaver.weather.client.dto.OpenWeatherMapResult;
import com.dbeaver.weather.config.AppConfig;
import com.dbeaver.weather.mapper.WeatherMapper;
import com.dbeaver.weather.repository.WeatherRepository;
import com.dbeaver.weather.repository.entity.WeatherHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class WeatherServiceTest {
    @Configuration
    @Import(WeatherService.class)
    static class Config {
    }

    @MockBean
    private OpenWeatherMapClient openWeatherMapClient;

    @MockBean
    private AppConfig appConfig;

    @MockBean
    private WeatherMapper weatherMapper;

    @MockBean
    private WeatherRepository weatherRepository;


    @Autowired
    private WeatherService weatherService;


    @Test
    public void queryCurrentWeather() {



//        given

        OpenWeatherMapResult openWeatherMapResult = new OpenWeatherMapResult();
        Main main = new Main();
        main.setTemp(20.0);
        openWeatherMapResult.setMain(main);
        WeatherHistory expected = new WeatherHistory();
        expected.setTemperature(20.0);
        expected.setDate(LocalDate.now());
        expected.setId(UUID.randomUUID());

        WeatherHistory weatherHistory = new WeatherHistory();
        weatherHistory.setTemperature(20.0);
        weatherHistory.setDate(LocalDate.now());

        given(this.openWeatherMapClient.getCurrentWeather(anyDouble(), anyDouble(), any())).willReturn(openWeatherMapResult);

       // given(weatherRepository.findByWeatherDate(null));

        given(weatherRepository.save(weatherHistory)).willReturn(expected);

//        when

        WeatherHistory result = weatherService.queryCurrentWeather();

//        them

       assertNotNull(result);
       assertEquals(expected, result);


    }

    @Test
    public void queryCurrentWeatherHistory() {
        //        given
        WeatherHistory weatherHistory = new WeatherHistory();
        LocalDate localDate = LocalDate.now();
        weatherHistory.setDate(localDate);
        weatherHistory.setTemperature(20.0);


        given(this.weatherRepository.findByDate(any())).willReturn(weatherHistory);
        //        when

        WeatherHistory result = weatherService.queryCurrentWeather();

        //        them
        assertNotNull(result);
        assertEquals(weatherHistory,result);

    }

}
