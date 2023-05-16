package com.example.weatheranalyzer.exception;

public class SensorNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

public SensorNotFoundException(String message) {
    super(message);
  }

}
