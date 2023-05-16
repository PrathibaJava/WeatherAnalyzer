package com.example.weatheranalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SensorNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

public SensorNotFoundException(String exception) {
    super(exception);
  }

}
