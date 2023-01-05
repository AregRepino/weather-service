package com.dbeaver.weather.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class WeatherHistoryDto {

    private UUID id;
    private double temperature;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
}
