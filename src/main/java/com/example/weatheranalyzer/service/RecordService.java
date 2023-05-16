package com.example.weatheranalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.weatheranalyzer.model.RecordQueryRequestModel;
import com.example.weatheranalyzer.model.RecordQueryResponseModel;
import com.example.weatheranalyzer.repository.RecordSearchRepository;

@Service
public class RecordService {

	private final RecordSearchRepository searchRepository;

	@Autowired
	RecordService(RecordSearchRepository searchRepository) {
		this.searchRepository = searchRepository;
	}

	public RecordQueryResponseModel queryRecords(RecordQueryRequestModel requestModel) {
		// validate request model from and to dates
		// validate request model for at least one metric
		// validate request model for existence of needed statistic
		return searchRepository.queryData(requestModel);
	}
}
