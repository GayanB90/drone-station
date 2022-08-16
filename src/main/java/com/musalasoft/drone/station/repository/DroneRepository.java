package com.musalasoft.drone.station.repository;

import com.musalasoft.drone.station.model.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends CrudRepository<Drone, String> {
    Drone findBySerialNo(String serialNo);
    Iterable<Drone> findAll();

    @Query("SELECT d FROM Drone d WHERE d.state = 0 AND d.batteryLevel > 25")
    Iterable<Drone> findAllAvailable();
}
