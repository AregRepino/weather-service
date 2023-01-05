package com.dbeaver.weather.repository.entity;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "weather_history")
public class WeatherHistory {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private double temperature;
    private LocalDate date;


    public WeatherHistory() {

    }


    public WeatherHistory(double temperature, LocalDate date) {
        this.temperature = temperature;
        this.date = date;
    }
}
