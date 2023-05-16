package com.example.weatheranalyzer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.weatheranalyzer.model.Record;
import com.example.weatheranalyzer.model.RecordQueryRequestModel;
import com.example.weatheranalyzer.model.RecordQueryResponseModel;
import com.example.weatheranalyzer.model.Sensor;
import com.example.weatheranalyzer.model.SensorRecordRequestModel;
import com.example.weatheranalyzer.repository.SensorRepository;
import com.example.weatheranalyzer.service.RecordService;
import com.example.weatheranalyzer.service.SensorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SensorRecordControllerTest {

	@Autowired
	SensorRecordController con;

	@MockBean
	SensorService sensorService;

	@MockBean
	RecordService recordService;

	@MockBean
	SensorRepository sensorRepository;

	@Autowired
	private MockMvc mockMvc;

	private Sensor sensor;
	private static String testQueryRequest;
	private SensorRecordRequestModel sensorRequest;

	@Test
	public void queryRecordsControllerTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		testQueryRequest = mapper.writeValueAsString(new RecordQueryRequestModel());
		when(recordService.queryRecords(new RecordQueryRequestModel())).thenReturn(new RecordQueryResponseModel());
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/query").contentType(MediaType.APPLICATION_JSON)
				.content(testQueryRequest)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void createSensorControllerTest() {
		HashMap<String, String> payload = new HashMap<>();
		payload.put("name", "Sensor1");
		when(sensorService.addSensor(payload.get("name"))).thenReturn(buildSensor());
		con.createSensor(payload);
		assertEquals(payload.get("name"), sensor.getName());
	}

	@Test
	public void createRecordTest() {
		con.createRecord(sensorRequest);
		verify(sensorService, times(1)).createRecord(any());
	}

	/*
	 * public RecordQueryRequestModel buildRecordQueryRequestModel() {
	 * queryRequest=new RecordQueryRequestModel(); Set<Long> ids = new HashSet<>();
	 * ids.add(1l); Set<String> metric = new HashSet<>(); metric.add("temperature");
	 * metric.add("humidity"); queryRequest.setSensorIds(ids);
	 * queryRequest.setMetrics(metric); queryRequest.setStatistic("min");
	 * queryRequest.setFromDate(new Date()); queryRequest.setToDate(new Date());
	 * return queryRequest; }
	 * 
	 * public RecordQueryResponseModel buildRecordQueryResponseModel() {
	 * queryResponse=new RecordQueryResponseModel();
	 * queryResponse.setHumidity(20.0); queryResponse.setTemperature(40.0);
	 * queryResponse.setHumidity(null); return queryResponse; }
	 */

	public Sensor buildSensor() {
		sensor = new Sensor();
		sensor.setId(1l);
		sensor.setName("Sensor1");
		sensor.setEnabled(true);
		sensor.setCreationDate(new Date(2023 - 05 - 15));
		sensor.addRecord(new Record());
		return sensor;
	}

	/*
	 * public SensorRecordRequestModel buildSensorRecordRequestModel() {
	 * sensorRequest = new SensorRecordRequestModel();
	 * sensorRequest.setSensorId(1l); sensorRequest.setTemperature(50.2);
	 * sensorRequest.setHumidity(20.0); sensorRequest.setWindSpeed(65.0); return
	 * sensorRequest; }
	 */
}
