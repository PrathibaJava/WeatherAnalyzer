package com.example.weatheranalyzer.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weatheranalyzer.exception.SensorNotFoundException;
import com.example.weatheranalyzer.model.RecordQueryRequestModel;
import com.example.weatheranalyzer.model.RecordQueryResponseModel;
import com.example.weatheranalyzer.model.Sensor;
import com.example.weatheranalyzer.model.SensorRecordRequestModel;
import com.example.weatheranalyzer.service.RecordService;
import com.example.weatheranalyzer.service.SensorService;

@RestController
@RequestMapping("/api")
public class SensorRecordController {

	private final SensorService sensorService;
	private final RecordService recordService;

	@Autowired
	public SensorRecordController(SensorService sensorService, RecordService recordService) {
		this.sensorService = sensorService;
		this.recordService = recordService;
	}

	@PostMapping(path = "/addSensor", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Sensor createSensor(@RequestBody Map<String, String> payload) {
		return sensorService.addSensor(payload.get("name"));
	}

	@PostMapping(path = "/addRecord", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void createRecord(@Valid @RequestBody SensorRecordRequestModel recordRequestModel) throws SensorNotFoundException {
		sensorService.createRecord(recordRequestModel);
	}

	@PostMapping(path = "/query", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RecordQueryResponseModel> queryRecords(@Valid @RequestBody RecordQueryRequestModel requestModel) {
		return new ResponseEntity<>(recordService.queryRecords(requestModel), HttpStatus.OK);
	}

}
