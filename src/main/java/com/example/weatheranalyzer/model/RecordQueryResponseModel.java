package com.example.weatheranalyzer.model;

public class RecordQueryResponseModel {
	private Double humidity;
	private Double temperature;
	private Double windSpeed;

	public RecordQueryResponseModel(Double humidity, Double temperature, Double windSpeed) {
		this.humidity = humidity;
		this.temperature = temperature;
		this.windSpeed = windSpeed;
	}

	public RecordQueryResponseModel() {
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

}
