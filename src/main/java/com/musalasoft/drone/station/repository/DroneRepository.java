package com.musalasoft.drone.station.repository;

import com.musalasoft.drone.station.model.Drone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends CrudRepository<Drone, String> {
    Drone findBySerialNo(String serialNo);
    Iterable<Drone> findAll();
}
