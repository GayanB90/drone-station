package com.musalasoft.drone.station.service;

import com.musalasoft.drone.station.model.DroneLoadResult;
import com.musalasoft.drone.station.model.LoadDroneRequest;

public interface DronePayloadService {
    DroneLoadResult loadPayloadToDrone(LoadDroneRequest loadDroneRequest);
}
