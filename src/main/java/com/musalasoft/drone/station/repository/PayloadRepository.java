package com.musalasoft.drone.station.repository;

import com.musalasoft.drone.station.model.Payload;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository extends CrudRepository<Payload, String> {
}
