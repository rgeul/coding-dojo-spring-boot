package com.assignment.spring.model;

import com.assignment.spring.api.WeatherResponse;

import javax.persistence.*;

@Entity
@Table(name = "weather")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String city;

    private String country;

    private Double temperature;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public WeatherEntity(WeatherResponse response) {
        setCity(response.getName());
        setCountry(response.getSys().getCountry());
        setTemperature(response.getMain().getTemp());
    }

    public WeatherEntity() {
    }

}
