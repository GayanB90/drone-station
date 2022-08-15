package com.musalasoft.drone.station.service.impl;

import com.musalasoft.drone.station.model.DroneLoadResult;
import com.musalasoft.drone.station.model.LoadDroneRequest;
import com.musalasoft.drone.station.repository.DroneRepository;
import com.musalasoft.drone.station.service.DronePayloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DronePayloadServiceImpl implements DronePayloadService {

    @Autowired
    DroneRepository droneRepository;

    @Override
    public DroneLoadResult loadPayloadToDrone(LoadDroneRequest loadDroneRequest) {
        return null;
    }
}
