package com.example.weatheranalyzer.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.weatheranalyzer.model.RecordQueryRequestModel;
import com.example.weatheranalyzer.repository.RecordSearchRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordServiceTest {
	
	@Autowired
	private RecordService recordService;
	
	@MockBean
	private RecordSearchRepository searchRepository;
	
	@Test
	public void queryRecordsTest() {
		recordService.queryRecords(buildRecord());
		verify(searchRepository, times(1)).queryData(any());
	}
	
	public RecordQueryRequestModel buildRecord() {
		RecordQueryRequestModel record=new RecordQueryRequestModel();
		Set<Long> sIds = new HashSet<>();
		sIds.add(1l);
		Set<String> metric = new HashSet<>();
		metric.add("temperature");
		record.setSensorIds(sIds);
		record.setMetrics(metric);
		record.setStatistic("min");
		return record;
	}
}
