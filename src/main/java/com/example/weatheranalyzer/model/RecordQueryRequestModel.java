package com.example.weatheranalyzer.model;

import java.util.Date;
import java.util.Set;

public class RecordQueryRequestModel {
	private Set<Long> sensorIds;
	private Set<String> metrics;
	private String statistic;
	private Date fromDate;
	private Date toDate;

	public Set<Long> getSensorIds() {
		return sensorIds;
	}

	public void setSensorIds(Set<Long> sensorIds) {
		this.sensorIds = sensorIds;
	}

	public Set<String> getMetrics() {
		return metrics;
	}

	public void setMetrics(Set<String> metrics) {
		this.metrics = metrics;
	}

	public String getStatistic() {
		return statistic;
	}

	public void setStatistic(String statistic) {
		this.statistic = statistic;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
