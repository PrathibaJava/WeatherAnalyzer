package com.example.weatheranalyzer.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.query.criteria.internal.path.SingularAttributePath;
import org.springframework.stereotype.Repository;
import com.example.weatheranalyzer.model.Record;
import com.example.weatheranalyzer.model.RecordQueryRequestModel;
import com.example.weatheranalyzer.model.RecordQueryResponseModel;
import com.example.weatheranalyzer.model.Sensor;

@Repository
public class RecordSearchRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public RecordQueryResponseModel queryData(RecordQueryRequestModel requestModel) {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		final Root<Record> record = cq.from(Record.class);
		final String[] metrics = requestModel.getMetrics().toArray(new String[0]);

		final Join<Record, Sensor> sensorJoin = record.join("sensor", JoinType.INNER);

		Expression[] selections = requestModel.getMetrics().stream().map(record::get)
				.map(path -> getAggregateExpression(cb, requestModel.getStatistic(), (SingularAttributePath) path))
				.toArray(Expression[]::new);

		cq.multiselect(selections);

		final List<Predicate> predicates = new ArrayList<>();

		// Add sensor id check.
		if (requestModel.getSensorIds().size() > 0) {
			predicates.add(sensorJoin.get("id").in(requestModel.getSensorIds()));
		}

		// Add Date range check
		if (requestModel.getFromDate() != null && requestModel.getToDate() != null) {
			predicates
					.add(cb.between(record.get("creationDate"), requestModel.getFromDate(), requestModel.getToDate()));
		}

		cq.where(predicates.toArray(new Predicate[0])).orderBy(cb.desc(record.get("creationDate")));

		Tuple result = entityManager.createQuery(cq).getSingleResult();

		RecordQueryResponseModel model = new RecordQueryResponseModel();
		for (int i = 0; i < metrics.length; i++) {
			setValueOnModel(metrics[i], result.get(i), model);
		}

		return model;
	}

	private void setValueOnModel(String metric, Object o, RecordQueryResponseModel model) {
		switch (metric) {
		case "temperature":
			model.setTemperature((Double) o);
			break;
		case "humidity":
			model.setHumidity((Double) o);
			break;
		case "windSpeed":
			model.setWindSpeed((Double) o);
			break;
		}
	}

	private Selection getAggregateExpression(CriteriaBuilder cb, String statistic, SingularAttributePath selection) {
		switch (statistic) {
		case "min":
			return cb.min(selection).alias(selection.getAttribute().getName());
		case "max":
			return cb.max(selection).alias(selection.getAttribute().getName());
		case "sum":
			return cb.sum(selection).alias(selection.getAttribute().getName());
		case "average":
			return cb.avg(selection).alias(selection.getAttribute().getName());
		default:
			throw new IllegalArgumentException(
					"Unable to match statistic: " + statistic + ". Expecting min, max, sum or average");
		}
	}
}
