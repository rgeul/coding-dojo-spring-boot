package com.assignment.spring.controller;

import com.assignment.spring.api.Main;
import com.assignment.spring.api.Sys;
import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.model.WeatherEntity;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherControllerTest {

    @Test
    public void ResponseToEntityMapperTest() {
        WeatherController weatherController = new WeatherController();
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setName("Amsterdam");
        weatherResponse.setSys(new Sys());
        weatherResponse.getSys().setCountry("NL");
        weatherResponse.setMain(new Main());
        weatherResponse.getMain().setTemp(281.79d);
        weatherResponse.setId(1);

        WeatherEntity weatherEntity = new WeatherEntity(weatherResponse);
        ResponseEntity<WeatherResponse> responseEntity = new ResponseEntity<>(weatherResponse, HttpStatus.OK);
        WeatherEntity weatherEntityMapped = weatherController.ResponseToEntityMapper(responseEntity.getBody());

        assertEquals(weatherEntity.getCity(), weatherEntityMapped.getCity());
        assertEquals(weatherEntity.getCountry(), weatherEntityMapped.getCountry());
        assertEquals(weatherEntity.getTemperature(), weatherEntityMapped.getTemperature());

    }
}