package com.musalasoft.drone.station.repository;

import com.musalasoft.drone.station.model.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone, String> {
    Iterable<Drone> findAll();
}
