package com.example.weatheranalyzer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.weatheranalyzer.model.Record;
import com.example.weatheranalyzer.model.Sensor;
import com.example.weatheranalyzer.model.SensorRecordRequestModel;
import com.example.weatheranalyzer.repository.SensorRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class SensorServiceTest {

	@Autowired
	private SensorService sensorService;

	@MockBean
	SensorRepository sensorRepository;

	private Sensor sensor;
	private SensorRecordRequestModel requestModel;

	@Test
	public void addSensorTest() {
		when(sensorRepository.save(any())).thenReturn(buildSensor());
		sensorService.addSensor("Sensor1");
		assertEquals(1l, sensor.getId());
		assertEquals("Sensor1", sensor.getName());
	}

	@Test
	public void createRecordTest() {
		Optional<Sensor> requestSensor = Optional.of(buildSensor());
		buildRequestModel();
		when(sensorRepository.findById(requestModel.getSensorId())).thenReturn(requestSensor);
		sensorService.createRecord(buildRequestModel());
		verify(sensorRepository, times(1)).save(any());
	}

	public Sensor buildSensor() {
		sensor = new Sensor();
		sensor.setId(1l);
		sensor.setName("Sensor1");
		sensor.setEnabled(true);
		sensor.setCreationDate(new Date(2023 - 05 - 15));
		sensor.addRecord(new Record(20.0, 30.0, 40.2));
		return sensor;
	}

	public SensorRecordRequestModel buildRequestModel() {
		requestModel = new SensorRecordRequestModel();
		requestModel.setSensorId(1l);
		return requestModel;
	}
}
