package com.dbeaver.weather.repository;

import com.dbeaver.weather.repository.entity.WeatherHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherHistory, Long> {


    WeatherHistory findByDate(LocalDate date);

}
