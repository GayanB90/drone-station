package com.musalasoft.drone.station.repository;

import com.musalasoft.drone.station.model.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, String> {
}
