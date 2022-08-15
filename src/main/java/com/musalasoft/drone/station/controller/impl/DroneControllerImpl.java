package com.musalasoft.drone.station.controller.impl;

import com.musalasoft.drone.station.controller.DroneController;
import com.musalasoft.drone.station.model.*;
import com.musalasoft.drone.station.repository.DroneRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroneControllerImpl implements DroneController {
    private final DroneRepository droneRepository;

    public DroneControllerImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public DroneRegisterResponse registerDrone(Drone drone) {
        try {
            droneRepository.save(drone);
            return new DroneRegisterResponse(DroneRequestStatus.SUCCESS, drone.getSerialNo());
        } catch (Exception e) {
            return new DroneRegisterResponse(DroneRequestStatus.FAILURE, drone.getSerialNo());
        }
    }

    @Override
    public LoadDroneResponse loadPayloadToDrone(LoadDroneRequest loadDroneRequest) {
        return null;
    }
}
