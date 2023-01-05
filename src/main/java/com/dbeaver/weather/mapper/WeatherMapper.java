package com.dbeaver.weather.mapper;

import com.dbeaver.weather.dto.WeatherHistoryDto;
import com.dbeaver.weather.repository.entity.WeatherHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class WeatherMapper {

    public abstract WeatherHistoryDto toWeatherHistoryDto (WeatherHistory weatherHistory);


    public abstract WeatherHistory toWeatherHistory (WeatherHistoryDto weatherHistoryDto);

}
