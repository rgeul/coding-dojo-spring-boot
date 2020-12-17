package com.assignment.spring.controller;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.config.AppConfig;
import com.assignment.spring.exception.CityNoContentException;
import com.assignment.spring.exception.ServerNotAvailableException;
import com.assignment.spring.model.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.resttemplate.RestTemplateErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private AppConfig configProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherRepository weatherRepository;

    /**
     * Request get mapping for the application at the /weather url
     *
     * @param city City as input for the API to receive specific weather response
     * @return Object if succesfull then return the saved WeatherEntity record. Else a ResponseEntity Exception
     **/
    @RequestMapping("/weather")
    public Object getWeatherByCity(@RequestParam String city) {
        var response = getApiResponse(city);
        try {
            if (response.getStatusCode().is2xxSuccessful()) {
                ResponseToEntityMapper(response.getBody());
                return weatherRepository.save(ResponseToEntityMapper(response.getBody()));
            }
            return response;
        } catch (Exception e) {
            logger.error("openweather-rest-client-error:" + e.getMessage());
            return new ResponseEntity<>("Internal application error. Please contact support.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets the API response from the OpenWeather API
     * @param city
     * @return ResponseEntity<WeatherResponse> from the API
     * API Request response from OpenWeather API
     */
    public ResponseEntity<WeatherResponse> getApiResponse(String city) {
        restTemplate.setErrorHandler(errorHandler());
        ResponseEntity<WeatherResponse> response;
        try {
            response = restTemplate.getForEntity(configProperties.getUrl(), WeatherResponse.class, city, configProperties.getAppId());
            if (response.getBody() == null) {
                throw new CityNoContentException("there's no data available for the given city");
            }
            return response;
        } catch (ResourceAccessException e) {
            throw new ServerNotAvailableException("external service not available");
        }
    }

    protected ResponseErrorHandler errorHandler() {
        return new RestTemplateErrorHandler();
    }

    /**
     * Maps the response tot the Entity object
     * @param response
     * @return WeatherEntity
     *
     */
    public WeatherEntity ResponseToEntityMapper(WeatherResponse response) {
        return new WeatherEntity(response);
    }

}
