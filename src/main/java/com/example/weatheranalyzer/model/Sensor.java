package com.example.weatheranalyzer.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sensor")
public class Sensor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

	@Column
	private boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor")
	private Set<Record> records = new LinkedHashSet<>();

	@PrePersist
	public void prePersist() {
		this.creationDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Record> getRecords() {
		return this.records;
	}

	public void addRecord(Record record) {
		this.records.add(record);
		record.setSensor(this);
	}

	public void addRecord(Double temperature, Double humidity, Double windSpeed) {
		Record record = new Record(temperature, humidity, windSpeed);
		this.addRecord(record);
	}
}
