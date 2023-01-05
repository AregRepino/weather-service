package com.dbeaver.weather.client;


import com.dbeaver.weather.client.dto.OpenWeatherMapResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${openweathermap.name}", url = "${openweathermap.url}")
public interface OpenWeatherMapClient {


    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/weather?units={units}&lat={lat}&lon={lon}&appid=${openweathermap.appId}")
    OpenWeatherMapResult getCurrentWeather(@RequestParam("lat") double lat, @RequestParam("lon") double lon, @RequestParam("units") String units);

}
