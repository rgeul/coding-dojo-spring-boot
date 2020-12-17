package com.assignment.spring.resttemplate;

import com.assignment.spring.exception.CityNotFoundException;
import com.assignment.spring.exception.ClientErrorException;
import com.assignment.spring.exception.ServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * RestTemplateErrorHandler is used in the WeatherController
 * and added as errorhandler to the RestTemplate
 */
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode()
                .series() == SERVER_ERROR) {
            throw new ServerErrorException("external service server error");
        } else if (httpResponse.getStatusCode()
                .series() == CLIENT_ERROR) {
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CityNotFoundException("city not found, check city");
            } else {
                throw new ClientErrorException("bad request, check city");
            }
        }
    }

}
