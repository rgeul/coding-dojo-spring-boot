package com.assignment.spring.model;

import com.assignment.spring.api.Main;
import com.assignment.spring.api.Sys;
import com.assignment.spring.api.WeatherResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class WeatherResponseAndEntityTest {

    @Test
    public void testSuccessfulParse() {
        var weatherResponse = buildWeatherResponse();
        var weatherEntity = new WeatherEntity(weatherResponse);

        assertThat(weatherEntity.getCity()).isEqualTo("Amsterdam");
        assertThat(weatherEntity.getCountry()).isEqualTo("NL");
        assertThat(weatherEntity.getTemperature()).isEqualTo(290.1);

    }

    @Test
    public void testFailParse() {
        var weatherResponse = new WeatherResponse();

        try {
            new WeatherEntity(weatherResponse);
            fail("Should not reach here");
        } catch (Exception e) {
            assert (true);
        }

    }

    private WeatherResponse buildWeatherResponse() {
        var weatherResponse = new WeatherResponse();

        weatherResponse.setName("Amsterdam");
        var sys = new Sys();

        sys.setCountry("NL");
        weatherResponse.setSys(sys);

        var main = new Main();
        main.setTemp(290.1);
        weatherResponse.setMain(main);

        return weatherResponse;
    }
}