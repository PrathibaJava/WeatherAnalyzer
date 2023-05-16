package com.example.weatheranalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.weatheranalyzer.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
