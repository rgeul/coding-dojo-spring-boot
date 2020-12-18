package com.assignment.spring.controller;

import com.assignment.spring.model.WeatherEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
@RunWith(SpringRunner.class)
public class WeatherControllerIT {

    private static final int ID = 1;
    private static final String CITY = "Amsterdam";
    private static final String COUNTRY = "NL";
    private static final double TEMP = 280.81;

    private WeatherEntity weatherEntity;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherController weatherController;

    @Before
    public void setup() {
        weatherEntity = new WeatherEntity();
        weatherEntity.setId(ID);
        weatherEntity.setCity(CITY);
        weatherEntity.setCountry(COUNTRY);
        weatherEntity.setTemperature(TEMP);
    }

    @Test
    public void testGetWeather() throws Exception {
        when(weatherController.getWeatherByCity(CITY)).thenReturn(weatherEntity);
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/weather").param("city", CITY))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"id\":1,\"city\":\"Amsterdam\",\"country\":\"NL\",\"temperature\":280.81}"));
    }
}