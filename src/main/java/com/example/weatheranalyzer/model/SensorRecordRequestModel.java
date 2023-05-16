package com.example.weatheranalyzer.model;

import javax.validation.constraints.NotNull;

public class SensorRecordRequestModel {

	@NotNull(message="Metrics should be associated with a Sensor. Please enter valid sensor Id")
	private Long sensorId;
	private Double temperature;
	private Double humidity;
	private Double windSpeed;

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
}
