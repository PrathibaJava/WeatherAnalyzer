package com.example.weatheranalyzer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.weatheranalyzer.exception.SensorNotFoundException;
import com.example.weatheranalyzer.model.Sensor;
import com.example.weatheranalyzer.model.SensorRecordRequestModel;
import com.example.weatheranalyzer.repository.SensorRepository;

@Service
@Transactional
public class SensorService {

	private final SensorRepository sensorRepository;

	@Autowired
	SensorService(SensorRepository sensorRepository) {
		this.sensorRepository = sensorRepository;
	}

	public Sensor addSensor(String name) {
		Sensor sensor = new Sensor();
		sensor.setName(name);
		sensor.setEnabled(true);
		sensorRepository.save(sensor);
		return sensor;
	}

	public void createRecord(SensorRecordRequestModel recordRequestModel) {
		Sensor sensor = sensorRepository.findById(recordRequestModel.getSensorId()).orElse(null);

		if (sensor == null) {
			throw new SensorNotFoundException("Sensor with id: " + recordRequestModel.getSensorId() + " not found");
		} else {
			sensor.addRecord(recordRequestModel.getTemperature(), recordRequestModel.getHumidity(),
					recordRequestModel.getWindSpeed());
			sensorRepository.save(sensor);
		}
	}
}
